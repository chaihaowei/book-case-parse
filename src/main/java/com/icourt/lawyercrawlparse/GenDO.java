package com.icourt.lawyercrawlparse;

import com.icourt.lawyercrawlparse.config.MasterDataConfig;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.ext.gen.GenConfig;
import org.beetl.sql.ext.gen.GenFilter;

import javax.sql.DataSource;

public class GenDO {


    public static void main(String[]args) throws Exception {
        genTable();
        return;

    }

    private static void genTable() throws Exception {
        MasterDataConfig master =new MasterDataConfig();
        DataSource dataSource = master.alphaDataSource();
        SQLManager sqlManger = master.getSqlManger(dataSource);

        sqlManger.genALL("com.icourt.lawyercrawlparse.entity", new GenConfig(), new GenFilter() {
            public boolean accept(String tableName) {
                if (tableName.equalsIgnoreCase("lawyer")||tableName.equalsIgnoreCase("user")) {
//                if (tableName.equalsIgnoreCase("kafka_lawyer")) {
                    return true;
                } else {
                    return false;
                }
                // return false
            }
        });
    }





}
