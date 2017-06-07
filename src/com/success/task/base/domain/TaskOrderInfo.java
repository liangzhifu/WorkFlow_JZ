package com.success.task.base.domain;

public class TaskOrderInfo {

	private Integer id;
	
	private Integer orderId;
	
	private Integer taskTypeInfoId;
	
	private String taskInfoValue;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
	
}
