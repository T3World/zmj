package com.zzmj.pojo.vo;

import java.io.Serializable;
import java.util.List;

public class WorkfaceDeviceInfo implements Serializable {
    /**
     * zz_device.Id (主键)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String id;
    /**
     * zz_workfacedevice.Workface_Id (工作面Id)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String workfaceId;
    /**
     * zz_device.DeviceType_Id (设备类型Id)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String deviceTypeId;
    /**
     * zz_device.Device_Id (设备类型Id)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String deviceId;

    /**
     * zz_device.Device_Name (设备名称)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String deviceName;

    /**
     * zz_device.Device_Alias (设备别名)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String deviceAlias;

    /**
     * zz_device.Device_Firm (设备厂商)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String deviceFirm;

    /**
     * zz_device.Device_Model (设备型号)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String deviceModel;

    /**
     * zz_device.Device_Img (设备图片)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String deviceImg;
    /**
     * zz_workfacedevice.Device_Count (设备数量)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String deviceCount;

    /**
     * zz_workfacedevice.Device_Codes (设备编码)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String deviceCodes;


    /**
     * zz_device.SortCode (排序码)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String sortCode;

    /**
     * zz_device.UpdateTime (更新时间)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String updateTime;

    /**
     * zz_device.isDel (删除标识(0,未删除1:删除）)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String isDel;

    /**
     * zz_device.Deivce_Attribute (设备参数)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private List<AttributeInnerAttributeDevice> deviceAttribute;

    public WorkfaceDeviceInfo() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceAlias() {
        return deviceAlias;
    }

    public void setDeviceAlias(String deviceAlias) {
        this.deviceAlias = deviceAlias;
    }

    public String getDeviceFirm() {
        return deviceFirm;
    }

    public void setDeviceFirm(String deviceFirm) {
        this.deviceFirm = deviceFirm;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceImg() {
        return deviceImg;
    }

    public void setDeviceImg(String deviceImg) {
        this.deviceImg = deviceImg;
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

    public List<AttributeInnerAttributeDevice> getDeviceAttribute() {
        return deviceAttribute;
    }

    public void setDeviceAttribute(List<AttributeInnerAttributeDevice> deviceAttribute) {
        this.deviceAttribute = deviceAttribute;
    }

    public String getWorkfaceId() {
        return workfaceId;
    }

    public void setWorkfaceId(String workfaceId) {
        this.workfaceId = workfaceId;
    }

    public String getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(String deviceCount) {
        this.deviceCount = deviceCount;
    }

    public String getDeviceCodes() {
        return deviceCodes;
    }

    public void setDeviceCodes(String deviceCodes) {
        this.deviceCodes = deviceCodes;
    }
}
