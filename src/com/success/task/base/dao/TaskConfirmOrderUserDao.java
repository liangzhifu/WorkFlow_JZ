package com.success.task.base.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.task.base.domain.TaskConfirmOrder;
import com.success.task.base.domain.TaskConfirmOrderUser;
import com.success.task.base.query.TaskConfirmOrderUserQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;
import com.success.web.framework.mybatis.Page;

@Repository("taskConfirmOrderUserDao")
public class TaskConfirmOrderUserDao extends BaseDao {

	public List<TaskConfirmOrderUser> selectTaskConfirmOrderUser(
			TaskConfirmOrderUserQuery query) throws DaoException {
		return this.sqlSession.selectList(
				"taskConfirmOrderUser.selectTaskConfirmOrderUser", query);
	}

	public Page selectPageTaskConfirmOrderUser(TaskConfirmOrderUserQuery query,
			int pageIndex, int pageSize) {
		return this.queryForPage(
				"taskConfirmOrderUser.selectTaskConfirmOrderUser", query,
				pageIndex, pageSize);
	}
	/**
	 * 更新TaskConfirmOrder确认人数据
	 * @param taskConfirmOrder
	 * @return
	 */
	public Integer updateTaskConfirmOrderUser(TaskConfirmOrder taskConfirmOrder) throws DaoException{
		if(taskConfirmOrder == null) return -1;
		return this.sqlSession.update("taskConfirmOrderUser.updateTaskConfirmOrderUser", taskConfirmOrder);
	}

}
