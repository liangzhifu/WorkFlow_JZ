package com.success.templet.tache.domain;

public class TacheUser {
	private Integer id;
	private Integer tacheId;
	private Integer userId;
	private String userName;
	private Integer orgId;
	private String orgName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTacheId() {
		return tacheId;
	}
	public void setTacheId(Integer tacheId) {
		this.tacheId = tacheId;
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
	@Override
	public String toString() {
		return "TacheUser [id=" + id + ", tacheId=" + tacheId + ", userId="
				+ userId + ", userName=" + userName + ", orgId=" + orgId
				+ ", orgName=" + orgName + "]";
	}
	
	
	
}
