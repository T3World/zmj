package com.zzmj.pojo.entity;

import java.io.Serializable;
import java.util.Date;

public class ZZRoleEntity implements Serializable {
	private static final long serialVersionUID = 839330809925764491L;

	/**
	 * ZZ_Role.Id (主键)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private String id;

	/**
	 * ZZ_Role.Role_Id (角色Id)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private String roleId;

	/**
	 * ZZ_Role.Role_Name (角色名字)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private String roleName;

	/**
	 * ZZ_Role.Role_Value (角色值)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private String roleValue;

	/**
	 * ZZ_Role.Role_Des (角色描述)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private String roleDes;

	/**
	 * ZZ_Role.CreatePerson (创建人)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private String createperson;

	/**
	 * ZZ_Role.CreateTime (创建时间)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private Date createtime;

	/**
	 * ZZ_Role.UpdateTime (修改时间)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private Date updatetime;

	/**
	 * ZZ_Role.SortCode (排序码)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private String sortcode;

	/**
	 * ZZ_Role.Status (状态码)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private String status;

	/**
	 * ZZ_Role.IsDel (是否删除,0,未删除,1删除)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private Integer isdel;

	private ZZModuleEntity moduleEntity;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleValue() {
		return roleValue;
	}

	public void setRoleValue(String roleValue) {
		this.roleValue = roleValue;
	}

	public String getRoleDes() {
		return roleDes;
	}

	public void setRoleDes(String roleDes) {
		this.roleDes = roleDes;
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

	public ZZModuleEntity getModuleEntity() {
		return moduleEntity;
	}

	public void setModuleEntity(ZZModuleEntity moduleEntity) {
		this.moduleEntity = moduleEntity;
	}

}