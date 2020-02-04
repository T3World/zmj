package com.zmj.pojo.entity;

import java.io.Serializable;

public class CommonValueEntity implements Serializable {

    private static final long serialVersionUID = -1409920373366714605L;

    private  double value;

    private  String time; // 查询的时候，以这个时间为准，进行查询

    public CommonValueEntity() {
        super();
    }

    public CommonValueEntity(Integer value, String time) {
        this.value = value;
        this.time = time;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "{" +
                "value='" + value + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}