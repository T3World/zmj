package com.t3w.haveadinner.zaokuang.service;

import com.t3w.haveadinner.service.UserServiceImpl;
import com.t3w.haveadinner.zaokuang.mapper.ZaoKuangUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ZaoKuangUserService")
public class ZaoKuangUserService extends UserServiceImpl {

    public ZaoKuangUserService() {
    }

    @Autowired
    public ZaoKuangUserService(ZaoKuangUserMapper userMapper) {
        this.setUserMapper(userMapper);
    }
}
