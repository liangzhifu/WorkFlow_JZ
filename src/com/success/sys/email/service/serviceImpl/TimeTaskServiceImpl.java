package com.success.sys.email.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.success.sys.email.dao.TimeTaskDao;
import com.success.sys.email.domain.TimeTask;
import com.success.sys.email.query.TimeTaskQuery;
import com.success.sys.email.service.TimeTaskService;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.exception.ServiceException;

@Service("timeTaskService")
public class TimeTaskServiceImpl implements TimeTaskService {

	@Resource(name = "timeTaskDao")
	private TimeTaskDao timeTaskDao;
	
	@Override
	public List<TimeTask> queryTimeTasks(TimeTaskQuery query) {
		// TODO Auto-generated method stub
		try{
			List<TimeTask> timeTasks = this.timeTaskDao.selectTimeTask(query);
			return timeTasks;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer insertTimeTask(TimeTask timeTask) throws ServiceException {
		// TODO Auto-generated method stub
		return this.timeTaskDao.insertTimeTask(timeTask);
	}

	@Override
	public Integer updateTimeTask(TimeTask timeTask) throws ServiceException {
		// TODO Auto-generated method stub
		return this.timeTaskDao.updateTimeTask(timeTask);
	}

	@Override
	public Integer deleteTimeTask(TimeTask timeTask) throws ServiceException {
		// TODO Auto-generated method stub
		return this.timeTaskDao.deleteTimeTask(timeTask);
	}

}
