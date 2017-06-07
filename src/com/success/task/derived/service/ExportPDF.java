package com.success.task.derived.service;

public interface ExportPDF {

	public String exportAllPDF(int orderId, String path) throws Exception;
	
	public String exportPDF(int orderId, String path) throws Exception;
	
	public String exportPDF2(Integer orderId, Integer agreementId, String path) throws Exception;
	
}
