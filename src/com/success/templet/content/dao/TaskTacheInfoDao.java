package com.success.templet.content.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.templet.content.domain.TaskTacheInfo;
import com.success.templet.content.domain.TaskTacheInfoValue;
import com.success.templet.content.query.TaskTacheInfoQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;
import com.success.web.framework.mybatis.Page;

@Repository("taskTacheInfoDao")
public class TaskTacheInfoDao extends BaseDao {

	public Page selectPageTaskTacheInfo(TaskTacheInfoQuery query, int pageIndex, int pageSize) throws DaoException{
		return this.queryForPage("taskTacheInfo.selectTaskTacheInfo", query, pageIndex, pageSize);
	}

	public List<TaskTacheInfo> selectTaskTacheInfo(TaskTacheInfoQuery query) throws DaoException{
		return this.sqlSession.selectList("taskTacheInfo.selectTaskTacheInfo", query);
	}
	
	/**
	 * 插入TaskTacheInfo数据
	 * @param taskTacheInfo
	 * @return
	 */
	public Integer insertTaskTacheInfo(TaskTacheInfo taskTacheInfo) throws DaoException{
		if(taskTacheInfo == null) return -1;
		return this.sqlSession.insert("taskTacheInfo.insertTaskTacheInfo", taskTacheInfo);
	}
	
	/**
	 * 更新TaskTacheInfo数据
	 * @param taskTacheInfo
	 * @return
	 */
	public Integer updateTaskTacheInfo(TaskTacheInfo taskTacheInfo) throws DaoException{
		if(taskTacheInfo == null) return -1;
		return this.sqlSession.update("taskTacheInfo.updateTaskTacheInfo", taskTacheInfo);
	}
	
	/**
	 * 删除TaskTacheInfo数据
	 * @param taskTacheInfo
	 * @return
	 */
	public Integer deleteTaskTacheInfo(TaskTacheInfo taskTacheInfo) throws DaoException{
		return this.sqlSession.delete("taskTacheInfo.deleteTaskTacheInfo", taskTacheInfo);
	}
	
	/**
	 * 查询指定ID的 TaskTacheInfo Domain对象
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public TaskTacheInfo selectTaskTacheInfoByIdQuery(TaskTacheInfoQuery query) throws DaoException{
		return this.sqlSession.selectOne("taskTacheInfo.selectByIdQuery", query);
	}
	
	/**
	 * 查询环节信息，根据模板ID
	 * @param query
	 * @return
	 * @throws DaoException
	 */
	public List<TaskTacheInfoValue> selectTaskTacheInfoValueByIdQuery(TaskTacheInfoQuery query) throws DaoException{
		return this.sqlSession.selectList("taskTacheInfo.selectTaskTacheInfoValueByIdQuery", query);
	}
	
	public List<TaskTacheInfoValue> selectTaskTacheInfoValueByIdQueryOld(TaskTacheInfoQuery query) throws DaoException{
		return this.sqlSession.selectList("taskTacheInfo.selectTaskTacheInfoValueByIdQueryOld", query);
	}
	
	public List<TaskTacheInfoValue> selectTaskTacheInfoValueByIdQueryVersion(TaskTacheInfoQuery query) throws DaoException{
		return this.sqlSession.selectList("taskTacheInfo.selectTaskTacheInfoValueByIdQueryVersion", query);
	}
}
