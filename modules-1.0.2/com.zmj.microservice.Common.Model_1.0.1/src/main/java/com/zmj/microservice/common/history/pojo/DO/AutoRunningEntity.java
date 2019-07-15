package com.zmj.microservice.common.history.pojo.DO;

import java.io.Serializable;

/**
 * @author umr,胡实现
 * @date 2019-03-30 14:05:41
 */
public class AutoRunningEntity implements Serializable {

    private static final long serialVersionUID = 9002779286480970530L;

    private  Boolean value;

    private  String time;

    public AutoRunningEntity() {
        super();
    }

    public AutoRunningEntity(boolean value, String time) {
        this.value = value;
        this.time = time;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
