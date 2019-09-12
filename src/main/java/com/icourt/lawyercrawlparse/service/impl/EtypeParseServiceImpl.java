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
public class EtypeParseServiceImpl extends IAbstractParseService {


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
            String name = StringUtils.replace(value, "服务地区：", "");
            if(StringUtils.isBlank(name)){
                return null;
            }
            String[] split = name.split("-");
            if (Objects.nonNull(split) && split.length > 0) {
                return split[0];
            }
            return null;
        }

        String city = userSimpleName + User.ALIAS_city;
        if (StringUtils.equalsAnyIgnoreCase(key, city)) {
            String name = StringUtils.replace(value, "服务地区：", "");
            if(StringUtils.isBlank(name)){
                return null;
            }
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
            if(StringUtils.isBlank(value)){
                return null;
            }
            String name = StringUtils.replace(value, "服务地区：", "");
            return name;
        }

        return value;

    }

    public void dualtypeMap() {
        String userSimpleName = User.class.getSimpleName();
        String lawyerSimpleName = Lawyer.class.getSimpleName();
        //姓名
        xpathType.put(userSimpleName + User.ALIAS_name, "/body/div[4]/div[1]/ul/li[1]/p/text()");
        //头像
        xpathType.put(userSimpleName + User.ALIAS_headimgurl, "/body/div[4]/div[2]/div[2]/div[1]/img/@src");
        //机构名称
        xpathType.put(userSimpleName + User.ALIAS_institution, "/body/div[4]/div[1]/ul/li[3]/p/text()");
        //职务
//        xpathType.put(userSimpleName + User.ALIAS_political_status, "//*[@id=\"j-main-left\"]/ul/li[3]/span[2]/text()");

        //手机号
        xpathType.put(userSimpleName + User.ALIAS_mobile_phone, "/body/div[4]/div[1]/ul/li[2]/p/text()");

        //邮箱
        xpathType.put(userSimpleName + User.ALIAS_email, "/body/div[4]/div[1]/ul/li[5]/p/text()");

        //需要处理 省份
        xpathType.put(userSimpleName + User.ALIAS_province, "/body/div[4]/div[2]/div[2]/div[2]/p[2]/text()");

        //需要处理 省份 城市
        xpathType.put(userSimpleName + User.ALIAS_city, "/body/div[4]/div[2]/div[2]/div[2]/p[2]/text()");
        //introduction 个人简介
        xpathType.put(userSimpleName + User.ALIAS_introduction, "/body/div[4]/div[2]/div[1]/div/allText()");

        //地址
        xpathType.put(userSimpleName + User.ALIAS_address, "/body/div[4]/div[1]/ul/li[6]/p/text()");

        //地区
        xpathType.put(userSimpleName + User.ALIAS_area, "/body/div[4]/div[2]/div[2]/div[2]/p[2]/text()");

        //执业证号
        xpathType.put(lawyerSimpleName + Lawyer.ALIAS_license_number, "/body/div[4]/div[1]/ul/li[4]/p/text()");

        //执业领域
        xpathType.put(lawyerSimpleName + Lawyer.ALIAS_business_area, "/body/div[5]/div[1]/div[3]/div/ul/li/allText()");

    }


}
