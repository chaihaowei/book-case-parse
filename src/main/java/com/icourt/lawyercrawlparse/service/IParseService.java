package com.icourt.lawyercrawlparse.service;


import com.icourt.lawyercrawlparse.entity.KafkaLawyer;

import java.util.Map;

/**
 * 解析类
 *     地址  -                                   类型 -                     规则
 *    http://www.lawtime.cn/lawyer/int951107956201   A                     -
 *     http://www.lawtime.cn/lawyer/id362028367122.html
 *     http://zhangkang.lawyer.lawtime.cn/int
 */
public interface IParseService {

    Map parse(String htmlContent) throws Exception;

    Map parse(KafkaLawyer entity,String htmlContent) throws Exception;


    boolean support(String htmlContent) throws  Exception;
}
