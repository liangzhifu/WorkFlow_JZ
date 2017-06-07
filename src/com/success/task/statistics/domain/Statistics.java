package com.success.task.statistics.domain;

public class Statistics {
	
	private String name;

	private Integer totalNum;
	
	private Integer completeNum;
	
	private Integer notCompleteNum;
	
	private Integer delayNum;
	
	private Integer completeDelayNum;
	
	private Integer allDelayNum;
	
	private Integer tacheId;
	
	private Integer ngNum;
	
	private Integer noAgreementNum;
	
	private Integer improveNum;
	
	private Integer noShowNum;

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getCompleteNum() {
		return completeNum;
	}

	public void setCompleteNum(Integer completeNum) {
		this.completeNum = completeNum;
	}

	public Integer getNotCompleteNum() {
		return notCompleteNum;
	}

	public void setNotCompleteNum(Integer notCompleteNum) {
		this.notCompleteNum = notCompleteNum;
	}

	public Integer getDelayNum() {
		return delayNum;
	}

	public void setDelayNum(Integer delayNum) {
		this.delayNum = delayNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCompleteDelayNum() {
		return completeDelayNum;
	}

	public void setCompleteDelayNum(Integer completeDelayNum) {
		this.completeDelayNum = completeDelayNum;
	}

	public Integer getAllDelayNum() {
		return allDelayNum;
	}

	public void setAllDelayNum(Integer allDelayNum) {
		this.allDelayNum = allDelayNum;
	}

	public Integer getTacheId() {
		return tacheId;
	}

	public void setTacheId(Integer tacheId) {
		this.tacheId = tacheId;
	}

	public Integer getNgNum() {
		return ngNum;
	}

	public void setNgNum(Integer ngNum) {
		this.ngNum = ngNum;
	}

	public Integer getNoAgreementNum() {
		return noAgreementNum;
	}

	public void setNoAgreementNum(Integer noAgreementNum) {
		this.noAgreementNum = noAgreementNum;
	}

	public Integer getImproveNum() {
		return improveNum;
	}

	public void setImproveNum(Integer improveNum) {
		this.improveNum = improveNum;
	}

	public Integer getNoShowNum() {
		return noShowNum;
	}

	public void setNoShowNum(Integer noShowNum) {
		this.noShowNum = noShowNum;
	}
}
