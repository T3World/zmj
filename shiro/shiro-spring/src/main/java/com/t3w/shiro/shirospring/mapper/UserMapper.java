package com.t3w.shiro.shirospring.mapper;

import com.t3w.shiro.shirospring.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User loginByUsernamePassword(String username,String password);

    String getUserRolesById(int id);
}
