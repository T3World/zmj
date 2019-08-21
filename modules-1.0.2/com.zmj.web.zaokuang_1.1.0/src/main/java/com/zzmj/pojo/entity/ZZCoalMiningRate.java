package com.zzmj.pojo.entity;

import java.util.Date;

/**
 * @description 采煤机各种率表
 * @author umr
 * @date 2019/7/26
 */
public class ZZCoalMiningRate {
    /**
     * id
     * */
    private String id;
    /**
     * 综采率Id
     * */

    private String rateId;
    /**
     * 工作面Id
     * */
    private String workfaceId;
    /**
     *
     * 通讯正常时间
     * */
    private String commNoramTime;
    /**
     * 通讯不正常时间
     * */
    private String commUnNormaTime;
    /**
     * 采煤机开始时间
     * */
    private String shearerRunningTime;
    /**
     * 采煤机未开机时间
     * */
    private String shearerUnRunningTime;
    /**
     * 总时间
     * */
    private String totalTime;
    /**
     * 综采率值
     * */
    private String rateValue;
    /**
     * 1、跟机率 2、记忆截割率 3、乳化液浓度合格率 4、故障率
     * */
    private String rateType;
    /**
     * 要分析的数据的时间 时间格式为yyyy/MM/dd
     * */
    private String sourceTime;
    /**
     * 计算时间
     * */
    private Date computeTime;

    public ZZCoalMiningRate() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRateId() {
        return rateId;
    }

    public void setRateId(String rateId) {
        this.rateId = rateId;
    }

    public String getWorkfaceId() {
        return workfaceId;
    }

    public void setWorkfaceId(String workfaceId) {
        this.workfaceId = workfaceId;
    }

    public String getCommNoramTime() {
        return commNoramTime;
    }

    public void setCommNoramTime(String commNoramTime) {
        this.commNoramTime = commNoramTime;
    }

    public String getCommUnNormaTime() {
        return commUnNormaTime;
    }

    public void setCommUnNormaTime(String commUnNormaTime) {
        this.commUnNormaTime = commUnNormaTime;
    }

    public String getShearerRunningTime() {
        return shearerRunningTime;
    }

    public void setShearerRunningTime(String shearerRunningTime) {
        this.shearerRunningTime = shearerRunningTime;
    }

    public String getShearerUnRunningTime() {
        return shearerUnRunningTime;
    }

    public void setShearerUnRunningTime(String shearerUnRunningTime) {
        this.shearerUnRunningTime = shearerUnRunningTime;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getRateValue() {
        return rateValue;
    }

    public void setRateValue(String rateValue) {
        this.rateValue = rateValue;
    }


    public String getSourceTime() {
        return sourceTime;
    }

    public void setSourceTime(String sourceTime) {
        this.sourceTime = sourceTime;
    }

    public Date getComputeTime() {
        return computeTime;
    }

    public void setComputeTime(Date computeTime) {
        this.computeTime = computeTime;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    @Override
    public String toString() {
        return "CoalMiningRate{" +
                "id='" + id + '\'' +
                ", rateId='" + rateId + '\'' +
                ", workfaceId='" + workfaceId + '\'' +
                ", commNoramTime='" + commNoramTime + '\'' +
                ", commUnNormaTime='" + commUnNormaTime + '\'' +
                ", shearerRunningTime='" + shearerRunningTime + '\'' +
                ", shearerUnRunningTime='" + shearerUnRunningTime + '\'' +
                ", totalTime='" + totalTime + '\'' +
                ", rateValue='" + rateValue + '\'' +
                ", rateType='" + rateType + '\'' +
                ", sourceTime='" + sourceTime + '\'' +
                ", computeTime=" + computeTime +
                '}';
    }
}
