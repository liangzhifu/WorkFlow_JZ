package com.success.task.base.dao;

import org.springframework.stereotype.Repository;

import com.success.task.base.domain.TaskWoOrderInfo;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;

import java.util.List;

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

	/**
	 * 获取订单的工单列表信息
	 * @param orderId 订单ID
	 * @return 返回结果
	 */
	public List<TaskWoOrderInfo> selectTaskWoOrderInfoListByOrderId(Integer orderId) {
		return this.sqlSession.selectList("taskWoOrderInfo.selectTaskWoOrderInfoListByOrderId", orderId);
	}
}
