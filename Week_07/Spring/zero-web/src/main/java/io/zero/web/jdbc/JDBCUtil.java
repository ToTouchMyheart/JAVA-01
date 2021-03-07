package io.zero.web.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author zhurui
 * @Date 2021/2/9 12:28 下午
 * @Version 1.0
 */
public class JDBCUtil {

    public static Connection borrowConn() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/zero?rewriteBatchedStatements=true", "root", "zhurui1208");
        } catch (ClassNotFoundException | SQLException e) {
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
