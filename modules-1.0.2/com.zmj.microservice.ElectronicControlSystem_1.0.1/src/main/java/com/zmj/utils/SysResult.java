package com.zmj.utils;

import java.io.Serializable;

/**
 * @ClassName: SysResult
 * @Description: 后台管理系统结果的封装类
 * @author umr
 * @date 2018年12月8日
 *
 */
public class SysResult implements Serializable {

    private static final long serialVersionUID = -1995443459132452577L;

    private int errcode;

    private String errmsg;

    private Object data;

    public SysResult() {
        super();
    }

    public SysResult(Integer errcode, String errmsg, Object data) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.data = data;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
