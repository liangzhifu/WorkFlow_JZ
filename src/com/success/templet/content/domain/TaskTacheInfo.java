package com.success.templet.content.domain;

import java.util.List;

import com.success.common.info.domain.InfoValue;

public class TaskTacheInfo {

	private Integer tacheId;
	
	private Integer infoSeq;
	
	private String infoCode;
	
	private String infoName;
	
	private Integer taskTacheInfoId;
	
	private Integer infoTypeId;
	
	private List<InfoValue> infoValueList;

	public Integer getTacheId() {
		return tacheId;
	}

	public void setTacheId(Integer tacheId) {
		this.tacheId = tacheId;
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

	public Integer getTaskTacheInfoId() {
		return taskTacheInfoId;
	}

	public void setTaskTacheInfoId(Integer taskTacheInfoId) {
		this.taskTacheInfoId = taskTacheInfoId;
	}

	public Integer getInfoTypeId() {
		return infoTypeId;
	}

	public void setInfoTypeId(Integer infoTypeId) {
		this.infoTypeId = infoTypeId;
	}

	public List<InfoValue> getInfoValueList() {
		return infoValueList;
	}

	public void setInfoValueList(List<InfoValue> infoValueList) {
		this.infoValueList = infoValueList;
	}
	
}
