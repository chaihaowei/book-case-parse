package com.icourt.lawyercrawlparse.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.format.DatePrinter;
import cn.hutool.core.util.ReUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.icourt.lawyercrawlparse.dao.FaxindataMapper;
import com.icourt.lawyercrawlparse.entity.DsColumn;
import com.icourt.lawyercrawlparse.entity.Faxindata;
import com.icourt.lawyercrawlparse.entity.JudgementExt;
import com.icourt.lawyercrawlparse.service.IFaxindataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.net.DatagramSocket;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chaihaowei
 */
@Slf4j
@Service
public class FaxindataServiceImpl implements IFaxindataService, InitializingBean {

    private List<String> caseLists= Lists.newArrayList();

    @Override
    public void afterPropertiesSet() throws Exception {
        caseLists.add("刑事");
        caseLists.add("民事");
        caseLists.add("商事");
        caseLists.add("知识产权");
        caseLists.add("行政");
        caseLists.add("行政及国家赔偿");
    }

    //下一条开始是这个则这条为标题
    private static final String contentSplit = "【";

    private static final String annon = "[一二三四五六七八九]{1,10}、";

    private static final String filterd = "(公报案例)|(指导性案例)|(审判指导与参考)|(典型案例发布)|(域外撷英){1}";

    //是否换行符
    private static final String isPargN = "(\r\n|\r|\n|\n\r)";


    //公报案例、指导性案例、审判指导与参考、典型案例发布、域外撷英的内容均不用提取解析。
    @Autowired
    private FaxindataMapper faxindataMapper;









    public BiMap<String, List<Faxindata>> getCaseMap(String bookId) {
        List<Faxindata> faxindata = getFaxindata(bookId);

        //案件和具体段落对应表
        BiMap<String, List<Faxindata>> casePars = HashBiMap.create();

        //案由和案件对应表
        BiMap<String, List<String>> caseMap = HashBiMap.create();

        dualCaseParhAndNameCase(faxindata, casePars, caseMap);

        log.info("开始过滤掉不需要的案例 过滤前：{}", casePars.size());

        caseMap.forEach((k, v) -> {
            List<String> allGroup0 = ReUtil.findAllGroup0(filterd, k);
            if (!CollectionUtils.isEmpty(allGroup0)) {
                v.forEach(casePars::remove);
            } else {
                for (String item : v) {
                    List<Faxindata> faxindata1 = casePars.get(item);

                    if (!CollectionUtils.isEmpty(faxindata1)) {
                        for (Faxindata e : faxindata1) {
                            e.setCaseCode(k);
                        }
                    }
                }
            }
        });
        log.info("开始过滤掉不需要的案例 过滤后：{}", casePars.size());

        casePars.forEach((k, v) -> {
            v.forEach(item -> {
                String text = Jsoup.clean(item.getArticleContent(), "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
                String replace = text;
                if (StringUtils.startsWith(text, "&nbsp;&nbsp;&nbsp;&nbsp;")) {
                    replace = StringUtils.replaceFirst(text, "&nbsp;&nbsp;&nbsp;&nbsp;", "");
                }
                replace = replace.replace("&nbsp;&nbsp;&nbsp;&nbsp;", "\n");
                item.setArticleContent(replace);

            });
        });

        return casePars;
    }

    /**
     * 处理案件段落 以及案由和段落
     * @param faxindata 数据源
     * @param caseParh 案件段落
     * @param caseMap  案件名称 和段落
     */
    private void dualCaseParhAndNameCase(List<Faxindata> faxindata, BiMap<String, List<Faxindata>> caseParh, BiMap<String, List<String>> caseMap) {
        //案件名称
        String caseName =null;
        //案由名称
        String caseCodeName = null;

        //1、确定案件 2、确认案由 3、正文
        for (int i = 0, j = 1; i < faxindata.size(); i++) {

            Faxindata cuurentnode = faxindata.get(i);

            //最后一行直接加进来
            if (j >= faxindata.size()) {

                if (StringUtils.isEmpty(caseName)) {
                    continue;
                }
                List<Faxindata> list = MapUtils.getObject(caseParh, caseName, Lists.newArrayList());
                list.add(cuurentnode);
                caseParh.put(caseName, list);
                continue;
            }

            //查找案件名称
            Faxindata nextNode = faxindata.get(j);
            String nextArticleTitle = nextNode.getArticleTitle();
            //根据下一行 来判断当前行是不是标题 当前行的前一行是不是案由
            if (StringUtils.startsWith(nextArticleTitle, contentSplit) && StringUtils.isBlank(caseName)) {
                caseName = cuurentnode.getArticleTitle();
                Faxindata preNode = faxindata.get(i - 1);

                //判断是不是注解
                List<String> allGroup0 = ReUtil.findAllGroup0(annon, preNode.getArticleTitle());
                if(!CollectionUtils.isEmpty(allGroup0)){
                    int prePre =i-2;
                    if(prePre>=0){
                        Faxindata prePreNode = faxindata.get(prePre);
                        String prePreNodeArticleTitle = prePreNode.getArticleTitle();
                        List<String> allGroup1 = ReUtil.findAllGroup0(annon, prePreNodeArticleTitle);
                        //如果为空 就是案由
                        if(CollectionUtils.isEmpty(allGroup1)){
                            caseCodeName =preNode.getArticleTitle();
                        }
                    }
                }else{
                    //判断不是标注
                    List<String> allGroup01 = ReUtil.findAllGroup0(contentSplit, preNode.getArticleTitle());
                    if(CollectionUtils.isEmpty(allGroup01)){
                        caseCodeName =preNode.getArticleTitle();
                    }
                }
                List<String> cases = MapUtils.getObject(caseMap, caseCodeName, Lists.newArrayList());
                cases.add(caseName);
                caseMap.put(caseCodeName, cases);
            } else {
                if (StringUtils.isNotBlank(caseName)) {
                    Faxindata source = faxindata.get(i);
                    List<Faxindata> list = MapUtils.getObject(caseParh, caseName, Lists.newArrayList());
                    list.add(source);
                    caseParh.put(caseName, list);
                }
            }


            List<String> allGroup0 = ReUtil.findAllGroup0(annon, nextArticleTitle);
            if (!StringUtils.startsWith(nextArticleTitle, contentSplit) && StringUtils.isNotBlank(caseName) && CollectionUtils.isEmpty(allGroup0)) {
                caseName = null;
            }
            j = j + 1;
        }
    }

    /**
     * 获取去重之后的book
     * @param bookId
     * @return
     */
    private List<Faxindata> getFaxindata(String bookId) {
        LambdaQueryWrapper<Faxindata> faxindataLambdaQueryWrapper = new LambdaQueryWrapper<>();
        faxindataLambdaQueryWrapper.eq(Faxindata::getBookId, bookId);
        List<Faxindata> faxindata = faxindataMapper.selectList(faxindataLambdaQueryWrapper);
        ArrayList<String> contins = Lists.newArrayList();

        Iterator<Faxindata> iterator = faxindata.iterator();
        while (iterator.hasNext()) {
            Faxindata next = iterator.next();
            if (contins.contains(next.getArticleId())) {
                iterator.remove();
            } else {
                contins.add(next.getArticleId());
            }
        }
        return faxindata;
    }


    @Override
    public List<DsColumn> transToDs(String bookId) {
        BiMap<String, List<Faxindata>> caseMap = getCaseMap(bookId);
        Set<String> strings = caseMap.keySet();


        for (String e : strings) {
            System.out.println("--------------start--------------------------------------start--------------------");
            System.out.println(e);
            List<Faxindata> list = MapUtils.getObject(caseMap, e);
            if (!CollectionUtils.isEmpty(list)) {
                for (Faxindata item : list) {
                    String articleTitle = item.getArticleTitle();
                    printlnIfnotBlank(articleTitle);
                    String articleContent = item.getArticleContent();
                    printlnIfnotBlank(articleContent);
                }
            }
            System.out.println("--------------end--------------------------------------end--------------------");
        }


        List<DsColumn> resultList =Lists.newArrayList();
        Digester md5 = new Digester(DigestAlgorithm.MD5);

        caseMap.forEach((k,v)->{
            String dsId ="";
            dsId =dsId+v;
            DsColumn column = new DsColumn();
            HashMap<Object, Object> map = Maps.newHashMap();
            map.put("caseName",k);
            map.put("source_type",3);

            map.put("caseEnumType",1);
            map.put(JudgementExt.textCause,v.stream().findFirst().orElse(new Faxindata()).getCaseCode());

            column.setExt(JSON.toJSONString(map));
            column.setRemark("全国优秀案例书籍");
            column.setScore("50");

            String collect = v.stream().map(e -> e.getArticleTitle() + "\n" + e.getArticleContent()).collect(Collectors.joining("\n"));
            dsId =dsId+"#"+collect;
            column.setText(collect);

            String s = md5.digestHex(dsId);
            column.setId(s);

            column.setSource("1");
            column.setPublishType("3");

            Date date = new Date();
            Long time = date.getTime();

            column.setUploadtimestamp(time.toString());
            System.out.println(JSON.toJSONString(column));
            resultList.add(column);
        });


        log.info("SUCCESS");
        return resultList;
    }



    private void printlnIfnotBlank(String str) {
        if (StringUtils.isNotBlank(str) || !ReUtil.isMatch(isPargN, StringUtils.trim(str))) {
            System.out.println(str);
        }
    }
}
