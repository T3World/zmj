package com.zmj.controller;

import com.zmj.electronic.contract.ElectronicControlSystemContractController;
import com.zmj.exception.IllegalParamException;
import com.zmj.exception.MysqlAccessDeniedException;
import com.zmj.microservice.common.history.cenum.ResponseCodeEnum;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.DTO.SupportPressureDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import com.zmj.service.impl.CommonValueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class ElectronicControlSystemController implements ElectronicControlSystemContractController {

    @Autowired
    private CommonValueServiceImpl commonValueService;

    /**
     * 单架历史记录查询，跟剧时间，类型，数据源。
     * @param supportPressureDTO  实体类 包含所用到的查询条件
     * @return 一个list结果
     */
    @Override
    public SysResult<CommonVO> getPressureData(SupportPressureDTO supportPressureDTO) {
        SysResult result = new SysResult();
        try {
            List<Map<String, Object>> list = commonValueService.getPressurceDaraByScuNoList(supportPressureDTO);
            result.setErrcode(ResponseCodeEnum.C_200.getValue());
            result.setErrmsg("访问成功");
            result.setData(list);
        } catch (IllegalParamException e){
            e.printStackTrace();
            result.setErrcode(ResponseCodeEnum.C_204.getValue());
            result.setErrmsg("请求参数异常");
        } catch (MysqlAccessDeniedException e){
            result.setErrcode(ResponseCodeEnum.C_204.getValue());
            result.setErrmsg(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setErrcode(ResponseCodeEnum.C_500.getValue());
            result.setErrmsg("服务器异常");
        }
        return  result;
    }

    /**
     * 根据主题和时间查询自动根机的状态
     * @param electronicControlSystemDTO 实体类
     * @return 返回结果 result
     */
    @Override
    public SysResult<CommonVO> getIsAutoRunningData(BaseUNDTO electronicControlSystemDTO) {
        SysResult<CommonVO> result = new SysResult();
        try {
            List<CommonVO> list =  commonValueService.getSortAutoRunning(electronicControlSystemDTO);
            result.setErrcode(ResponseCodeEnum.C_200.getValue());
            result.setErrmsg("访问成功");
            result.setData(list);
        }catch (MysqlAccessDeniedException e){
            result.setErrcode(ResponseCodeEnum.C_204.getValue());
            result.setErrmsg(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setErrcode(ResponseCodeEnum.C_500.getValue());
            result.setErrmsg("服务器异常");
        }
        return result;
    }

    /**
     * 用于查询在指定时间段内煤机的红外位置
     * @param electronicControlSystemDTO 实体类
     * @return 返回值 SysResult
     */
    @Override
    public SysResult<CommonVO> getInfraredPosData(BaseUNDTO electronicControlSystemDTO) {
        SysResult<CommonVO> result = new SysResult();
        try {
            List<CommonVO> list = commonValueService.getInfraredShearerPosData(electronicControlSystemDTO);
            result.setErrcode(ResponseCodeEnum.C_200.getValue());
            result.setErrmsg("访问成功");
            result.setData(list);
        } catch (MysqlAccessDeniedException e){
            result.setErrcode(ResponseCodeEnum.C_204.getValue());
            result.setErrmsg(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setErrcode(ResponseCodeEnum.C_500.getValue());
            result.setErrmsg("服务器异常");
        }
        return result;
    }

}
