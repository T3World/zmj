package com.zmj.microservice.arithmetic.shearer.pojo;

import java.io.Serializable;
/**
 * @description lueluelue
 * @author umr
 * @date 2019/7/12
 */
public class ShearerFourRatio implements Serializable {

    private long ancTime;
    private long endTime;
    private long samDur;
    private double runRatio;
    private double offRatio;
    private double timeOutRatio;

    public ShearerFourRatio() {
    }

    public ShearerFourRatio(long ancTime, long endTime, long samDur, double runRatio, double offRatio, double timeOutRatio) {
        this.ancTime = ancTime;
        this.endTime = endTime;
        this.samDur = samDur;
        this.runRatio = runRatio;
        this.offRatio = offRatio;
        this.timeOutRatio = timeOutRatio;
    }

    public double getRunRatio() {
        return runRatio;
    }

    public void setRunRatio(double runRatio) {
        this.runRatio = runRatio;
    }

    public double getOffRatio() {
        return offRatio;
    }

    public void setOffRatio(double offRatio) {
        this.offRatio = offRatio;
    }

    public double getTimeOutRatio() {
        return timeOutRatio;
    }

    public void setTimeOutRatio(double timeOutRatio) {
        this.timeOutRatio = timeOutRatio;
    }
}
