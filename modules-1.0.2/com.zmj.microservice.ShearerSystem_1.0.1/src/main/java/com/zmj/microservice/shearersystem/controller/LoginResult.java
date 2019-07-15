package com.zmj.microservice.shearersystem.controller;

public class LoginResult {
    private String Code;
    private String Error_Msg;
    private UserInfo Data;

    public LoginResult() {
    }

    //get set  ....
    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getError_Msg() {
        return Error_Msg;
    }

    public void setError_Msg(String error_Msg) {
        Error_Msg = error_Msg;
    }

    public UserInfo getData() {
        return Data;
    }

    public void setData(UserInfo data) {
        Data = data;
    }
}
