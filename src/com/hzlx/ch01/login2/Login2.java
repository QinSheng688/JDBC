package com.hzlx.ch01.login2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Login2 {
    static final String URL="jdbc:mysql://localhost:3306/pet_db?useSSL=false&useUnicode=true&charterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    static final String USERNAME="root";
    static final String PASSWORD="123456";

    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        System.out.println("用户名");
        String username=sc.next();
        System.out.println("密码");
        String password=sc.next();
        //注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //获取连接
        Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        //准备执行sql
        String sql ="select * from t_user_info where user_name=? and password=? ";
        //准备sql预执行器
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //给占位符赋值
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        //执行sql
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            System.out.println("成功"+resultSet.getString("user_name"));

        }else {
            System.out.println("错误");
        }
        //释放资源
        resultSet.close();
        preparedStatement.close();
        connection.close();

    }

}
