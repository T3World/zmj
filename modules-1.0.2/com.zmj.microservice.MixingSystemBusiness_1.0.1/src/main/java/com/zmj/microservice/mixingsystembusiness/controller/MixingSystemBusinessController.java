package com.zmj.microservice.mixingsystembusiness.controller;

import com.zmj.microservice.common.history.cenum.ResponseCodeEnum;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import com.zmj.microservice.mixingbusinesssystemcontract.MixingSystemBusinessContract;
import com.zmj.microservice.mixingsystembusiness.service.MixingSystemBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MixingSystemBusinessController implements MixingSystemBusinessContract {


    @Autowired
    private MixingSystemBusinessService mixingBusinessSystemService;

    @Override
    public SysResult<CommonVO> getConcentrationData(BaseUNDTO baseUNDTO) {
        SysResult result = new SysResult();
        try {
            result = mixingBusinessSystemService.getConcentrationData(baseUNDTO);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return  new SysResult(ResponseCodeEnum.UNKNOW_EXCEPTION,e.getMessage());
        }

    }


    @Override
    public SysResult<CommonVO> getHuaYeConcentrationData(BaseUNDTO baseUNDTO) {
        SysResult result = new SysResult();
        try {
            result = mixingBusinessSystemService.getHuaYeConcentrationData(baseUNDTO);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return  new SysResult(ResponseCodeEnum.UNKNOW_EXCEPTION,e.getMessage());
        }
    }
}
