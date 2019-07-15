package com.zmj.microservice.history.base.contract;

import com.zmj.microservice.common.history.pojo.DTO.FilterStationDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.FilterDataVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author umr
 * @date 2019/6/5
 */
@RequestMapping("/FilterStation")
public interface FilterStationContract {

    @RequestMapping(value = "/getInOutPressureData",method = RequestMethod.POST
            ,consumes = "application/json;charset=utf-8")
    SysResult<FilterDataVO<CommonVO>> getInOutPressureData(@RequestBody FilterStationDTO fs);
}
