package com.zmj.history.controller;

import com.zmj.history.service.impl.MixingSystemServiceImpl;
import com.zmj.microservice.common.history.base.BaseController;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import com.zmj.microservice.history.base.contract.MixingSystemContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 乳化液,华液
 * @author umr
 * @date 2019/6/6
 */
@RestController
public class MixingSystemController extends BaseController implements MixingSystemContract {


    @Autowired
    private MixingSystemServiceImpl mixService;


    @Override
    public SysResult<CommonVO> getConcentrationData(BaseUNDTO  dto) {
        return tryCatch(() -> new SysResult(mixService.getConcentrationData(dto)));
    }

    @Override
    public SysResult<CommonVO> getHuaYeConcentrationData(BaseUNDTO dto) {
        return tryCatch(() -> new SysResult(mixService.getHuaYeConcentrationData(dto)));
    }
}
