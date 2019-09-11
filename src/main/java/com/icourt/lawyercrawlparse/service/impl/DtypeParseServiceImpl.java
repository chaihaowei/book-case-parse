package com.icourt.lawyercrawlparse.service.impl;

import com.icourt.lawyercrawlparse.entity.Lawyer;
import com.icourt.lawyercrawlparse.entity.User;
import com.icourt.lawyercrawlparse.service.IAbstractParseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 解析类
 */
@Service
@Slf4j
public class DtypeParseServiceImpl extends IAbstractParseService {


    public String nameDual(String key, String value) {
        String userSimpleName = User.class.getSimpleName();
        String lawyerSimpleName = Lawyer.class.getSimpleName();

        String userName = userSimpleName + User.ALIAS_name;
        if (StringUtils.equalsAnyIgnoreCase(key, userName)) {
            String name = StringUtils.replace(value, "律师", "");
            return name;
        }


        if (StringUtils.equalsAnyIgnoreCase(key, userSimpleName + User.ALIAS_headimgurl)) {
            String name = StringUtils.replace(value, "//", "");
            return name;
        }


        return value;
    }

    public void dualtypeMap() {
        String userSimpleName = User.class.getSimpleName();
        String lawyerSimpleName = Lawyer.class.getSimpleName();
        //姓名
        xpathType.put(userSimpleName + User.ALIAS_name, "/body/div[4]/div[1]/div[1]/div[2]/div/div[2]/h4/text()");
        //头像
        xpathType.put(userSimpleName + User.ALIAS_headimgurl, "/body/div[4]/div[1]/div[1]/div[2]/div/div[1]/a/img/@src");
        //机构名称
        xpathType.put(userSimpleName + User.ALIAS_institution, "/body/div[4]/div[1]/div[1]/div[2]/ul/li[5]/text()");
        //职务
//        xpathType.put(userSimpleName + User.ALIAS_political_status, "//*[@id=\"j-main-left\"]/ul/li[3]/span[2]/text()");

        //手机号
        xpathType.put(userSimpleName + User.ALIAS_mobile_phone, "/body/div[4]/div[1]/div[1]/div[2]/ul/li[1]/text()");

        //邮箱
        xpathType.put(userSimpleName + User.ALIAS_email, "/body/div[4]/div[1]/div[1]/div[2]/ul/li[3]/text()");

        //需要处理 省份
//        xpathType.put(userSimpleName + User.ALIAS_province, "/body/div[2]/div/div[1]/div[2]/div[1]/text()");

        //需要处理 省份 城市
//        xpathType.put(userSimpleName + User.ALIAS_city, "/body/header/div/div/div[2]/span[1]/em/text()");
        //introduction 个人简介
        xpathType.put(userSimpleName + User.ALIAS_introduction, "/body/div[4]/div[2]/div[2]/div/div[1]/text()");

        //地址
        xpathType.put(userSimpleName + User.ALIAS_address, "/body/div[4]/div[2]/div[2]/div/div[2]/table/tbody/tr[4]/td/text()");

        //地区
//        xpathType.put(userSimpleName + User.ALIAS_area, "/body/div[2]/div/div[1]/div[2]/div[1]/text()");

        //执业证号
        xpathType.put(lawyerSimpleName + Lawyer.ALIAS_license_number, "/body/div[4]/div[1]/div[1]/div[2]/ul/li[4]/text()/text()");

        //执业领域
//        xpathType.put(lawyerSimpleName + Lawyer.ALIAS_business_area, "/body/div[5]/div[1]/div[3]/div/ul/li/allText()");

    }


}
