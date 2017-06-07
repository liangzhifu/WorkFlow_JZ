package com.success.task.contract.service;

import java.util.List;

import com.success.task.contract.domain.TaskContractOrg;
import com.success.task.contract.query.TaskContractQuery;
import com.success.web.framework.exception.ServiceException;

public interface TaskContractService {

	public List<TaskContractOrg> queryTaskContractOrgs(TaskContractQuery query) throws ServiceException;

	public Integer addTaskContractOrg(TaskContractOrg taskContractOrg) throws ServiceException;
	
	public Integer deleteTaskContractOrg(TaskContractQuery query) throws ServiceException;
	
}
