package com.zmj.microservice.common.history.pojo.DTO;

/**
* @author:         umr
* @date:           2019/3/30
*/
public class TractionSpeedDataDTO extends BaseUNDTO{

    public TractionSpeedDataDTO() {
    }

    public TractionSpeedDataDTO(String dataSourceName, Long startTime, Long endTime) {
        super(dataSourceName, startTime, endTime);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
