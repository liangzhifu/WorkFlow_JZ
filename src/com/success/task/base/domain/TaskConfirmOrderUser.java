package com.success.task.base.domain;

public class TaskConfirmOrderUser {
	private Integer orderId;
	private Integer taskTypeInfoId;
	private String taskInfoValue;
	private Integer confirmOrderId;
	private Integer confirmUserId;
	private String confirmOrderStateCode;
	private String runCode;
	private Integer userId;
	private String userName;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getTaskTypeInfoId() {
		return taskTypeInfoId;
	}

	public void setTaskTypeInfoId(Integer taskTypeInfoId) {
		this.taskTypeInfoId = taskTypeInfoId;
	}

	public String getTaskInfoValue() {
		return taskInfoValue;
	}

	public void setTaskInfoValue(String taskInfoValue) {
		this.taskInfoValue = taskInfoValue;
	}

	public Integer getConfirmOrderId() {
		return confirmOrderId;
	}

	public void setConfirmOrderId(Integer confirmOrderId) {
		this.confirmOrderId = confirmOrderId;
	}

	public Integer getConfirmUserId() {
		return confirmUserId;
	}

	public void setConfirmUserId(Integer confirmUserId) {
		this.confirmUserId = confirmUserId;
	}

	public String getConfirmOrderStateCode() {
		return confirmOrderStateCode;
	}

	public void setConfirmOrderStateCode(String confirmOrderStateCode) {
		this.confirmOrderStateCode = confirmOrderStateCode;
	}

	public String getRunCode() {
		return runCode;
	}

	public void setRunCode(String runCode) {
		this.runCode = runCode;
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

	@Override
	public String toString() {
		return "TaskConfirmOrderUser [orderId=" + orderId + ", taskTypeInfoId="
				+ taskTypeInfoId + ", taskInfoValue=" + taskInfoValue
				+ ", confirmOrderId=" + confirmOrderId + ", confirmUserId="
				+ confirmUserId + ", confirmOrderStateCode="
				+ confirmOrderStateCode + ", runCode=" + runCode + ", userId="
				+ userId + ", userName=" + userName + "]";
	}

}
