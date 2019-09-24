package com.icourt.lawyercrawlparse.service;

import com.icourt.lawyercrawlparse.entity.DsColumn;

import java.util.List;

/**
 * faxindata
 *
 * @author 柴浩伟
 * @date 2019/09/19
 */
public interface IFaxindataService {


    /**
     * 转变为Ds结构
     */
    List<DsColumn> transToDs(String bookId);

}