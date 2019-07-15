package com.zmj.history.controller;


import com.zmj.history.service.CrusherSystemService;
import com.zmj.microservice.common.history.base.BaseController;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import com.zmj.microservice.history.base.contract.CrusherSystemContract;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


/**
* @description:    破碎机
* @author:         umr
* @date:           2019/4/20
*/
@RestController
public class CrusherSystemController extends BaseController implements CrusherSystemContract {
    @Autowired
    private CrusherSystemService service;

    private static final Logger LOGGER = Logger.getLogger(ConveyorSystemController.class);

    @Override
    public SysResult<CommonVO> getCrusherHighSpeadMotorCurrent(BaseUNDTO dto) {
      return tryCatch(() -> new SysResult(service.getCrusherHighSpeadMotorCurrent(dto)));
    }

    @Override
    public SysResult<CommonVO> getCrusherLowSpeadMotorCurrent(BaseUNDTO dto) {
     return tryCatch(() -> new SysResult(service.getCrusherLowSpeadMotorCurrent(dto)));
    }
}
