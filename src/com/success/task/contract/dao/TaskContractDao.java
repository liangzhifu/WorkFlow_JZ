package com.success.task.contract.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.task.contract.domain.TaskContractOrg;
import com.success.task.contract.query.TaskContractQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;

@Repository("taskContractDao")
public class TaskContractDao extends BaseDao {

	public List<TaskContractOrg> selectTaskContractOrg(TaskContractQuery query) throws DaoException{
		return this.sqlSession.selectList("taskContract.selectTaskContractOrg", query);
	}
	
	public Integer insertTaskContractOrg(TaskContractOrg taskContractOrg) throws DaoException{
		if(taskContractOrg == null) return -1;
		return this.sqlSession.update("taskContract.insertTaskContractOrg", taskContractOrg);
	}
	
	public Integer deleteTaskContractOrg(TaskContractQuery query) throws DaoException{
		return this.sqlSession.delete("taskContract.deleteTaskContractOrg", query);
	}
	
}
