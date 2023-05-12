package com.hzlx.ch01.dao.impl;

import com.hzlx.ch01.dao.BaseDao;
import com.hzlx.ch01.dao.UserDao;

public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public int addUser(String name, Integer age) {
        String sql="insert into account (name,money) value(?,?)";
        return executeUpdate(sql, name,age);
    }
}
