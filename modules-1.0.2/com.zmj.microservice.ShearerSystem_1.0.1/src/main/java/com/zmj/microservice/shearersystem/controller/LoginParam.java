package com.zmj.microservice.shearersystem.controller;

import java.io.Serializable;

public class LoginParam implements Serializable {
    private String userAccount;
    private String userPassword;


    public LoginParam(String userAccount, String userPassword) {
        this.userAccount = userAccount;
        this.userPassword = userPassword;
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
}
