package com.success.history.base.domain;

import java.util.Date;

public class HistoryAgreementContent {

	private Integer id;
	
	private Integer historyAgreementId;
	
	private Integer seq;
	
	private String problem;
	
	private String improve;
	
	private Integer responsible;
	
	private String responsibleName;
	
	private Date term;
	
	private String termStr;
	
	private String state;
	
	private Integer confirm;
	
	private String confirmName;
	
	private String contentState;
	
	private String refuseReason;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHistoryAgreementId() {
		return historyAgreementId;
	}

	public void setHistoryAgreementId(Integer historyAgreementId) {
		this.historyAgreementId = historyAgreementId;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getImprove() {
		return improve;
	}

	public void setImprove(String improve) {
		this.improve = improve;
	}

	public Integer getResponsible() {
		return responsible;
	}

	public void setResponsible(Integer responsible) {
		this.responsible = responsible;
	}

	public String getResponsibleName() {
		return responsibleName;
	}

	public void setResponsibleName(String responsibleName) {
		this.responsibleName = responsibleName;
	}

	public Date getTerm() {
		return term;
	}

	public void setTerm(Date term) {
		this.term = term;
	}

	public String getTermStr() {
		return termStr;
	}

	public void setTermStr(String termStr) {
		this.termStr = termStr;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getConfirm() {
		return confirm;
	}

	public void setConfirm(Integer confirm) {
		this.confirm = confirm;
	}

	public String getConfirmName() {
		return confirmName;
	}

	public void setConfirmName(String confirmName) {
		this.confirmName = confirmName;
	}

	public String getContentState() {
		return contentState;
	}

	public void setContentState(String contentState) {
		this.contentState = contentState;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}
	
}
