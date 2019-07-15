package com.zmj.pojo.entity;

import java.io.Serializable;

public class AutoRunningEntity implements Serializable {

    private static final long serialVersionUID = 9002779286480970530L;

    private  boolean value;

    private  String time;

    public AutoRunningEntity() {
        super();
    }

    public AutoRunningEntity(boolean value, String time) {
        this.value = value;
        this.time = time;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
