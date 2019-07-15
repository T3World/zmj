package com.zmj.web.microservice.zaokuangContract.API;

import com.zmj.web.microservice.zaokuangContract.Vo.SysResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hushixian
 * @date 2019-04-10 13:21
 */
@RequestMapping("/API/BaseData")
public interface BaseDataContract {

    /**
     * 得到工作面配置信息
     * @param workfaceId
     * @return
     */
    @RequestMapping(value = "/getConfig",method = RequestMethod.POST,consumes = "application/json")
    SysResult getConfig(@RequestParam(name="Workface_Id",required=true) String workfaceId);

    /**
     * 设置工作面的配置信息
     * @param workfaceId
     * @param fontMinPressure
     * @param fontMaxPressure
     * @param supportDir
     * @param shearerPosCacheTime
     * @param conveyorDir
     * @param supportCount
     * @return
     */
    @RequestMapping(value = "/setConfig",method = RequestMethod.POST,consumes = "application/json")
    SysResult setConfig(
            @RequestParam(name="Workface_Id",required=false)String workfaceId,
            @RequestParam(name="Font_MinPressure",required=false)String fontMinPressure,
            @RequestParam(name="Font_MaxPressure",required=false)String fontMaxPressure,
            @RequestParam(name="Support_Dir",required=false)String supportDir,
            @RequestParam(name="ShearerPos_CacheTime",required=false)String shearerPosCacheTime,
            @RequestParam(name="Conveyor_Dir",required=false)String conveyorDir,
            @RequestParam(name="Support_Count",required=false)String supportCount);

    /**
     * 得到工作面列表
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getCompanyWithWorkfaceList",method = RequestMethod.POST,consumes = "application/json")
    SysResult getCompanyWithWorkfaceList(@RequestParam("User_Id") String userId);
}
