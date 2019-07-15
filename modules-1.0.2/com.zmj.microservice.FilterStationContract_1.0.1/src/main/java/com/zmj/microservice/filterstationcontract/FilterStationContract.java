package com.zmj.microservice.filterstationcontract;

import com.zmj.microservice.common.history.pojo.DTO.FilterStationDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.FilterDataVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/FilterStation",method = RequestMethod.POST
        ,consumes = "application/json;charset=utf-8")
public interface FilterStationContract {

//    @RequestMapping(value = "/FilterStation/getInPressureData",method = RequestMethod.POST,consumes = "application/json")
//    public SysResult<CommonVO<Double>> getInPressureData(@RequestBody FilterStationDTO fs);
//
//    @RequestMapping(value = "/FilterStation/getOutPressureData",method = RequestMethod.POST,consumes = "application/json")
//    public SysResult<CommonVO<Double>> getOutPressureData(@RequestBody FilterStationDTO fs);

    @RequestMapping(value = "/getInOutPressureData",method = RequestMethod.POST
            ,consumes = "application/json;charset=utf-8")
    public SysResult<FilterDataVO<CommonVO>> getInOutPressureData(@RequestBody FilterStationDTO fs);

}
