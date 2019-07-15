package com.zzmj.pojo.entity;

import java.io.Serializable;

public class ZZRolemoduleEntity implements Serializable {
    private static final long serialVersionUID = 5811167245296941765L;


	/**
	 * ZZ_RoleModule.Id (主键)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private String id;

	/**
	 * ZZ_RoleModule.Role_Id (角色Id)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private String roleId;

	/**
	 * ZZ_RoleModule.Module_Id (模块Id)
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	private String moduleId;

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

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
}