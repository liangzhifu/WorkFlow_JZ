package com.success.task.base.service;

import javax.servlet.http.HttpServletRequest;

import com.success.task.base.domain.TaskConfirmOrder;
import com.success.task.base.query.TaskConfirmOrderUserQuery;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;

public interface TaskConfirmOrderUserService {

	public Page getPageTaskConfirmOrderUser(TaskConfirmOrderUserQuery query,
			int pageIndex, int pageSize) throws ServiceException;

	public TaskConfirmOrderUserQuery setTaskConfirmOrderUserQueryData(
			HttpServletRequest request);
	
	public Integer editTaskConfirmOrderUser(TaskConfirmOrder taskConfirmOrder) throws ServiceException;
}
