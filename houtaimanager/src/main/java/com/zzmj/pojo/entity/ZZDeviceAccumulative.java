package com.zzmj.pojo.entity;

import com.zzmj.pojo.dto.WorkfaceDeviceAccumulative;

/**
 * 设备累积量表'
 * @author yangquan
 *
 */
public class ZZDeviceAccumulative {
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

    public ZZDeviceAccumulative() {
    }

    public ZZDeviceAccumulative(WorkfaceDeviceAccumulative workfaceDeviceAccumulative) {
        this();
        String id = workfaceDeviceAccumulative.getId();
        String deviceId = workfaceDeviceAccumulative.getDeviceId();
        String cumulativeCoalWeight = workfaceDeviceAccumulative.getCumulativeCoalWeight();
        String cumulativeLoadCycles = workfaceDeviceAccumulative.getCumulativeLoadCycles();
        String cumulativeRunTime = workfaceDeviceAccumulative.getCumulativeRunTime();
        if (id!=null)
            this.id = id;
        if (deviceId!=null)
            this.deviceId = deviceId;
        if(cumulativeCoalWeight!=null)
            this.cumulativeCoalWeight = cumulativeCoalWeight;
        if(cumulativeLoadCycles!=null)
            this.cumulativeLoadCycles = cumulativeLoadCycles;
        if(cumulativeRunTime!=null)
            this.cumulativeRunTime = cumulativeRunTime;
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
	@Override
	public String toString() {
		return "ZZDeviceAccumulative [id=" + id + ", deviceId=" + deviceId
				+ ", cumulativeRunTime=" + cumulativeRunTime
				+ ", cumulativeLoadCycles=" + cumulativeLoadCycles
				+ ", cumulativeCoalWeight=" + cumulativeCoalWeight + "]";
	}

	public ZZDeviceAccumulative(String id, String deviceId,
			String cumulativeRunTime, String cumulativeLoadCycles,
			String cumulativeCoalWeight) {
		super();
		this.id = id;
		this.deviceId = deviceId;
		this.cumulativeRunTime = cumulativeRunTime;
		this.cumulativeLoadCycles = cumulativeLoadCycles;
		this.cumulativeCoalWeight = cumulativeCoalWeight;
	}
}
