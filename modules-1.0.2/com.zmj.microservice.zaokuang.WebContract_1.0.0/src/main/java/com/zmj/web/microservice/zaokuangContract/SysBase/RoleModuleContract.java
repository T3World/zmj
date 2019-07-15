package com.zmj.web.microservice.zaokuangContract.SysBase;

import com.zmj.web.microservice.zaokuangContract.Vo.SysResult;
import com.zmj.web.microservice.zaokuangContract.entity.RoleModuleResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hushixian
 * @date 2019-04-09 17:07
 */
@RequestMapping("/SysBase/RoleModule")
public interface RoleModuleContract {

    @RequestMapping(value = "/selectModuleByRoleId",method = RequestMethod.POST,consumes = "application/json")
    SysResult selectModuleByRoleId(@RequestParam(name="roleId",required=true) String roleId);

    @RequestMapping(value = "/permitRoleModules",method = RequestMethod.POST,consumes = "application/json")
    SysResult permitRoleModules(@RequestBody  RoleModuleResult result);

    @RequestMapping(value = "/getRoleModuleTreeData",method = RequestMethod.POST,consumes = "application/json")
    SysResult getRoleModuleTreeData(String roleId);

}
