package com.success.task.contract.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.success.task.contract.dao.TaskContractDao;
import com.success.task.contract.domain.TaskContractOrg;
import com.success.task.contract.query.TaskContractQuery;
import com.success.task.contract.service.TaskContractService;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.exception.ServiceException;

@Service("taskContractService")
public class TaskContractServiceImpl implements TaskContractService {

	@Resource(name = "taskContractDao")
	private TaskContractDao taskContractDao;
	
	@Override
	public List<TaskContractOrg> queryTaskContractOrgs(TaskContractQuery query)
			throws ServiceException {
		// TODO Auto-generated method stub
		try{
			List<TaskContractOrg> taskContractOrgs = this.taskContractDao.selectTaskContractOrg(query);
			return taskContractOrgs;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer addTaskContractOrg(TaskContractOrg taskContractOrg)
			throws ServiceException {
		// TODO Auto-generated method stub
		if(taskContractOrg == null) return null;
		return this.taskContractDao.insertTaskContractOrg(taskContractOrg);
	}

	@Override
	public Integer deleteTaskContractOrg(TaskContractQuery query)
			throws ServiceException {
		// TODO Auto-generated method stub
		return this.taskContractDao.deleteTaskContractOrg(query);
	}

}
