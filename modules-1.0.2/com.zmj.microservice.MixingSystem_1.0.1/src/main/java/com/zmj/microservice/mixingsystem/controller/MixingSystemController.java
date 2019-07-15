package com.zmj.microservice.mixingsystem.controller;

import com.zmj.microservice.common.history.cenum.ResponseCodeEnum;
import com.zmj.microservice.common.history.exception.EmptyResultException;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import com.zmj.microservice.mixingsystem.exception.IllegalParamException;
import com.zmj.microservice.mixingsystem.exception.MysqlAccessDeniedException;
import com.zmj.microservice.mixingsystem.service.impl.MixingSystemServiceImpl;
import com.zmj.microservice.mixingsystemcontract.controller.MixingSystemContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
public class MixingSystemController implements MixingSystemContract {


    @Autowired
    private MixingSystemServiceImpl systemService;


    @Override
    public SysResult<CommonVO> getConcentrationData(BaseUNDTO  baseUNDTO) {
        SysResult<CommonVO> result =  new SysResult();
        List<CommonVO> list = new ArrayList<>();
        try {
            list = systemService.getConcentrationData(baseUNDTO);
            result.setErrcode(200);
            result.setErrmsg("成功");

        }catch (MysqlAccessDeniedException e){
            result.setErrcode(503);
            result.setErrmsg(e.getMessage());
        }catch (IllegalParamException e){
            result.setErrcode(ResponseCodeEnum.C_400);
            result.setErrmsg(e.getMessage());
        }catch (EmptyResultException e) {
            e.printStackTrace();
            result.setErrcode(ResponseCodeEnum.C_206);
            result.setErrmsg(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setErrcode(500);
            result.setErrmsg("服务器异常");
        }
        result.setData(list);
        return result;
    }

    @Override
    public SysResult<CommonVO> getHuaYeConcentrationData(BaseUNDTO baseUNDTO) {
        SysResult<CommonVO> result = new SysResult();
        List<CommonVO> list = new LinkedList<>();
        try {
            list = systemService.getHuaYeConcentrationData(baseUNDTO);
            result.setErrcode(200);
            result.setErrmsg("成功");
        }catch (MysqlAccessDeniedException e){
            result.setErrcode(201);
            result.setErrmsg(e.getMessage());
        } catch (IllegalParamException e){
            result.setErrcode(201);
            result.setErrmsg(e.getMessage());
        }catch (EmptyResultException e) {
            e.printStackTrace();
            result.setErrcode(ResponseCodeEnum.C_206);
            result.setErrmsg(e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            result.setErrcode(500);
            result.setErrmsg("服务器异常");
        }

        result.setData(list);
        return result;
    }
}
