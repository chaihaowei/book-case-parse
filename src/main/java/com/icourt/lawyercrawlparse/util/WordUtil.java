package com.icourt.lawyercrawlparse.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.icourt.lawyercrawlparse.constants.CaseTypeEnum;
import com.icourt.lawyercrawlparse.entity.DsColumn;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.docx4j.Docx4J;
import org.docx4j.XmlUtils;
import org.docx4j.convert.out.HTMLSettings;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Tbl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import javax.xml.bind.JAXBElement;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.util.*;

@Slf4j
public class WordUtil {
    public static String replaceStr="\n" +
            "\n" +
            "  \n" +
            "  \n" +
            "  \n" +
            "  TO HIDE THESE MESSAGES, TURN OFF debug level logging for org.docx4j.convert.out.common.writer.AbstractMessageWriter ";

    private static String convertDocx2Txt(String path) throws Exception {
        File templateFile = new File(path);
        //设置读取ooxml
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateFile);
        HTMLSettings htmlSettings = Docx4J.createHTMLSettings();
        htmlSettings.setWmlPackage(wordMLPackage);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //转换为html
        Docx4J.toHTML(htmlSettings, baos, Docx4J.FLAG_NONE);
        String str = baos.toString();

        //过滤table标签
        Whitelist whitelist = new Whitelist().addTags("table", "tbody", "td", "tr");
        String clean = Jsoup.clean(str, "", whitelist, new Document.OutputSettings().prettyPrint(false));
//        clean = clean.replace(" ", "");
        clean = clean.replace("&nbsp;", "\n");
        clean =clean.replace(replaceStr,"");
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
                return StringUtils.endsWith(name, "docx");
            }
        };
        CaseTypeEnum[] values = CaseTypeEnum.values();
        List<CaseTypeEnum> caseTypeEnums = Arrays.asList(values);

        List<File> files = FileUtil.loopFiles("/Users/chaihaowei/bookParse/parse",fileFilter);

        List<DsColumn>  dsColumnList =Lists.newArrayList();
        for (File e : files) {
            log.info(FileUtil.getAbsolutePath(e));
            log.info(FileUtil.getName(e));
            String absolutePath = FileUtil.getAbsolutePath(e);
            Integer caseType =null;
            for (CaseTypeEnum caseTypeEnum : caseTypeEnums) {
                int i = absolutePath.indexOf(caseTypeEnum.getDesc());
                if (i >= 0) {
                    caseType = caseTypeEnum.getType();
                }
            }
            String name = FileUtil.getName(e);
            DsColumn column = wordToBean(e.getAbsolutePath(), StringUtils.substring(name,0, StringUtils.indexOf(name,".")), caseType);
            dsColumnList.add(column);
        }
        return dsColumnList;
    }
    public static DsColumn wordToBean(String path,String caseName,Integer caseType) throws Exception {
        Digester md5 = new Digester(DigestAlgorithm.MD5);

        DsColumn column = new DsColumn();
        String txt = convertDocx2Txt(path);
        HashMap<Object, Object> map = Maps.newHashMap();
        map.put("caseName", caseName);
        map.put("source_type", 3);
        map.put("caseEnumType", 1);
        if(Objects.nonNull(caseType)) {
            map.put("caseType", caseType);
        }
        column.setPublishType("3");
        column.setText(txt);
        String s = JSON.toJSONString(map);
        column.setExt(s);
        Date date = new Date();
        Long time = date.getTime();

        column.setUploadtimestamp(time.toString());
        column.setId(md5.digestHex(s));

        return column;
    }

    public static void main(String[] args) throws Exception {
//        convertDocx2Txt();
        List<DsColumn> dsColumns = filePathToBean();
        dsColumns.forEach(e->{
            println(JSON.toJSONString(e));
        });

//        DsColumn column = wordToBean();
//        println(JSON.toJSONString(column));
//        readTxt();
    }
}