package com.zmj.microservice.history.consumer.contract;


import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/MixingSystemBusiness",method = RequestMethod.POST,consumes = "application/json")
public interface MixingSystemBusinessContract {

    @RequestMapping(value = "/getConcentrationData",method = RequestMethod.POST,consumes = "application/json")
    SysResult<CommonVO> getConcentrationData(@RequestBody BaseUNDTO baseUNDTO);

    @RequestMapping(value = "/getHuaYeConcentrationData",method = RequestMethod.POST,consumes = "application/json")
    SysResult<CommonVO> getHuaYeConcentrationData(@RequestBody BaseUNDTO baseUNDTO);

}
