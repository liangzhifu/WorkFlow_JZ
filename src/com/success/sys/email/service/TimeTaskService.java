package com.success.sys.email.service;

import java.util.List;

import com.success.sys.email.domain.TimeTask;
import com.success.sys.email.query.TimeTaskQuery;
import com.success.web.framework.exception.ServiceException;

public interface TimeTaskService {

	public List<TimeTask> queryTimeTasks(TimeTaskQuery query);
	
	public Integer insertTimeTask(TimeTask timeTask) throws ServiceException;
	
	public Integer updateTimeTask(TimeTask timeTask) throws ServiceException;
	
	public Integer deleteTimeTask(TimeTask timeTask) throws ServiceException;
	
}
