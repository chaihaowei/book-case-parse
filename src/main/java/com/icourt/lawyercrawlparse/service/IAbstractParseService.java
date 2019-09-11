package com.icourt.lawyercrawlparse.service;

import com.alibaba.fastjson.JSON;
import com.icourt.lawyercrawlparse.entity.KafkaLawyer;
import com.icourt.lawyercrawlparse.entity.Lawyer;
import com.icourt.lawyercrawlparse.entity.User;
import com.icourt.lawyercrawlparse.util.XpathUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public abstract class IAbstractParseService  implements  IParseService, InitializingBean {

    @Autowired
    protected SQLManager sqlManager;

    protected Map<String,String> xpathType = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        dualtypeMap();
    }

    protected abstract void dualtypeMap();

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
    public Map parse(KafkaLawyer entity, String htmlContent) throws  Exception {
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


    public boolean support(String htmlContent) throws Exception {
        String userSimpleName = User.class.getSimpleName();
        String s = xpathType.get(userSimpleName + User.ALIAS_name);
        String oneByXPath = XpathUtils.getOneByXPath(htmlContent, s);
        return StringUtils.isNotBlank(oneByXPath);
    }
    protected abstract String nameDual(String k, String oneByXPath);

}
