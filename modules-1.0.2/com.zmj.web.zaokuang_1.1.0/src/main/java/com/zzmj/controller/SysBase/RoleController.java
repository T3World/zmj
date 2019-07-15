package com.zzmj.controller.SysBase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zzmj.pojo.entity.ZZRoleEntity;
import com.zzmj.pojo.entity.ZZRoleuserEntity;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZRoleModuleService;
import com.zzmj.service.ZZRoleService;
import com.zzmj.service.impl.ZZRoleUserServiceImpl;
import com.zzmj.util.CodeUtil;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.exception.DoSqlFailedException;
import com.zzmj.util.exception.IllegalParamException;

/**
 * 
 * @author hushixian 角色管理 controller
 *
 */
@RestController
@RequestMapping("/SysBase/Role")
public class RoleController {

    @Autowired
    private ZZRoleService roleService;

    @Autowired
    private ZZRoleUserServiceImpl zzRoleUserServiceImpl;

    @Autowired
    private ZZRoleModuleService roleModuleService;

    @RequestMapping("/listRole")
    public SysResult listRoleEntity() {
        try {
            return roleService.listRoleAll();
        } catch (DoSqlFailedException e) {
            e.printStackTrace();
            return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
        }
       
    }

    /**
     * 新增角色
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/addZZRole")
    public SysResult addZZRole(ZZRoleEntity zzRoleEntity) {
        try {
            return roleService.addZZRoleEntity(zzRoleEntity);
        } catch (DoSqlFailedException e) {
            e.printStackTrace();
            return new SysResult(ErrorUtil.CODE2001, e.getMessage(), null);
        }
    }

    /**
     * 根据id修改角色信息
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateRole")
    public SysResult updateRole(ZZRoleEntity zzRoleEntity) {
        try {
            return this.roleService.updateZZRoleEntity(zzRoleEntity);
        } catch (DoSqlFailedException e) {
            e.printStackTrace();
            return new SysResult(ErrorUtil.CODE2001, e.getMessage(), null);
        }
    }

    /**
     * 根据id删除角色
     * 
     * @param request
     * @param response
     * @return
     * @throws IllegalParamException
     */
    @RequestMapping("/delRole")
    public SysResult delRole(@RequestParam(value = "roleId", required = true) String roleId) {
        try {
            return this.roleService.delRole(roleId);
        } catch (DoSqlFailedException e) {
            e.printStackTrace();
            return new SysResult(ErrorUtil.CODE2000, e.getMessage(), null);
        }
    }

    /**
     * 把角色的权限赋予用户
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/addRoleUser")
    public String addRoleUser(HttpServletRequest request, HttpServletResponse response) {
        String roleId = request.getParameter("roleId");
        int n = zzRoleUserServiceImpl.delRoleUser(roleId);
        if (n > 0) {
            String userId = request.getParameter("userId");
            String ids[] = userId.split(",");
            for (int i = 0; i < ids.length; i++) {
                ZZRoleuserEntity zzRoleuserEntity = new ZZRoleuserEntity();
                zzRoleuserEntity.setId(CodeUtil.createUuid36());
                zzRoleuserEntity.setUserId(ids[i]);
                zzRoleuserEntity.setRoleId(roleId);
                int result = zzRoleUserServiceImpl.addRoleUserEntity(zzRoleuserEntity);
            }
            request.setAttribute("Code", ErrorUtil.CODE2000);
            request.setAttribute("msg", "权限赋予成功");
        }
        request.setAttribute("Code", ErrorUtil.CODE2001);
        request.setAttribute("msg", "权限赋予失败");
        return null;
    }
}
