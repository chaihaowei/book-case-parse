package com.icourt.lawyercrawlparse.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ReUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.icourt.lawyercrawlparse.util.OneParWordUtil.println;

public class ListCaseName {

    private static final String caseSpilt="(^([.]?))[0-9]{1,3}.*案$";

    public static List<String> getOneWordCases(){
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                String name = pathname.getName();
                return StringUtils.endsWith(name, "docx")||StringUtils.endsWith(name, "doc")&& !pathname.isHidden()&& StringUtils.indexOf(name,"~$")<0;
            }
        };
        List<File> files = FileUtil.loopFiles("/Users/chaihaowei/bookParse/parse", fileFilter);


        List<String> collect = files.stream().map(e -> {
            String name = e.getName();
            String substring = StringUtils.substring(name, 0, StringUtils.indexOf(name,"."));
            return substring;
        }).collect(Collectors.toList());
        return collect;
    }

    public static List<String> getMulWordCases(String path) throws Exception {



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
            }
        }
        for (List<String> item : cases) {
            for (String e : item) {
                OneParWordUtil.println(e);
            }
        }

        return cases.stream().flatMap(Collection::stream).collect(Collectors.toList());
    }




    public static void main(String []args) throws Exception {
        Map<String,List<String>> caseMap = Maps.newHashMap();
        List<String> oneWordCases = getOneWordCases();
        caseMap.put("单篇.docx",oneWordCases);


        String path="/Users/chaihaowei/bookParse/bookParse2/已审核校对-人 民 法 院 案 例 选2012-03 zst-1.docx";

        List<String> mulWordCases = getMulWordCases(path);
        caseMap.put("已审核校对-人 民 法 院 案 例 选2012-03 zst-1.docx",mulWordCases);

         String path2="/Users/chaihaowei/bookParse/bookParse2/已审核校对-人民法院案\u001D例选2012-04-01.docx";
        List<String> mulWordCases1 = getMulWordCases(path2);
        caseMap.put("已审核校对-人民法院案\u001D例选2012-04-01.docx",mulWordCases1);



        List<String> out =Lists.newArrayList();

        caseMap.forEach((k,v)->{
            out.add(k);
            out.addAll(v);
        });

        String collect = out.stream().collect(Collectors.joining("\n"));

        File file = FileUtil.newFile("/Users/chaihaowei/Desktop/1.txt");
        FileOutputStream fos = new FileOutputStream(file);

        IoUtil.writeUtf8(fos,true,collect);
    }
}
