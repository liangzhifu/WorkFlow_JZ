package com.success.sys.webservice.service;

public interface AsmxClient {

	public String createAccountService(String userName)throws Exception;
	
	public String updateAccountService(String userName, String appStatus)throws Exception;
	
	public String staffInfoService(String userName)throws Exception;
	
}
