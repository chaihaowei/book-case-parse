package com.icourt.lawyercrawlparse.web;

import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.fastjson.JSON;
import com.icourt.lawyercrawlparse.entity.KafkaLawyer;
import com.icourt.lawyercrawlparse.service.IParseService;
import com.jayway.jsonpath.JsonPath;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")
@Slf4j
public class ParseController {



    @Autowired
    private List<IParseService> parseService;

    @Autowired
    private SQLManager sqlManager;

    @GetMapping("/parse")
    public void parse() throws Exception{

        KafkaLawyer kafkaLawyer =new KafkaLawyer();
//        kafkaLawyer.setDateType("com.icourt.lawyercrawlparse.service.impl.BtypeParseServiceImpl");
//        kafkaLawyer.setJsonContent("1");
        List<KafkaLawyer> all = sqlManager.lambdaQuery(KafkaLawyer.class).orderBy(KafkaLawyer::getId).select();

//        List<Integer> collect = Arrays.stream(ids.split(",")).filter(StringUtils::isNotBlank).map(e->{
//            String trim = StringUtils.trim(e);
//            return trim;
//        }).map(Integer::valueOf).collect(Collectors.toList());
//
//        List<KafkaLawyer> all = sqlManager.query(KafkaLawyer.class).andIn(KafkaLawyer.ALIAS_id,collect).select();
//


        ThreadPoolExecutor threadPoolExecutor = ThreadUtil.newExecutor(5, 10);

        AtomicInteger total = new AtomicInteger(all.size());
        List<Integer> list = new ArrayList<>();
        List<Exception> exlist = new ArrayList<>();
        for (KafkaLawyer e : all) {

            threadPoolExecutor.execute(()-> {
                try {
                    execute(e);
                } catch (Exception ex) {
                    exlist.add(ex);
                    list.add(e.getId());
                    ex.printStackTrace();
                }finally {
                    int i = total.decrementAndGet();
                    if(i==0||i==1){
                        for (Exception exception : exlist) {
                            exception.printStackTrace();
                        }
                        log.info("完成---：{}",list);
                    }
                }
            });

        }
    }

    private void execute(KafkaLawyer e) throws Exception {
        String message = e.getMessage();
        for (IParseService iParseService : parseService) {
            String html = JsonPath.parse(message).read("allTextHtml");
            Map parse = iParseService.parse(e,html);
            if(MapUtils.isNotEmpty(parse)){
                e.setJsonContent(JSON.toJSONString(parse));
                String name = iParseService.getClass().getName();
                e.setDateType(name);
                sqlManager.updateById(e);

            }

        }
    }

}
