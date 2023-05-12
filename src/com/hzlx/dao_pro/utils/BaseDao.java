package com.hzlx.dao_pro.utils;

import com.hzlx.chi.utils.PropertiesUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseDao <T>{
    private static String URL=null;
    private static String USERNAME=null;
    private static String PASSWORD=null;
    private static String driver=null;
    private Connection connection=null;
    private PreparedStatement preparedStatement=null;
    private ResultSet resultSet=null;

    static {
        init();
        try {

            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //加载外部配置到程序中
    //初始化方法
    private static void init(){
        //加载jdbc配置文件
        PropertiesUtil.load("jdbc.properties");
        URL=PropertiesUtil.getValue("jdbc.url");
        driver=PropertiesUtil.getValue("jdbc.driver");
        USERNAME=PropertiesUtil.getValue("jdbc.userName");
        PASSWORD=PropertiesUtil.getValue("jdbc.password");


    }

    //通用的增删改方法
    //sql  需要执行的sql,包括？占位符
    //objs ？代表的参数列表
    public int executeUpdate(String sql,Object... objs){
        int rows=0;
        try {
            //获取数据库链接
            connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);
            //准备sql预执行器
            preparedStatement=connection.prepareStatement(sql);
            for (int i = 0; i < objs.length; i++) {
                preparedStatement.setObject(i+1,objs[i]);
            }
            rows= preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("数据库链接异常");
            e.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rows;
    }
    //查询封装成一个对象
    public T selectOne(String sql,Class clazz,Object... objs){
        Object obj=null;
        try {
            connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);
            preparedStatement=connection.prepareStatement(sql);
            for (int i = 0; i < objs.length; i++) {
                preparedStatement.setObject(i+1,objs[i]);

            }
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                //从结果集解析数据，并封装成对象
                try {
                    obj = clazz.newInstance();
                    //get到所有属性
                    Field[] fields = clazz.getDeclaredFields();
                    for (int i = 0; i < fields.length; i++) {
                        String fieldName = fields[i].getName();
                        String oldChar = fieldName.substring(0, 1);
                        String methodName="set"+oldChar.toUpperCase()+fieldName.substring(1);
                        Method[] methods = clazz.getMethods();
                        Method method = clazz.getMethod(methodName, fields[i].getType());
                        method.invoke(obj,resultSet.getObject(i+1));


                    }

                } catch (InstantiationException e) {
                    System.out.println("实例化对象失败");
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchMethodException e) {
                    System.out.println("没有set方法");
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            System.out.println("获取数据库链接失败");
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
        return (T)obj;
    }

    /*
    * 查询多个结果，对象
    * 返回集合
    * */
    public List<T> selectListForObject(String sql,Class clazz, Object... objs){
        //存放结果的集合
        List<T> list=null;
        try {
            connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);
            preparedStatement=connection.prepareStatement(sql);
            //替换占位符
            for (int i = 0; i < objs.length; i++) {
                preparedStatement.setObject(i+1,objs[i]);

            }
            resultSet= preparedStatement.executeQuery();
            list=new ArrayList<>();
            //遍历结果集，封装对象并放到list中
           while (resultSet.next()){
               //通过反射创建对象
               Object obj = clazz.newInstance();
               //获取所有字段
               Field[] fields = clazz.getDeclaredFields();
               //获取set方法
               for (int i = 0; i < fields.length; i++) {
                   String name = fields[i].getName();
                   String firstChar=name.substring(0,1).toUpperCase();
                   //获取方法名
                   String methodName = "set" + firstChar + name.substring(1);
                   //根据方法名获取对象
                   Method method = clazz.getMethod(methodName, fields[i].getType());
                   //执行set方法赋值
                   method.invoke(obj,resultSet.getObject(i+1));
               }
               list.add((T)obj);
           }
        } catch (SQLException e) {
            System.out.println("获取链接失败");
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            System.out.println("没有set方法");
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            System.out.println("赋值失败");
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

        return list;
    }

    //查单条数据把结果封装到map集合中
    //返回结果集
    public Map<String,Object> selectOneForMap(String sql,Object... objs){
        Map<String,Object> map=null;
        try {
            connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);
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

    //查多条数据，装list中
    public List<Map<String,Object>> selectListForMap(String sql,Object... objs){
        //装结果集的容器
        List<Map<String,Object>> list=null;
        try {
            connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);
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
}
