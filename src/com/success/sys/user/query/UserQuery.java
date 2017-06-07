package com.success.sys.user.query;

public class UserQuery {

	// 用户ID
	private Integer userId;
	// 登录用户名
	private String userCode;
	// 用户名
	private String userName;
	// 手机号
	private String mobileTel;
	//组织id
	private String orgId;
	//工号
	private String userWorkId;
	//是否查询下级组织
	private String isCheckAll;
	//是否领导
	private String isHeader;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobileTel() {
		return mobileTel;
	}
	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getUserWorkId() {
		return userWorkId;
	}
	public void setUserWorkId(String userWorkId) {
		this.userWorkId = userWorkId;
	}
	
	
	public String getIsCheckAll() {
		return isCheckAll;
	}
	public void setIsCheckAll(String isCheckAll) {
		this.isCheckAll = isCheckAll;
	}
	
	public String getIsHeader() {
		return isHeader;
	}
	public void setIsHeader(String isHeader) {
		this.isHeader = isHeader;
	}
	@Override
	public String toString() {
		return "UserQuery [userId=" + userId + ", userCode=" + userCode
				+ ", userName=" + userName + ", mobileTel=" + mobileTel
				+ ", orgId=" + orgId + ", userWorkId=" + userWorkId
				+ ", isCheckAll=" + isCheckAll + ", isHeader=" + isHeader + "]";
	}

	
	
	
}
