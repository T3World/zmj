package com.zmj.microservice.common.history.pojo.VO;

import java.util.List;
/**
* @author:         umr
* @date:           2019/3/30
*/
public class FilterDataVO<T> {

    private  String  pressureType;

    private List<T> data;

    public FilterDataVO() {
        super();
    }

    public FilterDataVO(String pressureType, List<T> data) {
        this.pressureType = pressureType;
        this.data = data;
    }

    public String getPressureType() {
        return pressureType;
    }

    public void setPressureType(String pressureType) {
        this.pressureType = pressureType;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "FilterDataVO{" +
                "pressureType='" + pressureType + '\'' +
                ", data=" + data +
                '}';
    }
}
