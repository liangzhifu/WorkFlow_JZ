package com.success.task.detail.query;

import java.util.List;

public class DetailQuery {

	private String publishCode;
	
	private String changeTime;
	
	private String changeTimeBegin;
	
	private String changeTimeEnd;
	
	private String orderStateCode;
	
	private Integer isDelay;
	
	private String createUser;
	
	private String createTimeBegin;
	
	private String createTimeEnd;
	
	private Integer userId;
	
	private Integer orgId;
	
	private Integer isPermission;
	
	private List<Integer> permissionUsers;
	
	private String issueDate;//发行日
	
	private String changeContent;//变更内容
	
	private String productionLine;//生产线
	
	private String carType;//车种名
	
	private String mountingMat;//安装席

	public String getPublishCode() {
		return publishCode;
	}

	public void setPublishCode(String publishCode) {
		this.publishCode = publishCode;
	}

	public Integer getIsDelay() {
		return isDelay;
	}

	public void setIsDelay(Integer isDelay) {
		this.isDelay = isDelay;
	}

	public String getOrderStateCode() {
		return orderStateCode;
	}

	public void setOrderStateCode(String orderStateCode) {
		this.orderStateCode = orderStateCode;
	}

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

	public String getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public List<Integer> getPermissionUsers() {
		return permissionUsers;
	}

	public void setPermissionUsers(List<Integer> permissionUsers) {
		this.permissionUsers = permissionUsers;
	}

	public Integer getIsPermission() {
		return isPermission;
	}

	public void setIsPermission(Integer isPermission) {
		this.isPermission = isPermission;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getChangeContent() {
		return changeContent;
	}

	public void setChangeContent(String changeContent) {
		this.changeContent = changeContent;
	}

	public String getProductionLine() {
		return productionLine;
	}

	public void setProductionLine(String productionLine) {
		this.productionLine = productionLine;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getMountingMat() {
		return mountingMat;
	}

	public void setMountingMat(String mountingMat) {
		this.mountingMat = mountingMat;
	}

	public String getChangeTimeBegin() {
		return changeTimeBegin;
	}

	public void setChangeTimeBegin(String changeTimeBegin) {
		this.changeTimeBegin = changeTimeBegin;
	}

	public String getChangeTimeEnd() {
		return changeTimeEnd;
	}

	public void setChangeTimeEnd(String changeTimeEnd) {
		this.changeTimeEnd = changeTimeEnd;
	}
	
}
