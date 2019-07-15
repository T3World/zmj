package com.zmj.history.controller;

import com.zmj.history.service.ConveyorSystemService;
import com.zmj.microservice.common.history.base.BaseController;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import com.zmj.microservice.history.base.contract.ConveyorSystemContract;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


/**
* @description:    刮板机历史查询
* @author:         umr
* @date:           2019/4/20
*/
@RestController
public class ConveyorSystemController extends BaseController implements ConveyorSystemContract {

    private static final Logger LOGGER = Logger.getLogger(ConveyorSystemController.class);

    @Autowired
    private ConveyorSystemService service;

    @Override
    public SysResult<CommonVO> getConveyorHeadHighSpeedMoterCurrent(BaseUNDTO dto) {
     return tryCatch(() -> new SysResult(service.getConveyorHeadHighSpeedMoterCurrent(dto)));
    }

    @Override
    public SysResult<CommonVO> getConveyorHeadLowSpeedMoterCurrent(BaseUNDTO dto) {
        return tryCatch(() -> new SysResult(service.getConveyorHeadLowSpeedMoterCurrent(dto)));
    }

    @Override
    public SysResult<CommonVO> getConveyorTailHighSpeedMoterCurrent(BaseUNDTO dto) {
        return tryCatch(() -> new SysResult(service.getConveyorTailHighSpeedMoterCurrent(dto)));
    }

    @Override
    public SysResult<CommonVO> getConveyorTailLowSpeedMoterCurrent(BaseUNDTO dto) {
        return tryCatch(() -> new SysResult(service.getConveyorTailLowSpeedMoterCurrent(dto)));
    }
}
