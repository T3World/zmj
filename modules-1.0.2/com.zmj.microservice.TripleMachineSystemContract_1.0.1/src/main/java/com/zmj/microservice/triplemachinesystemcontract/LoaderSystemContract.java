package com.zmj.microservice.triplemachinesystemcontract;

import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
* @description:    转载机系统接口
* @author:         umr
* @date:           2019/4/20
*/
public interface LoaderSystemContract {

    /**
    * 获得转载机高速电机电流
    * @date        2019/4/20 9:34
    */
    @RequestMapping(value = "/LoaderSystem/getLoaderHighSpeadMotorCurrent",method = RequestMethod.POST,consumes = "application/json")
    SysResult<CommonVO> getLoaderHighSpeadMotorCurrent(@RequestBody BaseUNDTO dto);
    /**
    * 获得转载机低速电机电流
    * @date        2019/4/20 9:34
    */
    @RequestMapping(value = "/LoaderSystem/getLoaderHLowSpeadMotorCurrent",method = RequestMethod.POST,consumes = "application/json")
    SysResult<CommonVO> getLoaderHLowSpeadMotorCurrent(@RequestBody BaseUNDTO dto);

}
