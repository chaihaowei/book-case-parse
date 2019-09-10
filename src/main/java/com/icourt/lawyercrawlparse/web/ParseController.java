package com.icourt.lawyercrawlparse.web;

import com.alibaba.fastjson.JSON;
import com.icourt.lawyercrawlparse.entity.KafkaLawyer;
import com.icourt.lawyercrawlparse.service.IParseService;
import com.jayway.jsonpath.JsonPath;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.collections4.MapUtils;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class ParseController {


    @Autowired
    private List<IParseService> parseService;

    @Autowired
    private SQLManager sqlManager;

    @GetMapping("/parse")
    public void parse() throws Exception{

        KafkaLawyer kafkaLawyer =new KafkaLawyer();
        kafkaLawyer.setDateType("com.icourt.lawyercrawlparse.service.impl.BtypeParseServiceImpl");

        List<KafkaLawyer> all = sqlManager.template(kafkaLawyer,1,10);
        for (KafkaLawyer e : all) {
            String message = e.getMessage();
            for (IParseService iParseService : parseService) {
                String html = JsonPath.parse(message).read("allTextHtml");
                Map parse = iParseService.parse(html);
                if(MapUtils.isNotEmpty(parse)){
                    e.setJsonContent(JSON.toJSONString(parse));
                    String name = iParseService.getClass().getName();
                    e.setDateType(name);
//                    sqlManager.updateById(e);
                }

            }
        }
    }

}
