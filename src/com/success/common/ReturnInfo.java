package com.success.common;

public class ReturnInfo {

	public boolean returnFlag;
	
	public String returnMessage;
	
	public ReturnInfo(){
		this.returnFlag = true;
		this.returnMessage = "";
	}

	public boolean getReturnFlag() {
		return returnFlag;
	}

	public void setReturnFlag(boolean returnFlag) {
		this.returnFlag = returnFlag;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}
	
}
