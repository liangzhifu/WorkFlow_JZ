package com.success.task.base.domain;

import java.util.Date;

public class TaskWoOrderDetail {

	private Integer woOrderId;
	
	private Integer orderId;
	
	private Integer tacheId;
	
	private String woRefuseReason;
	
	private Integer replyUser;
	
	private String replyUserName;
	
	private Integer isDelay;
	
	private String woOrderStateCode;
	
	private Date replyTime;
	
	private Date completeTime;
	
	private Date createTime;
	
	private Date requireCompleteTime;
	
	private Integer id;
	
	private Integer taskTacheInfoId;
	
	private String woInfoValue;

	public Integer getWoOrderId() {
		return woOrderId;
	}

	public void setWoOrderId(Integer woOrderId) {
		this.woOrderId = woOrderId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getTacheId() {
		return tacheId;
	}

	public void setTacheId(Integer tacheId) {
		this.tacheId = tacheId;
	}

	public String getWoRefuseReason() {
		return woRefuseReason;
	}

	public void setWoRefuseReason(String woRefuseReason) {
		this.woRefuseReason = woRefuseReason;
	}

	public Integer getReplyUser() {
		return replyUser;
	}

	public void setReplyUser(Integer replyUser) {
		this.replyUser = replyUser;
	}

	public Integer getIsDelay() {
		return isDelay;
	}

	public void setIsDelay(Integer isDelay) {
		this.isDelay = isDelay;
	}

	public String getWoOrderStateCode() {
		return woOrderStateCode;
	}

	public void setWoOrderStateCode(String woOrderStateCode) {
		this.woOrderStateCode = woOrderStateCode;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTaskTacheInfoId() {
		return taskTacheInfoId;
	}

	public void setTaskTacheInfoId(Integer taskTacheInfoId) {
		this.taskTacheInfoId = taskTacheInfoId;
	}

	public String getWoInfoValue() {
		return woInfoValue;
	}

	public void setWoInfoValue(String woInfoValue) {
		this.woInfoValue = woInfoValue;
	}

	public String getReplyUserName() {
		return replyUserName;
	}

	public void setReplyUserName(String replyUserName) {
		this.replyUserName = replyUserName;
	}

	public Date getRequireCompleteTime() {
		return requireCompleteTime;
	}

	public void setRequireCompleteTime(Date requireCompleteTime) {
		this.requireCompleteTime = requireCompleteTime;
	}
	
}
