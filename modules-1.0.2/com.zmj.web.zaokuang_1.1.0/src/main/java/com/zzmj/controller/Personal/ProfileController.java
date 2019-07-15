package com.zzmj.controller.Personal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zzmj.pojo.entity.ZZUserEntity;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZUserService;
import com.zzmj.util.ErrorUtil;

/**
 * 
 * @author hushixian 个人信息controller
 *
 */
@RestController
@RequestMapping("/Personal/Profile")
public class ProfileController {

	@Autowired
	private ZZUserService userService;

	@RequestMapping("/getUserInfo")
	public SysResult getUserInfo(String userId) {
		if (null == userId) {
			return new SysResult(ErrorUtil.CODE2001, "参数不能为空!", null);
		} else {
			return this.userService.getZZUserById(userId);
		}
	}

	@RequestMapping("/setUserInfo")
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
