package com.zmj.microservice.SupportPressureService.pojo.DTO;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class SupportPressureDTO implements Serializable {

    @NotBlank
    private String scuNoList;

    @NotBlank
    private String dataSourceName;

    private String pressureType = "1";

    @NotBlank
    private String startTime;

    @NotBlank
    private String endTime;

    public SupportPressureDTO() {
    }

    public SupportPressureDTO(String dataSourceName, String scuNoList, String startTime, String endTime) {
        this.dataSourceName = dataSourceName;
        this.scuNoList = scuNoList;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public SupportPressureDTO(String dataSourceName, String scuNoList, String pressureType, String startTime, String endTime) {
        this.dataSourceName = dataSourceName;
        this.scuNoList = scuNoList;
        this.pressureType = pressureType;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
