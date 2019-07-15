package com.zmj.microservice.filterstationbusiness.controller;
import com.zmj.microservice.FilterStationBusinessContract.FilterStationBusinessContract;
import com.zmj.microservice.common.history.pojo.DTO.FilterStationDTO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import com.zmj.microservice.filterstationbusiness.service.FilterStationBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilterStationBusinessController implements FilterStationBusinessContract {

    public FilterStationBusinessService service;

    @Autowired
    public FilterStationBusinessController(FilterStationBusinessService service) {
        this.service = service;
    }

    /**
     * 进出口压力查询，根据参数不同返回不同的结果，并对结果进行去重的处理，把去重之后的结果返回给前端
     * @param filterStationDTO
     * @return
     */
    @Override
    public SysResult getInOutPressureData(FilterStationDTO filterStationDTO) {
        SysResult result = service.getInOutPressureDataBusiness(filterStationDTO);
        return result;
    }
}
