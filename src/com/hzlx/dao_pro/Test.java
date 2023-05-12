package com.hzlx.dao_pro;

import com.hzlx.dao_pro.dao.BusinessInfoDao;
import com.hzlx.dao_pro.dao.impl.BusinessInfoDaoImpl;
import com.hzlx.dao_pro.entity.BusinessInfo;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        BusinessInfoDao businessInfoDao=new BusinessInfoDaoImpl();
//        BusinessInfo businessInfo=businessInfoDao.getBusinessInfoUserNameAndPwd("xjcwj","123456");
//        System.out.println(businessInfo);


        List<BusinessInfo> businessInfoall=businessInfoDao.getBusinessInfoAll();
        System.out.println(businessInfoall);

    }
}
