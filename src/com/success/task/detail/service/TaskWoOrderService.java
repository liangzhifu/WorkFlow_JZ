package com.success.task.detail.service;

import java.util.List;

import com.success.task.base.query.TaskWoOrderDetailQuery;
import com.success.task.detail.domain.TaskWoOrder;
import com.success.web.framework.exception.ServiceException;

public interface TaskWoOrderService {

	public Integer addTaskWoOrder(TaskWoOrder taskWoOrder) throws ServiceException;
	
	public Integer editTaskWoOrder(TaskWoOrder taskWoOrder) throws ServiceException;
	
	public Integer deleteTaskWoOrder(TaskWoOrder taskWoOrder) throws ServiceException;
	
	public void startWoOrder(int orderId) throws ServiceException;
	
	public List<TaskWoOrder> queryTaskWoOrders(TaskWoOrderDetailQuery query) throws ServiceException;
	
	public void editRefuseWoOrder(int woOrderId, String refuseReason) throws ServiceException;
	
	public void editRefuseWoOrder2(int woOrderId, String refuseReason) throws ServiceException;
	
	public void editRefuseWoOrder3(String woOrderIds, String refuseReason) throws ServiceException;
	
	public void editAcceptWoOrder(int woOrderId) throws ServiceException;
	
	public void editCompleteWoOrder(int orderId) throws ServiceException;
	
}
