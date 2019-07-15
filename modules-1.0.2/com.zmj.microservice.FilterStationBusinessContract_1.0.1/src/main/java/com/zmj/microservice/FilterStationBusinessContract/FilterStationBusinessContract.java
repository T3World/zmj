package com.zmj.microservice.FilterStationBusinessContract;

import com.zmj.microservice.common.history.pojo.DTO.FilterStationDTO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/FilterStationBusiness")
public interface FilterStationBusinessContract{

    /**
     * 进出口压力查询，根据参数不同返回不同的结果，并对结果进行去重的处理，把去重之后的结果返回给前端
     * @param filterStationDTO
     * @return
     */
    @RequestMapping(value = "/getInOutPressureData",method = RequestMethod.POST
            ,consumes = "application/json")
    SysResult getInOutPressureData(@RequestBody FilterStationDTO filterStationDTO);

}
