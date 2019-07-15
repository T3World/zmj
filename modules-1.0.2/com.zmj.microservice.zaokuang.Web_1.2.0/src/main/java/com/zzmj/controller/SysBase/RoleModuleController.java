package com.zzmj.controller.SysBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zmj.web.microservice.zaokuangContract.SysBase.RoleModuleContract;
import com.zmj.web.microservice.zaokuangContract.Vo.SysResult;
import com.zmj.web.microservice.zaokuangContract.entity.RoleModuleResult;
import com.zzmj.service.ZZRoleModuleService;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.exception.DoSqlFailedException;

/**
 * @ClassName: RoleModuleController
 * @Description: 提供角色权限管理
 * @author umr
 * @date 2018年12月27日
 *
 */
@RestController
public class RoleModuleController implements RoleModuleContract {
	@Autowired
	private ZZRoleModuleService service;

	@Override
	public SysResult selectModuleByRoleId(@RequestParam(name = "roleId", required = true) String roleId) {
		try {
			return service.selectModuleByRoleId(roleId);
		} catch (DoSqlFailedException e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	@Override
	public SysResult permitRoleModules(RoleModuleResult result) {
		// 更新结果的条数
		try {
			return service.updateModules(result);
		} catch (DoSqlFailedException e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	@Override
	public SysResult getRoleModuleTreeData(String roleId) {
		try {
			return service.getRoleModuleTreeData(roleId);
		} catch (DoSqlFailedException e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}
}
