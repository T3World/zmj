package com.zmj.microservice.filterstation.controller;

import com.zmj.microservice.common.history.cenum.ResponseCodeEnum;
import com.zmj.microservice.common.history.exception.ConnectDBException;
import com.zmj.microservice.common.history.exception.EmptyResultException;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import com.zmj.microservice.common.history.pojo.DTO.FilterStationDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.FilterDataVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import com.zmj.microservice.filterstation.service.FilterStationService;
import com.zmj.microservice.filterstationcontract.FilterStationContract;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* @description:    FilterStationController
* @author:         umr
* @date:           2019/4/28
*/
@RestController
public class FilterStationController implements FilterStationContract {

    private static final Logger logger = Logger.getLogger("FilterStationController");

    @Autowired
    private FilterStationService service;

    @Override
    public SysResult<FilterDataVO<CommonVO>> getInOutPressureData(FilterStationDTO filterStationDTO) {
        long s = System.currentTimeMillis();
        List<FilterDataVO<CommonVO>> data = null;
        try{
            data = this.service.getInOutPressureData(filterStationDTO);
        } catch (IllegalParamException e){
            return new SysResult<>(ResponseCodeEnum.C_400, e.getMessage());
        } catch (ConnectDBException e){
            return new SysResult<>(ResponseCodeEnum.C_503, e.getMessage());
        } catch (EmptyResultException e){
            return new SysResult<>(ResponseCodeEnum.C_204, e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            return new SysResult<>(ResponseCodeEnum.C_500,"系统错误!请联系管理员");
        }
        return new SysResult<>(data);
    }

}
