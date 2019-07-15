package com.zzmj.pojo.entity;

import java.io.Serializable;

public class ZZWorkfaceconfigEntity implements Serializable {
    private static final long serialVersionUID = 892747454420045481L;

    /**
     * ZZ_WorkfaceConfig.Id (主键)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String id;

    /**
     * ZZ_WorkfaceConfig.Workface_Id (工作面Id)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String workfaceId;

    /**
     * ZZ_WorkfaceConfig.Belt_Type (皮带类型:0:单条皮带;1:多条皮带)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String beltType;

    /**
     * ZZ_WorkfaceConfig.Font_MinPressure (前柱压力最小值)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String fontMinpressure;

    /**
     * ZZ_WorkfaceConfig.Font_MaxPressure (前柱压力最大值)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String fontMaxpressure;

    /**
     * ZZ_WorkfaceConfig.Back_MinPressure (后柱压力最小值)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String backMinpressure;

    /**
     * ZZ_WorkfaceConfig.Back_MaxPressure (后柱压力最大值)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String backMaxpressure;

    /**
     * ZZ_WorkfaceConfig.Support_Dir (支架方向:0:小端;1:大端)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private Integer supportDir;

    /**
     * ZZ_WorkfaceConfig.Support_Count (支架数量)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private Integer supportCount;

    /**
     * ZZ_WorkfaceConfig.Conveyor_Dir (运输机方向:0:右边;1:左边)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private Integer conveyorDir;

    /**
     * ZZ_WorkfaceConfig.Pressure_ChartType
     * (压力图显示:10:显示前柱压力;01:显示后柱压力;11:显示前后柱压力)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String pressureCharttype;

    /**
     * ZZ_WorkfaceConfig.ShearerPos_CacheTime (煤机缓存查询时间)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String shearerposCachetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkfaceId() {
        return workfaceId;
    }

    public void setWorkfaceId(String workfaceId) {
        this.workfaceId = workfaceId;
    }

    public String getBeltType() {
        return beltType;
    }

    public void setBeltType(String beltType) {
        this.beltType = beltType;
    }

    public String getFontMinpressure() {
        return fontMinpressure;
    }

    public void setFontMinpressure(String fontMinpressure) {
        this.fontMinpressure = fontMinpressure;
    }

    public String getFontMaxpressure() {
        return fontMaxpressure;
    }

    public void setFontMaxpressure(String fontMaxpressure) {
        this.fontMaxpressure = fontMaxpressure;
    }

    public String getBackMinpressure() {
        return backMinpressure;
    }

    public void setBackMinpressure(String backMinpressure) {
        this.backMinpressure = backMinpressure;
    }

    public String getBackMaxpressure() {
        return backMaxpressure;
    }

    public void setBackMaxpressure(String backMaxpressure) {
        this.backMaxpressure = backMaxpressure;
    }

    public Integer getSupportDir() {
        return supportDir;
    }

    public void setSupportDir(Integer supportDir) {
        this.supportDir = supportDir;
    }

    public Integer getSupportCount() {
        return supportCount;
    }

    public void setSupportCount(Integer supportCount) {
        this.supportCount = supportCount;
    }

    public Integer getConveyorDir() {
        return conveyorDir;
    }

    public void setConveyorDir(Integer conveyorDir) {
        this.conveyorDir = conveyorDir;
    }

    public String getPressureCharttype() {
        return pressureCharttype;
    }

    public void setPressureCharttype(String pressureCharttype) {
        this.pressureCharttype = pressureCharttype;
    }

    public String getShearerposCachetime() {
        return shearerposCachetime;
    }

    public void setShearerposCachetime(String str) {
        this.shearerposCachetime = str;
    }

}