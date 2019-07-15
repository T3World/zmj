package com.zmj.microservice.mixingsystemcontract.controller;

import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/MixingSystem",method = RequestMethod.POST)
public interface MixingSystemContract {

    /**
     * 用于查询指定时间段内的乳化液浓度的历史数据
     * @param md 用来接收参数的实体类
     * @return 返回值
     */
    @RequestMapping(value = "/getConcentrationData",method = RequestMethod.POST,consumes = "application/json")
    public SysResult<CommonVO> getConcentrationData(@RequestBody BaseUNDTO md);


    /**
     *
     * 用于查询指定时间段内的华液乳化液浓度的历史数据
     * @param md 用来接收参数的实体类
     * @return 返回值
     */
    @RequestMapping(value = "/getHuaYeConcentrationData",method =  RequestMethod.POST,consumes = "application/json")
    public  SysResult<CommonVO> getHuaYeConcentrationData(@RequestBody  BaseUNDTO md);

}
