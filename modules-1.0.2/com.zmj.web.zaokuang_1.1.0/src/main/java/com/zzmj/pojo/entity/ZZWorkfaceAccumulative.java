package com.zzmj.pojo.entity;

import java.io.Serializable;

public class ZZWorkfaceAccumulative implements Serializable {

    /**
     * zz_wofkfaceaccumulative.Workface_Id (工作面id)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String workfaceId;

    /**
     * zz_wofkfaceaccumulative.Workface_ RunningTime (工作面运行累计时间)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String workfaceRunningTime;

    /**
     * zz_wofkfaceaccumulative.Workface_ AccuPropulsion (工作面累计推进度)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String workfaceAccuPropulsion;

    /**
     * zz_wofkfaceaccumulative.Workface_ExcessCoal (工作面过煤量)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String workfaceExcessCoal;

    /**
     * zz_wofkfaceaccumulative.Workface_CycleNumber (工作面累计循环数)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String workfaceCycleNumber;

    /**
     * zz_wofkfaceaccumulative.UpdateTime (更新时间)
     *
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    private String updateTime;

    public String getWorkfaceId() {
        return workfaceId;
    }

    public void setWorkfaceId(String workfaceId) {
        this.workfaceId = workfaceId;
    }

    public String getWorkfaceRunningTime() {
        return workfaceRunningTime;
    }

    public void setWorkfaceRunningTime(String workfaceRunningTime) {
        this.workfaceRunningTime = workfaceRunningTime;
    }

    public String getWorkfaceAccuPropulsion() {
        return workfaceAccuPropulsion;
    }

    public void setWorkfaceAccuPropulsion(String workfaceAccuPropulsion) {
        this.workfaceAccuPropulsion = workfaceAccuPropulsion;
    }

    public String getWorkfaceExcessCoal() {
        return workfaceExcessCoal;
    }

    public void setWorkfaceExcessCoal(String workfaceExcessCoal) {
        this.workfaceExcessCoal = workfaceExcessCoal;
    }

    public String getWorkfaceCycleNumber() {
        return workfaceCycleNumber;
    }

    public void setWorkfaceCycleNumber(String workfaceCycleNumber) {
        this.workfaceCycleNumber = workfaceCycleNumber;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}