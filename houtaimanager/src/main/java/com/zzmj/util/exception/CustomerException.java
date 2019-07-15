package com.zzmj.util.exception;

public class CustomerException extends Exception {

	/**
	 * 自定义异常类用来替代Exception
	 */
	private static final long serialVersionUID = -2985157854697142072L;
	private String retCd; // 异常对应的返回码
	private String msgDes; // 异常对应的描述信息

	public CustomerException() {
		super();
	}

	public CustomerException(String message) {
		super(message);
		msgDes = message;
	}

	public CustomerException(String retCd, String msgDes) {
		super();
		this.retCd = retCd;
		this.msgDes = msgDes;
	}

	public String getRetCd() {
		return retCd;
	}

	public String getMsgDes() {
		return msgDes;
	}
}