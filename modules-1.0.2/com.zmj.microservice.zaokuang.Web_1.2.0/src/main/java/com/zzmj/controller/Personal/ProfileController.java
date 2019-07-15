package com.zzmj.controller.Personal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.zmj.web.microservice.zaokuangContract.Personal.ProfileContract;
import com.zmj.web.microservice.zaokuangContract.Vo.SysResult;
import com.zmj.web.microservice.zaokuangContract.entity.ZZUserEntity;
import com.zzmj.service.ZZUserService;
import com.zzmj.util.ErrorUtil;

/**
 * 
 * @author hushixian 个人信息controller
 *
 */
@RestController
public class ProfileController implements ProfileContract {

	@Autowired
	private ZZUserService userService;

	@Override
	public SysResult getUserInfo(String userId) {
		if (null == userId) {
			return new SysResult(ErrorUtil.CODE2001, "参数不能为空!", null);
		} else {
			return this.userService.getZZUserById(userId);
		}
	}

	@Override
	public SysResult setUserInfo(ZZUserEntity entity) {
		if (null == entity)
			return new SysResult(ErrorUtil.CODE2001, "参数不能为空!", null);
		String userId = entity.getUserId();
		SysResult flag = this.userService.updateZZUserEntity(entity, userId);
		if (flag.getData() == null)
			return new SysResult(ErrorUtil.CODE2001, "更新失败!", flag);
		return new SysResult(ErrorUtil.CODE2000, "ok", flag);
	}
}
