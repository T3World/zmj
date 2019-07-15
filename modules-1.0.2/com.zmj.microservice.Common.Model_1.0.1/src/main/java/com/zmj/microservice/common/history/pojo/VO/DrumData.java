package com.zmj.microservice.common.history.pojo.VO;

import java.util.List;
/**
* @author:         umr
* @date:           2019/3/30
*/
public class DrumData<T> {
    private String drumType;
    private List<T> data;

    public DrumData() {
    }

    public DrumData(String drumType) {
        this.drumType = drumType;
    }

    public DrumData(String drumType, List<T> data) {
        this.drumType = drumType;
        this.data = data;
    }

    public String getDrumType() {
        return drumType;
    }

    public void setDrumType(String drumType) {
        this.drumType = drumType;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "DrumData{" +
                "drumType='" + drumType + '\'' +
                ", data=" + data +
                '}';
    }
}
