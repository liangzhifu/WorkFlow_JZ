package com.success.task.base.service.serviceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.success.task.base.dao.TaskConfirmOrderUserDao;
import com.success.task.base.domain.TaskConfirmOrder;
import com.success.task.base.query.TaskConfirmOrderUserQuery;
import com.success.task.base.service.TaskConfirmOrderUserService;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;

@Service("taskConfirmOrderUserService")
public class TaskConfirmOrderUserServiceImpl implements
		TaskConfirmOrderUserService {

	@Resource(name = "taskConfirmOrderUserDao")
	private TaskConfirmOrderUserDao taskConfirmOrderUserDao;

	@Override
	public Page getPageTaskConfirmOrderUser(TaskConfirmOrderUserQuery query,
			int pageIndex, int pageSize) throws ServiceException {
		// TODO Auto-generated method stub
		return taskConfirmOrderUserDao.selectPageTaskConfirmOrderUser(query,
				pageIndex, pageSize);
	}
	@Override
	public Integer editTaskConfirmOrderUser(TaskConfirmOrder taskConfirmOrder)
			throws ServiceException {
		// TODO Auto-generated method stub
		if(taskConfirmOrder == null) return null;
		return this.taskConfirmOrderUserDao.updateTaskConfirmOrderUser(taskConfirmOrder);
	}
	@Override
	public TaskConfirmOrderUserQuery setTaskConfirmOrderUserQueryData(
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		TaskConfirmOrderUserQuery query = new TaskConfirmOrderUserQuery();
		String taskInfoValue = request.getParameter("taskInfoValue");
		String userName = request.getParameter("userName");
		if (taskInfoValue != null && !taskInfoValue.equals("")) {
			query.setTaskInfoValue(taskInfoValue);
		}
		if (userName != null && !userName.equals("")) {
			query.setUserName(userName);
		}
		return query;
	}

}
