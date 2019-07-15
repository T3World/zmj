package com.zmj.microservice.triplemachinesystemcontract;


import com.zmj.microservice.common.history.pojo.DTO.*;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
* @description:    刮板机接口
* @author:         umr
* @date:           2019/4/20
*/
public interface ConveyorSystemContract {

    /**
     * 获取刮板机机头高速电机电流；*/
    @RequestMapping(value = "/ConveyorSystem/getConveyorHeadHighSpeedMoterCurrent",method = RequestMethod.POST,consumes = "application/json")
    SysResult<CommonVO> getConveyorHeadHighSpeedMoterCurrent(@RequestBody BaseUNDTO dto);
    /**
     * 获取刮板机机头低速电机电流；*/
    @RequestMapping(value = "/ConveyorSystem/getConveyorHeadLowSpeedMoterCurrent",method = RequestMethod.POST,consumes = "application/json")
    SysResult<CommonVO> getConveyorHeadLowSpeedMoterCurrent(@RequestBody BaseUNDTO dto);
    /**
     * 获取刮板机机尾高速电机电流；*/
    @RequestMapping(value = "/ConveyorSystem/getConveyorTailHighSpeedMoterCurrent",method = RequestMethod.POST,consumes = "application/json")
    SysResult<CommonVO> getConveyorTailHighSpeedMoterCurrent(@RequestBody BaseUNDTO dto);
    /**
     * 获取刮板机机尾低速电机电流；*/
    @RequestMapping(value = "/ConveyorSystem/getConveyorTailLowSpeedMoterCurrent",method = RequestMethod.POST,consumes = "application/json")
    SysResult<CommonVO> getConveyorTailLowSpeedMoterCurrent(@RequestBody BaseUNDTO dto);


}
