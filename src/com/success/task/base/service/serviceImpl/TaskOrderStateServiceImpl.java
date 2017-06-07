package com.success.task.base.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.success.task.base.dao.TaskOrderStateDao;
import com.success.task.base.domain.TaskOrderState;
import com.success.task.base.service.TaskOrderStateService;
import com.success.web.framework.exception.DaoException;

@Service("taskOrderStateService")
public class TaskOrderStateServiceImpl implements TaskOrderStateService {

	@Resource(name = "taskOrderStateDao")
	private TaskOrderStateDao taskOrderStateDao;
	
	@Override
	public List<TaskOrderState> queryTaskOrderStates() {
		// TODO Auto-generated method stub
		try{
			List<TaskOrderState> taskOrderStates = this.taskOrderStateDao.selectTaskOrderState();
			return taskOrderStates;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

}
