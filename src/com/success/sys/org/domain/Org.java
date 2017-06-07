package com.success.sys.org.domain;

public class Org {

	public Integer orgId;
	
	public Integer parentId;
	
	public String orgName;
	
	public String orgPathCode;
	private String orgCreateDate;
	private String orgMark;
	private Integer gradeId;
	private Integer dpcoiAddJurisdiction;

	private Integer agreementTrack;

	private Integer dpcoiQuaisAct;

	private Integer taskEditJurisdiction;

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgPathCode() {
		return orgPathCode;
	}

	public void setOrgPathCode(String orgPathCode) {
		this.orgPathCode = orgPathCode;
	}

	public String getOrgCreateDate() {
		return orgCreateDate;
	}

	public void setOrgCreateDate(String orgCreateDate) {
		this.orgCreateDate = orgCreateDate;
	}

	public String getOrgMark() {
		return orgMark;
	}

	public void setOrgMark(String orgMark) {
		this.orgMark = orgMark;
	}
	
	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	@Override
	public String toString() {
		return "Org [orgId=" + orgId + ", parentId=" + parentId + ", orgName="
				+ orgName + ", orgPathCode=" + orgPathCode + ", orgCreateDate="
				+ orgCreateDate + ", orgMark=" + orgMark + ", gradeId="
				+ gradeId + "]";
	}


	public Integer getDpcoiAddJurisdiction() {
		return dpcoiAddJurisdiction;
	}

	public void setDpcoiAddJurisdiction(Integer dpcoiAddJurisdiction) {
		this.dpcoiAddJurisdiction = dpcoiAddJurisdiction;
	}

	public Integer getAgreementTrack() {
		return agreementTrack;
	}

	public void setAgreementTrack(Integer agreementTrack) {
		this.agreementTrack = agreementTrack;
	}

	public Integer getDpcoiQuaisAct() {
		return dpcoiQuaisAct;
	}

	public void setDpcoiQuaisAct(Integer dpcoiQuaisAct) {
		this.dpcoiQuaisAct = dpcoiQuaisAct;
	}

	public Integer getTaskEditJurisdiction() {
		return taskEditJurisdiction;
	}

	public void setTaskEditJurisdiction(Integer taskEditJurisdiction) {
		this.taskEditJurisdiction = taskEditJurisdiction;
	}
}
