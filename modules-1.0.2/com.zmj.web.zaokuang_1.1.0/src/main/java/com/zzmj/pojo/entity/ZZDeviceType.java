package com.zzmj.pojo.entity;

import java.io.Serializable;

public class ZZDeviceType implements Serializable {
	/**
	 * zz_devicetype.Id (主键)
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	private String id;

	/**
	 * zz_devicetype.DeviceType_Id (设备类型Id)
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	private String deviceTypeId;

	/**
	 * zz_devicetype.DeviceType_Name (设备名称)
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	private String deviceTypeName;

	/**
	 * zz_devicetype.DeviceType_Alias (设备别名)
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	private String deviceTypeAlias;

	/**
	 * zz_devicetype.SortCode (排序码)
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	private String sortCode;

	/**
	 * zz_devicetype.UpdateTime (更新时间)
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	private String updateTime;

	/**
	 * zz_devicetype.isDel (删除标志（0，未删除，1，删除）)
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	private String isDel;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(String deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public String getDeviceTypeName() {
		return deviceTypeName;
	}

	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}

	public String getDeviceTypeAlias() {
		return deviceTypeAlias;
	}

	public void setDeviceTypeAlias(String deviceTypeAlias) {
		this.deviceTypeAlias = deviceTypeAlias;
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

}