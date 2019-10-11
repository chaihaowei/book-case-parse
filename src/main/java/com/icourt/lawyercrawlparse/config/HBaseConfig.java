package com.icourt.lawyercrawlparse.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Objects;

@Configuration
@Slf4j
public class HBaseConfig {
    @Value("${hbase.zookeeper.quorum}")
    private String quorum;

    private volatile Connection connection;


    @Bean
    public org.apache.hadoop.conf.Configuration getCfg() {
        org.apache.hadoop.conf.Configuration conf = HBaseConfiguration.create();
        conf.set(HConstants.ZOOKEEPER_QUORUM, quorum);

        // for test
//        conf.set("hbase.master","172.28.2.7:16000");
//        conf.set("zookeeper.znode.parent","/hbase");
//        conf.set("hbase.zookeeper.property.clientPort","2182");
//        conf.set("hbase.zookeeper.property.dataDir","/Users/larson/Documents/zk-data/serv1/data");

        return conf;
    }

    /**
     * 单例模式确保hbase中connection唯一
     *
     * @return
     */
    public Connection getConn() {
        if (null == this.connection) {
            synchronized (this) {
                if (null == this.connection) {
                    try {
                        this.connection = ConnectionFactory.createConnection(getCfg());
                    } catch (IOException e) {
                        log.error("acquire hbase connection failure");
                    }
                }
            }
        }
        return this.connection;
    }

    /**
     * 关闭table
     *
     * @param table
     */
    public void closeTable(Table table) {
        try {
            if (Objects.nonNull(table)) {
                table.close();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 关闭table
     *
     * @param admin
     */
    public void closeAdmin(Admin admin) {
        try {
            if (Objects.nonNull(admin)) {
                admin.close();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void closeConn(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

//    public HbaseTemplate getTemplate() {
//        return new HbaseTemplate(getCfg());
//    }
}
