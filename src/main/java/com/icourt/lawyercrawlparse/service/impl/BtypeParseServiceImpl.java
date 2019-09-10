package com.icourt.lawyercrawlparse.service.impl;

import com.icourt.lawyercrawlparse.entity.Lawyer;
import com.icourt.lawyercrawlparse.entity.User;
import com.icourt.lawyercrawlparse.service.IParseService;
import com.icourt.lawyercrawlparse.util.XpathUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 解析类
 */
@Service
@Slf4j
public class BtypeParseServiceImpl implements IParseService, InitializingBean {
    @Autowired
    private SQLManager sqlManager;

    private Map<String,String> xpathType = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        dualtypeMap();
    }

    private String nameDual (String key,String value){
        String userSimpleName = User.class.getSimpleName();
        String lawyerSimpleName = Lawyer.class.getSimpleName();

        String userName= userSimpleName+User.ALIAS_name;
        if(StringUtils.equalsAnyIgnoreCase(key,userName)){
            String  name = StringUtils.replace(value, "律师", "");
            return name;
        }


        if(StringUtils.equalsAnyIgnoreCase(key,userSimpleName+User.ALIAS_headimgurl)){
            String  name = StringUtils.replace(value, "//", "");
            return name;
        }

        return value;

    }

    private void dualtypeMap() {
        String userSimpleName = User.class.getSimpleName();
        String lawyerSimpleName = Lawyer.class.getSimpleName();

        //姓名
        xpathType.put(userSimpleName+User.ALIAS_name,"/body/div[3]/div[2]/div[1]/div[2]/div/a/text()");
        //头像
        xpathType.put(userSimpleName+User.ALIAS_headimgurl,"/body/div[3]/div[2]/div[1]/div[1]/a/img/@src");
        //机构名称
        xpathType.put(userSimpleName+User.ALIAS_institution,"//*[@id=\"j-main-left\"]/ul/li[4]/span[2]/text()");

        //手机号
        xpathType.put(userSimpleName+User.ALIAS_mobile_phone,"//*[@id=\"j-main-left\"]/ul/li[2]/span[2]/text()");
        //职务？ todo
        xpathType.put(userSimpleName+User.ALIAS_position,"//*[@id=\"j-main-left\"]/ul/li[3]/span[2]/text()");

        //机构名称
        xpathType.put(userSimpleName+User.ALIAS_institution,"/body/div[3]/div[2]/div[2]/ul[1]/li[2]/span/text()");

        //机构地址
        xpathType.put(userSimpleName+User.ALIAS_address,"/body/div[3]/div[2]/div[2]/ul[2]/li/span/text()");

        //执业领域
        xpathType.put(lawyerSimpleName+Lawyer.ALIAS_business_area,"/body/div[3]/div[2]/div[2]/div[1]/a/allText()");

        //机构
        xpathType.put(userSimpleName+User.ALIAS_institution,"//*[@id=\"introduce_main\"]/text()");
    }

    @Override
    public Map parse(String htmlContent) throws  Exception {
        if(!support(htmlContent)){
            return new HashMap();
        }
        Map<String,String > map = new HashMap() ;
        xpathType.forEach((k,v)->{
            log.info("k：{} v:{}",k, v);

            String oneByXPath = XpathUtils.getOneByXPath(htmlContent, v);
            String result = nameDual(k, oneByXPath);

            map.put(k,result);
            log.info("{}:{}",k, result);
        });
        return map;
    }

    @Override
    public boolean support(String htmlContent) throws Exception {
        String userSimpleName = User.class.getSimpleName();
        String s = xpathType.get(userSimpleName + User.ALIAS_name);
        String oneByXPath = XpathUtils.getOneByXPath(htmlContent, s);
        return StringUtils.isNotBlank(oneByXPath);
    }


}
