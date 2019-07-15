package com.zzmj.service;

import com.zmj.web.microservice.zaokuangContract.Vo.SysResult;
import com.zzmj.util.exception.EmptyResultException;

public interface LoginService {
	/**
	 * @Title: doLogin
	 * @Description: 根据用户名密码进行登陆
	 * @param: @return 返回登陆的用户信息
	 * @return: ZZUserEntity
	 * @throws EmptyResultException
	 */
	SysResult doLogin(String userAccount, String userPassword) throws EmptyResultException;

	/**
	 * @Title: selectRoleValueByUserId
	 * @Description: 根据userId查询权限
	 * @param: @param userId
	 * @param: @return
	 * @return: int
	 */
	int selectRoleValueByUserId(String userId);
}
