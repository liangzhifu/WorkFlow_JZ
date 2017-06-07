package com.success.task.base.service;

import java.util.List;

import com.success.task.base.domain.TaskConfirmOrder;
import com.success.task.base.query.TaskConfirmOrderQuery;
import com.success.web.framework.exception.ServiceException;

public interface TaskConfirmOrderService {
	
	public List<TaskConfirmOrder> queryTaskConfirmOrders(TaskConfirmOrderQuery query) throws ServiceException;

	public Integer addTaskConfirmOrder(TaskConfirmOrder taskConfirmOrder) throws ServiceException;
	
	public Integer editTaskConfirmOrder(TaskConfirmOrder taskConfirmOrder) throws ServiceException;
	
	public Integer deleteTaskConfirmOrder(TaskConfirmOrder taskConfirmOrder) throws ServiceException;
	
	public TaskConfirmOrder selectTaskConfirmOrderByIdQuery(TaskConfirmOrderQuery query) throws ServiceException;
	
	public void startTaskConfirmOrder(Integer orderId, Integer confirmUserSeq) throws ServiceException;
	
	public void editConfirmTaskConfirmOrder(Integer confirmOrderId) throws ServiceException, Exception;
	
	public void editConfirmOrderUser(Integer orderId, Integer userId, String runCode) throws ServiceException;
	
	public TaskConfirmOrder selectTaskConfirmOrderByUserSeq(TaskConfirmOrderQuery query) throws ServiceException;
	
	public void editSendEmail(TaskConfirmOrder taskConfirmOrder) throws ServiceException;
	
}
