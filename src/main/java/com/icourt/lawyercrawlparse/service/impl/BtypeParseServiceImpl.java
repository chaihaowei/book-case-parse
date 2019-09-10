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

    private void dualtypeMap() {
        String userSimpleName = User.class.getSimpleName();
        String lawyerSimpleName = Lawyer.class.getSimpleName();

        xpathType.put(userSimpleName+User.ALIAS_name,"/body/div[3]/div[2]/div[1]/div[2]/div/a/text()");
        xpathType.put(userSimpleName+User.ALIAS_mobile_phone,"//*[@id=\"j-main-left\"]/ul/li[2]/span[2]/text()");
        xpathType.put(userSimpleName+User.ALIAS_position,"//*[@id=\"j-main-left\"]/ul/li[3]/span[2]/text()");
        xpathType.put(userSimpleName+User.ALIAS_institution,"//*[@id=\"j-main-left\"]/ul/li[4]/span[2]/text()");
        xpathType.put(userSimpleName+User.ALIAS_institution,"//*[@id=\"j-main-left\"]/ul/li[4]/span[2]/text()");

        xpathType.put(lawyerSimpleName+Lawyer.ALIAS_license_number,"//*[@id=\"j-main-left\"]/ul/li[5]/span[2]/text()");
        xpathType.put(userSimpleName+User.ALIAS_email,"//*[@id=\"j-main-left\"]/ul/li[6]/span[2]/text()");
        xpathType.put(userSimpleName+User.ALIAS_address,"/body/div[3]/div[2]/div[2]/ul[2]/li/span/text()");

        xpathType.put(lawyerSimpleName+Lawyer.ALIAS_business_area,"/html/body/div[3]/div[2]/div[2]/div[1]/a/allText()");
        xpathType.put(userSimpleName+User.ALIAS_area,"");
        xpathType.put("remark","/body/div[5]/div[1]/div[3]/div/div[2]/text()");
    }

    @Override
    public Map parse(String htmlContent) throws  Exception {
        if(!support(htmlContent)){
            return new HashMap();
        }
        Map<String,String > map = new HashMap() ;
        xpathType.forEach((k,v)->{
            String oneByXPath = XpathUtils.getOneByXPath(htmlContent, v);
            map.put(k,oneByXPath);
            log.info("{}:{}",k, oneByXPath);
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
