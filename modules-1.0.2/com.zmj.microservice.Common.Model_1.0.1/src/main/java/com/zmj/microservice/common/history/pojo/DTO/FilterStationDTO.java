package com.zmj.microservice.common.history.pojo.DTO;

/**
 * @author:   umr
 * @date:     2019/3/30
 */
public class FilterStationDTO extends BaseUNDTO{

    private String pressureType;

    public FilterStationDTO() {
    }

    public FilterStationDTO(String dataSourceName, Long startTime, Long endTime, String pressureType) {
        super(dataSourceName, startTime, endTime);
        this.pressureType = pressureType;
    }

    public String getPressureType() {
        return pressureType;
    }

    public void setPressureType(String pressureType) {
        this.pressureType = pressureType;
    }

    @Override
    public String toString() {
        return super.toString()+"FilterStationDTO{" +
                "pressureType='" + pressureType + '\'' +
                '}';
    }
}
