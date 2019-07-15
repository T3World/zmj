package com.zmj.web.microservice.zaokuangContract.entity;

import java.io.Serializable;

public class ZZGlobalEntity implements Serializable {
    private static final long serialVersionUID = -6709958364934667410L;

    /**
     * ZZ_Global.Id (主键)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String id;

    /**
     * ZZ_Global.Global_Id (全局参数)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String globalId;

    /**
     * ZZ_Global.Global_ShearerPosTime (煤机轨迹查询时间)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String globalShearerpostime;

    /**
     * ZZ_Global.Global_CheTime (浓度查询时间)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String globalChetime;

    /**
     * ZZ_Global.Global_DiffPressureTime (压差查询时间)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String globalDiffpressuretime;

    /**
     * ZZ_Global.Global_MinePressureTime (矿压查询时间)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String globalMinepressuretime;

    /**
     * ZZ_Global.Global_SettingPressureTime (初撑力查询时间)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String globalSettingpressuretime;

    public String getId() {
        System.out.println(id);
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGlobalId() {
        System.out.println(globalId);
        return globalId;
    }

    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }

    public String getGlobalShearerpostime() {
        System.out.println(globalShearerpostime);
        return globalShearerpostime;
    }

    public void setGlobalShearerpostime(String globalShearerpostime) {
        this.globalShearerpostime = globalShearerpostime;
    }

    public String getGlobalChetime() {
        return globalChetime;
    }

    public void setGlobalChetime(String globalChetime) {
        this.globalChetime = globalChetime;
    }

    public String getGlobalDiffpressuretime() {
        return globalDiffpressuretime;
    }

    public void setGlobalDiffpressuretime(String globalDiffpressuretime) {
        this.globalDiffpressuretime = globalDiffpressuretime;
    }

    public String getGlobalMinepressuretime() {
        return globalMinepressuretime;
    }

    public void setGlobalMinepressuretime(String globalMinepressuretime) {
        this.globalMinepressuretime = globalMinepressuretime;
    }

    public String getGlobalSettingpressuretime() {
        return globalSettingpressuretime;
    }

    public void setGlobalSettingpressuretime(String globalSettingpressuretime) {
        this.globalSettingpressuretime = globalSettingpressuretime;
    }
}