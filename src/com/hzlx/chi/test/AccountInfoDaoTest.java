package com.hzlx.chi.test;

import com.hzlx.chi.dao.AccountInfoDao;
import com.hzlx.chi.dao.impl.AccountInfoDaoImpl;
import com.hzlx.chi.entity.AccountInfo;

public class AccountInfoDaoTest {
    public static void main(String[] args) {
        AccountInfoDao accountInfoDao=new AccountInfoDaoImpl();
        AccountInfo accountInfo=new AccountInfo();
        accountInfo.setName("李四");
        accountInfo.setType("中国银行");
        accountInfo.setMoney(1500.0);
        accountInfo.setRemark("加班费");
        accountInfo.setUserId(2);

        int rows = accountInfoDao.addAccountInfo(accountInfo);
        System.out.println(rows>0?"新增成功":"新增失败");
    }
}
