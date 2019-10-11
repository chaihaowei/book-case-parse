package com.icourt.lawyercrawlparse.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.icourt.lawyercrawlparse.constants.CaseTypeEnum;
import com.icourt.lawyercrawlparse.entity.DsColumn;
import com.icourt.lawyercrawlparse.entity.JudgementExt;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.docx4j.Docx4J;
import org.docx4j.convert.out.HTMLSettings;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.util.*;
import java.util.regex.Pattern;

@Slf4j
public class OneParWordUtil {
    //严格的案号正则，若提不到，则取标签
    private static final Pattern CASE_NUMBER_STRICT_PATTERN = Pattern.compile("(案号[：:])?([\\[\\(〔（][O０１２３４５６７８９0-9]{3,4}[）\\)〕\\]].{1,15}[O０１２３４５６７８９0-9\\-、之零一二三四五六七八九十]{1,12}号([之|其|\\-]?[O０１２３４５６７８９0-9零一二三四五六七八九十]{0,3}))");


    public static String replaceStr = "\n" +
            "\n" +
            "  \n" +
            "  \n" +
            "  \n" +
            "  TO HIDE THESE MESSAGES, TURN OFF debug level logging for org.docx4j.convert.out.common.writer.AbstractMessageWriter ";

    public static String convertDocx2Txt(String path) throws Exception {
        File templateFile = Doc2Docx.getFile(path);
        //设置读取ooxml
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateFile);
        HTMLSettings htmlSettings = Docx4J.createHTMLSettings();
        htmlSettings.setWmlPackage(wordMLPackage);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //转换为html
        Docx4J.toHTML(htmlSettings, baos, Docx4J.FLAG_NONE);
        String str = baos.toString();

        //过滤table标签
        Whitelist whitelist = new Whitelist().addTags("table", "tbody", "td", "tr","img");
        String clean = Jsoup.clean(str, "", whitelist, new Document.OutputSettings().prettyPrint(false));
//        clean = clean.replace(" ", "");
        clean = clean.replace("&nbsp;", "\n");
        clean = clean.replace(replaceStr, "");
        //输出
        println(clean);
        return clean;
    }


    public static void println(String msg) {
        System.out.println(msg);
    }


    public static List<DsColumn> filePathToBean() throws Exception {
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                String name = pathname.getName();
                return StringUtils.endsWith(name, "docx")||StringUtils.endsWith(name, "doc")&& !pathname.isHidden()&& StringUtils.indexOf(name,"~$")<0;
            }
        };
        CaseTypeEnum[] values = CaseTypeEnum.values();
        List<CaseTypeEnum> caseTypeEnums = Arrays.asList(values);

        List<File> files = FileUtil.loopFiles("/Users/chaihaowei/bookParse/parse", fileFilter);
        for (File file : files) {

            println(file.getAbsolutePath() + "isHidder" + file.isHidden());
        }
        List<DsColumn> dsColumnList = Lists.newArrayList();
        for (File e : files) {
            log.info(FileUtil.getAbsolutePath(e));
            log.info(FileUtil.getName(e));
            String absolutePath = FileUtil.getAbsolutePath(e);
            Integer caseType = null;
            for (CaseTypeEnum caseTypeEnum : caseTypeEnums) {
                int i = absolutePath.indexOf(caseTypeEnum.getDesc());
                if (i >= 0) {
                    caseType = caseTypeEnum.getType();
                }
            }
            String name = FileUtil.getName(e);
            DsColumn column = wordToBean(e.getAbsolutePath(), StringUtils.substring(name, 0, StringUtils.indexOf(name, ".")), caseType);
            dsColumnList.add(column);
        }
        return dsColumnList;
    }

    public static DsColumn wordToBean(String path, String caseName, Integer caseType) throws Exception {
        Digester md5 = new Digester(DigestAlgorithm.MD5);

        DsColumn column = new DsColumn();
        String txt = convertDocx2Txt(path);

        HashMap<String, Object> map = Maps.newHashMap();
        map.put("caseName", caseName);
        map.put("source_type", 3);
        map.put("caseEnumType", 1);
        if (Objects.nonNull(caseType)) {
            map.put("caseType", caseType);
        }
        column.setPublishType("3");
        column.setText(txt);

        List<String> allGroup0 = ReUtil.findAllGroup0(CASE_NUMBER_STRICT_PATTERN, txt);
        log.info("案号：{}:",allGroup0);
        if(!CollectionUtils.isEmpty(allGroup0)&&allGroup0.size()<=2){
            map.put(JudgementExt.caseNum,allGroup0.get(allGroup0.size()-1));
        }

        String s = JSON.toJSONString(map);
        column.setExt(s);
        Date date = new Date();
        Long time = date.getTime();

        column.setUploadtimestamp(time.toString());
        column.setId(DsUtil.getDsId(map));
        return column;
    }



    public static void main(String[] args) throws Exception {
//        convertDocx2Txt();
        List<DsColumn> dsColumns = filePathToBean();
        dsColumns.forEach(e -> {
            println(JSON.toJSONString(e));
        });

//        DsColumn column = wordToBean();
//        println(JSON.toJSONString(column));
//        readTxt();
    }
}