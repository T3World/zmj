package com.zzmj.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.zzmj.pojo.entity.ZZRoleEntity;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.util.exception.DoSqlFailedException;
import com.zzmj.util.exception.EmptyResultException;
import com.zzmj.util.exception.IllegalParamException;

public interface ZZRoleService {

    /**
     * 根据roleId来修改角色信息
     * 
     * @param roleId 角色id
     * @param roleEntity 要修改的结果
     * @return
     * @throws DoSqlFailedException
     */
    SysResult updateZZRoleEntity(ZZRoleEntity roleEntity) throws DoSqlFailedException;

    /**
     * 新增角色方法
     * 
     * @param roleEntity
     * @return
     * @throws DoSqlFailedException
     */
    SysResult addZZRoleEntity(ZZRoleEntity roleEntity) throws DoSqlFailedException;

    /**
     * 查询角色列表
     * 
     * @return
     */
    List<ZZRoleEntity> listRoleEntity();

    SysResult listRoleAll() throws EmptyResultException;

    SysResult delRole(String roleId) throws IllegalParamException;
    
}
