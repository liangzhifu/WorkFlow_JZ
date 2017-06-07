package com.success.common.info.domain;

public class InfoValue {

	private Integer infoId;
	
	private String infoName;
	
	private Integer infoMesId;//1代办定单信息，2代表工单信息
	
	private Integer priMesId;//工单或者定单信息Id
	
	private Integer infoKey;//0默认，1多选框其他

	private Integer agreementFlag;//是否关联立合
	
	private String infoKeyName;

	public Integer getInfoId() {
		return infoId;
	}

	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}

	public String getInfoName() {
		return infoName;
	}

	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}

	public Integer getInfoMesId() {
		return infoMesId;
	}

	public void setInfoMesId(Integer infoMesId) {
		this.infoMesId = infoMesId;
	}

	public Integer getPriMesId() {
		return priMesId;
	}

	public void setPriMesId(Integer priMesId) {
		this.priMesId = priMesId;
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

	public Integer getAgreementFlag() {
		return agreementFlag;
	}

	public void setAgreementFlag(Integer agreementFlag) {
		this.agreementFlag = agreementFlag;
	}
}
