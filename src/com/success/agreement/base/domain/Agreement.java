package com.success.agreement.base.domain;

import java.util.List;

public class Agreement {
	
	private Integer agreementId;
	
	private Integer orderId;

	private String agreementName;
	
	private String agreementState;
	
	private Integer createUserId;
	
	private String createUser;
	
	private Integer trackId;
	
	private String trackName;
	
	private String publishCode;
	
	private String project;
	
	private String cutLOT;
	
	private Integer num;
	
	private String createTime;
	
	private String conclusionState;
	
	private String conclusionMessage;
	
	private String agreementStateName;
	
	private String invalidateText;
	
	private Integer isClose;
	
	private List<AgreementContent> agreementContentList;
	
	private List<AgreementTache> agreementTacheList;

	public String getAgreementName() {
		return agreementName;
	}

	public void setAgreementName(String agreementName) {
		this.agreementName = agreementName;
	}

	public String getPublishCode() {
		return publishCode;
	}

	public void setPublishCode(String publishCode) {
		this.publishCode = publishCode;
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

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getConclusionState() {
		return conclusionState;
	}

	public void setConclusionState(String conclusionState) {
		this.conclusionState = conclusionState;
	}

	public String getConclusionMessage() {
		return conclusionMessage;
	}

	public void setConclusionMessage(String conclusionMessage) {
		this.conclusionMessage = conclusionMessage;
	}

	public String getAgreementStateName() {
		return agreementStateName;
	}

	public void setAgreementStateName(String agreementStateName) {
		this.agreementStateName = agreementStateName;
	}

	public Integer getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(Integer agreementId) {
		this.agreementId = agreementId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getAgreementState() {
		return agreementState;
	}

	public void setAgreementState(String agreementState) {
		this.agreementState = agreementState;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getInvalidateText() {
		return invalidateText;
	}

	public void setInvalidateText(String invalidateText) {
		this.invalidateText = invalidateText;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public List<AgreementContent> getAgreementContentList() {
		return agreementContentList;
	}

	public void setAgreementContentList(List<AgreementContent> agreementContentList) {
		this.agreementContentList = agreementContentList;
	}

	public List<AgreementTache> getAgreementTacheList() {
		return agreementTacheList;
	}

	public void setAgreementTacheList(List<AgreementTache> agreementTacheList) {
		this.agreementTacheList = agreementTacheList;
	}

	public Integer getTrackId() {
		return trackId;
	}

	public void setTrackId(Integer trackId) {
		this.trackId = trackId;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public Integer getIsClose() {
		return isClose;
	}

	public void setIsClose(Integer isClose) {
		this.isClose = isClose;
	}

}
