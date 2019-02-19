package com.example.mybatisdemo.pojo;

import java.io.Serializable;

public class HistoryDO implements Serializable {

    private Integer id;
    private String dataName;
    private String dataValue;
    private String valueType;
    private String generateTime;
    private String storeTime;

    public Integer getId() {
        return id;
    }

    public String getDataName() {
        return dataName;
    }

    public String getDataValue() {
        return dataValue;
    }

    public String getValueType() {
        return valueType;
    }

    public String getGenerateTime() {
        return generateTime;
    }

    public String getStoreTime() {
        return storeTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public void setGenerateTime(String generateTime) {
        this.generateTime = generateTime;
    }

    public void setStoreTime(String storeTime) {
        this.storeTime = storeTime;
    }
}
