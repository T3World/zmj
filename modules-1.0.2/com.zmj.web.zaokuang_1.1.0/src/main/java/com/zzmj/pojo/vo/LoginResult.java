package com.zzmj.pojo.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.zzmj.pojo.entity.ZZModuleEntity;
import com.zzmj.pojo.entity.ZZRoleEntity;

import java.io.Serializable;
import java.util.List;

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

    /**
     * 用户所拥有的所有角色名
     * */
    private List<ZZRoleEntity> roles;
    /**
     * 用户所拥有的所有模块值
     * */
    @JSONField(name = "permit")
    private List<ZZModuleEntity> rights;

    public LoginResult() {
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public List<ZZRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<ZZRoleEntity> roles) {
        this.roles = roles;
    }

    public List<ZZModuleEntity> getRights() {
        return rights;
    }

    public void setRights(List<ZZModuleEntity> rights) {
        this.rights = rights;
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

}
