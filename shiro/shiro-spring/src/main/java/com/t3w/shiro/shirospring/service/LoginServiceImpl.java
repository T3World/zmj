package com.t3w.shiro.shirospring.service;

import com.t3w.shiro.shirospring.pojo.UserInfo;
import com.t3w.shiro.shirospring.shiro.other.ShiroManager;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private ShiroManager manager;

    @Override
    public UserInfo doLogin(String username, String password) throws IllegalParamException {

        if (null==username||null==password)
            throw new IllegalParamException("用户名密码不能为空!");

        UsernamePasswordToken token = new UsernamePasswordToken();
        token.setUsername(username);
        token.setPassword(password.toCharArray());

        Subject subject = manager.getSubject();

        subject.login(token);
        UserInfo info = (UserInfo)subject.getPrincipal();
        return info;
    }
}
