package com.zmj.electronic.contract.pojo.DTO;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class ElectronicControlSystemDTO implements Serializable {

    private static final long serialVersionUID = -838401271663826702L;

    @NotBlank
    private String scuNoList;

    @NotBlank
    private String dataSourceName;

    private String pressureType;

    @NotBlank
    private String startTime;

    @NotBlank
    private String endTime;

    public ElectronicControlSystemDTO() {
        this.pressureType = "1";
    }

    public ElectronicControlSystemDTO(String dataSourceName, String scuNoList, String startTime, String endTime) {
        this.dataSourceName = dataSourceName;
        this.scuNoList = scuNoList;
        this.startTime = startTime;
        this.endTime = endTime;
        this.pressureType = "1";
    }

    public ElectronicControlSystemDTO(String dataSourceName, String scuNoList, String pressureType, String startTime, String endTime) {
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
