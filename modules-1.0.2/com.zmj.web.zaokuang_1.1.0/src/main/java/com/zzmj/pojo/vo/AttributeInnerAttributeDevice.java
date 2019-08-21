package com.zzmj.pojo.vo;

import java.io.Serializable;
/**
 * @author umr
 * @date 2019/6/28
 */
public class AttributeInnerAttributeDevice implements Serializable {

    /**
     * zz_deviceattribute.Id (Id主键)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String id;

    /**
     * zz_deviceattribute.Attribute_Id (属性Id)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String attributeId;

    /**
     * zz_deviceattribute.Attribute_Name (属性名称)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String attributeName;

    /**
     * zz_deviceattribute.Attribute_Alias (属性别名)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String attributeAlias;

    /**
     * zz_deviceattribute.Attribute_Unit (属性单位)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String attributeUnit;

    /**
     * zz_deviceattribute.DeviceType_Id (设备类型Id)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String deviceTypeId;

    /**
     * zz_deviceattribute.SortCode (排序码)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String sortCode;

    /**
     * zz_deviceattribute.UpdateTime (更新时间)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String updateTime;

    /**
     * zz_deviceattribute.isDel (删除标志（0,未删除，1，删除）)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String isDel;

    private String deviceId;
    private String attributeValue;

    public AttributeInnerAttributeDevice() {
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

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeAlias() {
        return attributeAlias;
    }

    public void setAttributeAlias(String attributeAlias) {
        this.attributeAlias = attributeAlias;
    }

    public String getAttributeUnit() {
        return attributeUnit;
    }

    public void setAttributeUnit(String attributeUnit) {
        this.attributeUnit = attributeUnit;
    }

    public String getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(String deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
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
