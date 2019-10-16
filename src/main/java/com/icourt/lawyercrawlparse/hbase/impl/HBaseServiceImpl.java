package com.icourt.lawyercrawlparse.hbase.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.icourt.lawyercrawlparse.config.HBaseConfig;
import com.icourt.lawyercrawlparse.entity.DsColumn;
import com.icourt.lawyercrawlparse.hbase.HBaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.client.coprocessor.AggregationClient;
import org.apache.hadoop.hbase.client.coprocessor.LongColumnInterpreter;
import org.apache.hadoop.hbase.coprocessor.AggregateImplementation;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * hBase相关操作
 *
 * @author Caomr
 */
@Slf4j
@Service
public class HBaseServiceImpl implements HBaseService {

    private static final String JID = "jid";
    private final byte[] CF_CONTENT = "content".getBytes();
    private final byte[] CF_MARK = "mark".getBytes();
    private final byte[] CF_CALC = "calc".getBytes();
    private Pattern contentQualifierPattern = Pattern.compile("text|ext|timeLimitedBasis");
    private Pattern calcQualifierPattern = Pattern.compile("score|trycount");

    @Resource
    private HBaseConfig hBaseConfig;

    @Override
    public void initMetaData(String tableName) throws Exception {
        Connection conn = hBaseConfig.getConn();
        Admin admin = null;
        try {
            admin = conn.getAdmin();
            String[] colFamilys = {"mark", "content", "calc"};
            createTableColumnFamily(admin, tableName, colFamilys);
        } finally {
            hBaseConfig.closeAdmin(admin);
        }
    }

    /**
     * 获得连接
     *
     * @return
     * @throws Exception
     */
    private Connection getConnection() throws Exception {
        Connection conn = hBaseConfig.getConn();
        if (Objects.isNull(conn)) {
            throw new Exception("HBASE CONNECTION IS NULL");
        }
        return conn;
    }


    /**
     * 预处理dsColumn
     *
     * @param dsColumn hBase对象
     * @return
     */
    @Override
    public boolean preHandle(DsColumn dsColumn) {
        JSONObject jsonObject = Optional.ofNullable(dsColumn.getExt()).map(s -> JSON.parseObject(s)).orElse(null);
        String jid = jsonObject.getString(JID);
        //如果为空，表示为新增,塞入jid
        if (StringUtils.isEmpty(jid)) {
            jsonObject.put(JID, dsColumn.getId());
            dsColumn.setExt(JSON.toJSONString(jsonObject));
        }
        return true;
    }

    @Override
    public DsColumn findOne(String tableName, String rowKey) throws Exception {
        //判断连接是否正常
        Connection conn = getConnection();
        Table table = conn.getTable(TableName.valueOf(tableName));
        if (table == null) {
            return null;
        }
        Get get = new Get(rowKey.getBytes());
        try {
            Result result = table.get(get);
            DsColumn dsColumn = mapResultToDsColumn(result);
            if (dsColumn != null) {
                dsColumn.setId(rowKey);
                return dsColumn;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }


    private DsColumn mapResultToDsColumn(Result result) throws Exception {
        if (result.isEmpty()) {
            return null;
        }
        HashMap<String, Object> resultMap = new HashMap<>(10);
        for (Cell cell : result.rawCells()) {
            resultMap.put(Bytes.toString(CellUtil.cloneQualifier(cell)), Bytes.toString(CellUtil.cloneValue(cell)));
        }
        DsColumn dsColumn = new DsColumn();
        BeanUtils.populate(dsColumn, resultMap);

        return dsColumn;
    }

    /**
     * 保存
     *
     * @param tableName
     * @param rowKey
     * @param column
     * @return
     */
    @Override
    public boolean saveOne(String tableName, String rowKey, DsColumn column) throws Exception {
        //判断连接是否正常
        Connection conn = getConnection();
        Table table = conn.getTable(TableName.valueOf(tableName));
        try {
            if (table == null) {
                throw new Exception("TABLE_NAME NOT EXISTS");
            }
            Put p = put(rowKey, column);
            table.put(p);
        } catch (Exception e) {
            log.error("HBase保存出错,dsId={}", rowKey, e);
            throw new Exception("HBase保存出错,dsId=" + rowKey, e);
        } finally {
            hBaseConfig.closeTable(table);
        }
        return true;
    }


    /**
     * 保存
     *
     * @param tableName
     * @param columns
     * @return
     * @throws Exception
     */
    @Override
    public boolean batch(String tableName, List<DsColumn> columns) throws Exception {
        if (CollectionUtils.isEmpty(columns)) {
            return false;
        }
        //判断连接是否正常
        Connection conn = getConnection();
        Table table = conn.getTable(TableName.valueOf(tableName));
        try {
            if (table == null) {
                throw new Exception("TABLE_NAME NOT EXISTS");
            }
            List<Row> puts = puts(columns);
            table.batch(puts, new Object[puts.size()]);
        } catch (Exception e) {
            log.error("HBase批量保存出错,dsId={}", columns.stream().collect(Collectors.toList()).toString());
            throw new Exception("HBase批量保存出错", e);
        } finally {
            hBaseConfig.closeTable(table);
        }
        return true;
    }

    /**
     * 转换
     *
     * @param columns
     * @return
     */
    private List<Row> puts(List<DsColumn> columns) {
        return columns.stream().map(c -> {
            try {
                return put(c.getId(), c);
            } catch (IllegalAccessException e) {
                log.error("dsColumn 转化出错,dsId={}", c.getId(), e);
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * 由column对象产生put对象
     *
     * @param rowKey
     * @param column
     * @return
     * @throws IllegalAccessException
     */
    private Put put(String rowKey, DsColumn column) throws IllegalAccessException {
        Put p = new Put(Bytes.toBytes(rowKey));
        for (Field f : column.getClass().getDeclaredFields()) {
            byte[] CF = CF_MARK;
            f.setAccessible(true);
            if (contentQualifierPattern.matcher(f.getName()).matches()) {
                CF = CF_CONTENT;
            }
            if (calcQualifierPattern.matcher(f.getName()).matches()) {
                CF = CF_CALC;
            }
            byte[] valueBytes = null;
            Object target = f.get(column);
            if (target == null) {
                continue;
            }
            if (f.getType().equals(String.class)) {
                valueBytes = Bytes.toBytes((String) target);
            } else if (f.getType().equals(Integer.class)) {
                valueBytes = Bytes.toBytes((Integer) target);
            } else if (f.getType().equals(Long.class)) {
                valueBytes = Bytes.toBytes(String.valueOf(target));
            }
            if (valueBytes != null) {
                p.addColumn(CF, Bytes.toBytes(f.getName()), valueBytes);
            }
        }
        return p;
    }


    /**
     * 判断是否存在
     *
     * @param tableName
     * @param rowKey
     * @return
     * @throws Exception
     */
    @Override
    public boolean exists(String tableName, String rowKey) throws Exception {
        //判断连接是否正常
        Connection conn = getConnection();
        Table table = conn.getTable(TableName.valueOf(tableName));
        try {
            if (table == null) {
                throw new Exception("TABLE_NAME NOT EXISTS");
            }
            Get get = new Get(rowKey.getBytes());
            return table.exists(get);
        } catch (Exception e) {
            log.error("HBASE判断表出错", e);
            return false;
        } finally {
            hBaseConfig.closeTable(table);
        }

    }

    /**
     * 创建表和列族
     *
     * @param admin
     * @param tableName
     * @param colFamilys
     * @throws Exception
     */
    private void createTableColumnFamily(Admin admin, String tableName, String[] colFamilys)
            throws Exception {
        TableName table = TableName.valueOf(tableName);
        if (admin.tableExists(table)) {
            log.warn("tableName " + tableName + " has exists");
            throw new Exception("table exists");
        }
        HTableDescriptor htbInputDes = new HTableDescriptor(table);

        for (String item : colFamilys) {
            htbInputDes.addFamily(new HColumnDescriptor(item).setMaxVersions(5));
        }
        htbInputDes.addCoprocessor(AggregateImplementation.class.getName());
        admin.createTable(htbInputDes);
    }

    @Override
    public long count(String tableName) throws Throwable {
        log.info("start to count ds");
        AggregationClient aggre = new AggregationClient(hBaseConfig.getCfg());
        Scan scan = new Scan();
        scan.addFamily(Bytes.toBytes(DsColumn.FamilyEnum.mark.name()));

        // 以下只统计未被删除的
        FilterList filters = new FilterList();
        filters.addFilter(new SingleColumnValueFilter(DsColumn.FamilyEnum.mark.name().getBytes(), DsColumn.FamilyQMarkEnum.status.name().getBytes(), CompareFilter.CompareOp.NOT_EQUAL, "1".getBytes()));
        scan.setFilter(filters);

        long count = aggre.rowCount(TableName.valueOf(tableName), new LongColumnInterpreter(), scan);
        log.info("end count ds, total rows:" + count);
        return count;
    }

//    private void saveRow(DsColumn column, Table table, String rowKey, DsColumn oldColumn) throws IOException {
//        Put p = new Put(Bytes.toBytes(rowKey));
//        p.addColumn(Bytes.toBytes(DsColumn.FamilyEnum.content.name()), Bytes.toBytes(DsColumn.FamilyQContentEnum.text.name()), Bytes.toBytes(column.getText()));
//        p.addColumn(Bytes.toBytes(DsColumn.FamilyEnum.content.name()), Bytes.toBytes(DsColumn.FamilyQContentEnum.ext.name()), Bytes.toBytes(column.getExt()));
//        p.addColumn(Bytes.toBytes(DsColumn.FamilyEnum.mark.name()), Bytes.toBytes(DsColumn.FamilyQMarkEnum.source.name()), Bytes.toBytes(column.getSource()));
//        p.addColumn(Bytes.toBytes(DsColumn.FamilyEnum.mark.name()), Bytes.toBytes(DsColumn.FamilyQMarkEnum.modifier.name()), Bytes.toBytes(column.getModifier()));
//        if (!StringUtils.isEmpty(column.getModifytype())) {
//            p.addColumn(Bytes.toBytes(DsColumn.FamilyEnum.mark.name()), Bytes.toBytes(DsColumn.FamilyQMarkEnum.modifytype.name()), Bytes.toBytes(column.getModifytype()));
//        }
////        if (!StringUtils.isEmpty(column.getTimeLimitedBasis())) {
////            p.addColumn(Bytes.toBytes(DsColumn.FamilyEnum.content.name()), Bytes.toBytes(DsColumn.FamilyQContentEnum.timeLimitedBasis.name()), Bytes.toBytes(column.getTimeLimitedBasis()));
////        }
//        if (StringUtils.isEmpty(column.getSyncstate())) {
//            column.setSyncstate("0");
//        }
//        p.addColumn(Bytes.toBytes(DsColumn.FamilyEnum.mark.name()), Bytes.toBytes(DsColumn.FamilyQMarkEnum.remark.name()), Bytes.toBytes(column.getRemark()));
//
//        p.addColumn(Bytes.toBytes(DsColumn.FamilyEnum.mark.name()), Bytes.toBytes(DsColumn.FamilyQMarkEnum.syncstate.name()), Bytes.toBytes(column.getSyncstate()));
//        //p.addColumn(Bytes.toBytes(DsColumn.FamilyEnum.mark.name()), Bytes.toBytes(DsColumn.FamilyQMarkEnum.uploadtimestamp.name()), Bytes.toBytes(column.getUploadtimestamp()));
//        String oldUploadTimeStamp = Optional.ofNullable(oldColumn).map(DsColumn::getUploadtimestamp).orElse(null);
//        if(StringUtils.isEmpty(oldUploadTimeStamp)){
//            p.addColumn(Bytes.toBytes(DsColumn.FamilyEnum.mark.name()), Bytes.toBytes(DsColumn.FamilyQMarkEnum.uploadtimestamp.name()), Bytes.toBytes(column.getUploadtimestamp()));
//        }
//        p.addColumn(Bytes.toBytes(DsColumn.FamilyEnum.calc.name()), Bytes.toBytes(DsColumn.FamilyQCalcEnum.score.name()), Bytes.toBytes(column.getScore()));
//        if (!StringUtils.isEmpty(column.getTrycount())) {
//            p.addColumn(Bytes.toBytes(DsColumn.FamilyEnum.calc.name()), Bytes.toBytes(DsColumn.FamilyQCalcEnum.trycount.name()), Bytes.toBytes(column.getTrycount()));
//        }
////        if (!StringUtils.isEmpty(column.getAttachs())) {
////            p.addColumn(Bytes.toBytes(DsColumn.FamilyEnum.mark.name()), Bytes.toBytes(DsColumn.FamilyQMarkEnum.attachs.name()), Bytes.toBytes(column.getAttachs()));
////        }
//        if (!StringUtils.isEmpty(column.getStatus())) {
//            p.addColumn(Bytes.toBytes(DsColumn.FamilyEnum.mark.name()), Bytes.toBytes(DsColumn.FamilyQMarkEnum.status.name()), Bytes.toBytes(column.getStatus()));
//        }
//        if (!StringUtils.isEmpty(column.getCrawltimestamp())) {
//            p.addColumn(Bytes.toBytes(DsColumn.FamilyEnum.mark.name()), Bytes.toBytes(DsColumn.FamilyQMarkEnum.crawltimestamp.name()), Bytes.toBytes(column.getCrawltimestamp()));
//        }
//        if (!StringUtils.isEmpty(column.getPublishType())) {
//            p.addColumn(Bytes.toBytes(DsColumn.FamilyEnum.mark.name()), Bytes.toBytes(DsColumn.FamilyQMarkEnum.publishType.name()), Bytes.toBytes(column.getPublishType()));
//        }
//        table.put(p);
//    }


}
