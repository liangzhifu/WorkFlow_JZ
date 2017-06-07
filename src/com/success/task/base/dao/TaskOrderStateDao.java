package com.success.task.base.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.task.base.domain.TaskOrderState;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;

@Repository("taskOrderStateDao")
public class TaskOrderStateDao extends BaseDao {

	public List<TaskOrderState> selectTaskOrderState() throws DaoException{
		return this.sqlSession.selectList("taskOrderState.selectTaskOrderState");
	}
	
}
