package com.zmj.electronic.contract.pojo.DO;

import java.io.Serializable;

public class SysResult implements Serializable {

    private static final long serialVersionUID = 6400275790297047107L;

    private  int errcode;

    private  String errmsg;

    private  Object data;

    public SysResult() {
        super();
    }

    public SysResult(int errcode, String errmsg, Object data) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.data = data;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
