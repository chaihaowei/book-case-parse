package com.icourt.lawyercrawlparse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.icourt.lawyercrawlparse.dao")
public class LawyerCrawlParseApplication {

    public static void main(String[] args) {
        SpringApplication.run(LawyerCrawlParseApplication.class, args);
    }

}
