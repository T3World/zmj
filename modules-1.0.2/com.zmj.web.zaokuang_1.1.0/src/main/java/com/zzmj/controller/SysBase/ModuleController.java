package com.zzmj.controller.SysBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zzmj.pojo.entity.ZZModuleEntity;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZModuleService;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.exception.DoSqlFailedException;

import java.util.List;

/**
 *
 * @author hushixian umr
 *
 */
@RestController
@RequestMapping("/SysBase/Module")
public class ModuleController {
    @Autowired
    private ZZModuleService zzModuleService;

    @RequestMapping(value = "/addModule", method = RequestMethod.POST)
    public SysResult addModule(ZZModuleEntity zzModuleEntity) {
        try {
            return zzModuleService.addModule(zzModuleEntity);
        } catch (DoSqlFailedException e) {
            e.printStackTrace();
            return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
        }
    }

    @RequestMapping(value = "/delModule")
    public SysResult delModule(@RequestParam(name="mId",required=true) String mId) {
        try {
            return zzModuleService.delModule(mId);
        } catch (DoSqlFailedException e) {
            e.printStackTrace();
            return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
        }

    }

    @RequestMapping(value = "/updateModule")
    public SysResult updateModule(ZZModuleEntity zzModuleEntity) {
        try {
            return zzModuleService.updateModule(zzModuleEntity);
        } catch (DoSqlFailedException e) {
            e.printStackTrace();
            return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
        }
    }

    @RequestMapping(value = "/listModule")
    public SysResult listModule(@RequestParam(name="keyword",required=false) String keyword,
                                @RequestParam(name="pageNo",required=false) Integer pageNo,
                                @RequestParam(name="pageSize",required=false) Integer pageSize) {
        try {
            return zzModuleService.listModule(keyword, pageNo, pageSize);
        } catch (DoSqlFailedException e) {
            e.printStackTrace();
            return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
        }
    }

    /**
     * 根据角色Id查询跟该角色关联的所有模块值，返回值List<String>(只用于测试)
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/getModuleValuesByRoleId", method = RequestMethod.POST)
    public List<String> getModuleValuesByRoleId(@RequestParam(name = "roleId", required = false) String roleId) {
        return this.zzModuleService.getModuleValuesByRoleId(roleId);
    }

}
