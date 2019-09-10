package com.icourt.lawyercrawlparse.service.impl;

import com.alibaba.fastjson.JSON;
import com.icourt.lawyercrawlparse.entity.KafkaLawyer;
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

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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
    @Override
    public Map parse(KafkaLawyer entity,String htmlContent) throws  Exception {
        if(!support(htmlContent)){
            return new HashMap();
        }
        Map parse = this.parse(htmlContent);

        String userSimpleName = User.class.getSimpleName();
        String lawyerSimpleName = Lawyer.class.getSimpleName();
        Map userMap = new HashMap();
        Set<String> strings = xpathType.keySet();

        //用户的
        List<String> users = strings.stream().filter(e -> StringUtils.startsWith(e, userSimpleName)).collect(Collectors.toList());

        //律所的
        List<String> lays = strings.stream().filter(e -> StringUtils.startsWith(e, lawyerSimpleName)).collect(Collectors.toList());

        users.forEach(e->{
            String replace = StringUtils.replace(e, userSimpleName, "");
            userMap.put(replace,parse.get(e));
        });

        Map layMap = new HashMap();

        lays.forEach(e->{
            String replace = StringUtils.replace(e, lawyerSimpleName, "");
            layMap.put(replace,parse.get(e));
        });


        User user = JSON.parseObject(JSON.toJSONString(userMap), User.class);
        user.setBaseId(entity.getId());
        user.setInsId(entity.getId());
        user.setScore(BigDecimal.ZERO);

        Lawyer lawyer = JSON.parseObject(JSON.toJSONString(layMap), Lawyer.class);
        lawyer.setBaseId(entity.getId());
        lawyer.setInsId(entity.getId());

        log.info("解析：{} 用户：{}",entity.getId(),user);
        log.info("解析: {} 律师：{}",entity.getId(),lawyer);

        sqlManager.insert(user);
        sqlManager.insert(lawyer);
        return parse;
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

        //机构名称 /html/body/div[3]/div[2]/div[1]/div[2]/p
        xpathType.put(userSimpleName+User.ALIAS_institution,"/body/div[3]/div[2]/div[2]/ul[1]/li[2]/span/text()");

        //个人简介
        xpathType.put(userSimpleName+User.ALIAS_introduction,"/body/div[3]/div[2]/div[1]/div[2]/p/text()");


        //机构地址
        xpathType.put(userSimpleName+User.ALIAS_address,"/body/div[3]/div[2]/div[2]/ul[2]/li/span/text()");

        //执业领域
        xpathType.put(lawyerSimpleName+Lawyer.ALIAS_business_area,"/body/div[3]/div[2]/div[2]/div[1]/a/allText()");
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
