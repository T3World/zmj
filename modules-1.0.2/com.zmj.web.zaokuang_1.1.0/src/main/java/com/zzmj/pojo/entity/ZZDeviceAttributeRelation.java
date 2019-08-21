package com.zzmj.pojo.entity;

import java.io.Serializable;

public class ZZDeviceAttributeRelation implements Serializable {
    private String id;
    private String attributeId;
    private String deviceId;
    private String attributeValue;

    public ZZDeviceAttributeRelation() {
    }

    public ZZDeviceAttributeRelation(String id, String attributeId, String deviceId, String attributeValue) {
        this.id = id;
        this.attributeId = attributeId;
        this.deviceId = deviceId;
        this.attributeValue = attributeValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }
}
