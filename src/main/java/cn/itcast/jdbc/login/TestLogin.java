package cn.itcast.jdbc.login;

import cn.itcast.jdbc.util.MyJdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * create by fwh on 2018/8/31 下午8:42
 * @description
 * 模拟登录页面 账户+密码
 */
public class TestLogin {
    /**
     * SQL注入原理：
     *  （1）select * from user where username='zhangsan' or '1=1' and password='666666'
        （2）比如这样的效果 boolean flag = login("zhangsan' or '1=1","666666");
            在用户名参数里面输入值  zhangsan' or '1=1，在密码随便输入一个值，会出现sql注入的漏洞
     */
    //用户账户
    @Test
    public void test(){
        Boolean login = login("1", "1");
        if(login){
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }
    }
    //登录查询返回结果值
    public Boolean login(String username,String password){
        Connection connection =null;
        Statement statement =null;
        ResultSet resultSet =null;
        try{
            connection=MyJdbcUtils.getConnect();
            statement=connection.createStatement();
            String sql = "select * from test where username='"+username+"' and password='"+password+"'";
            resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                System.out.println("用户名为："+resultSet.getString("username"));
                return true;
            }else{
                return false;
            }
        }catch (Exception e){

        }finally {
            MyJdbcUtils.closeResource(resultSet,statement,connection);
        }
        return false;
    }
}
