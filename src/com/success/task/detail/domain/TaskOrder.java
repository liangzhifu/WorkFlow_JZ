package com.success.task.detail.domain;

import java.util.Date;
import java.util.List;

import com.success.task.base.domain.TaskConfirmOrder;
import com.success.task.base.domain.TaskOrderInfo;
import com.success.templet.task.domain.TaskType;

public class TaskOrder {

	private Integer orderId;
	
	private Integer taskTypeId;
	
	private Integer cycleId;
	
	private Date createTime;
	
	private Integer createUserId;
	
	private Date realCompleteTime;
	
	private Date requireCompleteTime;
	
	private Integer isDelay;
	
	private String orderStateCode;
	
	private String orderContractState;
	
	private String orderTache;
	
	private String confirmOrderStateCode;
	
	private Date noticeTime;
	
	private Date delayNoticeTime;
	
	private Date alarmTime;
	
	private String invalidateText;
	
	private TaskType taskType;
	
	private List<TaskOrderInfo> taskOrderInfoList;
	
	private List<TaskWoOrder> taskWoOrderList;
	
	private List<TaskConfirmOrder> taskConfirmOrderList;
	
	private Integer contractUser;
	
	private Integer version;

	private String agreementState;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getTaskTypeId() {
		return taskTypeId;
	}

	public void setTaskTypeId(Integer taskTypeId) {
		this.taskTypeId = taskTypeId;
	}

	public Integer getCycleId() {
		return cycleId;
	}

	public void setCycleId(Integer cycleId) {
		this.cycleId = cycleId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Date getRealCompleteTime() {
		return realCompleteTime;
	}

	public void setRealCompleteTime(Date realCompleteTime) {
		this.realCompleteTime = realCompleteTime;
	}

	public Date getRequireCompleteTime() {
		return requireCompleteTime;
	}

	public void setRequireCompleteTime(Date requireCompleteTime) {
		this.requireCompleteTime = requireCompleteTime;
	}

	public Integer getIsDelay() {
		return isDelay;
	}

	public void setIsDelay(Integer isDelay) {
		this.isDelay = isDelay;
	}

	public String getOrderStateCode() {
		return orderStateCode;
	}

	public void setOrderStateCode(String orderStateCode) {
		this.orderStateCode = orderStateCode;
	}

	public String getConfirmOrderStateCode() {
		return confirmOrderStateCode;
	}

	public void setConfirmOrderStateCode(String confirmOrderStateCode) {
		this.confirmOrderStateCode = confirmOrderStateCode;
	}

	public Date getDelayNoticeTime() {
		return delayNoticeTime;
	}

	public void setDelayNoticeTime(Date delayNoticeTime) {
		this.delayNoticeTime = delayNoticeTime;
	}

	public Date getAlarmTime() {
		return alarmTime;
	}

	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}

	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	public List<TaskOrderInfo> getTaskOrderInfoList() {
		return taskOrderInfoList;
	}

	public void setTaskOrderInfoList(List<TaskOrderInfo> taskOrderInfoList) {
		this.taskOrderInfoList = taskOrderInfoList;
	}

	public List<TaskWoOrder> getTaskWoOrderList() {
		return taskWoOrderList;
	}

	public void setTaskWoOrderList(List<TaskWoOrder> taskWoOrderList) {
		this.taskWoOrderList = taskWoOrderList;
	}

	public List<TaskConfirmOrder> getTaskConfirmOrderList() {
		return taskConfirmOrderList;
	}

	public void setTaskConfirmOrderList(List<TaskConfirmOrder> taskConfirmOrderList) {
		this.taskConfirmOrderList = taskConfirmOrderList;
	}

	public Date getNoticeTime() {
		return noticeTime;
	}

	public void setNoticeTime(Date noticeTime) {
		this.noticeTime = noticeTime;
	}

	public String getInvalidateText() {
		return invalidateText;
	}

	public void setInvalidateText(String invalidateText) {
		this.invalidateText = invalidateText;
	}

	public String getOrderContractState() {
		return orderContractState;
	}

	public void setOrderContractState(String orderContractState) {
		this.orderContractState = orderContractState;
	}

	public String getOrderTache() {
		return orderTache;
	}

	public void setOrderTache(String orderTache) {
		this.orderTache = orderTache;
	}

	public Integer getContractUser() {
		return contractUser;
	}

	public void setContractUser(Integer contractUser) {
		this.contractUser = contractUser;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getAgreementState() {
		return agreementState;
	}

	public void setAgreementState(String agreementState) {
		this.agreementState = agreementState;
	}
}
