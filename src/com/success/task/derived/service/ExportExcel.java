package com.success.task.derived.service;

import java.util.List;

import com.success.task.detail.domain.Detail;
import com.success.task.screen.domain.TaskScreen;

public interface ExportExcel {

	public String excelService(List<Detail> detailList, String path) throws Exception;
	
	public String excelScreenService(List<TaskScreen> taskScreenList, String path) throws Exception;
	
}
