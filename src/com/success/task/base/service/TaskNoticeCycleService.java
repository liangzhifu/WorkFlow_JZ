package com.success.task.base.service;

import javax.servlet.http.HttpServletRequest;

import com.success.task.base.domain.TaskNoticeCycle;
import com.success.task.base.query.TaskNoticeCycleQuery;
import com.success.web.framework.exception.ServiceException;

public interface TaskNoticeCycleService {
	
	public Integer addTaskNoticeCycle(TaskNoticeCycle taskNoticeCycle) throws ServiceException;
	
	public Integer editTaskNoticeCycle(TaskNoticeCycle taskNoticeCycle) throws ServiceException;
	
	public Integer deleteTaskNoticeCycle(TaskNoticeCycle taskNoticeCycle) throws ServiceException;
	
	public TaskNoticeCycle selectTaskNoticeCycleByIdQuery(TaskNoticeCycleQuery query) throws ServiceException;
	
	public TaskNoticeCycleQuery setTaskNoticeCycleQueryData(HttpServletRequest request) throws ServiceException ;
	
	public TaskNoticeCycle setTaskNoticeCycleData(HttpServletRequest request);
	
}
