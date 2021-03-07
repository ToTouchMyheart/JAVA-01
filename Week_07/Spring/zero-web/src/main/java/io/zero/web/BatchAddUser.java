package io.zero.web;

import java.sql.*;
import java.util.List;
import java.util.UUID;

import io.zero.web.jdbc.HikariCPUtil;
import io.zero.web.jdbc.JDBCUtil;
import io.zero.web.jdbc.dto.User;

/**
 * @Author zhurui
 * @Date 2021/2/9 12:57 下午
 * @Version 1.0
 */
public class BatchAddUser {

    public static void main(String[] args) {
        BatchAddUser userDao = new BatchAddUser();
        userDao.add();
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

    public void add() {
        Connection connection = null;
        try {
            connection = JDBCUtil.borrowConn();
            connection.setAutoCommit(false);
            //这里需要注意，SQL语句的格式必须是预处理的这种，就是values(?,?,...,?)，否则批处理不起作用
            PreparedStatement statement = connection.prepareStatement("insert into `user`(user_name,nick_name,password,card_number) values(?,?,?,?)");
            System.out.println("开始插入数据");
            Long startTime = System.currentTimeMillis();
            for (int i = 0; i < 1000000; i++) {
                UUID randomUUID = UUID.randomUUID();
                String name = randomUUID.toString();
                String uuid = String.format("%018d", randomUUID.toString().hashCode());
                statement.setString(1, name);
                statement.setString(2, name);
                statement.setString(3, uuid);
                statement.setString(4, uuid);
                //将要执行的SQL语句先添加进去，不执行
                statement.addBatch();
            }
            //100W条SQL语句已经添加完成，执行这100W条命令并提交
            statement.executeBatch();
            connection.commit();
            Long endTime = System.currentTimeMillis();
            System.out.println("插入完毕,用时：" + (endTime - startTime));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.returnConn(connection);
        }
    }

}
