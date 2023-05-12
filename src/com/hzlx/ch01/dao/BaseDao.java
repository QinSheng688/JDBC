package com.hzlx.ch01.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;

//封装通用的CRUD操作
public class BaseDao  {
    private final String URL="jdbc:mysql://localhost:3306/itcast?useSSL=false&useUnicode=true&charterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private final String USERNAME="root";
    private final String PASSWORD="123456";
    private Connection connection=null;
    private PreparedStatement preparedStatement=null;
    private ResultSet resultSet=null;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //通用的增删改方法
    //sql 需要执行的sql包含？
    //objs ？对应的参数
    //return 受影响行数
    public int executeUpdate(String sql,Object... objs){
        int rows=0;
        try {
            connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);
            //获取sql预执行器
            preparedStatement= connection.prepareStatement(sql);
            //替换占位符
            for (int i = 0; i < objs.length; i++) {
                //循环替换？
                preparedStatement.setObject(i+1,objs[i]);
            }
            rows= preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("数据库链接异常");
            e.printStackTrace();
        }finally {
            try {
                if (preparedStatement != null){
                    preparedStatement.close();
                }
                if (connection!=null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }


    //查询数据封装成一个对象
    //clazz封装对象的字节码


}
