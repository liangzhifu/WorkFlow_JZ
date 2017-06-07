package com.success.templet.tache.query;

public class TacheUserQuery {

	private Integer OrderId;

	private Integer tacheId;

	private String tacheIdIsNot;

	//0--删除，1--新增
	private String managerFlag;

	public Integer getTacheId() {
		return tacheId;
	}
	public void setTacheId(Integer tacheId) {
		this.tacheId = tacheId;
	}
	public String getTacheIdIsHave() {
		return tacheIdIsNot;
	}
	public void setTacheIdIsHave(String tacheIdIsNot) {
		this.tacheIdIsNot = tacheIdIsNot;
	}
	@Override
	public String toString() {
		return "TacheUserQuery [tacheId=" + tacheId + ", tacheIdIsNot="
				+ tacheIdIsNot + "]";
	}

	public Integer getOrderId() {
		return OrderId;
	}

	public void setOrderId(Integer orderId) {
		OrderId = orderId;
	}

	public String getManagerFlag() {
		return managerFlag;
	}

	public void setManagerFlag(String managerFlag) {
		this.managerFlag = managerFlag;
	}
}
