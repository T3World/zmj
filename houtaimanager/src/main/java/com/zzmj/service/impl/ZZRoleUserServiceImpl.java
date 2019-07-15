package com.zzmj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzmj.mapper.ZZRoleuserMapper;
import com.zzmj.pojo.entity.ZZRoleuserEntity;
import com.zzmj.pojo.entity.ZZRoleuserExample;
import com.zzmj.service.ZZRoleUserService;

@Service("ZZRoleUserService")
public class ZZRoleUserServiceImpl implements ZZRoleUserService {

    @Autowired
    private ZZRoleuserMapper roleuserMapper;

    /**
     * 根据roleId 查询角色下所有的用户
     * 
     * @param roleId 角色id
     * @return
     */
    @Override
    public List<ZZRoleuserEntity> listUserRole(String userId) {
        ZZRoleuserExample example = new ZZRoleuserExample();
        example.createCriteria().andUserIdEqualTo(userId);
        example.setDistinct(true);
        List<ZZRoleuserEntity> list = roleuserMapper.selectByExample(example);
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    /**
     * 新增角色与用户关系方法，给用户赋予角色时调用
     * 
     * @param userId 用户id
     * @param roleId 角色id
     * @return
     */
    @Override
    public int addRoleUserEntity(ZZRoleuserEntity roleuserEntity) {
        int n = roleuserMapper.insert(roleuserEntity);
        if (n > 0) {
            return n;
        }
        return 0;
    }

    /**
     * 根据 roleId 删除该角色下，所有的用户
     * 
     * @param roleId
     * @return
     */
    @Override
    public int delRoleUser(String roleId) {
        ZZRoleuserExample example = new ZZRoleuserExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        int n = roleuserMapper.deleteByExample(example);
        if (n > 0) {
            return n;
        }
        return 0;
    }

    /**
     * 根据 userId 删除该用户下， 所有的角色。
     * 
     * @param userId
     * @return
     */
    @Override
    public int delUserRole(String userId) {
        ZZRoleuserExample example = new ZZRoleuserExample();
        example.createCriteria().andUserIdEqualTo(userId);
        int n = roleuserMapper.deleteByExample(example);
        if (n > 0) {
            return n;
        }
        return 0;
    }

    @Override
    public int selectRoleValueByUserId(String userId) {
        int flag = roleuserMapper.selectRoleValueByUserId(userId);
        return flag;
    }

    @Override
    public String[] listUserIdsByRoleId(String roleId) {
        String[] userIds = this.roleuserMapper.selectUserIdsByRoleId(roleId);
        return userIds;
    }

}
