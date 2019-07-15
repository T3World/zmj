package com.zmj.microservice.history.base.contract;

import com.zmj.microservice.common.history.pojo.DTO.DrumHeightDataDTO;
import com.zmj.microservice.common.history.pojo.DTO.PositionDataDTO;
import com.zmj.microservice.common.history.pojo.DTO.RunningStateDataDTO;
import com.zmj.microservice.common.history.pojo.DTO.TractionSpeedDataDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.DrumData;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * @description 采煤机系统
 * @author umr
 * @date 2019/6/5
 */
public interface ShearerSystemContract {

    /**
     * 获取煤机速度；*/
    @RequestMapping(value = "/ShearerSystem/getTractionSpeedData",method = RequestMethod.POST,consumes = "application/json")
    SysResult<CommonVO> getTractionSpeedData(@RequestBody TractionSpeedDataDTO tsd);
    /**
     * 获取滚筒高度；*/
    @RequestMapping(value = "/ShearerSystem/getDrumHeightData",method = RequestMethod.POST,consumes = "application/json")
    SysResult<DrumData> getDrumHeightData(@RequestBody DrumHeightDataDTO dhd);
    /**
     * 获取编码器位置；*/
    @RequestMapping(value = "/ShearerSystem/getPositionData",method = RequestMethod.POST,consumes = "application/json")
    SysResult<CommonVO> getPositionData(@RequestBody PositionDataDTO pd);
    /**
     * 煤机运行状态；*/
    @RequestMapping(value = "/ShearerSystem/getRunningStateData",method = RequestMethod.POST,consumes = "application/json")
    SysResult<CommonVO> getRunningStateData(@RequestBody RunningStateDataDTO rsd);

}
