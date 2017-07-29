package com.success.sys.email.domain;

public class TimeTask {

	private Integer timeTaskId;
	
	private Integer woOrderId;
	
	private Integer orderId;
	
	private Integer confirmOrderId;
	
	private Integer noticeType;
	
	private Integer noticeColor;
	
	private Integer deleteState;
	
	private String taskTypeName;
	
	private String publishCode;
	
	private Integer userId;
	
	private String userName;
	
	private String userEmail;
	
	private Integer failedNum;
	
	private String comment;

	private String emailTitle;

	public Integer getTimeTaskId() {
		return timeTaskId;
	}

	public void setTimeTaskId(Integer timeTaskId) {
		this.timeTaskId = timeTaskId;
	}

	public Integer getWoOrderId() {
		return woOrderId;
	}

	public void setWoOrderId(Integer woOrderId) {
		this.woOrderId = woOrderId;
	}

	public Integer getConfirmOrderId() {
		return confirmOrderId;
	}

	public void setConfirmOrderId(Integer confirmOrderId) {
		this.confirmOrderId = confirmOrderId;
	}

	public Integer getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(Integer noticeType) {
		this.noticeType = noticeType;
	}

	public Integer getNoticeColor() {
		return noticeColor;
	}

	public void setNoticeColor(Integer noticeColor) {
		this.noticeColor = noticeColor;
	}

	public String getTaskTypeName() {
		return taskTypeName;
	}

	public void setTaskTypeName(String taskTypeName) {
		this.taskTypeName = taskTypeName;
	}

	public String getPublishCode() {
		return publishCode;
	}

	public void setPublishCode(String publishCode) {
		this.publishCode = publishCode;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Integer getFailedNum() {
		return failedNum;
	}

	public void setFailedNum(Integer failedNum) {
		this.failedNum = failedNum;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getDeleteState() {
		return deleteState;
	}

	public void setDeleteState(Integer deleteState) {
		this.deleteState = deleteState;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getEmailTitle() {
		return emailTitle;
	}

	public void setEmailTitle(String emailTitle) {
		this.emailTitle = emailTitle;
	}
}
