package com.hzlx.ch01.ps_CRUD;

import java.sql.*;
import java.util.*;

//JDBC的preparedStatement增删改查
public class PsForCRUD {
    static final String URL="jdbc:mysql://localhost:3306/itcast?useSSL=false&useUnicode=true&charterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    static final String USERNAME="root";
    static final String PASSWORD="123456";
    static Connection connection=null;
    static PreparedStatement preparedStatement=null;
    static ResultSet resultSet=null;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
       getAll().forEach(System.out::println);

    }
    static void insert(Integer id,String name,Double money){

        try {
            //获取链接
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            //准备执行的sql
            String sql="insert into account values(?,?,?)";
            //获取预执行器
            preparedStatement=connection.prepareStatement(sql);
            //替换占位符
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setDouble(3,money);

            //执行sql
            int i = preparedStatement.executeUpdate();
            if (i>0){
                System.out.println("成功");
            }else {
                System.out.println("失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
    static void update(String name,Integer id){
        try {
            connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);
            String sql="update account set name=? where id=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,id);
            int i = preparedStatement.executeUpdate();

            if (i>0){
                System.out.println("成功");
            }else {
                System.out.println("失败");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                   e.printStackTrace();
                }
            }
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                   e.printStackTrace();
                }
            }
        }
    }

    static void deleteById(Integer id){
        try {
            connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);
            String sql="delete from  account where id=?";
            preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            int i = preparedStatement.executeUpdate();
            if (i>0){
                System.out.println("成功");
            }else {
                System.out.println("失败");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                   e.printStackTrace();
                }
            }
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    static List<Map<String, Object>> getAll(){
        List<Map<String, Object>> list=null;
        try {
            connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);
            String sql="select * from account";
            connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            list=new ArrayList<>();
            while (resultSet.next()){
                Map<String,Object> map=new HashMap<>();
                map.put("id",resultSet.getInt("id"));
                map.put("name",resultSet.getString("name"));
                map.put("money",resultSet.getDouble("money"));
                list.add(map);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
