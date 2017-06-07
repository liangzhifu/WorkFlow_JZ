package com.success.task.statistics.query;

public class StatisticsQuery {
	
	private String createUser;
	
	private String createTimeBegin;
	
	private String createTimeEnd;
	
	private String changeTimeBegin;
	
	private String changeTimeEnd;
	
	private String improveTimeBegin;
	
	private String improveTimeEnd;
	
	private Integer userId;
	
	private Integer orgId;
	
	private Integer isDelay;
	
	private String orderStateCode;

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateTimeBegin() {
		return createTimeBegin;
	}

	public void setCreateTimeBegin(String createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrderStateCode() {
		return orderStateCode;
	}

	public void setOrderStateCode(String orderStateCode) {
		this.orderStateCode = orderStateCode;
	}

	public Integer getIsDelay() {
		return isDelay;
	}

	public void setIsDelay(Integer isDelay) {
		this.isDelay = isDelay;
	}

	public String getChangeTimeEnd() {
		return changeTimeEnd;
	}

	public void setChangeTimeEnd(String changeTimeEnd) {
		this.changeTimeEnd = changeTimeEnd;
	}

	public String getChangeTimeBegin() {
		return changeTimeBegin;
	}

	public void setChangeTimeBegin(String changeTimeBegin) {
		this.changeTimeBegin = changeTimeBegin;
	}

	public String getImproveTimeBegin() {
		return improveTimeBegin;
	}

	public void setImproveTimeBegin(String improveTimeBegin) {
		this.improveTimeBegin = improveTimeBegin;
	}

	public String getImproveTimeEnd() {
		return improveTimeEnd;
	}

	public void setImproveTimeEnd(String improveTimeEnd) {
		this.improveTimeEnd = improveTimeEnd;
	}
}
