package com.success.templet.run.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.success.templet.run.dao.TaskTypeRunDao;
import com.success.templet.run.domain.TaskTypeRun;
import com.success.templet.run.query.TaskTypeRunQuery;
import com.success.templet.run.service.TaskTypeRunService;
import com.success.web.framework.exception.DaoException;

@Service("taskTypeRunService")
public class TaskTypeRunServiceImpl implements TaskTypeRunService {

	@Resource(name = "taskTypeRunDao")
	private TaskTypeRunDao taskTypeRunDao;
	
	@Override
	public List<TaskTypeRun> queryTaskTypeRuns(TaskTypeRunQuery query) {
		// TODO Auto-generated method stub
		try{
			List<TaskTypeRun> taskTypeRuns = this.taskTypeRunDao.selectTaskTypeRun(query);
			return taskTypeRuns;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

}
