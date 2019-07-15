package com.zmj.microservice.shearersystem.controller;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private String User_Id;
    private String User_Name;
    private String User_Job;
    private String User_Account;
    private String User_Tel;
    private Long Login_Time;
    private String Org_Id;
    private Boolean Edit_Flag;

    public UserInfo() {
    }

    //get set ...
    public String getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(String user_Id) {
        User_Id = user_Id;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public String getUser_Job() {
        return User_Job;
    }

    public void setUser_Job(String user_Job) {
        User_Job = user_Job;
    }

    public String getUser_Account() {
        return User_Account;
    }

    public void setUser_Account(String user_Account) {
        User_Account = user_Account;
    }

    public String getUser_Tel() {
        return User_Tel;
    }

    public void setUser_Tel(String user_Tel) {
        User_Tel = user_Tel;
    }

    public Long getLogin_Time() {
        return Login_Time;
    }

    public void setLogin_Time(Long login_Time) {
        Login_Time = login_Time;
    }

    public String getOrg_Id() {
        return Org_Id;
    }

    public void setOrg_Id(String org_Id) {
        Org_Id = org_Id;
    }

    public Boolean getEdit_Flag() {
        return Edit_Flag;
    }

    public void setEdit_Flag(Boolean edit_Flag) {
        Edit_Flag = edit_Flag;
    }
}
