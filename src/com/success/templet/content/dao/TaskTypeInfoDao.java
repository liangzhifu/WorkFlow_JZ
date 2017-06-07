package com.success.templet.content.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.templet.content.domain.TaskTypeInfo;
import com.success.templet.content.domain.TaskTypeInfoValue;
import com.success.templet.content.query.TaskTypeInfoQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;
import com.success.web.framework.mybatis.Page;

@Repository("taskTypeInfoDao")
public class TaskTypeInfoDao extends BaseDao {

	public Page selectPageTaskTypeInfo(TaskTypeInfoQuery query, int pageIndex, int pageSize) throws DaoException{
		return this.queryForPage("taskTypeInfo.selectTaskTypeInfo", query, pageIndex, pageSize);
	}

	public List<TaskTypeInfo> selectTaskTypeInfo(TaskTypeInfoQuery query) throws DaoException{
		return this.sqlSession.selectList("taskTypeInfo.selectTaskTypeInfo", query);
	}
	
	/**
	 * 插入TaskTypeInfo数据
	 * @param taskTypeInfo
	 * @return
	 */
	public Integer insertTaskTypeInfo(TaskTypeInfo taskTypeInfo) throws DaoException{
		if(taskTypeInfo == null) return -1;
		return this.sqlSession.insert("taskTypeInfo.insertTaskTypeInfo", taskTypeInfo);
	}
	
	/**
	 * 更新TaskTypeInfo数据
	 * @param taskTypeInfo
	 * @return
	 */
	public Integer updateTaskTypeInfo(TaskTypeInfo taskTypeInfo) throws DaoException{
		if(taskTypeInfo == null) return -1;
		return this.sqlSession.update("taskTypeInfo.updateTaskTypeInfo", taskTypeInfo);
	}
	
	/**
	 * 删除TaskTypeInfo数据
	 * @param taskTypeInfo
	 * @return
	 */
	public Integer deleteTaskTypeInfo(TaskTypeInfo taskTypeInfo) throws DaoException{
		return this.sqlSession.delete("taskTypeInfo.deleteTaskTypeInfo", taskTypeInfo);
	}
	
	/**
	 * 查询指定ID的 TaskTypeInfo Domain对象
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public TaskTypeInfo selectTaskTypeInfoByIdQuery(TaskTypeInfoQuery query) throws DaoException{
		return this.sqlSession.selectOne("taskTypeInfo.selectByIdQuery", query);
	}
	
	/**
	 * 查询模板信息，根据模板ID
	 * @param query
	 * @return
	 * @throws DaoException
	 */
	public List<TaskTypeInfoValue> selectTaskTypeInfoValueByIdQuery(TaskTypeInfoQuery query) throws DaoException{
		return this.sqlSession.selectList("taskTypeInfo.selectTaskTypeInfoValueByIdQuery", query);
	}
}
