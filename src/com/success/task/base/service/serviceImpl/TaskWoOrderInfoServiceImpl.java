package com.success.task.base.service.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.success.task.base.dao.TaskWoOrderInfoDao;
import com.success.task.base.domain.TaskWoOrderInfo;
import com.success.task.base.service.TaskWoOrderInfoService;
import com.success.web.framework.exception.ServiceException;

@Service("taskWoOrderInfoService")
public class TaskWoOrderInfoServiceImpl implements TaskWoOrderInfoService {

	@Resource(name = "taskWoOrderInfoDao")
	private TaskWoOrderInfoDao taskWoOrderInfoDao;
	
	@Override
	public Integer addTaskWoOrderInfo(TaskWoOrderInfo taskWoOrderInfo)
			throws ServiceException {
		// TODO Auto-generated method stub
		if(taskWoOrderInfo == null) return null;
		return this.taskWoOrderInfoDao.insertTaskWoOrderInfo(taskWoOrderInfo);
	}

	@Override
	public Integer editTaskWoOrderInfo(TaskWoOrderInfo taskWoOrderInfo)
			throws ServiceException {
		// TODO Auto-generated method stub
		if(taskWoOrderInfo == null) return null;
		return this.taskWoOrderInfoDao.updateTaskWoOrderInfo(taskWoOrderInfo);
	}

}
