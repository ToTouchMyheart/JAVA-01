package io.zero.zz.zero.jdbc;

import io.zero.zz.zero.jdbc.dto.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @Author zhurui
 * @Date 2021/2/9 12:57 下午
 * @Version 1.0
 */
public class UserDao {

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        userDao.list("zero");
    }

    public List<User> list(String name) {
        Statement statement;
        Connection conn = null;
        try {
            conn = HikariCPUtil.borrowConn();
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select name from user where name like '%" + name + "%'; ");
            while (resultSet.next()) {
                String value = resultSet.getString("name");
                System.out.println(value);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.returnConn(conn);
        }
        return null;
    }

    public void add(User user) {
        {
            Statement statement;
            Connection conn = null;
            try {
                conn = JDBCUtil.borrowConn();
                statement = conn.createStatement();
                statement.executeUpdate("insert into user (name) values('" + user.getName() + "') ");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                JDBCUtil.returnConn(conn);
            }
        }
    }

}
