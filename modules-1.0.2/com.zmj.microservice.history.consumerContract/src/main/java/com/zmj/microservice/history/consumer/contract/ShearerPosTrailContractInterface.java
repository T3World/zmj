package com.zmj.microservice.history.consumer.contract;

import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.DTO.ShearerPositionDTO;
import com.zmj.microservice.common.history.pojo.DTO.TractionSpeedDataDTO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;

/**
 * Created by umr on 2019/3/7.
 */
public interface ShearerPosTrailContractInterface {
    /**
     * 红外位置数据,该接口直接调用电控系统信息微服务
     * */
    @RequestMapping(value = "/ShearerPosTrail/getInfraredPosData",method = RequestMethod.POST,consumes = "application/json;charset=utf-8")
    SysResult getInfraredPosData(@RequestBody BaseUNDTO dto);
    /**
     * 编码器位置数据,--原始数据就是编码器,这个可能写错了
     * */
    @RequestMapping(value = "/ShearerPosTrail/getPositionData",method = RequestMethod.POST,consumes = "application/json")
    SysResult getPositionData(@RequestBody ShearerPositionDTO dto);
    /**
     * 红外位置与自动跟机
     * */
    @RequestMapping(value = "/ShearerPosTrail/getInfraredPosWithAutoRunningData",method = RequestMethod.POST,consumes = "application/json")
    SysResult getInfraredPosWithAutoRunningData(@RequestBody BaseUNDTO dto);
    /**
     * 红外位置与记忆截割
     * */
    @RequestMapping(value = "/ShearerPosTrail/getInfraredPosWithAutoModeData",method = RequestMethod.POST,consumes = "application/json")
    SysResult getInfraredPosWithAutoModeData(@RequestBody BaseUNDTO dto);
    /**
     * 编码位置与红外
     * */
    @RequestMapping(value = "/ShearerPosTrail/getPositionWithAutoRunningData",method = RequestMethod.POST,consumes = "application/json;charset=utf-8")
    SysResult getPositionWithAutoRunningData(@RequestBody ShearerPositionDTO dto);
    /**
     * 编码位置与记忆截割
     * */
    @RequestMapping(value = "/ShearerPosTrail/getPositionWithAutoModeData",method = RequestMethod.POST,consumes = "application/json;charset=utf-8")
    SysResult getPositionWithAutoModeData(@RequestBody ShearerPositionDTO dto) throws ParseException;
    /**
     * 采煤机速度
     * */
    @RequestMapping(value = "/ShearerPosTrail/getTractionSpeedData",method = RequestMethod.POST,consumes = "application/json;charset=utf-8")
    SysResult getTractionSpeedData(@RequestBody TractionSpeedDataDTO dto) throws ParseException;
}
