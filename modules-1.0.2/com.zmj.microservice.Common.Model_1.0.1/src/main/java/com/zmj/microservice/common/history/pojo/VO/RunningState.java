package com.zmj.microservice.common.history.pojo.VO;

import java.io.Serializable;
import java.util.List;
/**
* @author:         umr
* @date:           2019/3/30
*/
public class RunningState<T> implements Serializable{
    private String state;
    private List<T> data;

    public RunningState() {
    }

    public RunningState(String state, List data) {
        this.state = state;
        this.data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RunningState{" +
                "state='" + state + '\'' +
                ", data=" + data +
                '}';
    }
}
