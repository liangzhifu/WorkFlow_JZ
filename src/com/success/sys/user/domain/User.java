package com.success.sys.user.domain;

public class User {

	// 用户ID
	private Integer userId;
	// 登录用户名
	private String userCode;
	// 用户名
	private String userName;
	// 工号
	private String userWorkId;
	// 性别
	private String userSex;
	// 密码
	private String password;
	// 组织ID
	private Integer orgId;
	private String orgName;
	// email
	private String email;
	// 手机
	private String mobileTel;
	// 是否领导
	private String isHeader;
	// 备注
	private String userMark;
	//
	private Integer deleteState;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileTel() {
		return mobileTel;
	}

	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}

	public String getUserWorkId() {
		return userWorkId;
	}

	public void setUserWorkId(String userWorkId) {
		this.userWorkId = userWorkId;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getIsHeader() {
		return isHeader;
	}

	public void setIsHeader(String isHeader) {
		this.isHeader = isHeader;
	}

	public String getUserMark() {
		return userMark;
	}

	public void setUserMark(String userMark) {
		this.userMark = userMark;
	}

	public Integer getDeleteState() {
		return deleteState;
	}

	public void setDeleteState(Integer deleteState) {
		this.deleteState = deleteState;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userCode=" + userCode
				+ ", userName=" + userName + ", userWorkId=" + userWorkId
				+ ", userSex=" + userSex + ", password=" + password
				+ ", orgId=" + orgId + ", email=" + email + ", mobileTel="
				+ mobileTel + ", isHeader=" + isHeader + ", userMark="
				+ userMark + ", deleteState=" + deleteState + "]";
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
