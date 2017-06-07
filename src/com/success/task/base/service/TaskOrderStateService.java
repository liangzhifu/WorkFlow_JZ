package com.success.task.base.service;

import java.util.List;

import com.success.task.base.domain.TaskOrderState;
import com.success.web.framework.exception.ServiceException;

public interface TaskOrderStateService {

	public List<TaskOrderState> queryTaskOrderStates() throws ServiceException;
	
}
