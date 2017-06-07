package com.success.task.screen.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.task.screen.domain.TaskScreen;
import com.success.task.screen.query.ScreenQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;
import com.success.web.framework.mybatis.Page;

@Repository("screenShowDao")
public class TaskScreenShowDao extends BaseDao {

	public Page selectPage(int pageIndex, int pageSize) throws DaoException{
		return this.queryForPage("screenShow.selectPage", null, pageIndex, pageSize);
	}
	
	public Long selectPageCount() throws DaoException{
		return this.sqlSession.selectOne("screenShow.selectPageCount");
	}
	
	public Long selectOverTimeCount() throws DaoException{
		return this.sqlSession.selectOne("screenShow.selectOverTimeCount");
	}
	
	public List<TaskScreen> selectTaskScreen(ScreenQuery query) throws DaoException{
		return this.sqlSession.selectList("screenShow.selectTaskScreen", query);
	}
}
