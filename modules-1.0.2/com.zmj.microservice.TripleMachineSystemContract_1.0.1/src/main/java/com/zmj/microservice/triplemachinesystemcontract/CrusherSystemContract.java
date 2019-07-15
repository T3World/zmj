package com.zmj.microservice.triplemachinesystemcontract;

import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
* @description:    破碎机系统接口
* @author:         umr
* @date:           2019/4/20
*/
public interface CrusherSystemContract {

    /**
    * 获得破碎机高速电机电流
    * @date        2019/4/20 9:30
    */
    @RequestMapping(value = "/CrusherSystem/getCrusherHighSpeadMotorCurrent",method = RequestMethod.POST,consumes = "application/json")
    SysResult<CommonVO> getCrusherHighSpeadMotorCurrent(@RequestBody BaseUNDTO dto);
   /**
   * 获得破碎机低速电机电流
   * @date        2019/4/20 9:31
   */
    @RequestMapping(value = "/CrusherSystem/getCrusherLowSpeadMotorCurrent",method = RequestMethod.POST,consumes = "application/json")
    SysResult<CommonVO> getCrusherLowSpeadMotorCurrent(@RequestBody BaseUNDTO dto);

}
