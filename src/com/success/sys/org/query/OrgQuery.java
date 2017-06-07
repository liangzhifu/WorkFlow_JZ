package com.success.sys.org.query;

public class OrgQuery {

	public Integer orgId;

	public Integer parentId;

	public String orgName;

	public String orgPathCode;
	private String orgCreateDate;
	private String orgMark;
	private Integer gradeId;

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
		return "OrgQuery [orgId=" + orgId + ", parentId=" + parentId
				+ ", orgName=" + orgName + ", orgPathCode=" + orgPathCode
				+ ", orgCreateDate=" + orgCreateDate + ", orgMark=" + orgMark
				+ ", gradeId=" + gradeId + "]";
	}
	

}
