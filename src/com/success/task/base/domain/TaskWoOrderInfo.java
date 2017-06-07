package com.success.task.base.domain;

public class TaskWoOrderInfo {

	private Integer id;
	
	private Integer woOrderId;
	
	private Integer taskTacheInfoId;
	
	private String woInfoValue;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWoOrderId() {
		return woOrderId;
	}

	public void setWoOrderId(Integer woOrderId) {
		this.woOrderId = woOrderId;
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
	
}
