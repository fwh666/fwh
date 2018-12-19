package cn.itcast.jdbc;

import cn.itcast.jdbc.util.MyJdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * create by fwh on 2018/8/31 下午8:17
 * 使用工具类 测试连接数据库
 */
public class Test_04 {

    @Test//查询方法
    public void select() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = MyJdbcUtils.getConnect();
            statement = connection.createStatement();
            String sql = "select * from test";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                System.out.println("id值："+id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            MyJdbcUtils.closeResource(resultSet, statement, connection);
        }
    }
}
