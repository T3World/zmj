package com.t3w.shiro.shirospring.mapper;

import com.t3w.shiro.shirospring.pojo.Resource;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResourceMapper {

    Resource selectResourceByName(String name);
}
