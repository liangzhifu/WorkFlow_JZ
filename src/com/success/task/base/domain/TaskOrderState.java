package com.success.task.base.domain;

public class TaskOrderState {

	private Integer orderStateId;
	
	private String orderStateCode;
	
	private String orderStateName;

	public Integer getOrderStateId() {
		return orderStateId;
	}

	public void setOrderStateId(Integer orderStateId) {
		this.orderStateId = orderStateId;
	}

	public String getOrderStateCode() {
		return orderStateCode;
	}

	public void setOrderStateCode(String orderStateCode) {
		this.orderStateCode = orderStateCode;
	}

	public String getOrderStateName() {
		return orderStateName;
	}

	public void setOrderStateName(String orderStateName) {
		this.orderStateName = orderStateName;
	}
	
}
