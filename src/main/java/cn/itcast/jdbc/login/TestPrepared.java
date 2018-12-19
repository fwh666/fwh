package cn.itcast.jdbc.login;

import cn.itcast.jdbc.util.MyJdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * create by fwh on 2018/8/31 下午8:59
 * 防止SQL注入，
 *      解决方法；
 *          采用预编译方法。代替直接的statement
 *          这样会把or转义，而不会当成条件去执行。
 */
public class TestPrepared {
    //用户账户
    @Test
    public void test(){
//        Boolean loginFalg = login("1", "1");//正常情况
        boolean loginFalg = login("1' or '1=1","666666");//SQL注入情况,or 被当成条件。
        if(loginFalg){
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }
    }
    //登录查询返回结果值
    public Boolean login(String username,String password){
        Connection connection =null;
        PreparedStatement preparedStatement =null;
        ResultSet resultSet =null;
        try{
            connection= MyJdbcUtils.getConnect();
            String sql = "select * from test where username=? and password=?";
            //对SQL进行预编译操作
             preparedStatement = connection.prepareStatement(sql);
//            statement=connection.createStatement();
//            resultSet = preparedStatement.executeQuery(sql);
            //添加参数
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            //执行SQL
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                System.out.println("用户名为："+resultSet.getString("username"));
                return true;
            }else{
                return false;
            }
        }catch (Exception e){

        }finally {
            MyJdbcUtils.closeResource(resultSet,preparedStatement,connection);
        }
        return false;
    }
}
