package com.success.sys.email.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.sys.email.domain.TimeTask;
import com.success.sys.email.query.TimeTaskQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;

@Repository("timeTaskDao")
public class TimeTaskDao extends BaseDao {

	public List<TimeTask> selectTimeTask(TimeTaskQuery query) throws DaoException{
		return this.sqlSession.selectList("timeTask.selectTimeTask", query);
	}
	
	public Integer insertTimeTask(TimeTask timeTask){
		if(timeTask == null) return -1;
		return this.sqlSession.insert("timeTask.insertTimeTask", timeTask);
	}
	
	public Integer updateTimeTask(TimeTask timeTask){
		if(timeTask == null) return -1;
		return this.sqlSession.update("timeTask.updateTimeTask", timeTask);
	}

	public Integer deleteTimeTask(TimeTask timeTask){
		return this.sqlSession.delete("timeTask.deleteTimeTask", timeTask);
	}
	
}
