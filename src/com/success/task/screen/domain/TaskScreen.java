package com.success.task.screen.domain;

public class TaskScreen {

	private String changeDate;//变更日期
	
	private String changeTime;//变更时间
	
	private String publishCode;//发行编号
	
	private String productionLine;//生产线
	
	private String carType;//车种
	
	private String mountingMat;//坐席
	
	private String tacheName;//未确认部门
	
	private String confirmName;//确认人
	
	private String contractResult;//立合结果
	
	private Integer orderId;
	
	private Integer colorId;
	
	private String changeContent;//变更内容

	public String getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(String changeDate) {
		this.changeDate = changeDate;
	}

	public String getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}

	public String getPublishCode() {
		return publishCode;
	}

	public void setPublishCode(String publishCode) {
		this.publishCode = publishCode;
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

	public String getTacheName() {
		return tacheName;
	}

	public void setTacheName(String tacheName) {
		this.tacheName = tacheName;
	}

	public String getConfirmName() {
		return confirmName;
	}

	public void setConfirmName(String confirmName) {
		this.confirmName = confirmName;
	}

	public String getContractResult() {
		return contractResult;
	}

	public void setContractResult(String contractResult) {
		this.contractResult = contractResult;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getColorId() {
		return colorId;
	}

	public void setColorId(Integer colorId) {
		this.colorId = colorId;
	}

	public String getChangeContent() {
		return changeContent;
	}

	public void setChangeContent(String changeContent) {
		this.changeContent = changeContent;
	}
	
}
