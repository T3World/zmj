package com.zmj.microservice.common.history.pojo.DTO;

/**
 * @author umr,hushixian
 * @date 2019-03-30 14:10:00
 */
public class DrumHeightDataDTO extends BaseUNDTO {
    /**
     * 滚筒类型: left,right,both (both包含左右滚筒)
     * */
    private String drumType;

    public DrumHeightDataDTO() {
    }

    public DrumHeightDataDTO(String dataSourceName, Long startTime, Long endTime) {
        super(dataSourceName, startTime, endTime);
    }

    public DrumHeightDataDTO(String dataSourceName, Long startTime, Long endTime, String drumType) {
        super(dataSourceName, startTime, endTime);
        this.drumType = drumType;
    }

    public String getDrumType() {
        return drumType;
    }

    public void setDrumType(String drumType) {
        this.drumType = drumType;
    }

    @Override
    public String toString() {
        return super.toString()+"DrumHeightDataDTO{" +
                "drumType='" + drumType + '\'' +
                '}';
    }
}
