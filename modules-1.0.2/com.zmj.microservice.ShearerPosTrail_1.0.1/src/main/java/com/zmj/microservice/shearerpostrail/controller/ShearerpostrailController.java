package com.zmj.microservice.shearerpostrail.controller;

import com.zmj.microservice.common.history.cenum.ResponseCodeEnum;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.DTO.ShearerPositionDTO;
import com.zmj.microservice.common.history.pojo.DTO.TractionSpeedDataDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import com.zmj.microservice.shearerpostrail.service.HistoryService;
import com.zmj.microservice.shearerpostrailcontract.ShearerPosTrailContractInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.logging.Logger;

/**
 * Created by umr on 2019/3/8.
 */
@RestController
public class ShearerpostrailController implements ShearerPosTrailContractInterface {
    private static final Logger logger = Logger.getLogger("ShearerpostrailController");

    @Autowired
    private HistoryService service;
    @Override
    public SysResult getInfraredPosData(BaseUNDTO fs) {
        return service.getInfraredPosData(fs);

    }

    @Override
    public SysResult getPositionData(ShearerPositionDTO dto) {
        SysResult<CommonVO> data = null;
        try {
            logger.info("receive");
            data = this.service.getPositionData(dto);
        }catch (Exception e){
            e.printStackTrace();
            return new SysResult(ResponseCodeEnum.UNKNOW_EXCEPTION,e.getMessage());
        }
        return data;
    }

    @Override
    public SysResult getInfraredPosWithAutoRunningData(BaseUNDTO dto) {
        try{
            return service.getInfraredPosWithAutoRunningData(dto);
        }catch (Exception e){
            e.printStackTrace();
            return new SysResult(ResponseCodeEnum.UNKNOW_EXCEPTION,e.getMessage());
        }
    }

    @Override
    public SysResult getInfraredPosWithAutoModeData(BaseUNDTO dto) {
        return null;
    }

    @Override
    public SysResult getPositionWithAutoRunningData(ShearerPositionDTO dto) {
        try {
            return service.getPositionWithAutoRunningData(dto) ;
        } catch (IllegalParamException e) {
            e.printStackTrace();
            return  new SysResult(ResponseCodeEnum.EMPTY_RESULT,e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
            return new SysResult(ResponseCodeEnum.UNKNOW_EXCEPTION,e.getMessage());
        }

    }

    @Override
    public SysResult getPositionWithAutoModeData(ShearerPositionDTO dto) throws ParseException {
        try {
            return this.service.getPositionWithAutoModeData(dto);
        } catch (IllegalParamException e) {
            e.printStackTrace();
            return  new SysResult(ResponseCodeEnum.EMPTY_RESULT,e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
            return new SysResult(ResponseCodeEnum.UNKNOW_EXCEPTION,e.getMessage());
        }
    }

    @Override
    public SysResult getTractionSpeedData(TractionSpeedDataDTO dto) throws ParseException {
        try {
            return this.service.getTractionSpeedData(dto);
        } catch (Exception e){
            e.printStackTrace();
            return new SysResult(ResponseCodeEnum.UNKNOW_EXCEPTION,e.getMessage());
        }
    }
}
