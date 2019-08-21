package com.zzmj.pojo.entity;

public class Rate {
	
	/**
	 * 组织机构名称
	 */
	private String orgName;
	
	/**
	 * 公司自动跟机率
	 */
	private String rate;
	
	/**
	 * 跟机率统计周期
	 */
	private int cycle;
	
	/**
	 * 统计开始时间
	 */
	private long startTime;

	/**
	 * 统计结束时间
	 */
	private long endTime;


	
	public Rate() {
		super();
	}
	
	public Rate(String orgName, String rate, int cycle, long startTime,
			long endTime) {
		super();
		this.orgName = orgName;
		this.rate = rate;
		this.cycle = cycle;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Rate(String orgName, String rate, long startTime, long endTime) {
		super();
		this.orgName = orgName;
		this.rate = rate;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public int getCycle() {
		return cycle;
	}

	public void setCycle(int cycle) {
		this.cycle = cycle;
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
