package com.success.task.base.query;

public class TaskConfirmOrderUserQuery {
	private String taskInfoValue;
	private String userName;
	public String getTaskInfoValue() {
		return taskInfoValue;
	}
	public void setTaskInfoValue(String taskInfoValue) {
		this.taskInfoValue = taskInfoValue;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "TaskConfirmOrderUserQuery [taskInfoValue=" + taskInfoValue
				+ ", userName=" + userName + "]";
	}
	
	
	
}
