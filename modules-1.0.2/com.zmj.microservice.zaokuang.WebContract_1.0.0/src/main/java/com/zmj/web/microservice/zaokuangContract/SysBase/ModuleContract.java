package com.zmj.web.microservice.zaokuangContract.SysBase;

import com.zmj.web.microservice.zaokuangContract.Vo.SysResult;
import com.zmj.web.microservice.zaokuangContract.entity.ZZModuleEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hushixian
 * @date 2019-04-09 16:17
 */
@RequestMapping(value = "/SysBase/Module")
public interface ModuleContract {

    /**
     * 新增模块
     * @param zzModuleEntity 模块实体类
     * @return SysResult
     */
    @RequestMapping(value = "/addModule",method = RequestMethod.POST,consumes = "application/json")
    SysResult addModule(@RequestBody ZZModuleEntity zzModuleEntity);

    /**
     * 根据模块id 删除模块
     * @param mId 模块id
     * @return SysResult
     */
    @RequestMapping(value = "/delModule", method = RequestMethod.POST,consumes = "application/json")
    SysResult delModule(@RequestParam(name="mId",required=true) String mId);

    /**
     *修改模块
     * @param zzModuleEntity 模块实体类
     * @return SysResult
     */
    @RequestMapping(value = "/updateModule",method = RequestMethod.POST,consumes = "application/json")
    SysResult updateModule(@RequestBody ZZModuleEntity zzModuleEntity);

    /**
     * 根据关键字，页码，显示条数
     * @param keyword 关键字
     * @param pageNo 页码
     * @param pageSize 条数
     * @return SysResult
     */
    @RequestMapping(value = "/listModule",method = RequestMethod.POST,consumes = "application/json")
    SysResult listModule(@RequestParam(name="keyword",required=false) String keyword,
                         @RequestParam(name="pageNo",required=false) Integer pageNo,
                         @RequestParam(name="pageSize",required=false) Integer pageSize);


}
