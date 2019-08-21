package com.zzmj.pojo.entity;

public class MineMessage {

	//通知通告表主键
	private String id;
	//通知Id
	private String messageId;
	//发表通知的用户Id,
	private String userId;
	//发表通知的用户名称,
	private String userName;
	//组织机构Id,
	private String orgId;
	//矿井名称,  组织机构名称
	private String orgName;
	//工作面Id(只有通知类型时矿井时才有此值)
	private String workfaceId;
	//工作面名称(只有通知类型时矿井时才有此值), 
	private String workfaceName;
	//通知内容,
	private String message;
	//:通知类型,
	private String messageType;
	//发表时间
	private String updateTime;
	
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getWorkfaceId() {
		return workfaceId;
	}
	public void setWorkfaceId(String workfaceId) {
		this.workfaceId = workfaceId;
	}
	public String getWorkfaceName() {
		return workfaceName;
	}
	public void setWorkfaceName(String workfaceName) {
		this.workfaceName = workfaceName;
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
	@Override
	public String toString() {
		return "MineMessage [messageId=" + messageId + ", userId=" + userId
				+ ", userName=" + userName + ", orgId=" + orgId + ", orgName="
				+ orgName + ", workfaceId=" + workfaceId + ", workfaceName="
				+ workfaceName + ", message=" + message + ", messageType="
				+ messageType + ", updateTime=" + updateTime + "]";
	}
	
	
}
