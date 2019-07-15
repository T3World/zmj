package com.zzmj.controller.SysBase;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zmj.web.microservice.zaokuangContract.SysBase.UserContract;
import com.zmj.web.microservice.zaokuangContract.Vo.SysResult;
import com.zmj.web.microservice.zaokuangContract.entity.ZZUserEntity;
import com.zzmj.service.impl.ZZModuleServiceImol;
import com.zzmj.service.impl.ZZRoleServiceImpl;
import com.zzmj.service.impl.ZZRoleUserServiceImpl;
import com.zzmj.service.impl.ZZUserServiceImpl;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.exception.DoSqlFailedException;

/**
 *
 * @author hushixian 用户管理controller
 *
 */
@Controller
public class UserController implements UserContract {

	@Autowired
	private ZZUserServiceImpl userServiceImpl;

	@Autowired
	private ZZRoleUserServiceImpl roleUserServiceImpl;

	@Autowired
	private ZZRoleServiceImpl roleServiceImpl;

	@Autowired
	private ZZModuleServiceImol zzModuleServiceImol;

	/**
	 * 新增方法
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@Override
	@RequestMapping("/addUserEntity")
	@ResponseBody
	public SysResult addUserEntity(ZZUserEntity userEntity) {
		try {
			return this.userServiceImpl.addZZUserEntity(userEntity);
		} catch (DoSqlFailedException e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	/**
	 * 修改用户信息的方法。
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@Override
	@RequestMapping("/updateUserEntity")
	@ResponseBody
	public SysResult updateUserEntity(ZZUserEntity userEntity) {
		try {
			return this.userServiceImpl.updateZZUserEntity(userEntity, userEntity.getUserId());
		} catch (DoSqlFailedException e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	/**
	 * 删除用户，其实就是改变用户的删除状态
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@Override
	@RequestMapping(value = "/delUserEntity", method = RequestMethod.POST)
	@ResponseBody
	public SysResult delUserEntity(@RequestParam(name = "userId", required = false) String userId) {
		int flag = 0;
		try {
			flag = this.userServiceImpl.delUser(userId);
		} catch (DoSqlFailedException e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), flag);
		}
		SysResult result = new SysResult(ErrorUtil.CODE2000, "ok", flag);
		return result;
	}

	/**
	 * 根据用户id查找，用户信息。
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@Override
	@RequestMapping(value = "/getUserById", method = RequestMethod.POST)
	public SysResult getUserById(@RequestParam(name = "userId", required = false) String userId) {
		try {
			return this.userServiceImpl.getZZUserById(userId);
		} catch (DoSqlFailedException e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

//	/**
//	 * 查询所有的用户信息
//	 *
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value = "/listUserEntity", method = RequestMethod.POST)
//	@ResponseBody
//	public SysResult listUserEntity(HttpServletRequest request, HttpServletResponse response) {
//		SysResult sysResult = new SysResult();
//		PageObject<ZZOrgEntity> list = null;
//		List<ZZUserEntity> userList = userServiceImpl.listZZuser();
//		if (userList.size() > 0) {
//			request.setAttribute("userList", userList);
//			request.setAttribute("Code", ErrorUtil.CODE2000);
//			request.setAttribute("ErrorMsg", "查询成功");
//		}
//		request.setAttribute("Code", ErrorUtil.CODE2001);
//		request.setAttribute("ErrorMsg", "查询失败");
//		return null;
//	}

	/**
	 * 给用户赋予角色权限
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@Override
	@RequestMapping(value = "/addUserRole", method = RequestMethod.POST)
	@ResponseBody
	public SysResult addUserRole(@RequestParam(name = "userId", required = false) String userId,
			@RequestParam(name = "roleIds", required = false) String roleIds) {
		try {
			return this.userServiceImpl.addUserRole(userId, roleIds);
		} catch (DoSqlFailedException e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	/**
	 * 获得组织结构列表值右面的方法。，并加上分页判断
	 *
	 * @return
	 */
	@Override
	@RequestMapping("/getUserListData")
	@ResponseBody
	public SysResult getUserListData(@RequestParam(name = "orgId", required = false) String orgId,
			@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "pageNo", required = false) Integer pageNo,
			@RequestParam(name = "pageSize", required = false) Integer pageSize) {
		try {
			return this.userServiceImpl.listUserPage(orgId, keyword, pageNo, pageSize);
		} catch (DoSqlFailedException e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	/**
	 * 根据用户id获取该用户所对应的角色权限
	 *
	 * @param userId 用户Id
	 * @return
	 */
	@Override
	@RequestMapping("/getUserRole")
	@ResponseBody
	public SysResult getUserRole(@RequestParam(name = "userId", required = false) String userId) {
		try {
			return this.userServiceImpl.getUserRole(userId);
		} catch (DoSqlFailedException e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	// 根据用户userId 获取用户的角色，所对应的 所有模块资源
	@Override
	@RequestMapping("/getUserModule")
	@ResponseBody
	public SysResult getUserModule(HttpServletRequest request) {
		// List<HashMap<String, Object>>
		SysResult result = new SysResult();
		String userId = request.getParameter("userId"); // 获取用户的Id
		List<HashMap<String, Object>> list = zzModuleServiceImol.getUserMoudel(userId); // 根据用户userId
		// 获取用户的角色，所对应的所有模块资源，资源列表
		if (list != null) {
			result.setCode(ErrorUtil.CODE2000);
			result.setErrorMsg("成功");
			result.setData(list);
		} else {
			result.setCode(ErrorUtil.CODE2001);
			result.setErrorMsg("失败");
		}
		return result;
	}
}
