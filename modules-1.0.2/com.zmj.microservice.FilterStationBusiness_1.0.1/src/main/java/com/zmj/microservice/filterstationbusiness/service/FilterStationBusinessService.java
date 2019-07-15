package com.zmj.microservice.filterstationbusiness.service;

import com.zmj.microservice.common.history.pojo.DTO.FilterStationDTO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;

public interface FilterStationBusinessService {
    /**
     * 进出口压力查询，根据参数不同返回不同的结果，并对结果进行去重的处理，把去重之后的结果返回给前端
     * @param filterStationDTO
     * @return
     */
    public SysResult getInOutPressureDataBusiness (FilterStationDTO filterStationDTO);
}
