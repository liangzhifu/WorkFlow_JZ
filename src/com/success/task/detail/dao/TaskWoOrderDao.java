package com.success.task.detail.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.task.base.domain.WoOrderEmailUser;
import com.success.task.detail.domain.TaskWoOrder;
import com.success.task.detail.query.TaskWoOrderQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;
import com.success.web.framework.mybatis.Page;

@Repository("taskWoOrderDao")
public class TaskWoOrderDao extends BaseDao {

	public Page selectPageTaskWoOrder(TaskWoOrderQuery query, int pageIndex, int pageSize) throws DaoException{
		return this.queryForPage("taskWoOrder.selectTaskWoOrder", query, pageIndex, pageSize);
	}

	public List<TaskWoOrder> selectTaskWoOrder(TaskWoOrderQuery query) throws DaoException{
		return this.sqlSession.selectList("taskWoOrder.selectTaskWoOrder", query);
	}
	
	public List<WoOrderEmailUser> selectWoOrderEmailUsers(TaskWoOrderQuery query) throws DaoException{
		return this.sqlSession.selectList("taskWoOrder.selectWoOrderEmailUsers", query);
	}
	
	/**
	 * 插入TaskWoOrder数据
	 * @param taskWoOrder
	 * @return
	 */
	public Integer insertTaskWoOrder(TaskWoOrder taskWoOrder) throws DaoException{
		if(taskWoOrder == null) return -1;
		return this.sqlSession.insert("taskWoOrder.insertTaskWoOrder", taskWoOrder);
	}
	
	/**
	 * 更新TaskWoOrder数据
	 * @param taskWoOrder
	 * @return
	 */
	public Integer updateTaskWoOrder(TaskWoOrder taskWoOrder) throws DaoException{
		if(taskWoOrder == null) return -1;
		return this.sqlSession.update("taskWoOrder.updateTaskWoOrder", taskWoOrder);
	}
	
	/**
	 * 删除TaskWoOrder数据
	 * @param taskWoOrder
	 * @return
	 */
	public Integer deleteTaskWoOrder(TaskWoOrder taskWoOrder) throws DaoException{
		return this.sqlSession.delete("taskWoOrder.deleteTaskWoOrder", taskWoOrder);
	}
	
	public TaskWoOrder selectTaskWoOrderByIdQuery(TaskWoOrderQuery query) throws DaoException{
		return this.sqlSession.selectOne("taskWoOrder.selectByIdQuery", query);
	}

	/**
	 * 查询定单中超时需要发送给领导的邮箱
	 * @param taskWoOrderQuery
	 * @return
	 */
	public String selectDelayManagerEmails(TaskWoOrderQuery taskWoOrderQuery){
		return this.sqlSession.selectOne("taskWoOrder.selectDelayManagerEmails", taskWoOrderQuery);
	}
}
