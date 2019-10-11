package com.icourt.lawyercrawlparse.web;

import com.icourt.lawyercrawlparse.dao.FaxindataMapper;
import com.icourt.lawyercrawlparse.entity.DsColumn;
import com.icourt.lawyercrawlparse.hbase.HBaseService;
import com.icourt.lawyercrawlparse.service.IFaxindataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FaxindataController {

    @Autowired
    private FaxindataMapper faxindataMapper;

    @Autowired
    private IFaxindataService faxindataService;

    @Autowired
    private HBaseService hBaseService;

    @GetMapping("/fax/{bookId}")
    public void getDs(@PathVariable(name ="bookId")String bookId){


        List<DsColumn> dsColumns = faxindataService.transToDs(bookId);
        System.out.println(1);
        try {
            hBaseService.batch("goodcase_demo", dsColumns);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/faxin")
    public void insertHBase() {

    }

    @GetMapping("/inithbase/{tableName}")
    public void initHbaseTable(@PathVariable(name ="tableName")String tableName) {
        try {
            hBaseService.initMetaData(tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void all(){
//        new
//        faxindataMapper.selectList()
//
//    }

}
