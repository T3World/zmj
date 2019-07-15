package com.zmj.web.microservice.zaokuangContract.SysBase;

import com.zmj.web.microservice.zaokuangContract.Vo.SysResult;
import com.zmj.web.microservice.zaokuangContract.entity.ZZRoleEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hushixian
 * @date 2019-04-09 16:56
 */
@RequestMapping(value = "/SysBase/Role")
public interface RoleContract {

    /**
     * 查询列表
     * @return
     */
    @RequestMapping(value = "/listRole",method = RequestMethod.POST,consumes = "application/json")
    SysResult listRoleEntity();

    /**
     * 新增角色
     * @param zzRoleEntity
     * @return
     */
    @RequestMapping(value = "/addZZRole",method = RequestMethod.POST,consumes = "application/json")
    SysResult addZZRole(@RequestBody ZZRoleEntity zzRoleEntity);

    /**
     * 根据id修改角色信息
     * @param zzRoleEntity
     * @return
     */
    @RequestMapping(value = "/updateRole",method = RequestMethod.POST,consumes = "application/json")
    SysResult updateRole(@RequestBody ZZRoleEntity zzRoleEntity);

    /**
     * 根据id删除角色
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/delRole",method = RequestMethod.POST,consumes = "application/json")
    SysResult delRole(@RequestParam(value = "roleId", required = true) String roleId);

    /**
     * 把角色的权限赋予用户
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/addRoleUser",method = RequestMethod.POST,consumes = "application/json")
    String addRoleUser(HttpServletRequest request, HttpServletResponse response);

}
