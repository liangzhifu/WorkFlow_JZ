package com.success.task.detail.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.success.sys.user.domain.User;
import org.json.JSONException;

import com.success.common.ReturnInfo;
import com.success.task.detail.domain.TaskOrder;
import com.success.task.detail.query.DetailQuery;
import com.success.task.detail.query.TaskOrderQuery;
import com.success.templet.task.domain.TaskType;
import com.success.templet.task.query.TaskTypeQuery;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;

public interface DetailService {

	public void addTask(HttpServletRequest request, int taskTypeId) throws Exception;
	
	public ReturnInfo deleteTask(int orderId, String invalidateText, User user) throws ServiceException;
	
	public Page queryDetailPage(DetailQuery query, int pageIndex, int pageSize) throws ServiceException;
	
	public Page queryDetailPage2(DetailQuery query, int pageIndex, int pageSize) throws ServiceException;
	
	public Page queryTaskAgentPage(DetailQuery query, int pageIndex, int pageSize) throws ServiceException;
	
	public DetailQuery setDetailQueryData(HttpServletRequest request);
	
	public TaskOrder getTaskOrder(TaskOrderQuery query) throws ServiceException;
	
	public TaskType getTaskType(TaskTypeQuery query) throws ServiceException;
	
	public TaskOrder getStaffTaskOrder(TaskOrderQuery query, Integer userId) throws ServiceException;
	
	public void editWoOrder(HttpServletRequest request, Integer orderId, Integer userId) throws ServiceException, JSONException;
	
	public List<Integer> queryPermisssionUsers(int userId) throws ServiceException;
	
	public void editTaskNoticeCycle(HttpServletRequest request, Integer orderId, Integer cycleId) throws Exception;
	
	public String doExportExcle(DetailQuery query, String path)throws Exception;
	
}
