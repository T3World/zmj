package com.zmj.history.controller;


import com.zmj.history.service.ShearerSystemService;
import com.zmj.microservice.common.history.base.BaseController;
import com.zmj.microservice.common.history.pojo.DTO.DrumHeightDataDTO;
import com.zmj.microservice.common.history.pojo.DTO.PositionDataDTO;
import com.zmj.microservice.common.history.pojo.DTO.RunningStateDataDTO;
import com.zmj.microservice.common.history.pojo.DTO.TractionSpeedDataDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.DrumData;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import com.zmj.microservice.history.base.contract.ShearerSystemContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description:    ShearerSystemController
 * @author:         umr
 * @date:           2019/4/28
 */
@RestController
public class ShearerSystemController extends BaseController implements ShearerSystemContract {

    private ShearerSystemService service;

    @Autowired
    public ShearerSystemController(ShearerSystemService service) {
        this.service = service;
    }

    @Override
    public SysResult<CommonVO> getTractionSpeedData(TractionSpeedDataDTO tsd) {
        return tryCatch(() -> new SysResult(service.getTractionSpeedData(tsd)));
    }

    @Override
    public SysResult<DrumData> getDrumHeightData(DrumHeightDataDTO dhd) {
        return tryCatch(() -> new SysResult(service.getDrumHeightData(dhd)));
    }

    @Override
    public SysResult<CommonVO> getPositionData(PositionDataDTO pd) {
        return tryCatch(() -> new SysResult(service.getPositionData(pd)));
    }

    @Override
    public SysResult<CommonVO> getRunningStateData(RunningStateDataDTO rsd) {
        return tryCatch(() -> new SysResult(service.getRunningStateData(rsd)));
    }

}
