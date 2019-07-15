package com.zmj.electronic.contract;

import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.DTO.SupportPressureDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public interface ElectronicControlSystemContractController {

    @RequestMapping(value = "/ElectronicControlSystem/getPressureData",method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public SysResult<CommonVO> getPressureData(@RequestBody SupportPressureDTO ecs);


    @RequestMapping(value = "/ElectronicControlSystem/getIsAutoRunningData",method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public  SysResult<CommonVO> getIsAutoRunningData(@RequestBody BaseUNDTO ecs);

    @RequestMapping(value = "/ElectronicControlSystem/getInfraredPosData",method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public  SysResult<CommonVO> getInfraredPosData(@RequestBody BaseUNDTO ecs);
}
