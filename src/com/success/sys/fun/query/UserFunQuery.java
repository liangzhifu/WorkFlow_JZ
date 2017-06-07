package com.success.sys.fun.query;

public class UserFunQuery {
	private Integer userFunId;
	private Integer userId;
	private Integer funId;
	//查询有或无权限
	private String checkHaveAccess;
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
	public Integer getFunId() {
		return funId;
	}
	public void setFunId(Integer funId) {
		this.funId = funId;
	}
	public String getCheckHaveAccess() {
		return checkHaveAccess;
	}
	public void setCheckHaveAccess(String checkHaveAccess) {
		this.checkHaveAccess = checkHaveAccess;
	}
	@Override
	public String toString() {
		return "UserFunQuery [userFunId=" + userFunId + ", userId=" + userId
				+ ", funId=" + funId + ", checkHaveAccess=" + checkHaveAccess
				+ "]";
	}
	
}
