package com.hzlx.dao_pro.dao;

import com.hzlx.dao_pro.entity.BusinessInfo;

import java.util.List;
import java.util.Map;

public interface BusinessInfoDao {
    BusinessInfo getBusinessInfoUserNameAndPwd(String userName, String password);
    //获取所有商家列表
    List<BusinessInfo> getBusinessInfoAll();
    //获取一共多少商家
    Integer countBusinessInfoAll();

    //根据商家id获取商家名
    Map<String,Object> getBusinessInfoForNameById(Integer id);

    List<Map<String,Object>> getBusinessInfoForMap();
}
