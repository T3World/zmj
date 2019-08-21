package com.zmj.microservice.common.history.pojo.DTO;


/**
* @author:         umr
* @date:           2019/3/30
*/
public class RunningStateDataDTO extends BaseUNDTO{
    /**
     * 采煤机状态码
     * */
    private String stateIds;

    public RunningStateDataDTO() {
    }

    public RunningStateDataDTO(BaseUNDTO dto,String stateIds) {
        super(dto.getDataSourceName(),dto.getStartTime(),dto.getEndTime());
        this.stateIds = stateIds;
    }

    public RunningStateDataDTO(String dataSourceName, Long startTime, Long endTime) {
        super(dataSourceName, startTime, endTime);
    }

    public RunningStateDataDTO(String dataSourceName, Long startTime, Long endTime, String stateIds) {
        super(dataSourceName, startTime, endTime);
        this.stateIds = stateIds;
    }

    public String getStateIds() {
        return stateIds;
    }

    public void setStateIds(String stateIds) {
        this.stateIds = stateIds;
    }

    @Override
    public String toString() {
        return super.toString()+"RunningStateDataDTO{" +
                "stateIds='" + stateIds + '\'' +
                '}';
    }
}
