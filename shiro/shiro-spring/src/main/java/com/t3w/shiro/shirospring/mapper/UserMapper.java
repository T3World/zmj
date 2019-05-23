package com.t3w.shiro.shirospring.mapper;

import com.t3w.shiro.shirospring.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User loginByUsernamePassword(@Param("username") String username, @Param("password") String password);

    String getUserRolesById(@Param("id") int id);
}
