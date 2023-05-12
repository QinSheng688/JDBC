package com.hzlx.dao_pro.dao.impl;

import com.hzlx.dao_pro.dao.BusinessInfoDao;
import com.hzlx.dao_pro.entity.BusinessInfo;
import com.hzlx.dao_pro.utils.BaseDao;

import java.util.List;
import java.util.Map;

public class BusinessInfoDaoImpl extends BaseDao<BusinessInfo>implements BusinessInfoDao {
    @Override
    public BusinessInfo getBusinessInfoUserNameAndPwd(String userName, String password) {
        String sql="select * from t_business_info where user_name=? and `password`=?";
        return selectOne(sql,BusinessInfo.class,userName,password);
    }

    @Override
    public List<BusinessInfo> getBusinessInfoAll() {
        String sql="select * from t_business_info";
        return selectListForObject(sql,BusinessInfo.class);
    }

    @Override
    public Integer countBusinessInfoAll() {
        String sql="select count(1) as count from t_business_info";
        Map<String, Object> map = selectOneForMap(sql);
        Integer countNum = Integer.parseInt(map.get("count").toString());
        return countNum;
    }

    @Override
    public Map<String, Object> getBusinessInfoForNameById(Integer id) {
        String sql="select `name` from t_business_info where id=?";
        return selectOneForMap(sql,id);
    }

    @Override
    public List<Map<String, Object>> getBusinessInfoForMap() {
        String sql="select * from t_business_info";
        return selectListForMap(sql);
    }
}
