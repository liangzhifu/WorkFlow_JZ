package com.success.task.screen.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.success.task.screen.domain.TaskScreen;
import com.success.task.screen.query.ScreenQuery;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;

public interface TaskScreenShowService {

	public Page queryPage(int pageIndex, int pageSize) throws ServiceException;
	
	public Long queryPageCount() throws ServiceException;
	
	public Long queryOverTimeCount() throws ServiceException;
	
	public List<TaskScreen> getTaskScreenList(ScreenQuery query) throws ServiceException;
	
	public ScreenQuery setScreenQueryData(HttpServletRequest request);
	
	public String doExportExcle(ScreenQuery query, String path)throws Exception;
	
}
