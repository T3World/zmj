package com.t3w.haveadinner.service;

import com.t3w.haveadinner.exception.TheBadTokenException;
import com.t3w.haveadinner.mapper.UserMapper;
import com.t3w.haveadinner.pojo.User;
import com.t3w.haveadinner.pojo.UserInfo;
import com.zmj.microservice.common.history.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserInfo verfyToken(String token) throws TheBadTokenException {
        if (JwtUtil.verify(token))
            throw new TheBadTokenException("token 已失效");

        Integer userId = JwtUtil.getClaimAsInteger(token, "userId");
        User user = userMapper.getUserById(userId);
        UserInfo userInfo = new UserInfo(user);
        userInfo.setToken(token);
        return userInfo;
    }
}
