package cn.itcast.jdbc.util;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * create by fwh on 2018/8/31 下午12:06
 * 提取封装工具类
 */
public class MyJdbcUtils {
    private static String drivername;
    private static String url;
    private static String username;
    private static String password;

    //加载数据源
    static {
        //会加载resource文件夹中的db开头文件，里面的对应的String字符串
        drivername = ResourceBundle.getBundle("db").getString("drivername");
        username =ResourceBundle.getBundle("db").getString("username");
        url=ResourceBundle.getBundle("db").getString("url");
        password=ResourceBundle.getBundle("db").getString("password");
    }
    //创建连接
    public static Connection getConnect() throws SQLException, ClassNotFoundException {
        Class.forName(drivername);
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
    //关闭资源
    public static void closeResource(ResultSet resultSet, Statement statement,Connection connection){
        if(null!=resultSet){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resultSet=null;
        }
        if(null!=statement){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            statement=null;
        }
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection=null;
        }

    }

}
