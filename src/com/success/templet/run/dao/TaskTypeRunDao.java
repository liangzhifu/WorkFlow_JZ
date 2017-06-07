package com.success.templet.run.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.templet.run.domain.TaskTypeRun;
import com.success.templet.run.query.TaskTypeRunQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;

@Repository("taskTypeRunDao")
public class TaskTypeRunDao extends BaseDao {

	public List<TaskTypeRun> selectTaskTypeRun(TaskTypeRunQuery query) throws DaoException{
		return this.sqlSession.selectList("taskTypeRun.selectTaskTypeRun", query);
	}
	
}
