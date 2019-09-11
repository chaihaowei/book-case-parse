package com.icourt.lawyercrawlparse.service.impl;

import com.icourt.lawyercrawlparse.entity.Lawyer;
import com.icourt.lawyercrawlparse.entity.User;
import com.icourt.lawyercrawlparse.service.IAbstractParseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 解析类
 */
@Service
@Slf4j
public class BtypeParseServiceImpl extends IAbstractParseService {


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

        if (StringUtils.equalsAnyIgnoreCase(key, lawyerSimpleName + Lawyer.ALIAS_license_number)) {
            if (StringUtils.startsWith(value, "执业律师（律师证编号：")) {
                String replace;
                replace = StringUtils.replace(value, "执业律师（律师证编号：", "");
                replace = StringUtils.replace(replace, "）", "");
                return replace;
            } else {
                return null;
            }
        }


        return value;

    }


    public void dualtypeMap() {
        String userSimpleName = User.class.getSimpleName();
        String lawyerSimpleName = Lawyer.class.getSimpleName();

        //姓名
        xpathType.put(userSimpleName + User.ALIAS_name, "/body/div[3]/div[2]/div[1]/div[2]/div/a/text()");
        //头像
        xpathType.put(userSimpleName + User.ALIAS_headimgurl, "/body/div[3]/div[2]/div[1]/div[1]/a/img/@src");
        //机构名称
        xpathType.put(userSimpleName + User.ALIAS_institution, "//*[@id=\"j-main-left\"]/ul/li[4]/span[2]/text()");

        //手机号
        xpathType.put(userSimpleName + User.ALIAS_mobile_phone, "//*[@id=\"j-main-left\"]/ul/li[2]/span[2]/text()");
        //职务？ todo
        xpathType.put(userSimpleName + User.ALIAS_position, "//*[@id=\"j-main-left\"]/ul/li[3]/span[2]/text()");

        //机构名称 /html/body/div[3]/div[2]/div[1]/div[2]/p
        xpathType.put(userSimpleName + User.ALIAS_institution, "/body/div[3]/div[2]/div[2]/ul[1]/li[2]/span/text()");

        //个人简介
        xpathType.put(userSimpleName + User.ALIAS_introduction, "//*[@id=\"introduce_main\"]/text()");


        //机构地址
        xpathType.put(userSimpleName + User.ALIAS_address, "/body/div[3]/div[2]/div[2]/ul[2]/li/span/text()");

        //执业领域
        xpathType.put(lawyerSimpleName + Lawyer.ALIAS_business_area, "/body/div[3]/div[2]/div[2]/div[1]/a/allText()");

        //执业证号
        xpathType.put(lawyerSimpleName + Lawyer.ALIAS_license_number, "/body/div[3]/div[2]/div[2]/ul[1]/li[1]/span/text()");

    }


}
