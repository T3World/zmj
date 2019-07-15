package com.zzmj.controller.Personal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zmj.web.microservice.zaokuangContract.Personal.ResetPasswordContract;
import com.zmj.web.microservice.zaokuangContract.Vo.SysResult;
import com.zmj.web.microservice.zaokuangContract.entity.ZZUserEntity;
import com.zzmj.service.ZZUserService;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.MD5Util;

@RestController
public class ResetPasswordController implements ResetPasswordContract {
	@Autowired
	private ZZUserService service;

	@Override
	public SysResult resetPassword(@RequestParam("userId") String userId,
			@RequestParam("oldPassword") String oldPassword, @RequestParam("userPassword") String userPassword) {
		int flag = this.service.getIfPasswordRight(userId, oldPassword);
		if (flag < 1)
			return new SysResult(ErrorUtil.CODE2001, "密码错误!", null);

		ZZUserEntity entity = new ZZUserEntity();
		userPassword = MD5Util.md5(userPassword);
		entity.setUserPassword(userPassword);
		entity.setUserId(userId);
		try {
			SysResult result = service.updateZZUserEntity(entity, userId);
			return result;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, "更新密码失败,请重试!", null);
		}
	}
}
