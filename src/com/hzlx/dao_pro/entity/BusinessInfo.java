package com.hzlx.dao_pro.entity;



import java.io.Serializable;

import java.util.Date;


/**
* 商家表
* @TableName t_business_info
*/
public class BusinessInfo implements Serializable {

    /**
    * 编号（主键）
    */

    private Integer id;
    /**
    * 商家编号
    */

    private String busNo;
    /**
    * 店名
    */

    private String name;
    /**
    * 用户名（账号）
    */

    private String userName;
    /**
    * 密码
    */

    private String password;
    /**
    * 入驻时间
    */

    private Date createTime;
    /**
    * 营业状态 0 打烊 1 营业
    */

    private Integer jobType;
    /**
    * 评分 5分制
    */

    private Double score;
    /**
    * 店铺状态 0注销 1正常 2整顿中 3装修中
    */

    private Integer status;

    /**
    * 编号（主键）
    */
    public void setId(Integer id){
    this.id = id;
    }

    /**
    * 商家编号
    */
    public void setBusNo(String busNo){
    this.busNo = busNo;
    }

    /**
    * 店名
    */
    public void setName(String name){
    this.name = name;
    }

    /**
    * 用户名（账号）
    */
    public void setUserName(String userName){
    this.userName = userName;
    }

    /**
    * 密码
    */
    public void setPassword(String password){
    this.password = password;
    }

    /**
    * 入驻时间
    */
    public void setCreateTime(Date createTime){
    this.createTime = createTime;
    }

    /**
    * 营业状态 0 打烊 1 营业
    */
    public void setJobType(Integer jobType){
    this.jobType = jobType;
    }

    /**
    * 评分 5分制
    */
    public void setScore(Double score){
    this.score = score;
    }

    /**
    * 店铺状态 0注销 1正常 2整顿中 3装修中
    */
    public void setStatus(Integer status){
    this.status = status;
    }


    /**
    * 编号（主键）
    */
    public Integer getId(){
    return this.id;
    }

    /**
    * 商家编号
    */
    public String getBusNo(){
    return this.busNo;
    }

    /**
    * 店名
    */
    public String getName(){
    return this.name;
    }

    /**
    * 用户名（账号）
    */
    public String getUserName(){
    return this.userName;
    }

    /**
    * 密码
    */
    public String getPassword(){
    return this.password;
    }

    /**
    * 入驻时间
    */
    public Date getCreateTime(){
    return this.createTime;
    }

    /**
    * 营业状态 0 打烊 1 营业
    */
    public Integer getJobType(){
    return this.jobType;
    }

    /**
    * 评分 5分制
    */
    public Double getScore(){
    return this.score;
    }

    /**
    * 店铺状态 0注销 1正常 2整顿中 3装修中
    */
    public Integer getStatus(){
    return this.status;
    }

    @Override
    public String toString() {
        return "BusinessInfo{" +
                "id=" + id +
                ", busNo='" + busNo + '\'' +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", jobType=" + jobType +
                ", score=" + score +
                ", status=" + status +
                '}';
    }
}
