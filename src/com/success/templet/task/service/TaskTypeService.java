package com.success.templet.task.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;

import com.success.templet.task.domain.TaskType;
import com.success.templet.task.query.TaskTypeQuery;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;

public interface TaskTypeService {

	public Page getPageTaskType(TaskTypeQuery query, int pageIndex, int pageSize) throws ServiceException;
	
	public List<TaskType> queryTaskTypes(TaskTypeQuery query) throws ServiceException;
	
	public JSONArray queryTaskTypeList(TaskTypeQuery query) throws ServiceException;
	
	public Integer addTaskType(TaskType taskType) throws ServiceException;
	
	public Integer editTaskType(TaskType taskType) throws ServiceException;
	
	public Integer deleteTaskType(TaskType taskType) throws ServiceException;
	
	public TaskType selectTaskTypeByIdQuery(TaskTypeQuery query) throws ServiceException;
	
	public TaskType selectTaskTypeByIdQueryOld(TaskTypeQuery query) throws ServiceException;
	
	public TaskType selectTaskTypeByIdQueryVersion(TaskTypeQuery query) throws ServiceException;
	
	public TaskTypeQuery setTaskTypeQueryData(HttpServletRequest request);
	
	public TaskType selectTaskTypeSelf(TaskTypeQuery query) throws ServiceException;
	
}
