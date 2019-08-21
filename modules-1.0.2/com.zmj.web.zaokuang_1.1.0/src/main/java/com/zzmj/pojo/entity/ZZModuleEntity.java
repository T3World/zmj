package com.zzmj.pojo.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ZZModuleEntity implements Serializable {
	private static final long serialVersionUID = -3983211087236674481L;

	/**
	 * ZZ_Module.Id (主键)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private String id;

	/**
	 * ZZ_Module.M_Id (模块Id)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private String mId;

	/**
	 * ZZ_Module.M_PId (模块父Id)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private String mPid;

	/**
	 * ZZ_Module.M_Name (模块名字)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private String mName;

	/**
	 * ZZ_Module.M_Value (模块值)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private String mValue;

	/**
	 * ZZ_Module.M_Url (模块Url)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private String mUrl;

	/**
	 * ZZ_Module.M_Icon (模块图标)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private String mIcon;

	/**
	 * ZZ_Module.M_Des (模块描述)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private String mDes;

	/**
	 * ZZ_Module.CreatePerson (创建人)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private String createperson;

	/**
	 * ZZ_Module.CreateTime (创建时间)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private Date createtime;

	/**
	 * ZZ_Module.UpdateTime (修改时间)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private Date updatetime;

	/**
	 * ZZ_Module.SortCode (排序码)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private String sortcode;

	/**
	 * ZZ_Module.Status (状态码)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private String status;

	/**
	 * 子模块
	 * */
	@JSONField(name = "modules")
	private List<ZZModuleEntity> children;

	private ZZRolemoduleEntity zzRolemoduleEntity;

	/**
	 * ZZ_Module.IsDel (是否删除;0表示没有被删除,1表示被删除)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private Integer isdel;

	private Boolean checked = false;

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmPid() {
		return mPid;
	}

	public void setmPid(String mPid) {
		this.mPid = mPid;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmValue() {
		return mValue;
	}

	public void setmValue(String mValue) {
		this.mValue = mValue;
	}

	public String getmUrl() {
		return mUrl;
	}

	public void setmUrl(String mUrl) {
		this.mUrl = mUrl;
	}

	public String getmIcon() {
		return mIcon;
	}

	public void setmIcon(String mIcon) {
		this.mIcon = mIcon;
	}

	public String getmDes() {
		return mDes;
	}

	public void setmDes(String mDes) {
		this.mDes = mDes;
	}

	public String getCreateperson() {
		return createperson;
	}

	public void setCreateperson(String createperson) {
		this.createperson = createperson;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getSortcode() {
		return sortcode;
	}

	public void setSortcode(String sortcode) {
		this.sortcode = sortcode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public ZZRolemoduleEntity getZzRolemoduleEntity() {
		return zzRolemoduleEntity;
	}

	public void setZzRolemoduleEntity(ZZRolemoduleEntity zzRolemoduleEntity) {
		this.zzRolemoduleEntity = zzRolemoduleEntity;
	}

    public List<ZZModuleEntity> getChildren() {
        return children;
    }

    public void setChildren(List<ZZModuleEntity> children) {
        this.children = children;
    }
}