package com.icourt.lawyercrawlparse.config;

import com.icourt.lawyercrawlparse.entity.KafkaLawyer;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReceiverToDb {


    @Autowired
    private SQLManager sqlManager;



    public void toDb(String recode, String message) {
        KafkaLawyer lawyer = new KafkaLawyer();
        lawyer.setMessage(message);
//        lawyer.setRecord(recode);
        sqlManager.insert(lawyer);
    }
}
