package com.zmj.microservice.common.history.pojo.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 历史查询使用的基本dto
 * @author umr,hushixian
 * @date 2019-03-30 14:07:07
 */
public class BaseUNDTO implements Serializable {

    /**
     * 用来指定查询那个工作面的数据 集团/公司/工作面
     * */
    @NotBlank
    private String dataSourceName;
    /**
     * 开始时间，时间戳
     * */
    @NotNull
    private Long startTime;
    /**
     * 开始时间，时间戳
     * */
    @NotNull
    private Long endTime;


    public BaseUNDTO() {
    }

    public BaseUNDTO(String dataSourceName, Long startTime, Long endTime) {
        this.dataSourceName = dataSourceName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "BaseUNDTO{" +
                "dataSourceName='" + dataSourceName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
