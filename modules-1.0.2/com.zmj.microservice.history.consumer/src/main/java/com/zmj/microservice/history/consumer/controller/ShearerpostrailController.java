package com.zmj.microservice.history.consumer.controller;

import com.zmj.microservice.common.history.cenum.ResponseCodeEnum;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.DTO.RunningStateDataDTO;
import com.zmj.microservice.common.history.pojo.DTO.ShearerPositionDTO;
import com.zmj.microservice.common.history.pojo.DTO.TractionSpeedDataDTO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import com.zmj.microservice.history.consumer.contract.ShearerPosTrailContractInterface;
import com.zmj.microservice.history.consumer.service.ShearerPosTrailService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * Created by umr on 2019/3/8.
 */
@RestController
public class ShearerpostrailController extends BaseController implements ShearerPosTrailContractInterface {
    private static final Logger logger = Logger.getLogger("ShearerpostrailController");

    @Autowired
    private ShearerPosTrailService sptService;
    @Override
    public SysResult getInfraredPosData(BaseUNDTO dto) {
        return tryCatch(() -> sptService.getInfraredPosData(dto));
    }

    @Override
    public SysResult getPositionData(ShearerPositionDTO dto) {
        return tryCatch(() -> sptService.getPositionData(dto));
    }

    @Override
    public SysResult getInfraredPosWithAutoRunningData(BaseUNDTO dto) {
        return tryCatch(() -> sptService.getInfraredPosWithAutoRunningData(dto));
    }

    @Override
    public SysResult getInfraredPosWithAutoModeData(BaseUNDTO dto) {
        return tryCatch(() -> sptService.getInfraredPosWithAutoModeData(dto));
    }

    @Override
    public SysResult getPositionWithAutoRunningData(ShearerPositionDTO dto) {
        return tryCatch(() -> sptService.getPositionWithAutoRunningData(dto));
    }

    @Override
    public SysResult getPositionWithAutoModeData(ShearerPositionDTO dto){
        return tryCatch(() -> sptService.getPositionWithAutoModeData(dto));
    }

    @Override
    public SysResult getTractionSpeedData(TractionSpeedDataDTO dto){
        return tryCatch(() -> sptService.getTractionSpeedData(dto));
    }

    @Override
    public SysResult getRunningStateList(RunningStateDataDTO dto) {
        try {
            return sptService.getRunningStateList(dto);
        } catch (FeignException e) {
            return new SysResult<>(ResponseCodeEnum.RPC_FAILED, e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            return new SysResult<>(ResponseCodeEnum.UNKNOW_EXCEPTION,e.getMessage());
        }
    }
}
