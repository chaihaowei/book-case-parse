package com.icourt.lawyercrawlparse.web;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ReUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.icourt.lawyercrawlparse.dao.DsColumnMapper;
import com.icourt.lawyercrawlparse.dao.FaxindataMapper;
import com.icourt.lawyercrawlparse.entity.DsColumn;
import com.icourt.lawyercrawlparse.entity.Faxindata;
import com.icourt.lawyercrawlparse.entity.JudgementExt;
import com.icourt.lawyercrawlparse.service.IFaxindataService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FaxindataController {

    private static final String caseSpilt="(^([.]?))[0-9]{1,3}.*案";

    @Autowired
    private FaxindataMapper faxindataMapper;

    @Autowired
    private DsColumnMapper dsColumnMapper;

    @Autowired
    private IFaxindataService faxindataService;
    @GetMapping("/fax/{bookId}")
    public void getDs(@PathVariable(name ="bookId")String bookId){



        List<DsColumn> dsColumns = faxindataService.transToDs(bookId);
    }

    @GetMapping("/fax/case-titile")
    public void test() throws FileNotFoundException {
        LambdaQueryWrapper<DsColumn> faxindataLambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<DsColumn> dsColumnList = dsColumnMapper.selectList(faxindataLambdaQueryWrapper);
        List<JSONObject> collect = dsColumnList.stream().map(DsColumn::getExt).map(JSONObject::parseObject).collect(Collectors.toList());

        List<String> collect1 = collect.stream().map(e -> MapUtils.getString(e, JudgementExt.caseName, "")).collect(Collectors.toList());

        collect1=collect1.stream().filter(e-> CollectionUtils.isNotEmpty(ReUtil.findAllGroup0(caseSpilt, e))).collect(Collectors.toList());
        String str = collect1.stream().collect(Collectors.joining("\n"));

        File file =  FileUtil.newFile("/Users/chaihaowei/Desktop/2.txt");
        FileOutputStream fos = new FileOutputStream(file);
        IoUtil.writeUtf8(fos,true,str);
    }



//    public void all(){
//        new
//        faxindataMapper.selectList()
//
//    }

}
