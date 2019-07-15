package com.zmj.microservice.common.history.pojo.VO;

import com.zmj.microservice.common.history.cenum.ResponseCodeEnum;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
* @author:         umr
* @date:           2019/3/30
*/
public class SysResult<T> implements Serializable {
    private static final long serialVersionUID = 4080305904020471390L;
    private Integer errcode;
    private String errmsg;
    private List<T> data;


    public SysResult() {
    }

    public SysResult(List<T> data) {
        this.errcode = ResponseCodeEnum.OK.getValue();
        this.errmsg = "ok";
        this.data = data;
    }

    public SysResult(ResponseCodeEnum errcode, String errmsg) {
        this.errcode = errcode.getValue();
        this.errmsg = errmsg;
        this.data = new LinkedList<>();
    }

    public SysResult(ResponseCodeEnum errcode, String errmsg, List<T> data) {
        this.errcode = errcode.getValue();
        this.errmsg = errmsg;
        this.data = data;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public List<T>getData() {
        return data;
    }

    public void setErrcode(ResponseCodeEnum errcode) {
        this.errcode = errcode.getValue();
    }
    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SysResult{" +
                "errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                ", data=" + data +
                '}';
    }
}

