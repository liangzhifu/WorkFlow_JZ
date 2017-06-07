package com.success.task.base.domain;

import java.util.Date;

public class TaskConfirmOrder {

	private Integer confirmOrderId;
	
	private Integer orderId;
	
	private String confirmOrderStateCode;
	
	private Integer confirmUserId;
	
	private String confirmUserName;
	
	private Integer confirmUserSeq;
	
	private String runType;
	
	private Date confirmTime;
	
	private String runCode;

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

	public String getConfirmOrderStateCode() {
		return confirmOrderStateCode;
	}

	public void setConfirmOrderStateCode(String confirmOrderStateCode) {
		this.confirmOrderStateCode = confirmOrderStateCode;
	}

	public Integer getConfirmUserId() {
		return confirmUserId;
	}

	public void setConfirmUserId(Integer confirmUserId) {
		this.confirmUserId = confirmUserId;
	}

	public Integer getConfirmUserSeq() {
		return confirmUserSeq;
	}

	public void setConfirmUserSeq(Integer confirmUserSeq) {
		this.confirmUserSeq = confirmUserSeq;
	}

	public String getRunType() {
		return runType;
	}

	public void setRunType(String runType) {
		this.runType = runType;
	}

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public String getConfirmUserName() {
		return confirmUserName;
	}

	public void setConfirmUserName(String confirmUserName) {
		this.confirmUserName = confirmUserName;
	}

	public String getRunCode() {
		return runCode;
	}

	public void setRunCode(String runCode) {
		this.runCode = runCode;
	}
	
}
