package com.zzmj.pojo.entity;

/**
 * 
 * @author yangquan
 *矿井通知通告数据
 */
public class ZZMineMessage {

	/**
	 * ID  主键
	 */
	private String id;
	
	/**
	 * Message_Id
	 */
	private String messageId;
	
	
	/**
	 * User_Id	用户Id
	 */
	private String userId;
	
	/**
	 *  Org_Id 公司Id
	 */
	private String orgId;
	
	/**
	 * Workface_Id 工作面Id
	 */
	private String workfaceId;
	
	/**
	 * Message	 通知通告内容
	 */
	private String message;
	
	/**
	 * MessageType  通知通告类型  1.矿井通知  2.生产处通知 (集团)
	 */
	private String messageType;
	
	/**
	 * UpdateTime 更新时间
	 */
	private String updateTime;
	
	/**
	 * IsDel  删除标识 0：未删除 1：已经删除
	 */
	private String isDel;

	
	
	public ZZMineMessage() {
		super();
	}
	

	public ZZMineMessage(String id, String messageId, String userId,
			String orgId, String workfaceId, String message,
			String messageType, String updateTime, String isDel) {
		super();
		this.id = id;
		this.messageId = messageId;
		this.userId = userId;
		this.orgId = orgId;
		this.workfaceId = workfaceId;
		this.message = message;
		this.messageType = messageType;
		this.updateTime = updateTime;
		this.isDel = isDel;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getWorkfaceId() {
		return workfaceId;
	}

	public void setWorkfaceId(String workfaceId) {
		this.workfaceId = workfaceId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	@Override
	public String toString() {
		return "ZZMineMessage [id=" + id + ", messageId=" + messageId
				+ ", userId=" + userId + ", orgId=" + orgId + ", workfaceId="
				+ workfaceId + ", message=" + message + ", messageType="
				+ messageType + ", updateTime=" + updateTime + ", isDel="
				+ isDel + "]";
	}
}
