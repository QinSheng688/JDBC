package com.hzlx.ch01.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

//模拟用户登录
public class LoginForJDBC {
    static final String URL="jdbc:mysql://localhost:3306/pet_db?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    static final String USERNAME="root";
    static final String PASSWORD="123456";
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        System.out.println("========欢迎登录=========");
        System.out.println("用户名");
        String username = sc.next();
        System.out.println("密码");
        String password=sc.next();
        if(login(username, password)){

            System.out.println("成功");
        }else {
            System.out.println("失败");
        }

    }
    private static boolean login(String username, String password) throws Exception {
        boolean flag=false;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        String sql="select * from t_user_info where user_name='"+username+"'and `password`='"+password+"'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            flag=true;
        }
        resultSet.close();
        statement.close();
        connection.close();
       return flag;
    }
}
