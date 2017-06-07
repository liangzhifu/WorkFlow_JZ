package com.success.task.base.dao;

import org.springframework.stereotype.Repository;

import com.success.task.base.domain.TaskWoOrderInfo;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;

@Repository("taskWoOrderInfoDao")
public class TaskWoOrderInfoDao extends BaseDao {

	/**
	 * 更新TaskWoOrderInfo数据
	 * @param taskWoOrderInfo
	 * @return
	 */
	public Integer insertTaskWoOrderInfo(TaskWoOrderInfo taskWoOrderInfo) throws DaoException{
		if(taskWoOrderInfo == null) return null;
		return this.sqlSession.update("taskWoOrderInfo.insertTaskWoOrderInfo", taskWoOrderInfo);
	}
	
	public Integer updateTaskWoOrderInfo(TaskWoOrderInfo taskWoOrderInfo) throws DaoException{
		if(taskWoOrderInfo == null) return null;
		return this.sqlSession.update("taskWoOrderInfo.updateTaskWoOrderInfo", taskWoOrderInfo);
	}
	
}
