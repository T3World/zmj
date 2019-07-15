package com.zmj.history.controller;


import com.zmj.history.service.LoaderSystemService;
import com.zmj.microservice.common.history.base.BaseController;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import com.zmj.microservice.history.base.contract.LoaderSystemContract;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


/**
* @description:    转载机
* @author:         umr
* @date:           2019/4/20
*/
@RestController
public class LoaderSystemController extends BaseController implements LoaderSystemContract {
    @Autowired
    private LoaderSystemService service;

    private static final Logger LOGGER = Logger.getLogger(ConveyorSystemController.class);

    @Override
    public SysResult<CommonVO> getLoaderHighSpeadMotorCurrent(BaseUNDTO dto) {
        return tryCatch(() -> new SysResult(service.getLoaderHighSpeadMotorCurrent(dto)));
    }

    @Override
    public SysResult<CommonVO> getLoaderHLowSpeadMotorCurrent(BaseUNDTO dto) {
        return tryCatch(() -> new SysResult(service.getLoaderHLowSpeadMotorCurrent(dto)));
    }
}
