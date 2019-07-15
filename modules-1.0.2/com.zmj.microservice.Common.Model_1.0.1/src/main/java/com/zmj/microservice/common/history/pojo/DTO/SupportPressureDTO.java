package com.zmj.microservice.common.history.pojo.DTO;

import javax.validation.constraints.NotBlank;

/**
* @author:         umr
* @date:           2019/3/30
*/
public class SupportPressureDTO extends BaseUNDTO {

    @NotBlank
    private String scuNoList;

    private String pressureType;


    public SupportPressureDTO() {
        this.pressureType = "1";
    }

    public SupportPressureDTO(@NotBlank String scuNoList, String pressureType) {
        this.scuNoList = scuNoList;
        this.pressureType = pressureType;
    }

    public SupportPressureDTO(String dataSourceName, Long startTime, Long endTime, @NotBlank String scuNoList, String pressureType) {
        super(dataSourceName, startTime, endTime);
        this.scuNoList = scuNoList;
        this.pressureType = pressureType;
    }

    public String getScuNoList() {
        return scuNoList;
    }

    public void setScuNoList(String scuNoList) {
        this.scuNoList = scuNoList;
    }

    public String getPressureType() {
        return pressureType;
    }

    public void setPressureType(String pressureType) {
        this.pressureType = pressureType;
    }

    @Override
    public String toString() {
        return super.toString()+"SupportPressureDTO{" +
                "scuNoList='" + scuNoList + '\'' +
                ", pressureType='" + pressureType + '\'' +
                '}';
    }
}
