package com.hzlx.ch01.jdbc_CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//使用jdbc实现查询数据
public class JDBCSelect {
    public static void main(String[] args) throws Exception {
        String url="jdbc:mysql://localhost:3306/itcast?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        String username="root";
        String password="123456";
        //注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //获取链接
        Connection connection = DriverManager.getConnection(url,username,password);
        //准备执行的sql
        String sql="select * from account";
        //获取执行器对象
        Statement statement = connection.createStatement();

       //获取结果集
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            double money = resultSet.getDouble(3);
            System.out.println("id"+id+"name"+name+"money"+money);
        }

        //关闭资源
        statement.close();
        connection.close();

    }


}
