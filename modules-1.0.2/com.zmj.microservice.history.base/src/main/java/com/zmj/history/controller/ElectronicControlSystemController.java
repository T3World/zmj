package com.zmj.history.controller;

import com.zmj.history.service.ElectronicControlService;
import com.zmj.microservice.common.history.base.BaseController;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.DTO.SupportPressureDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import com.zmj.microservice.history.base.contract.ElectronicControlSystemContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 电控类
 * @author umr
 * @date 2019/6/6
 */
@RestController
public class ElectronicControlSystemController extends BaseController implements ElectronicControlSystemContract {

    @Autowired
    private ElectronicControlService eleService;

    /**
     * 单架历史记录查询，跟剧时间，类型，数据源。
     * @param dto  实体类 包含所用到的查询条件
     * @return 一个list结果
     */
    @Override
    public SysResult<CommonVO> getPressureData(SupportPressureDTO dto) {
        return  tryCatch(() -> new SysResult(eleService.getPressureData(dto)));
    }

    /**
     * 根据主题和时间查询自动根机的状态
     * @param dto 实体类
     * @return 返回结果 result
     */
    @Override
    public SysResult<CommonVO> getIsAutoRunningData(BaseUNDTO dto) {
        return tryCatch(()->new SysResult(eleService.getIsAutoRunningData(dto)));
    }

    /**
     * 用于查询在指定时间段内煤机的红外位置
     * @param dto 实体类
     * @return 返回值 SysResult
     */
    @Override
    public SysResult<CommonVO> getInfraredPosData(BaseUNDTO dto) {
        return tryCatch(()->new SysResult(eleService.getInfraredPosData(dto)));
    }

}
