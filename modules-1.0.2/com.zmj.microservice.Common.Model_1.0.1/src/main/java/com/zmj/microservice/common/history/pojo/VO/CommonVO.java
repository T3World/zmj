package com.zmj.microservice.common.history.pojo.VO;

import java.io.Serializable;
/**
* @author:   umr
* @date:     2019/3/30
*/
public class CommonVO<T> implements Serializable {
    private T value;
    private String time;

    public CommonVO() {
    }

    public CommonVO(T value, String time) {
        this.value = value;
        this.time = time;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
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
        return "CommonVO{" +
                "value=" + value +
                ", time='" + time + '\'' +
                '}';
    }
}
