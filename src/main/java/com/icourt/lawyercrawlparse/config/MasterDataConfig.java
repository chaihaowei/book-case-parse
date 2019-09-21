package com.icourt.lawyercrawlparse.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class MasterDataConfig {


    @Primary
    @Bean(name ="dataSource-beet")
    public  DataSource alphaDataSource() {
        String court_info_url = "jdbc:mysql://localhost:3306/matter_case?useUnicode=true&characterEncoding=utf8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
        String court_info_username = "root";
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(court_info_url);
        druidDataSource.setUsername(court_info_username);
        druidDataSource.setPassword(court_info_username);
        return druidDataSource;
    }


    @Bean(name ="sqlManger")
    public  SQLManager getSqlManger(@Qualifier("dataSource-beet") DataSource dataSource){

        ConnectionSource source = ConnectionSourceHelper.getSingle(dataSource);
        DBStyle mysql = new MySqlStyle();
        // sql语句放在classpagth的/sql 目录下
        SQLLoader loader = new ClasspathLoader("/sql");
        // 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
        UnderlinedNameConversion nc = new UnderlinedNameConversion();
        SQLManager sqlManager = new SQLManager(mysql, loader, source, nc, new Interceptor[]{new DebugInterceptor()});

        return sqlManager;
    }


}