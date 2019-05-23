package com.t3w.shiro.shirospring.mapper;

import com.t3w.shiro.shirospring.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {

    Role selectRoleByName(String name);

}
