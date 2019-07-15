package com.zzmj.pojo.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class PosEntity {

	@JSONField(name = "Value")
	private Object value;

	@JSONField(name = "Time")
	private String time;

	public PosEntity() {
		super();
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
