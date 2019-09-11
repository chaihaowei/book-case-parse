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
public class CtypeParseServiceImpl extends IAbstractParseService {


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
            return name;
        }
        return value;
    }

    public void dualtypeMap() {
        String userSimpleName = User.class.getSimpleName();
        String lawyerSimpleName = Lawyer.class.getSimpleName();
        //姓名
        xpathType.put(userSimpleName + User.ALIAS_name, "/body/div[2]/div[3]/div[1]/h1/text()");
        //头像
        xpathType.put(userSimpleName + User.ALIAS_headimgurl, "/body/div[2]/div[3]/div[1]/img/@src");

        //机构名称
        xpathType.put(userSimpleName + User.ALIAS_institution, "/body/div[2]/div[3]/div[2]/p[1]/text()");

        //手机号
        xpathType.put(userSimpleName + User.ALIAS_mobile_phone, "/body/div[3]/ul/li[8]/p/span/text()");

        //需要处理 省份  todo
        xpathType.put(userSimpleName + User.ALIAS_province, "/body/div[2]/div[3]/div[3]/p[2]/span/text()");

        //introduction 个人简介
        xpathType.put(userSimpleName + User.ALIAS_introduction, "/body/div[4]/div[2]/div[1]/div/allText()");

        //执业证号
        xpathType.put(lawyerSimpleName + Lawyer.ALIAS_license_number, "/body/div[2]/div[3]/div[2]/p[2]/text()");

        //执业领域
        xpathType.put(userSimpleName + User.ALIAS_area, "/body/div[2]/div[3]/div[2]/p[3]/span/allText()");
    }
}
