package com.success.sys.org.domain;

public class OrgFun {
	private Integer orgId;
	private Integer funId;
	private String orgFun;
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Integer getFunId() {
		return funId;
	}
	public void setFunId(Integer funId) {
		this.funId = funId;
	}
	public String getOrgFun() {
		return orgFun;
	}
	public void setOrgFun(String orgFun) {
		this.orgFun = orgFun;
	}
	@Override
	public String toString() {
		return "OrgFun [orgId=" + orgId + ", funId=" + funId + ", orgFun="
				+ orgFun + "]";
	}
	
}
