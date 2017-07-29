package com.success.task.base.query;

public class TaskWoOrderDetailQuery {

	private Integer orderId;
	
	private Integer userId;

	private String woOrderStateCode;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getWoOrderStateCode() {
		return woOrderStateCode;
	}

	public void setWoOrderStateCode(String woOrderStateCode) {
		this.woOrderStateCode = woOrderStateCode;
	}
}
