package com.zmj.web.microservice.zaokuangContract.Vo;

import com.alibaba.fastjson.annotation.JSONField;

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
    @JSONField(name = "Code")
    private String code;
    @JSONField(name = "Error_Msg")
    private String errorMsg;
    @JSONField(name = "Data")
    private Object data;

    public SysResult() {
        super();
    }

    public SysResult(String code) {
        super();
        this.code = code;
    }

    public SysResult(String code, String errorMsg) {
        super();
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public SysResult(String code, String errorMsg, Object data) {
        super();
        this.code = code;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
