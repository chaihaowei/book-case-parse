package com.icourt.lawyercrawlparse.util;

import cn.hutool.core.util.ReUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.icourt.lawyercrawlparse.constants.CaseTypeEnum;
import com.icourt.lawyercrawlparse.entity.DsColumn;
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
import java.util.*;

/**
 * ocr版书 案例籍解析类
 */
public class BookWordUtil {

    private static final String caseSpilt = "(^([.]?))[0-9]{1,3}.*案$";
    //    private static final String caseSpilt="(^([.]?))[0-9]{1,3}[(\\u002E)、].*(?=案)";
//    private static final String path="/Users/chaihaowei/bookParse/bookParse2/已审核校对-人 民 法 院 案 例 选2012-03 zst-1.docx";
    private static final String path = "/Users/wangcai/Documents/wenshu/bookParse/bookParse2/已审核校对-人民法院案\u001D例选2012-04-01.docx";
//    private static final String path = "/Users/wangcai/Documents/wenshu/bookParse/bookParse2/已审核校对-人 民 法 院 案 例 选2012-03 zst-1.docx";

    public static String convertDocx2Txt(String path) throws Exception {
        File templateFile = Doc2Docx.getFile(path);
        //设置读取ooxml
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateFile);
        HTMLSettings htmlSettings = Docx4J.createHTMLSettings();
        htmlSettings.setWmlPackage(wordMLPackage);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //转换为html
        Docx4J.toHTML(htmlSettings, baos, Docx4J.FLAG_NONE);
        String str = Doc2Docx.parseWord2Html(path);

        Document html = Jsoup.parse(str);
        html.select("p").append("\n");
        str = html.html();
        //过滤table标签
        Whitelist whitelist = new Whitelist().addTags("table", "tbody", "td", "tr", "img");
        String clean = Jsoup.clean(str, "", whitelist, new Document.OutputSettings().prettyPrint(false));
        clean = clean.replace(" ", "");
        clean = clean.replace("&nbsp;", "\n");
//        clean = clean.replace(OneParWordUtil.replaceStr, "");
        //输出
        OneParWordUtil.println(clean);
        return clean;
    }

    private static int getCaseType(String line, int caseType) {
        if (line.indexOf("民事诉讼指导性案例") >= 0) {
            return 2;
        }
        if (line.indexOf("特别策划•强制执行专题") >= 0) {
            return 5;
        }
        for (CaseTypeEnum caseTypeEnum : CaseTypeEnum.values()) {
            int i = line.indexOf(caseTypeEnum.getDesc());
            if (i >= 0) {
                if (line.length() < 5 || line.contains("案例精选")) {
                    caseType = caseTypeEnum.getType();
                    return caseType;
                }
            }
        }
        return caseType;
    }

    public static List<DsColumn> getDsColumn() throws Exception {

        String txt = convertDocx2Txt(path);
        String[] split = StringUtils.split(txt, "\n");
        List<String> lines = Arrays.asList(split);

        List<List<String>> cases = Lists.newArrayList();
        List<String> oneCase = Lists.newArrayList();
        int caseType = 0;
        Map<String, Integer> caseMap = new HashMap<>();
        Map<String, String> ponitMap = new HashMap<>();
        Boolean flag = false;
        String keyPoint = "";
        for (String line : lines) {
            line = StringUtils.replace(line, " ", "");
            if (StringUtils.isBlank(line)) {
                continue;
            }
            caseType = getCaseType(line, caseType);
            if (flag) {
                if (line.indexOf("【") >= 0) {
                    flag = false;
                    keyPoint = keyPoint.replace("【要点提示】", "").replace("\u2028", "");
//                    keyPoint = keyPoint.substring(1, keyPoint.length() - 1);
                    ponitMap.put(oneCase.get(0), keyPoint.trim());
                    keyPoint = "";
                } else {
                    keyPoint += line + "\n";
                }
            }
            if (line.indexOf("【要点提示】") >= 0) {
                flag = true;
                keyPoint += line + "\n";
            }

            List<String> allGroup0 = ReUtil.findAllGroup0(caseSpilt, line);
            if (!CollectionUtils.isEmpty(allGroup0)) {
                cases.add(oneCase);
                oneCase = Lists.newArrayList();
                oneCase.add(line);
                caseMap.put(line, caseType);
            } else {
                if (!CollectionUtils.isEmpty(oneCase)) {
                    oneCase.add(line);
                }
            }
        }
        cases.add(oneCase);
        List<DsColumn> columns = new ArrayList<>();
        String source = lines.get(0) + " " + lines.get(1);
        for (List<String> item : cases) {
            if (CollectionUtils.isEmpty(item)) {
                continue;
            }
            DsColumn dsColumn = new DsColumn();
            dsColumn.setSource("al_crawl_courtal");
            Map<String, Object> map = new HashMap<>();
            map.put("caseName", item.get(0));
            map.put("source", source);
            map.put("source_type", 3);
            map.put("caseEnumType", 1);
            if (caseMap.get(item.get(0)) > 0) {
                map.put("caseType", caseMap.get(item.get(0)));
            }
            map.put("keyPoint", ponitMap.get(item.get(0)));
            dsColumn.setExt(JSON.toJSONString(map));
            dsColumn.setText(item.toString());
            Date date = new Date();
            Long time = date.getTime();
            dsColumn.setPublishType("7");

            dsColumn.setUploadtimestamp(time.toString());
            dsColumn.setId(DsUtil.getDsId(map));
            String text = "";
            for (String e : item) {
                OneParWordUtil.println(e);
                text += e + "\n";
            }
            dsColumn.setText(text);
            columns.add(dsColumn);
        }

        return columns;
    }

    public static void main(String[] args) throws Exception {
        getDsColumn();
    }
}
