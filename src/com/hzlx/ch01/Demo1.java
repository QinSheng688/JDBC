package com.hzlx.ch01;

import java.sql.*;

public class Demo1 {
    public static void main(String[] args) throws Exception {
        //1.驱动
       // Driver driver = new com.mysql.cj.jdbc.Driver();
        //2.考驾照
       // DriverManager.registerDriver(driver);
        //省略后
        //Class.forName("com.mysql.cj.jdbc.Driver");

        //搭桥前需要准备搭桥的物料
        String url="jdbc:mysql://localhost:3306/pet_db?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        String username="root";
        String password="123456";
        //3.搭桥
        Connection connection = DriverManager.getConnection(url,username,password);
        //4.准备清单（sql语句）
        String sql="select * from t_business_info";
        //5.准备拉货的小车
        Statement statement = connection.createStatement();
        //6.拉货
        ResultSet resultSet = statement.executeQuery(sql);
        //7.把集装箱拆开
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            System.out.println("第"+id+"家店："+name);
        }
        //8.过河拆桥
        resultSet.close();
        statement.close();
        connection.close();

    }
}
