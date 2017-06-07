package com.success.agreement.base.query;

public class AgreementQuery {

	private String publishCode;
	
	private String project;
	
	private String cutLOT;
	
	private String createUser;
	
	private String createTime;
	
	private String agreementState;

	public String getPublishCode() {
		return publishCode;
	}

	public void setPublishCode(String publishCode) {
		this.publishCode = publishCode;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getAgreementState() {
		return agreementState;
	}

	public void setAgreementState(String agreementState) {
		this.agreementState = agreementState;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getCutLOT() {
		return cutLOT;
	}

	public void setCutLOT(String cutLOT) {
		this.cutLOT = cutLOT;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
}
