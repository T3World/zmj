package com.zmj.web.microservice.zaokuangContract.entity;

import java.io.Serializable;
import java.util.Date;

public class ZZUserEntity implements Serializable {
    /**
     * @Fields: {todo}(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = -7245327364104940268L;

    /**
     * ZZ_User.Id (主键)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String id;

    /**
     * ZZ_User.User_Id (用户Id)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String userId;

    /**
     * ZZ_User.User_Name (用户名)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String userName;

    /**
     * ZZ_User.User_Account (用户账号)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String userAccount;

    /**
     * ZZ_User.User_Tel(用户电话)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String userTel;

    /**
     * ZZ_User.User_Password (用户密码)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String userPassword;

    /**
     * ZZ_User.User_Job (用户职位)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String userJob;

    /**
     * ZZ_User.Org_Id (所属组织机构Id)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String orgId;

    /**
     * ZZ_User.CreateTime (创建时间)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private Date createtime;

    /**
     * ZZ_User.UpdateTime (修改时间)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private Date updatetime;

    /**
     * ZZ_User.Status (状态码)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String status;

    /**
     * ZZ_User.IsDel (是否删除;0,未删除;1,删除)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private Integer isdel;
    private String orgName;

    private ZZOrgEntity zzOrgEntity;

    private ZZRoleuserEntity zzRoleuserEntity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserJob() {
        return userJob;
    }

    public void setUserJob(String userJob) {
        this.userJob = userJob;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public ZZOrgEntity getZzOrgEntity() {
        return zzOrgEntity;
    }

    public void setZzOrgEntity(ZZOrgEntity zzOrgEntity) {
        this.zzOrgEntity = zzOrgEntity;
    }

    public ZZRoleuserEntity getZzRoleuserEntity() {
        return zzRoleuserEntity;
    }

    public void setZzRoleuserEntity(ZZRoleuserEntity zzRoleuserEntity) {
        this.zzRoleuserEntity = zzRoleuserEntity;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

}