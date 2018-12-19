package cn.itcast.jdbc;

import org.junit.Test;

import java.sql.*;

/**
 * create by fwh on 2018/8/30 下午10:26
 * jdbc的入门案例
 * 需要相关的jar包
 */
public class Test_01 {
    static Connection connection = null;
    static Statement statement = null;
    static ResultSet resultSet = null;

    //查询测试jdbc
    public static void main(String[] args) {
//        Connection connection =null;
//        Statement statement =null;
//        ResultSet resultSet = null;
        //加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //创建连接
            connection = DriverManager.getConnection("jdbc:mysql:///day06", "root", "123456");
            //创建statement
            statement = connection.createStatement();
            //执行SQL
            String sql = "select * from test";
            //得到结果
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                System.out.println("id" + id + "用户名" + username + "密码" + password);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭资源：原则后打开先关闭
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {

            }


        }
    }

    @Test
    public void updateSql() {
        try {
            connect();
            String sql = "update test set id ='33' where id ='1'";
            int i = statement.executeUpdate(sql);
            if (i > 0) {
                System.out.println("successful");
            } else {
                System.out.println("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    //抽取通用方法
    public void connect() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        //创建连接
        connection = DriverManager.getConnection("jdbc:mysql:///day06", "root", "123456");
        //创建statement
        statement = connection.createStatement();

    }

    public void close() throws SQLException {
        resultSet.close();
        statement.close();
        connection.close();
    }

}
