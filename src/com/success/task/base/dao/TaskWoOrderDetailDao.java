package com.success.task.base.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.task.base.domain.TaskWoOrderDetail;
import com.success.task.base.query.TaskWoOrderDetailQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;

@Repository("taskWoOrderDetailDao")
public class TaskWoOrderDetailDao extends BaseDao {

	public List<TaskWoOrderDetail> selectTaskWoOrderDetail(TaskWoOrderDetailQuery query) throws DaoException{
		return this.sqlSession.selectList("taskWoOrderDetail.selectTaskWoOrderDetail", query);
	}
	
}
