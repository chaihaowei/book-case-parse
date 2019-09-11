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
public class AtypeParseServiceImpl extends IAbstractParseService {


    public String nameDual(String key, String value) {
        String userSimpleName = User.class.getSimpleName();
        String lawyerSimpleName = Lawyer.class.getSimpleName();

        String userName = userSimpleName + User.ALIAS_name;
        if (StringUtils.equalsAnyIgnoreCase(key, userName)) {
            String name = StringUtils.replace(value, "律师", "");
            return name;
        }

        String province = userSimpleName + User.ALIAS_province;
        if (StringUtils.equalsAnyIgnoreCase(key, province)) {
            String name = StringUtils.replace(value, "所在地区：", "");
            String[] split = name.split("-");
            if (Objects.nonNull(split) && split.length > 0) {
                return split[0];
            }
            return null;
        }

        String city = userSimpleName + User.ALIAS_city;
        if (StringUtils.equalsAnyIgnoreCase(key, city)) {
            String name = StringUtils.replace(value, "所在地区：", "");
            String[] split = name.split("-");
            if (Objects.nonNull(split) && split.length > 1) {
                return split[1];
            }
            return null;
        }

        if (StringUtils.equalsAnyIgnoreCase(key, userSimpleName + User.ALIAS_headimgurl)) {
            String name = StringUtils.replace(value, "//", "");
            return name;
        }


        if (StringUtils.equalsAnyIgnoreCase(key, userSimpleName + User.ALIAS_area)) {
            String name = StringUtils.replace(value, "所在地区：", "");
            return name;
        }

        return value;

    }

    public void dualtypeMap() {
        String userSimpleName = User.class.getSimpleName();
        String lawyerSimpleName = Lawyer.class.getSimpleName();
        //姓名
        xpathType.put(userSimpleName + User.ALIAS_name, "//*[@id=\"j-main-left\"]/ul/li[1]/span[2]/text()");
        //头像
        xpathType.put(userSimpleName + User.ALIAS_headimgurl, "/body/div[2]/div/div[1]/div[1]/div/img/@src");
        //机构名称
        xpathType.put(userSimpleName + User.ALIAS_institution, "//*[@id=\"j-main-left\"]/ul/li[4]/span[2]/text()");
        //职务
        xpathType.put(userSimpleName + User.ALIAS_political_status, "//*[@id=\"j-main-left\"]/ul/li[3]/span[2]/text()");

        //手机号
        xpathType.put(userSimpleName + User.ALIAS_mobile_phone, "//*[@id=\"j-main-left\"]/ul/li[2]/span[2]/text()");

        //邮箱
        xpathType.put(userSimpleName + User.ALIAS_email, "//*[@id=\"j-main-left\"]/ul/li[6]/span[2]/text()");

        //需要处理 省份
        xpathType.put(userSimpleName + User.ALIAS_province, "/body/div[2]/div/div[1]/div[2]/div[1]/text()");

        //需要处理 省份 城市
        xpathType.put(userSimpleName + User.ALIAS_city, "/body/div[2]/div/div[1]/div[2]/div[1]/text()");
        //introduction 个人简介
        xpathType.put(userSimpleName + User.ALIAS_introduction, "/body/div[5]/div[1]/div[3]/div/div[2]/text()");

        //地址
        xpathType.put(userSimpleName + User.ALIAS_address, "//*[@id=\"j-main-left\"]/ul/li[7]/span[2]/text()");

        //地区
        xpathType.put(userSimpleName + User.ALIAS_area, "/body/div[2]/div/div[1]/div[2]/div[1]/text()");

        //执业证号
        xpathType.put(lawyerSimpleName + Lawyer.ALIAS_license_number, "//*[@id=\"j-main-left\"]/ul/li[5]/span[2]/text()");

        //执业领域
        xpathType.put(lawyerSimpleName + Lawyer.ALIAS_business_area, "/body/div[5]/div[1]/div[3]/div/ul/li/allText()");

    }


}
