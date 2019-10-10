package com.icourt.lawyercrawlparse.util;

import cn.hutool.core.util.ReUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * ocr版书 案例籍解析类
 */
public class BookWordUtil {

    private static final String caseSpilt="(^([.]?))[0-9]{1,3}.*案$";
//    private static final String caseSpilt="(^([.]?))[0-9]{1,3}[(\\u002E)、].*(?=案)";
//    private static final String path="/Users/chaihaowei/bookParse/bookParse2/已审核校对-人 民 法 院 案 例 选2012-03 zst-1.docx";
    private static final String path="/Users/chaihaowei/bookParse/bookParse2/已审核校对-人民法院案\u001D例选2012-04-01.docx";
    public static  void main(String[]args) throws Exception {

        String txt = OneParWordUtil.convertDocx2Txt(path);
        String[] split = StringUtils.split(txt, "\n");
        List<String>  lines = Arrays.asList(split);

        List<List<String>> cases = Lists.newArrayList();
        List<String> oneCase =Lists.newArrayList();
        for (String line : lines) {
            line= StringUtils.replace(line," ","");
            if(StringUtils.isBlank(line)){
                continue;
            }
            List<String> allGroup0 = ReUtil.findAllGroup0(caseSpilt, line);
            if(!CollectionUtils.isEmpty(allGroup0)){
                cases.add(oneCase);
                oneCase=Lists.newArrayList();
                oneCase.add(line);
            }else{
                if(!CollectionUtils.isEmpty(oneCase)){
                    oneCase.add(line);
                }
            }
        }
        for (List<String> item : cases) {
            for (String e : item) {
                OneParWordUtil.println(e);
            }
        }
    }
}
