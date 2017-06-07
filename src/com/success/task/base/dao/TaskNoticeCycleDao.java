package com.success.task.base.dao;

import org.springframework.stereotype.Repository;

import com.success.task.base.domain.TaskNoticeCycle;
import com.success.task.base.query.TaskNoticeCycleQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;

@Repository("taskNoticeCycleDao")
public class TaskNoticeCycleDao extends BaseDao {
	
	/**
	 * 插入TaskNoticeCycle数据
	 * @param taskNoticeCycle
	 * @return
	 */
	public Integer insertTaskNoticeCycle(TaskNoticeCycle taskNoticeCycle) throws DaoException{
		if(taskNoticeCycle == null) return -1;
		return this.sqlSession.insert("taskNoticeCycle.insertTaskNoticeCycle", taskNoticeCycle);
	}
	
	/**
	 * 更新TaskNoticeCycle数据
	 * @param taskNoticeCycle
	 * @return
	 */
	public Integer updateTaskNoticeCycle(TaskNoticeCycle taskNoticeCycle) throws DaoException{
		if(taskNoticeCycle == null) return -1;
		return this.sqlSession.update("taskNoticeCycle.updateTaskNoticeCycle", taskNoticeCycle);
	}
	
	/**
	 * 删除TaskNoticeCycle数据
	 * @param taskNoticeCycle
	 * @return
	 */
	public Integer deleteTaskNoticeCycle(TaskNoticeCycle taskNoticeCycle) throws DaoException{
		return this.sqlSession.delete("taskNoticeCycle.deleteTaskNoticeCycle", taskNoticeCycle);
	}
	
	/**
	 * 查询指定ID的 TaskNoticeCycle Domain对象
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public TaskNoticeCycle selectTaskNoticeCycleByIdQuery(TaskNoticeCycleQuery query) throws DaoException{
		return this.sqlSession.selectOne("taskNoticeCycle.selectByIdQuery", query);
	}
	
}
