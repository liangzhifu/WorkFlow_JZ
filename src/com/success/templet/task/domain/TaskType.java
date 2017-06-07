package com.success.templet.task.domain;

import java.util.List;

import com.success.templet.content.domain.TaskTypeInfo;
import com.success.templet.run.domain.TaskTypeRun;
import com.success.templet.tache.domain.TaskTache;

public class TaskType {

	private Integer taskTypeId;
	
	private String taskTypeName;
	
	private String taskTypeWordPath;
	
	private Integer cycleId;
	
	private List<TaskTypeInfo> taskTypeInfo;//模板信息
	
	private List<TaskTache> taskTache;//模板环节
	
	private List<TaskTypeRun> taskTypeRunList;
	
	private Integer version;

	public Integer getTaskTypeId() {
		return taskTypeId;
	}

	public void setTaskTypeId(Integer taskTypeId) {
		this.taskTypeId = taskTypeId;
	}

	public String getTaskTypeName() {
		return taskTypeName;
	}

	public void setTaskTypeName(String taskTypeName) {
		this.taskTypeName = taskTypeName;
	}

	public String getTaskTypeWordPath() {
		return taskTypeWordPath;
	}

	public void setTaskTypeWordPath(String taskTypeWordPath) {
		this.taskTypeWordPath = taskTypeWordPath;
	}

	public Integer getCycleId() {
		return cycleId;
	}

	public void setCycleId(Integer cycleId) {
		this.cycleId = cycleId;
	}

	public List<TaskTypeInfo> getTaskTypeInfo() {
		return taskTypeInfo;
	}

	public void setTaskTypeInfo(List<TaskTypeInfo> taskTypeInfo) {
		this.taskTypeInfo = taskTypeInfo;
	}

	public List<TaskTache> getTaskTache() {
		return taskTache;
	}

	public void setTaskTache(List<TaskTache> taskTache) {
		this.taskTache = taskTache;
	}

	public List<TaskTypeRun> getTaskTypeRunList() {
		return taskTypeRunList;
	}

	public void setTaskTypeRunList(List<TaskTypeRun> taskTypeRunList) {
		this.taskTypeRunList = taskTypeRunList;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
