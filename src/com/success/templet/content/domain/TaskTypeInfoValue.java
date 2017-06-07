package com.success.templet.content.domain;

public class TaskTypeInfoValue {

	private Integer taskTypeInfoId;
	
	private Integer infoSeq;
	
	private String infoCode;
	
	private String infoName;
	
	private Integer infoTypeId;
	
	private float infoLength;
	
	private Integer sysInfoId;
	
	public String sysInfoName;
	
	private Integer infoKey;

	private Integer agreementFlag;
	
	private String infoKeyName;

	public Integer getTaskTypeInfoId() {
		return taskTypeInfoId;
	}

	public void setTaskTypeInfoId(Integer taskTypeInfoId) {
		this.taskTypeInfoId = taskTypeInfoId;
	}

	public Integer getInfoSeq() {
		return infoSeq;
	}

	public void setInfoSeq(Integer infoSeq) {
		this.infoSeq = infoSeq;
	}

	public String getInfoCode() {
		return infoCode;
	}

	public void setInfoCode(String infoCode) {
		this.infoCode = infoCode;
	}

	public String getInfoName() {
		return infoName;
	}

	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}

	public Integer getInfoTypeId() {
		return infoTypeId;
	}

	public void setInfoTypeId(Integer infoTypeId) {
		this.infoTypeId = infoTypeId;
	}

	public Integer getSysInfoId() {
		return sysInfoId;
	}

	public void setSysInfoId(Integer sysInfoId) {
		this.sysInfoId = sysInfoId;
	}

	public String getSysInfoName() {
		return sysInfoName;
	}

	public void setSysInfoName(String sysInfoName) {
		this.sysInfoName = sysInfoName;
	}

	public Integer getInfoKey() {
		return infoKey;
	}

	public void setInfoKey(Integer infoKey) {
		this.infoKey = infoKey;
	}

	public String getInfoKeyName() {
		return infoKeyName;
	}

	public void setInfoKeyName(String infoKeyName) {
		this.infoKeyName = infoKeyName;
	}

	public float getInfoLength() {
		return infoLength;
	}

	public void setInfoLength(float infoLength) {
		this.infoLength = infoLength;
	}

	public Integer getAgreementFlag() {
		return agreementFlag;
	}

	public void setAgreementFlag(Integer agreementFlag) {
		this.agreementFlag = agreementFlag;
	}
}
