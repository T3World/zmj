package com.zmj.history.controller;

import com.zmj.history.service.FilterStationService;
import com.zmj.microservice.common.history.base.BaseController;
import com.zmj.microservice.common.history.pojo.DTO.FilterStationDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.FilterDataVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import com.zmj.microservice.history.base.contract.FilterStationContract;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


/**
* @description:    FilterStationController
* @author:         umr
* @date:           2019/4/28
*/
@RestController
public class FilterStationController extends BaseController implements FilterStationContract {

    private static final Logger logger = Logger.getLogger(FilterStationController.class);

    @Autowired
    private FilterStationService fsService;

    @Override
    public SysResult<FilterDataVO<CommonVO>> getInOutPressureData(FilterStationDTO dto) {
        return tryCatch(()->new SysResult(fsService.getInOutPressureData(dto)));
    }

}
