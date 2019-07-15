package com.zzmj.pojo.vo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
/**
* @description:    java类作用描述
* @author:         umr
* @date:           2019/5/6
*/
public class LoginResult implements Serializable {

    /**
     * @Fields: {todo}(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = -6855452478175423309L;

    @JSONField(name = "User_Id")
    private String userId;

    @JSONField(name = "User_Name")
    private String userName;

    @JSONField(name = "User_Account")
    private String userAccount;

    @JSONField(name = "User_Tel")
    private String userTel;

    @JSONField(name = "User_Job")
    private String userJob;

    @JSONField(name = "Org_Id")
    private String orgId;

    @JSONField(name = "Status")
    private String status;

    @JSONField(name = "Edit_Flag")
    private Boolean isAdmin;

    @JSONField(name = "Login_Time")
    private Long loginTime;

    @JSONField(name = "Token")
    private String token;



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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long l) {
        this.loginTime = l;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
