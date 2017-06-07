package com.success.templet.tache.domain;

import java.util.List;

import com.success.templet.content.domain.TaskTacheInfo;

public class TaskTache {
	
	private Integer taskTypeId;

	private Integer tacheId;
	
	private String tacheName;
	
	private String tacheCode;
	
	private Integer orgId;
	
	private String orgName;
	
	private Integer staffId;
	
	private String staffName;
	
	private List<TaskTacheInfo> taskTacheInfo;//环节信息

	public Integer getTacheId() {
		return tacheId;
	}

	public void setTacheId(Integer tacheId) {
		this.tacheId = tacheId;
	}

	public String getTacheName() {
		return tacheName;
	}

	public void setTacheName(String tacheName) {
		this.tacheName = tacheName;
	}

	public String getTacheCode() {
		return tacheCode;
	}

	public void setTacheCode(String tacheCode) {
		this.tacheCode = tacheCode;
	}

	public List<TaskTacheInfo> getTaskTacheInfo() {
		return taskTacheInfo;
	}

	public void setTaskTacheInfo(List<TaskTacheInfo> taskTacheInfo) {
		this.taskTacheInfo = taskTacheInfo;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getTaskTypeId() {
		return taskTypeId;
	}

	public void setTaskTypeId(Integer taskTypeId) {
		this.taskTypeId = taskTypeId;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	
}
