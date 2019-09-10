package com.icourt.lawyercrawlparse.service.impl;

import com.alibaba.fastjson.JSON;
import com.icourt.lawyercrawlparse.entity.Lawyer;
import com.icourt.lawyercrawlparse.entity.User;
import com.icourt.lawyercrawlparse.entity.KafkaLawyer;
import com.icourt.lawyercrawlparse.service.IParseService;
import com.icourt.lawyercrawlparse.util.XpathUtils;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
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
public class AtypeParseServiceImpl implements IParseService, InitializingBean {

    @Autowired
    private SQLManager sqlManager;


    private  Map<String,String>  xpathType = new HashMap<>();

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

        String province= userSimpleName+User.ALIAS_province;
        if(StringUtils.equalsAnyIgnoreCase(key,province)){
            String  name = StringUtils.replace(value, "所在地区：", "");
            String[] split = name.split("-");
            if(Objects.nonNull(split)&&split.length>0){
                return split[0];
            }
            return null;
        }

        String city= userSimpleName+User.ALIAS_city;
        if(StringUtils.equalsAnyIgnoreCase(key,city)){
            String  name = StringUtils.replace(value, "所在地区：", "");
            String[] split = name.split("-");
            if(Objects.nonNull(split)&&split.length>1){
                return split[1];
            }
            return null;
        }

        if(StringUtils.equalsAnyIgnoreCase(key,userSimpleName+User.ALIAS_area)){
            String  name = StringUtils.replace(value, "所在地区：", "");
            return name;
        }

        return value;

    }

    private void dualtypeMap() {
        String userSimpleName = User.class.getSimpleName();
        String lawyerSimpleName = Lawyer.class.getSimpleName();
        //姓名
        xpathType.put(userSimpleName+User.ALIAS_name,"//*[@id=\"j-main-left\"]/ul/li[1]/span[2]/text()");
        //头像
        xpathType.put(userSimpleName+User.ALIAS_headimgurl,"/body/div[2]/div/div[1]/div[1]/div/img/@src");
        //机构名称
        xpathType.put(userSimpleName+User.ALIAS_institution,"//*[@id=\"j-main-left\"]/ul/li[4]/span[2]/text()");
        //职务
        xpathType.put(userSimpleName+User.ALIAS_political_status,"//*[@id=\"j-main-left\"]/ul/li[3]/span[2]/text()");

        //手机号
        xpathType.put(userSimpleName+User.ALIAS_mobile_phone,"//*[@id=\"j-main-left\"]/ul/li[2]/span[2]/text()");

        //邮箱
        xpathType.put(userSimpleName+User.ALIAS_email,"//*[@id=\"j-main-left\"]/ul/li[6]/span[2]/text()");

        //需要处理 省份
        xpathType.put(userSimpleName+User.ALIAS_province,"/body/div[2]/div/div[1]/div[2]/div[1]/text()");

        //需要处理 省份 城市
        xpathType.put(userSimpleName+User.ALIAS_city,"/body/div[2]/div/div[1]/div[2]/div[1]/text()");
        //introduction 个人简介
        xpathType.put(userSimpleName+User.ALIAS_introduction,"/body/div[5]/div[1]/div[3]/div/div[2]/text()");

        //地址
        xpathType.put(userSimpleName+User.ALIAS_address,"//*[@id=\"j-main-left\"]/ul/li[7]/span[2]/text()");

        //地区
        xpathType.put(userSimpleName+User.ALIAS_area,"/body/div[2]/div/div[1]/div[2]/div[1]/text()");

        //执业证号
        xpathType.put(lawyerSimpleName+Lawyer.ALIAS_license_number,"//*[@id=\"j-main-left\"]/ul/li[5]/span[2]/text()");

        //执业领域
        xpathType.put(lawyerSimpleName+Lawyer.ALIAS_business_area,"/body/div[5]/div[1]/div[3]/div/ul/li/allText()");

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


    @Override
    public boolean support(String htmlContent) throws Exception {
        String userSimpleName = User.class.getSimpleName();
        String s = xpathType.get(userSimpleName + User.ALIAS_name);
        String oneByXPath = XpathUtils.getOneByXPath(htmlContent, s);
        return StringUtils.isNotBlank(oneByXPath);
    }


}
