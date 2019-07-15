package com.zmj.microservice.common.history.pojo.DTO;

/**
* @author:         umr
* @date:     2019/3/30
*/
public class PositionDataDTO extends BaseUNDTO {
    public PositionDataDTO() {
    }

    public PositionDataDTO(String dataSourceName, Long startTime, Long endTime) {
        super(dataSourceName, startTime, endTime);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
