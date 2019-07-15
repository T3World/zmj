package com.zzmj.pojo.entity;

import java.io.Serializable;

public class ZZWorkfaceconfigEntity implements Serializable {
	/**
	 * zz_workfaceconfig.Id (主键)
	 *
	 * @ibatorgenerated 2019-06-21 14:13:29
	 */
	private String id;

	/**
	 * zz_workfaceconfig.Workface_Id (工作面Id)
	 *
	 * @ibatorgenerated 2019-06-21 14:13:29
	 */
	private String workfaceId;

	/**
	 * zz_workfaceconfig.Belt_Type (皮带类型:0:单条皮带;1:多条皮带)
	 *
	 * @ibatorgenerated 2019-06-21 14:13:29
	 */
	private String beltType;

	/**
	 * zz_workfaceconfig.Font_MinPressure (前柱压力最小值)
	 *
	 * @ibatorgenerated 2019-06-21 14:13:29
	 */
	private String fontMinpressure;

	/**
	 * zz_workfaceconfig.Font_MaxPressure (前柱压力最大值)
	 *
	 * @ibatorgenerated 2019-06-21 14:13:29
	 */
	private String fontMaxpressure;

	/**
	 * zz_workfaceconfig.Back_MinPressure (后柱压力最小值)
	 *
	 * @ibatorgenerated 2019-06-21 14:13:29
	 */
	private String backMinpressure;

	/**
	 * zz_workfaceconfig.Back_MaxPressure (后柱压力最大值)
	 *
	 * @ibatorgenerated 2019-06-21 14:13:29
	 */
	private String backMaxpressure;

	/**
	 * zz_workfaceconfig.Support_Dir (支架方向:0:小端;1:大端)
	 *
	 * @ibatorgenerated 2019-06-21 14:13:29
	 */
	private Integer supportDir;

	/**
	 * zz_workfaceconfig.Support_Count (支架数量)
	 *
	 * @ibatorgenerated 2019-06-21 14:13:29
	 */
	private Integer supportCount;

	/**
	 * zz_workfaceconfig.Conveyor_Dir (运输机方向:0:右边;1:左边)
	 *
	 * @ibatorgenerated 2019-06-21 14:13:29
	 */
	private Integer conveyorDir;

	/**
	 * zz_workfaceconfig.Pressure_ChartType (压力图显示:10:显示前柱压力;01:显示后柱压力;11:显示前后柱压力)
	 *
	 * @ibatorgenerated 2019-06-21 14:13:29
	 */
	private String pressureCharttype;

	/**
	 * zz_workfaceconfig.Workface_Length (工作面长度)
	 *
	 * @ibatorgenerated 2019-06-21 14:13:29
	 */
	private String workfaceLength;

	/**
	 * zz_workfaceconfig.ShearerPos_CacheTime (煤机缓存查询时间)
	 *
	 * @ibatorgenerated 2019-06-21 14:13:29
	 */
	private String shearerposCachetime;

	/**
	 * zz_workfaceconfig.Workface_AbleLength (工作面可采走向长度)
	 *
	 * @ibatorgenerated 2019-06-21 14:13:29
	 */
	private String workfaceAbleLength;

	/**
	 * zz_workfaceconfig.Workface_CoalThickness (煤层厚度)
	 *
	 * @ibatorgenerated 2019-06-21 14:13:29
	 */
	private String workfaceCoalThickness;

	/**
	 * zz_workfaceconfig.Workface_Inclination (工作面倾角)
	 *
	 * @ibatorgenerated 2019-06-21 14:13:29
	 */
	private String workfaceInclination;

	/**
	 * zz_workfaceconfig.Workface_AverageHeight (平均采高)
	 *
	 * @ibatorgenerated 2019-06-21 14:13:29
	 */
	private String workfaceAverageHeight;

	/**
	 * zz_workfaceconfig.Workface_ StartTime (工作面开始时间)
	 *
	 * @ibatorgenerated 2019-06-21 14:13:29
	 */
	private String workfaceStartTime;
	
	/**
	 * zz_workfaceconfig.Morning_Shift
	 * 早班时间范围 格式为 HH:mm-HH:mm'
	 */
	private String morningShift;
	
	/**
	 *  zz_workfaceconfig.Afternoon_Shift
	 *  (午班时间范围 格式为 HH:mm-HH:mm',)
	 */
	private String afternoonShift;
	
	/**
	 * zz_workfaceconfig.Night_Shift
	 * (晚班时间范围 格式为 HH:mm-HH:mm)
	 */
	private String nightShift;

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

	public String getWorkfaceLength() {
		return workfaceLength;
	}

	public void setWorkfaceLength(String workfaceLength) {
		this.workfaceLength = workfaceLength;
	}

	public String getShearerposCachetime() {
		return shearerposCachetime;
	}

	public void setShearerposCachetime(String shearerposCachetime) {
		this.shearerposCachetime = shearerposCachetime;
	}

	public String getWorkfaceAbleLength() {
		return workfaceAbleLength;
	}

	public void setWorkfaceAbleLength(String workfaceAbleLength) {
		this.workfaceAbleLength = workfaceAbleLength;
	}

	public String getWorkfaceCoalThickness() {
		return workfaceCoalThickness;
	}

	public void setWorkfaceCoalThickness(String workfaceCoalThickness) {
		this.workfaceCoalThickness = workfaceCoalThickness;
	}

	public String getWorkfaceInclination() {
		return workfaceInclination;
	}

	public void setWorkfaceInclination(String workfaceInclination) {
		this.workfaceInclination = workfaceInclination;
	}

	public String getWorkfaceAverageHeight() {
		return workfaceAverageHeight;
	}

	public void setWorkfaceAverageHeight(String workfaceAverageHeight) {
		this.workfaceAverageHeight = workfaceAverageHeight;
	}

	public String getWorkfaceStartTime() {
		return workfaceStartTime;
	}

	public void setWorkfaceStartTime(String workfaceStartTime) {
		this.workfaceStartTime = workfaceStartTime;
	}

	public String getMorningShift() {
		return morningShift;
	}

	public void setMorningShift(String morningShift) {
		this.morningShift = morningShift;
	}

	public String getAfternoonShift() {
		return afternoonShift;
	}

	public void setAfternoonShift(String afternoonShift) {
		this.afternoonShift = afternoonShift;
	}

	public String getNightShift() {
		return nightShift;
	}

	public void setNightShift(String nightShift) {
		this.nightShift = nightShift;
	}
	

	
}