package com.zmj.microservice.common.history.pojo.VO;

import java.io.Serializable;
import java.util.List;

/**
 * @description 封装多架支架压力数据
 * @author umr
 * @date 2019/6/6
 */
public class PressureData implements Serializable {
    private static final long serialVersionUID = 2L;
    private String scuNo;
    private List data;

    public PressureData() {
    }

    public PressureData(String scuNo, List data) {
        this.scuNo = scuNo;
        this.data = data;
    }

    public String getScuNo() {
        return scuNo;
    }

    public void setScuNo(String scuNo) {
        this.scuNo = scuNo;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
