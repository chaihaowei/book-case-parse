package com.icourt.lawyercrawlparse.hbase;


import com.icourt.lawyercrawlparse.entity.DsColumn;

import java.util.List;

/**
 * @author Caomr
 */
public interface HBaseService {

    String JUDGEMENT_DS = "judgement_ds";

    /**
     * 初始化表及列族元数据
     * @throws Exception
     */
    void initMetaData(String tableName) throws Exception;

    /**
     * 前置处理
     *
     * @param dsColumn
     * @return
     */
    boolean preHandle(DsColumn dsColumn);

    /**
     * 查询操作
     *
     * @param tableName
     * @param rowKey
     * @return
     * @throws Exception
     */
    DsColumn findOne(String tableName, String rowKey) throws Exception;

    /**
     * 单个保存
     *
     * @param tableName
     * @param rowKey
     * @param column
     * @return
     * @throws Exception
     */
    boolean saveOne(String tableName, String rowKey, DsColumn column) throws Exception;

    /**
     * 批量写操作
     *
     * @param tableName
     * @param columns
     * @return
     * @throws Exception
     */
    boolean batch(String tableName, List<DsColumn> columns) throws Exception;


    /**
     * 判断是否存在
     *
     * @param tableName
     * @param rowKey
     * @return
     * @throws Exception
     */
    boolean exists(String tableName, String rowKey) throws Exception;

    public long count(String tableName) throws Throwable;

}
