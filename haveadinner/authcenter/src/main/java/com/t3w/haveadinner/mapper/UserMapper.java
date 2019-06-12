package com.t3w.haveadinner.mapper;

import com.t3w.haveadinner.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User loginByUsernamePassword(@Param("username") String username, @Param("password") String password);

    String getUserRolesById(@Param("id") Integer id);

    User getUserById(@Param("id") Integer id);
}
