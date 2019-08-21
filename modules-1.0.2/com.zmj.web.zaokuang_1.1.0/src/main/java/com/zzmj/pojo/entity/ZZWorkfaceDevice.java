package com.zzmj.pojo.entity;

import java.io.Serializable;

public class ZZWorkfaceDevice implements Serializable {
	/**
	 * zz_workfacedevice.Id (Id主键)
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
	 * zz_workfacedevice.Device_Id (设备Id)
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	private String deviceId;

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
	 * zz_workfacedevice.SortCode (排序码)
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	private String sortCode;

	/**
	 * zz_workfacedevice.UpdateTime (更新时间)
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	private String updateTime;

	/**
	 * zz_workfacedevice.isDel (删除标志（0，未删除，1：删除）)
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	private String isDel;
	
	/**
	 * 设备类型id
	 */
	private String deviceTypeId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWorkfaceId() {
		return workfaceId;
	}

	public void setWorkfaceId(String workfaceId) {
		this.workfaceId = workfaceId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
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

	public String getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(String deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}


	
}