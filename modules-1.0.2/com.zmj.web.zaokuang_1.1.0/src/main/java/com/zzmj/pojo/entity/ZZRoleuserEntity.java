package com.zzmj.pojo.entity;

import java.io.Serializable;

public class ZZRoleuserEntity implements Serializable {
	private static final long serialVersionUID = 7059371795549273370L;

	/**
	 * ZZ_RoleUser.Id (主键)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private String id;

	/**
	 * ZZ_RoleUser.Role_Id (角色Id)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private String roleId;

	/**
	 * ZZ_RoleUser.User_Id (用户Id)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private String userId;

	private ZZRoleEntity zzRoleEntity;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ZZRoleEntity getZzRoleEntity() {
		return zzRoleEntity;
	}

	public void setZzRoleEntity(ZZRoleEntity zzRoleEntity) {
		this.zzRoleEntity = zzRoleEntity;
	}

}