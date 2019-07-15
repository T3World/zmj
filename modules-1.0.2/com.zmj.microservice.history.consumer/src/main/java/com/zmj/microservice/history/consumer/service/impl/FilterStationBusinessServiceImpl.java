package com.zmj.microservice.history.consumer.service.impl;

import com.zmj.microservice.common.history.pojo.DTO.FilterStationDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.FilterDataVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import com.zmj.microservice.common.history.util.CommonUtil;
import com.zmj.microservice.history.consumer.service.FilterStationBusinessService;
import com.zmj.microservice.history.consumer.service.feign.FilterStationContractFeignService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterStationBusinessServiceImpl implements FilterStationBusinessService {

    private static final Logger logger = Logger.getLogger(FilterStationBusinessServiceImpl.class);
    @Autowired
    public FilterStationContractFeignService service;
    /**
     * 进出口压力查询，根据参数不同返回不同的结果，并对结果进行去重的处理，把去重之后的结果返回给前端
     *
     * @param filterStationDTO
     * @return
     */
    @Override
    public SysResult getInOutPressureDataBusiness (FilterStationDTO filterStationDTO) {
        long s = System.currentTimeMillis();
        // 获得进出口压力的结果
        SysResult<FilterDataVO<CommonVO>> inOutPressureData = service.getInOutPressureData(filterStationDTO);
        if(inOutPressureData.getErrcode()!=200)
            return inOutPressureData;
        // 得到进出口压力的值
        List<FilterDataVO<CommonVO>> data = inOutPressureData.getData();
        for (int i = 0; i <data.size() ; i++) {
            FilterDataVO<CommonVO> vo = data.get(i);
            List<CommonVO> pressureData = vo.getData();
            CommonUtil.rmreAndRound(pressureData);
            vo.setData(pressureData);
        }
        long e = System.currentTimeMillis();
        logger.info("getInOutPressureDataBusiness cost time : "+(e-s)+"ms");
        return inOutPressureData;
    }
}
