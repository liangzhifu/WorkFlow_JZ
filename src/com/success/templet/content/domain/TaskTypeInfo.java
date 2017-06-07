package com.success.templet.content.domain;

import java.util.List;

import com.success.common.info.domain.InfoValue;

public class TaskTypeInfo {

	private Integer taskTypeId;
	
	private Integer infoSeq;
	
	private String infoCode;
	
	private String infoName;
	
	private Integer taskTypeInfoId;
	
	private Integer infoTypeId;
	
	private float infoLength;
	
	private List<InfoValue> infoValueList;

	public Integer getTaskTypeId() {
		return taskTypeId;
	}

	public void setTaskTypeId(Integer taskTypeId) {
		this.taskTypeId = taskTypeId;
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

	public Integer getTaskTypeInfoId() {
		return taskTypeInfoId;
	}

	public void setTaskTypeInfoId(Integer taskTypeInfoId) {
		this.taskTypeInfoId = taskTypeInfoId;
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

	public float getInfoLength() {
		return infoLength;
	}

	public void setInfoLength(float infoLength) {
		this.infoLength = infoLength;
	}
	
}
