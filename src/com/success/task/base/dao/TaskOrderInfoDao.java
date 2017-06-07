package com.success.task.base.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.task.base.domain.TaskOrderInfo;
import com.success.task.base.query.TaskOrderInfoQuery;
import com.success.task.detail.query.TaskOrderQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;

@Repository("taskOrderInfoDao")
public class TaskOrderInfoDao extends BaseDao {

	public List<TaskOrderInfo> selectTaskOrderInfo(TaskOrderInfoQuery query) throws DaoException{
		return this.sqlSession.selectList("taskOrderInfo.selectTaskOrderInfo", query);
	}
	
	/**
	 * 更新TaskOrderInfo数据
	 * @param taskOrderInfo
	 * @return
	 */
	public Integer insertTaskOrderInfo(TaskOrderInfo taskOrderInfo) throws DaoException{
		if(taskOrderInfo == null) return -1;
		return this.sqlSession.update("taskOrderInfo.insertTaskOrderInfo", taskOrderInfo);
	}
	
	/**
	 * 更新TaskOrderInfo数据
	 * @param taskOrderInfo
	 * @return
	 */
	public Integer updateTaskOrderInfo(TaskOrderInfo taskOrderInfo) throws DaoException{
		if(taskOrderInfo == null) return -1;
		return this.sqlSession.update("taskOrderInfo.updateTaskOrderInfo", taskOrderInfo);
	}
	
	public String selectTaskPublishCode(TaskOrderQuery query) throws DaoException{
		return this.sqlSession.selectOne("taskOrderInfo.selectTaskPublishCode", query);
	}
	
	public String selectTaskChangeTime(TaskOrderQuery query) throws DaoException{
		return this.sqlSession.selectOne("taskOrderInfo.selectTaskChangeTime", query);
	}
	
	public Integer updateTaskChangeTime(TaskOrderInfo taskOrderInfo) throws DaoException{
		return this.sqlSession.update("taskOrderInfo.updateOrderChangeTime", taskOrderInfo);
	}
	
	public Integer updateTaskPublishCode(TaskOrderInfo taskOrderInfo) throws DaoException{
		return this.sqlSession.update("taskOrderInfo.updateOrderPublishCode", taskOrderInfo);
	}
	
	public Integer selectCountPublishCode(TaskOrderInfo taskOrderInfo) throws DaoException{
		return this.sqlSession.selectOne("taskOrderInfo.selectCountPublishCode", taskOrderInfo);
	}
}
