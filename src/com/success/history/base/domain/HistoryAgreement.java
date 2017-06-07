package com.success.history.base.domain;

import java.util.Date;
import java.util.List;

import com.success.agreement.base.domain.AgreementTache;

public class HistoryAgreement {

	private Integer agreementId;
	
	private Integer orderId;

	private String agreementName;
	
	private String agreementState;
	
	private Integer createUserId;
	
	private Integer trackId;
	
	private String trackName;
	
	private String createUser;
	
	private String publishCode;
	
	private String project;
	
	private String cutLOT;
	
	private Integer num;
	
	private Date createTime;
	
	private String createTimeStr;
	
	private String conclusionState;
	
	private String conclusionMessage;
	
	private String agreementStateName;
	
	private String invalidateText;
	
	private List<HistoryAgreementContent> historyAgreementContentList;
	
	private List<AgreementTache> agreementTacheList;
	
	private Integer id;
	
	private Integer editUser;
	
	private String editUserName;
	
	private String editTimeStr;
	
	private String editType;

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

	public String getAgreementName() {
		return agreementName;
	}

	public void setAgreementName(String agreementName) {
		this.agreementName = agreementName;
	}

	public String getAgreementState() {
		return agreementState;
	}

	public void setAgreementState(String agreementState) {
		this.agreementState = agreementState;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
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

	public String getInvalidateText() {
		return invalidateText;
	}

	public void setInvalidateText(String invalidateText) {
		this.invalidateText = invalidateText;
	}

	public List<HistoryAgreementContent> getHistoryAgreementContentList() {
		return historyAgreementContentList;
	}

	public void setHistoryAgreementContentList(
			List<HistoryAgreementContent> historyAgreementContentList) {
		this.historyAgreementContentList = historyAgreementContentList;
	}

	public List<AgreementTache> getAgreementTacheList() {
		return agreementTacheList;
	}

	public void setAgreementTacheList(List<AgreementTache> agreementTacheList) {
		this.agreementTacheList = agreementTacheList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEditUser() {
		return editUser;
	}

	public void setEditUser(Integer editUser) {
		this.editUser = editUser;
	}

	public String getEditUserName() {
		return editUserName;
	}

	public void setEditUserName(String editUserName) {
		this.editUserName = editUserName;
	}

	public String getEditTimeStr() {
		return editTimeStr;
	}

	public void setEditTimeStr(String editTimeStr) {
		this.editTimeStr = editTimeStr;
	}

	public String getEditType() {
		return editType;
	}

	public void setEditType(String editType) {
		this.editType = editType;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
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
}
