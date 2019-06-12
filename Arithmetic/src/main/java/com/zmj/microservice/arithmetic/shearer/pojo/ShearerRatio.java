package com.zmj.microservice.arithmetic.shearer.pojo;

import java.io.Serializable;
/**
* @description:    装采煤机各种率
* @author:         umr
* @date:           2019/5/9
*/
public class ShearerRatio implements Serializable {
    /**
     * 开机率的日期
     */
    private String date;

    /**
     * 率的计算开始时间
     * */
    private long startTime;
    /**
     * 率的计算结束时间
     * */
    private long endTime;
    /**
     * 从ancTime到当前时刻的正常通讯时长
     */
    private long commDur = 0L;

    /**
     * 从ancTime到当前时刻的开机时长
     */
    private long runDur = 0L;
    /**
     * 从ancTime到当前时刻的绝对时长
     */
    private long samDur = 0L;
    /**
     * 通讯正常率
     */
    private double commRatio = 0L;
    /**
     * 开机率
     */
    private double runRatio = 0L;

    public ShearerRatio() {
    }

    public ShearerRatio(String date) {
        this.date = date;
    }


    public ShearerRatio(long startTime, long endTime, long commDur, long runDur, long samDur, double commRatio, double runRatio) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.commDur = commDur;
        this.runDur = runDur;
        this.samDur = samDur;
        this.commRatio = commRatio;
        this.runRatio = runRatio;
    }

    public ShearerRatio(String date, long startTime, long endTime, long commDur, long runDur, long samDur, double commRatio, double runRatio) {
        this(startTime,  endTime,  commDur,  runDur,  samDur,  commRatio,  runRatio);
        this.date = date;
    }

    public long getCommDur() {
        return commDur;
    }

    public void setCommDur(long commDur) {
        this.commDur = commDur;
    }

    public long getRunDur() {
        return runDur;
    }

    public void setRunDur(long runDur) {
        this.runDur = runDur;
    }

    public long getSamDur() {
        return samDur;
    }

    public void setSamDur(long samDur) {
        this.samDur = samDur;
    }

    public double getCommRatio() {
        return commRatio;
    }

    public void setCommRatio(double commRatio) {
        this.commRatio = commRatio;
    }

    public double getRunRatio() {
        return runRatio;
    }

    public void setRunRatio(double runRatio) {
        this.runRatio = runRatio;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
