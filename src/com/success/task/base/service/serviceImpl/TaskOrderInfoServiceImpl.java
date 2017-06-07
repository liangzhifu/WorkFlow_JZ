package com.success.task.base.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.success.task.base.dao.TaskOrderInfoDao;
import com.success.task.base.domain.TaskOrderInfo;
import com.success.task.base.query.TaskOrderInfoQuery;
import com.success.task.base.service.TaskOrderInfoService;
import com.success.task.detail.query.TaskOrderQuery;
import com.success.web.framework.exception.ServiceException;

@Service("taskOrderInfoService")
public class TaskOrderInfoServiceImpl implements TaskOrderInfoService {

	@Resource(name = "taskOrderInfoDao")
	private TaskOrderInfoDao taskOrderInfoDao;
	
	@Override
	public Integer addTaskOrderInfo(TaskOrderInfo taskOrderInfo)
			throws ServiceException {
		// TODO Auto-generated method stub
		if(taskOrderInfo == null) return null;
		return this.taskOrderInfoDao.insertTaskOrderInfo(taskOrderInfo);
	}

	@Override
	public Integer updateTaskChangeTime(TaskOrderInfo taskOrderInfo)
			throws ServiceException {
		// TODO Auto-generated method stub
		return this.taskOrderInfoDao.updateTaskChangeTime(taskOrderInfo);
	}

	@Override
	public String selectTaskChangeTime(TaskOrderQuery query)
			throws ServiceException {
		// TODO Auto-generated method stub
		return this.taskOrderInfoDao.selectTaskChangeTime(query);
	}

	@Override
	public List<TaskOrderInfo> selectTaskOrderInfo(TaskOrderInfoQuery query)
			throws ServiceException {
		// TODO Auto-generated method stub
		return this.taskOrderInfoDao.selectTaskOrderInfo(query);
	}

}
