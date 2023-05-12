package com.hzlx.chi.dao.impl;

import com.hzlx.chi.dao.AccountInfoDao;
import com.hzlx.chi.entity.AccountInfo;
import com.hzlx.chi.utils.BaseDao;

public class AccountInfoDaoImpl extends BaseDao<AccountInfo>implements AccountInfoDao {
    //添加账户信息

    @Override
    public int addAccountInfo(AccountInfo accountInfo) {
        String sql="insert into t_account_info value(null,?,?,?,?,now(),now(),?)";
        return executeUpdate(sql,
                accountInfo.getName(),
                accountInfo.getType(),
                accountInfo.getMoney(),
                accountInfo.getRemark(),
                accountInfo.getUserId()
                );
    }
}
