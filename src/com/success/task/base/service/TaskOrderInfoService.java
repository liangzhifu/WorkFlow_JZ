package com.success.task.base.service;

import java.util.List;

import com.success.task.base.domain.TaskOrderInfo;
import com.success.task.base.query.TaskOrderInfoQuery;
import com.success.task.detail.query.TaskOrderQuery;
import com.success.web.framework.exception.ServiceException;

public interface TaskOrderInfoService {

	public Integer addTaskOrderInfo(TaskOrderInfo taskOrderInfo) throws ServiceException;
	
	public Integer updateTaskChangeTime(TaskOrderInfo taskOrderInfo) throws ServiceException;
	
	public String selectTaskChangeTime(TaskOrderQuery query) throws ServiceException;
	
	public List<TaskOrderInfo> selectTaskOrderInfo(TaskOrderInfoQuery query) throws ServiceException;
}
