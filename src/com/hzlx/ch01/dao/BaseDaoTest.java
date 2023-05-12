package com.hzlx.ch01.dao;

import com.hzlx.ch01.dao.impl.UserDaoImpl;

public class BaseDaoTest {
    public static void main(String[] args) {
//        String sql="insert into account (name,money) value(?,?)";
//        BaseDao baseDao=new BaseDao();
//        int rows=baseDao.executeUpdate(sql,"王五",1000);
//        if (rows>0){
//            System.out.println("成功");
//        }else {
//            System.out.println("失败");
//        }
//
//        String sql2="delete from account where name=?";
//        int i= baseDao.executeUpdate(sql2,"王五");
//        if (i>0){
//            System.out.println("删除成功");
//        }else {
//            System.out.println("失败");
        UserDao userDao=new UserDaoImpl();
        userDao.addUser("张",25);



        }
    }

