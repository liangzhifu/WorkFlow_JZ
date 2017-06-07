package com.success.task.screen.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.task.base.domain.OrderContractState;
import com.success.task.screen.query.ScreenQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;
import com.success.web.framework.mybatis.Page;

@Repository("screenDao")
public class TaskScreenDao extends BaseDao {

	public List<OrderContractState> selectOrderContractState() throws DaoException{
		return this.sqlSession.selectList("taskOrderContractState.selectOrderContractState");
	}
	
	public Page selectPage(int pageIndex, int pageSize, ScreenQuery query) throws DaoException{
		return this.queryForPage("screen.selectPage", query, pageIndex, pageSize);
	}
	
}
