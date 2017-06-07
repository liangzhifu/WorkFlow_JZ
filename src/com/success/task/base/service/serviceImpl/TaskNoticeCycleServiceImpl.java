package com.success.task.base.service.serviceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.success.task.base.dao.TaskNoticeCycleDao;
import com.success.task.base.domain.TaskNoticeCycle;
import com.success.task.base.query.TaskNoticeCycleQuery;
import com.success.task.base.service.TaskNoticeCycleService;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.util.ServletAPIUtil;

@Service("taskNoticeCycleService")
public class TaskNoticeCycleServiceImpl implements TaskNoticeCycleService {

	@Resource(name = "taskNoticeCycleDao")
	private TaskNoticeCycleDao taskNoticeCycleDao;
	
	@Override
	public Integer addTaskNoticeCycle(TaskNoticeCycle taskNoticeCycle)
			throws ServiceException {
		// TODO Auto-generated method stub
		if(taskNoticeCycle == null) return null;
		return this.taskNoticeCycleDao.insertTaskNoticeCycle(taskNoticeCycle);
	}

	@Override
	public Integer editTaskNoticeCycle(TaskNoticeCycle taskNoticeCycle)
			throws ServiceException {
		// TODO Auto-generated method stub
		if(taskNoticeCycle == null) return null;
		return this.taskNoticeCycleDao.updateTaskNoticeCycle(taskNoticeCycle);
	}

	@Override
	public Integer deleteTaskNoticeCycle(TaskNoticeCycle taskNoticeCycle)
			throws ServiceException {
		// TODO Auto-generated method stub
		return this.deleteTaskNoticeCycle(taskNoticeCycle);
	}

	@Override
	public TaskNoticeCycle selectTaskNoticeCycleByIdQuery(
			TaskNoticeCycleQuery query) throws ServiceException {
		// TODO Auto-generated method stub
		return this.taskNoticeCycleDao.selectTaskNoticeCycleByIdQuery(query);
	}

	@Override
	public TaskNoticeCycleQuery setTaskNoticeCycleQueryData(
			HttpServletRequest request) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaskNoticeCycle setTaskNoticeCycleData(HttpServletRequest request) {
		// TODO Auto-generated method stub
		TaskNoticeCycle taskNoticeCycle = new TaskNoticeCycle();
//		Integer isNotice = ServletAPIUtil.getIntegerParameter("is_notice", request, 0);
//		Integer isAlarm = ServletAPIUtil.getIntegerParameter("is_alarm", request, 0);
//		Integer isDelay = ServletAPIUtil.getIntegerParameter("is_delay", request, 0);
		Integer noticeCyele = ServletAPIUtil.getIntegerParameter("notice_cycle", request, 0);
//		Integer delayCycle = ServletAPIUtil.getIntegerParameter("delay_cycle", request, 0);
		Integer alarmTime = ServletAPIUtil.getIntegerParameter("alarm_time", request, 0);
		
//		taskNoticeCycle.setIsNotice(isNotice);
//
//		taskNoticeCycle.setIsAlarm(isAlarm);
//
//		taskNoticeCycle.setIsDelay(isDelay);	

		taskNoticeCycle.setNoticeCycle(noticeCyele);

//		taskNoticeCycle.setDelayCycle(delayCycle);
//
		taskNoticeCycle.setAlarmTime(alarmTime);

		return taskNoticeCycle;
	}

}
