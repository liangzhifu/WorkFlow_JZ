package com.success.task.detail.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.task.detail.domain.TaskOrder;
import com.success.task.detail.query.TaskOrderQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;
import com.success.web.framework.mybatis.Page;

@Repository("taskOrderDao")
public class TaskOrderDao extends BaseDao {

	public Page selectPageTaskOrder(TaskOrderQuery query, int pageIndex, int pageSize) throws DaoException{
		return this.queryForPage("taskOrder.selectTaskOrder", query, pageIndex, pageSize);
	}

	public List<TaskOrder> selectTaskOrder(TaskOrderQuery query) throws DaoException{
		return this.sqlSession.selectList("taskOrder.selectTaskOrder", query);
	}
	
	public List<TaskOrder> selectDelayTaskOrder() throws DaoException{
		return this.sqlSession.selectList("taskOrder.selectDelayTaskOrder");
	}
	
	public List<TaskOrder> selectNoticeTaskOrder() throws DaoException{
		return this.sqlSession.selectList("taskOrder.selectNoticeTaskOrder");
	}
	
	public List<TaskOrder> selectAlarmTaskOrder() throws DaoException{
		return this.sqlSession.selectList("taskOrder.selectAlarmTaskOrder");
	}
	
	public List<TaskOrder> selectInvalidTaskOrder() throws DaoException{
		return this.sqlSession.selectList("taskOrder.selectInvalidTaskOrder");
	}
	
	/**
	 * 插入TaskOrder数据
	 * @param taskOrder
	 * @return
	 */
	public Integer insertTaskOrder(TaskOrder taskOrder) throws DaoException{
		if(taskOrder == null) return -1;
		return this.sqlSession.insert("taskOrder.insertTaskOrder", taskOrder);
	}
	
	/**
	 * 更新TaskOrder数据
	 * @param taskOrder
	 * @return
	 */
	public Integer updateTaskOrder(TaskOrder taskOrder) throws DaoException{
		if(taskOrder == null) return -1;
		return this.sqlSession.update("taskOrder.updateTaskOrder", taskOrder);
	}
	
	public Integer updateTaskOrderContract(TaskOrder taskOrder) throws DaoException{
		if(taskOrder == null) return -1;
		return this.sqlSession.update("taskOrder.updateTaskOrderContract", taskOrder);
	}
	
	/**
	 * 删除TaskOrder数据
	 * @param taskOrder
	 * @return
	 */
	public Integer deleteTaskOrder(TaskOrder taskOrder) throws DaoException{
		return this.sqlSession.delete("taskOrder.deleteTaskOrder", taskOrder);
	}
	
	public TaskOrder selectTaskOrderByIdQuery(TaskOrderQuery query) throws DaoException{
		return this.sqlSession.selectOne("taskOrder.selectByIdQuery", query);
	}

	/**
	 * 查询变更单是否需要立合
	 * @param query 查询条件
	 * @return 返回结果
	 * @throws DaoException 异常
	 */
	public Integer selectAgreementNum(TaskOrderQuery query) throws DaoException {
		return this.sqlSession.selectOne("taskOrder.selectAgreementNum", query);
	}

	/**
	 * 超时未立合作废的4M变更单
	 * @return 返回结果
	 * @throws DaoException 异常
	 */
	public List<TaskOrder> selectInvalidTaskOrderByAgreement() throws DaoException{
		return this.sqlSession.selectList("taskOrder.selectInvalidTaskOrderByAgreement");
	}
}
