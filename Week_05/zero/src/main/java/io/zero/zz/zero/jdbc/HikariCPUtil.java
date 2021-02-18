package io.zero.zz.zero.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author zhurui
 * @Date 2021/2/9 12:28 下午
 * @Version 1.0
 */
public class HikariCPUtil {

    private static HikariConfig config = new HikariConfig();

    private static HikariDataSource dataSource = new HikariDataSource();

    static {
        config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/zero?characterEncoding=utf8");
        config.setUsername("root");
        config.setPassword("qweqwe321");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource = new HikariDataSource(config);
    }


    public static Connection borrowConn() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void returnConn(Connection conn) {
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
