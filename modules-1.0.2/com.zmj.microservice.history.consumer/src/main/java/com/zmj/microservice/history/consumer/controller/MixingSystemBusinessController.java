package com.zmj.microservice.history.consumer.controller;

import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import com.zmj.microservice.history.consumer.contract.MixingSystemBusinessContract;
import com.zmj.microservice.history.consumer.service.MixingSystemBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MixingSystemBusinessController extends BaseController implements MixingSystemBusinessContract {


    @Autowired
    private MixingSystemBusinessService mixingBusinessSystemService;

    @Override
    public SysResult<CommonVO> getConcentrationData(BaseUNDTO dto) {
        return tryCatch(() -> mixingBusinessSystemService.getConcentrationData(dto));
    }


    @Override
    public SysResult<CommonVO> getHuaYeConcentrationData(BaseUNDTO dto) {
        return tryCatch(() -> mixingBusinessSystemService.getHuaYeConcentrationData(dto));
    }
}
