package com.zmj.web.microservice.zaokuangContract.SysBase;

import com.zmj.web.microservice.zaokuangContract.Vo.SysResult;
import com.zmj.web.microservice.zaokuangContract.entity.ZZGlobalEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hushixian
 * @date 2019-04-09 16:08
 */
@RequestMapping(value = "/SysBase/Global")
public interface GlobalContract {

    /**
     *新增全局参数
     * @param zzGlobalEntity 全局参数实体类
     * @return SysResult
     */
    @RequestMapping(value = "/saveGlobalParameters", method =  RequestMethod.POST,consumes = "application/json")
    SysResult saveGlobalParameters(@RequestBody ZZGlobalEntity zzGlobalEntity);

    /**
     *
     * @param globalId 全局参数Id
     * @return SysResult
     */
    @RequestMapping(value = "/select", method =  RequestMethod.POST,consumes = "application/json")
    SysResult select(@RequestParam(name="globalId",required=false) String globalId);

}
