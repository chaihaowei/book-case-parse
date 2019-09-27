package com.icourt.lawyercrawlparse.util;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.icourt.lawyercrawlparse.entity.DsColumn;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class WordUtil {

    public static String path = "/Users/chaihaowei/bookParse/知识产权/2017/3.安徽合肥32-杨芳-安徽鑫龙安全防护科技有限公司与长兴耿宇金属制品有限公司，宣城市房屋建筑安装有限责任公司侵害实用新型专利权纠纷案.docx";


    private static String convertDocx2Txt() throws Exception {
        File templateFile = new File(path);
        String name = "我的增量补丁整理软件.html";
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateFile);
        HTMLSettings htmlSettings = Docx4J.createHTMLSettings();

        htmlSettings.setWmlPackage(wordMLPackage);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Docx4J.toHTML(htmlSettings, baos, Docx4J.FLAG_NONE);
        String str = baos.toString();

        Whitelist whitelist = new Whitelist().addTags("table", "tbody", "td", "tr");
        String clean = Jsoup.clean(str, "", whitelist, new Document.OutputSettings().prettyPrint(false));
//        clean = clean.replace(" ", "");
        clean = clean.replace("&nbsp;", "\n");

        println(clean);
        return clean;
    }

    public static void println(String msg) {
        System.out.println(msg);
    }

    private static void readTxt() throws Exception {
        File templateFile = new File(path);
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateFile);
        String contentType = wordMLPackage.getContentType();
        log.info("contentType:{}", contentType);

        MainDocumentPart mainDocumentPart = wordMLPackage.getMainDocumentPart();


        String xpath = "//w:tbl";

        List<Object> list = mainDocumentPart.getJAXBNodesViaXPath(xpath, false);
        list.forEach((item) -> {

            JAXBElement item1 = (JAXBElement) item;
            Object value = item1.getValue();

//            (Tbl) item;
//            org.docx4j.wml.Tbl
            String name = value.getClass().getName();

        });
        if (list.size() > 0) {
            Tbl tbl = (Tbl) XmlUtils.unwrap(list.get(list.size() - 1));
            mainDocumentPart.getContent().remove(tbl.getParent());
            wordMLPackage.save(new java.io.File("/Users/chaihaowei/bookParse/知识产权/2017/3.安徽合肥32-杨芳-安徽鑫龙安全防护科技有限公司与长兴耿宇金属制品有限公司，宣城市房屋建筑安装有限责任公司侵害实用新型专利权纠纷案——2.docx"));
            System.out.println(list.size());
        }

    }


    public static DsColumn wordToBean() throws Exception {
        Digester md5 = new Digester(DigestAlgorithm.MD5);

        DsColumn column = new DsColumn();
        String txt = convertDocx2Txt();
        HashMap<Object, Object> map = Maps.newHashMap();
        map.put("caseName", "安徽合肥32-杨芳-安徽鑫龙安全防护科技有限公司与长兴耿宇金属制品有限公司，宣城市房屋建筑安装有限责任公司侵害实用新型专利权纠纷案");
        map.put("source_type", 3);
        map.put("caseEnumType", 1);
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

        DsColumn column = wordToBean();
        println(JSON.toJSONString(column));
//        readTxt();
    }
}