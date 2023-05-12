package com.hzlx.ch02.事务;

import java.sql.*;

public class Demo1 {

    private static String url="jdbc:mysql://localhost:3306/itcast?useSSL=false&useUniCode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private static String username="root";
    private static String password="123456";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        testTransaction();

    }



    private static void testTransaction() {
        Connection connection=null;
        PreparedStatement preparedStatement=null;

        try {
            connection= DriverManager.getConnection(url,username,password);
            //设置事务手动提交
            connection.setAutoCommit(false);
            String sql="update account set money=money-? where id=?";
            preparedStatement=connection.prepareStatement(sql);
            //转出
            preparedStatement.setDouble(1,100);
            preparedStatement.setInt(2,1);
            preparedStatement.executeUpdate();
            //制造异常
            //java.lang.ArithmeticException: / by zero除0错误
            int i=1/0;
            //转入
            preparedStatement.setDouble(1,-100);
            preparedStatement.setInt(2,2);
            preparedStatement.executeUpdate();

        }
        catch (Exception e){
            if (connection!=null){
                //回滚事务
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            //提交事务
            if (connection!=null){
                try {
                    connection.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                if (preparedStatement!=null){
                    preparedStatement.close();
                }
                if (connection!=null){
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        }
    }

