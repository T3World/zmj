package com.zzmj.service;

import java.util.List;

import com.zzmj.pojo.entity.ZZRoleuserEntity;

public interface ZZRoleUserService {

    /**
     * 根据roleId 查询角色下所有的用户
     * 
     * @param roleId 角色id
     * @return
     */
    String[] listUserIdsByRoleId(String roleId);

    /**
     * 根据roleId 查询角色下所有的用户
     * 
     * @param roleId 角色id
     * @return
     */
    List<ZZRoleuserEntity> listUserRole(String userId);

    /**
     * 新增角色与用户关系方法，给用户赋予角色是调用
     * 
     * @param userId 用户id
     * @param roleId 角色id
     * @return
     */
    int addRoleUserEntity(ZZRoleuserEntity roleuserEntity);

    /**
     * 根据 roleId 删除该角色下，所有的用户
     * 
     * @param roleId
     * @return
     */
    int delRoleUser(String roleId);

    /**
     * 根据 userId 删除该用户下， 所有的角色。
     * 
     * @param userId
     * @return
     */
    int delUserRole(String userId);

    /**
     * @Title: selectRoleValueByUserId
     * @Description: 根据userId查询权限
     * @param: @param userId
     * @param: @return
     * @return: int
     */
    int selectRoleValueByUserId(String userId);

}
