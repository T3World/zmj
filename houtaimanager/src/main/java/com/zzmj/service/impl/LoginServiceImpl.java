package com.zzmj.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzmj.mapper.ZZRoleuserMapper;
import com.zzmj.mapper.ZZUserMapper;
import com.zzmj.pojo.vo.LoginResult;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.LoginService;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.MD5Util;
import com.zzmj.util.exception.DoSqlFailedException;

@Service
public class LoginServiceImpl implements LoginService {

	private static Logger logger = Logger.getLogger(LoginServiceImpl.class);
	
    @Autowired
    private ZZUserMapper userMapper;

    @Autowired
    private ZZRoleuserMapper roleUserMapper;

    @Override
    public SysResult doLogin(String userAccount, String userPassword) {
    	//参数验证
    	if (null == userAccount || "".equals(userAccount))
            return new SysResult(ErrorUtil.CODE2001, "用户名不能为空!", null);
        if (null == userPassword || "".equals(userPassword))
            return new SysResult(ErrorUtil.CODE2001, "密码不能为空!", null);
        Boolean isAdmin = false;
        // 对接受的密码进行MD5编码;
        userPassword = MD5Util.md5(userPassword);
        long loginTime = System.currentTimeMillis() / 1000;
    	try {
    		LoginResult user = this.userMapper.selectForLogin(userAccount, userPassword);
    		if (null == user)
    			return new SysResult(ErrorUtil.CODE2001, "用户名或密码错误!", null);
            user.setLoginTime(loginTime);
            String userId = user.getUserId();
            int f = this.selectRoleValueByUserId(userId);
            if (f > 0)
                isAdmin = true;
            user.setIsAdmin(isAdmin);
    		return new SysResult(ErrorUtil.CODE2000, "登陆成功!", user);
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new DoSqlFailedException("登录失败", e);
		}
    }

    @Override
    public int selectRoleValueByUserId(String userId) {
        int flag = roleUserMapper.selectRoleValueByUserId(userId);
        return flag;
    }

}
