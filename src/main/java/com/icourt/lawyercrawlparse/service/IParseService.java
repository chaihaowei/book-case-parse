package com.icourt.lawyercrawlparse.service;


import com.icourt.lawyercrawlparse.entity.KafkaLawyer;

import java.util.Map;

/**
 * 解析类
 *     地址  -                                   类型 -                     规则
 *    http://www.lawtime.cn/lawyer/int951107956201   A                     -
 *     http://www.lawtime.cn/lawyer/id362028367122.html
 *     http://zhangkang.lawyer.lawtime.cn/int
 *     http://zjf1998.lawyer.lawtime.cn/int
 *     http://zhanghuijin.lawyer.lawtime.cn/int
 */
public interface IParseService {

    /**
     * 解析类
     * @param entity  实体类
     * @param htmlContent html
     * @return  解析的map
     * @throws Exception 异常
     */
    Map parse(KafkaLawyer entity,String htmlContent) throws Exception;

}
