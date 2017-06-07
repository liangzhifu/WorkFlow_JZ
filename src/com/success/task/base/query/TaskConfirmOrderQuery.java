package com.success.task.base.query;

public class TaskConfirmOrderQuery {

	private Integer confirmOrderId;
	
	private Integer orderId;
	
	private Integer confirmUserSeq;
	
	public TaskConfirmOrderQuery(){
		this.confirmOrderId = null;
		this.orderId = null;
		this.confirmUserSeq = null;
	}

	public Integer getConfirmOrderId() {
		return confirmOrderId;
	}

	public void setConfirmOrderId(Integer confirmOrderId) {
		this.confirmOrderId = confirmOrderId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getConfirmUserSeq() {
		return confirmUserSeq;
	}

	public void setConfirmUserSeq(Integer confirmUserSeq) {
		this.confirmUserSeq = confirmUserSeq;
	}
	
}
