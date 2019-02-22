package com.zmj.microservice.SupportPressureService.pojo.VO;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class SysResult<T> implements Serializable {
    private int errcode;
    private String errmsg;
    private List<T> data;


    public SysResult() {
    }

    public SysResult(List<T> data) {
        this.errcode = 200;
        this.errmsg = "ok";
        this.data = data;
    }

    public SysResult(int errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.data = new LinkedList<>();
    }

    public SysResult(int errcode, String errmsg, List<T> data) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.data = data;
    }

    public int getErrcode() {
        return errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public List getData() {
        return data;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public void setData(List data) {
        this.data = data;
    }
}
