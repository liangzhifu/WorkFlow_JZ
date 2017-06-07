package com.success.task.base.domain;

public class WoOrderEmailUser {

	private Integer woOrderId;
	
	private Integer userId;
	
	private String userName;
	
	private String userEmail;

	public Integer getWoOrderId() {
		return woOrderId;
	}

	public void setWoOrderId(Integer woOrderId) {
		this.woOrderId = woOrderId;
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
}
