package com.success.task.base.service;

import com.success.task.base.domain.TaskWoOrderInfo;
import com.success.web.framework.exception.ServiceException;

public interface TaskWoOrderInfoService {

	public Integer addTaskWoOrderInfo(TaskWoOrderInfo taskWoOrderInfo) throws ServiceException;
	
	public Integer editTaskWoOrderInfo(TaskWoOrderInfo taskWoOrderInfo) throws ServiceException;
	
}
