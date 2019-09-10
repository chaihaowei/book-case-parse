package com.icourt.lawyercrawlparse.util;

import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.InputMismatchException;
import org.seimicrawler.xpath.JXDocument;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Slf4j
public class XpathUtils {


    public  static String getOneByXPath(String html, String xPath){
        List<String> listByXPath = XpathUtils.getListByXPath(html, xPath);
        if(CollectionUtils.isEmpty(listByXPath)){
            return null;
        }

        String collect = listByXPath.stream().collect(Collectors.joining(" "));
//        String s = first.orElseGet(null);
        return collect;
    }


    public static  List<String> getListByXPath(String html, String xPath){
        JXDocument jxDocument = JXDocument.create(html);
        List<Object> rs =null;
//        try {
           rs = jxDocument.sel(xPath);
//        }catch (Exception ex){
//            log.error(ex.getMessage());
//        }
        if(CollectionUtils.isEmpty(rs)){
            return new ArrayList<>();
        }
        List<String> collect = rs.stream().map(e -> e.toString()).collect(Collectors.toList());
        return collect;
    }


}
