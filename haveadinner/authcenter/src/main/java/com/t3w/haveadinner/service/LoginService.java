package com.t3w.haveadinner.service;

import com.t3w.haveadinner.pojo.UserInfo;
import com.zmj.microservice.common.history.exception.IllegalParamException;

/**
 * @description:    java类作用描述
 * @author:         umr
 * @date:           2019/5/17
 */
public interface LoginService {
    /**
     * 登录
     * @return 用户信息,权限信息
     * @date        2019/5/17 14:42
     */
    UserInfo doLogin(String username, String password) throws IllegalParamException;
}
