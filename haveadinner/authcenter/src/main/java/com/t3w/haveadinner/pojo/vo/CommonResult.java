package com.t3w.haveadinner.pojo.vo;

public class CommonResult<T> {

    private final int OK = 200;

    private Integer errcode;
    private String errmsg;
    private T data;

    public CommonResult() {
    }

    public CommonResult(Integer errcode) {
        this.errcode = errcode;
    }

    public CommonResult(T data) {
        this.errcode = OK;
        this.errmsg = "ok";
        this.data = data;
    }

    public CommonResult(Integer errcode, String errmsg, T data) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.data = data;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
