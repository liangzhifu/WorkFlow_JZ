package com.success.sys.fun.domain;

public class UserFun {
	//id
	private Integer userFunId;
	//用户id
	private Integer userId;
	//用户名称
	private String userName;
	//功能id
	private Integer funId;
	//功能名称
	private String funName;
	
	public Integer getUserFunId() {
		return userFunId;
	}
	public void setUserFunId(Integer userFunId) {
		this.userFunId = userFunId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getFunId() {
		return funId;
	}
	public void setFunId(Integer funId) {
		this.funId = funId;
	}
	public String getFunName() {
		return funName;
	}
	public void setFunName(String funName) {
		this.funName = funName;
	}
	@Override
	public String toString() {
		return "UserFun [userFunId=" + userFunId + ", userId=" + userId
				+ ", userName=" + userName + ", funId=" + funId + ", funName="
				+ funName + "]";
	}
}
