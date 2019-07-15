package com.zzmj.service;

import com.zmj.web.microservice.zaokuangContract.Vo.SysResult;
import com.zmj.web.microservice.zaokuangContract.entity.RoleModuleResult;
import com.zzmj.util.exception.DoSqlFailedException;
import com.zzmj.util.exception.EmptyResultException;

public interface ZZRoleModuleService {
	SysResult selectModuleByRoleId(String roleId) throws EmptyResultException;

	int deleteModuleByRoleId(String roleId);

	int addModules(RoleModuleResult result) throws DoSqlFailedException;

	SysResult updateModules(RoleModuleResult result) throws DoSqlFailedException;

	SysResult getRoleModuleTreeData(String roleId) throws EmptyResultException;
}
