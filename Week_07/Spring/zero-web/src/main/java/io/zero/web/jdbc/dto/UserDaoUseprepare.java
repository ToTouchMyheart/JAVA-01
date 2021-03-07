package io.zero.web.jdbc.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import io.zero.zz.zero.jdbc.JDBCUtil;

/**
 * @Author zhurui
 * @Date 2021/2/9 12:57 下午
 * @Version 1.0
 */
public class UserDaoUseprepare {

    public static void main(String[] args) {
        UserDaoUseprepare userDao = new UserDaoUseprepare();
        userDao.batch(Arrays.asList(new User("two"), new User("three")));
    }

    public List<User> list(String name) {
        PreparedStatement preparedStatement;

        Connection conn = null;
        try {
            conn = JDBCUtil.borrowConn();
            preparedStatement = conn.prepareStatement(("select name from user where name = ?; "));
            preparedStatement.setObject(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
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
            PreparedStatement preparedStatement;
            Connection conn = null;
            try {
                conn = JDBCUtil.borrowConn();
                preparedStatement = conn.prepareStatement("insert into user (name) values(?)");
                preparedStatement.setObject(1, user.getName());
                preparedStatement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                JDBCUtil.returnConn(conn);
            }
        }
    }

    public void batch(List<User> users) {
        {
            PreparedStatement preparedStatement;
            Connection conn = null;
            try {
                conn = JDBCUtil.borrowConn();
                preparedStatement = conn.prepareStatement("insert into user (name) values(?)");
                for (User user : users) {
                    preparedStatement.setObject(1, user.getName());
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                JDBCUtil.returnConn(conn);
            }
        }
    }

}
