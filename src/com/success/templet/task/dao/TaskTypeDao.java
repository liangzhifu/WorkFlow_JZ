package com.success.templet.task.dao;

import org.springframework.stereotype.Repository;

import com.success.templet.task.domain.TaskType;
import com.success.templet.task.query.TaskTypeQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;

@Repository("taskTypeDao")
public class TaskTypeDao extends BaseDao {

	/**
	 * 查询指定ID的 TaskType Domain对象
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public TaskType selectTaskTypeByIdQuery(TaskTypeQuery query) throws DaoException{
		return this.sqlSession.selectOne("taskType.selectByIdQuery", query);
	}
	
}
