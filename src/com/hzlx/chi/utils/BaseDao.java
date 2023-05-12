package com.hzlx.chi.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseDao<T> {
    private Connection connection=null;
    private PreparedStatement preparedStatement =null;
    private ResultSet resultSet=null;

    /**
     * 通用的 增删改方法
     *
     * @param sql  需要执行的sql其中包含（?占位符）
     * @param objs ?号对应的参数  可变参数列表
     * @return 受影响行数  private Connection connection=null;
     *     private PreparedSta
     */
    public int executeUpdate(String sql, Object... objs) {
        int rows = 0;
        try {
            //获取链接-->从连接处中获取链接
            connection = ConnectionPool.getConnection();
            //获取SQL预执行器
            preparedStatement = connection.prepareStatement(sql);
            //替换？占位符
            for (int i = 0; i < objs.length; i++) {
                //循环替换？
                preparedStatement.setObject(i + 1, objs[i]);
            }
            rows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL执行异常");
            e.printStackTrace();
        } finally {
            ConnectionPool.closeAll(connection,preparedStatement,null);
        }
        return rows;
    }

    public List<Map<String,Object>> selectListForMap(String sql, Object... objs){
        //装结果集的容器
        List<Map<String,Object>> list=null;
        try {
            connection=ConnectionPool.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            for (int i = 0; i < objs.length; i++) {
                preparedStatement.setObject(i+1,objs[i]);

            }
            resultSet=preparedStatement.executeQuery();
            //遍历结果集，装成map放进list
            list=new ArrayList<>();
            //获取mata对象，方便获取列名
            ResultSetMetaData metaData = resultSet.getMetaData();
            //结果集一共多少列
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()){
                Map<String,Object> map=new HashMap<>();
                for (int i = 0; i <=columnCount; i++) {
                    //获取列名
                    String columnName = metaData.getColumnLabel(i);
                    //根据列名获取结果
                    Object value = resultSet.getObject(columnName);
                    //把columnName作为key 放入map
                    map.put(columnName,value);
                }
                list.add(map);
            }
        } catch (SQLException e) {
            System.out.println("链接失败");
            e.printStackTrace();
        }finally {
            try {
                if (resultSet!=null){
                    resultSet.close();
                }
                if (preparedStatement!=null){
                    preparedStatement.close();
                }
                if (connection!=null){
                    connection.close();
                }

            } catch (SQLException e) {
                System.out.println("释放资源异常");
                e.printStackTrace();
            }
        }

        return list;
    }

    public Map<String,Object> selectOneForMap(String sql,Object... objs){
        Map<String,Object> map=null;
        try {
            connection=ConnectionPool.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            for (int i = 0; i < objs.length; i++) {
                preparedStatement.setObject(i+1,objs[i]);

            }
            resultSet=preparedStatement.executeQuery();
            map=new HashMap<>();
            //字段名字都在meta对象身上
            ResultSetMetaData metaData = resultSet.getMetaData();
            //获取结果的总列数
            int columnCount = metaData.getColumnCount();

            if (resultSet.next()){
                for (int i = 0; i <= columnCount; i++) {
                    String columnName = metaData.getColumnLabel(i);
                    //根据列名从结果集获取数据
                    Object value = resultSet.getObject(columnName);
                    map.put(columnName,value);
                }
            }
//            else {
//                throw new RuntimeException("没有找到结果集");
//            }
        } catch (SQLException e) {
            System.out.println("链接失败");
            e.printStackTrace();
        }finally {
            try {
                if (resultSet!=null){
                    resultSet.close();
                }
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

        return map;
    }
}
