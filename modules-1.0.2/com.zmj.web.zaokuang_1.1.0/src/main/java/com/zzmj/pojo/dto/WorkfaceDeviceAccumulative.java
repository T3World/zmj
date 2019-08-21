package com.zzmj.pojo.dto;

import java.io.Serializable;

/**
 * @description 用于通过工作面ID,更新设备累积量
 * @author umr
 * @date 2019/7/9
 */
public class WorkfaceDeviceAccumulative implements Serializable {
    private String id;
    /**
     * '设备类型Id'
     */
    private String deviceId;
    /**
     * 设备累计运行时间'
     */
    private String cumulativeRunTime;
    /**
     * '设备累计承载循环数',
     */
    private String cumulativeLoadCycles;
    /**
     * 设备累计过煤量'
     */
    private String cumulativeCoalWeight;

    /**
     * 工作面参数
     * */
    private String workfaceId;

    /**
     * 设备编号
     */
    private String code;

    public WorkfaceDeviceAccumulative() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCumulativeRunTime() {
        return cumulativeRunTime;
    }

    public void setCumulativeRunTime(String cumulativeRunTime) {
        this.cumulativeRunTime = cumulativeRunTime;
    }

    public String getCumulativeLoadCycles() {
        return cumulativeLoadCycles;
    }

    public void setCumulativeLoadCycles(String cumulativeLoadCycles) {
        this.cumulativeLoadCycles = cumulativeLoadCycles;
    }

    public String getCumulativeCoalWeight() {
        return cumulativeCoalWeight;
    }

    public void setCumulativeCoalWeight(String cumulativeCoalWeight) {
        this.cumulativeCoalWeight = cumulativeCoalWeight;
    }

    public String getWorkfaceId() {
        return workfaceId;
    }

    public void setWorkfaceId(String workfaceId) {
        this.workfaceId = workfaceId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
