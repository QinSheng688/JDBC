package com.hzlx.ch01.jdbc_CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

//使用jdbc实现修改数据
public class JDBCUpdate {
    public static void main(String[] args) throws Exception {
        String url="jdbc:mysql://localhost:3306/itcast?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        String username="root";
        String password="123456";
        //注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //获取链接
        Connection connection = DriverManager.getConnection(url,username,password);
        //准备执行的sql
        String sql="update account set name='向阳' where id=3";
        //获取执行器对象
        Statement statement = connection.createStatement();
        //执行sql，获取受影响的行数
        int i = statement.executeUpdate(sql);
        if (i>0){
            System.out.println("成功");
        }else {
            System.out.println("失败");
        }
        //关闭资源
        statement.close();
        connection.close();

    }


}
