package com.success.task.base.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.task.base.domain.TaskConfirmOrder;
import com.success.task.base.query.TaskConfirmOrderQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;

@Repository("taskConfirmOrderDao")
public class TaskConfirmOrderDao extends BaseDao {

	public List<TaskConfirmOrder> selectTaskConfirmOrder(TaskConfirmOrderQuery query) throws DaoException{
		return this.sqlSession.selectList("taskConfirmOrder.selectTaskConfirmOrder", query);
	}
	
	/**
	 * 更新TaskConfirmOrder数据
	 * @param taskOrderInfo
	 * @return
	 */
	public Integer insertTaskConfirmOrder(TaskConfirmOrder taskConfirmOrder) throws DaoException{
		if(taskConfirmOrder == null) return -1;
		return this.sqlSession.update("taskConfirmOrder.insertTaskConfirmOrder", taskConfirmOrder);
	}
	
	/**
	 * 更新TaskConfirmOrder数据
	 * @param taskConfirmOrder
	 * @return
	 */
	public Integer updateTaskConfirmOrder(TaskConfirmOrder taskConfirmOrder) throws DaoException{
		if(taskConfirmOrder == null) return -1;
		return this.sqlSession.update("taskConfirmOrder.updateTaskConfirmOrder", taskConfirmOrder);
	}
	
	/**
	 * 删除TaskConfirmOrder数据
	 * @param taskConfirmOrder
	 * @return
	 */
	public Integer deleteTaskConfirmOrder(TaskConfirmOrder taskConfirmOrder) throws DaoException{
		return this.sqlSession.delete("taskConfirmOrder.deleteTaskConfirmOrder", taskConfirmOrder);
	}
	
	/**
	 * 查询指定ID的 TaskConfirmOrder Domain对象
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public TaskConfirmOrder selectTaskConfirmOrderByIdQuery(TaskConfirmOrderQuery query) throws DaoException{
		return this.sqlSession.selectOne("taskConfirmOrder.selectByIdQuery", query);
	}
	
	public TaskConfirmOrder selectTaskConfirmOrderByUserSeq(TaskConfirmOrderQuery query) throws DaoException{
		return this.sqlSession.selectOne("taskConfirmOrder.selectByUserSeq", query);
	}
	
}
