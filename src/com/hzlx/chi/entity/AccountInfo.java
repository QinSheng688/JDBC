package com.hzlx.chi.entity;

import java.io.Serializable;
import java.util.Date;

public class AccountInfo implements Serializable {
    private Integer id;
    private String name;
    private String type;
    private Double money;
    private String remark;
    private Date createTime;
    private Date updateTime;
    private Integer userId;

    public AccountInfo() {
    }

    public AccountInfo(Integer id, String name, String type, Double money, String remark, Date createTime, Date updateTime, Integer userId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.money = money;
        this.remark = remark;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.userId = userId;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * 设置
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取
     * @return money
     */
    public Double getMoney() {
        return money;
    }

    /**
     * 设置
     * @param money
     */
    public void setMoney(Double money) {
        this.money = money;
    }

    /**
     * 获取
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取
     * @return createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取
     * @return updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取
     * @return userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String toString() {
        return "AccountInfo{id = " + id + ", name = " + name + ", type = " + type + ", money = " + money + ", remark = " + remark + ", createTime = " + createTime + ", updateTime = " + updateTime + ", userId = " + userId + "}";
    }
}
