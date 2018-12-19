package cn.itcast.jdbc.util;

import java.util.ResourceBundle;

/**
 * create by fwh on 2018/8/31 上午11:55
 */
public class ReadFileTest {
    public static void main(String[] args) {
//        String drivername = ResourceBundle.getBundle("db").getString("mysql.jdbc.driver");
//        System.out.println(drivername);
        read2();
    }
    public static void read2() {
        //绑定连接资源
        String drivername = ResourceBundle.getBundle("db").getString("drivername");
        String url = ResourceBundle.getBundle("db").getString("url");
        String username = ResourceBundle.getBundle("db").getString("username");
        String password = ResourceBundle.getBundle("db").getString("password");
        System.out.println(drivername);
        System.out.println(url);
        System.out.println(username);
        System.out.println(password);
    }
}
