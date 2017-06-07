package com.success.task.detail.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.task.detail.domain.Detail;
import com.success.task.detail.query.DetailQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;
import com.success.web.framework.mybatis.Page;

@Repository("detailDao")
public class DetailDao extends BaseDao {

	public Page selectPageDetail(DetailQuery query, int pageIndex, int pageSize) throws DaoException{
		return this.queryForPage("detail.selectDetail", query, pageIndex, pageSize);
	}
	
	public Page selectPageDetail2(DetailQuery query, int pageIndex, int pageSize) throws DaoException{
		return this.queryForPage("detail.selectDetail2", query, pageIndex, pageSize);
	}
	
	public Page selectPageTaskAgent(DetailQuery query, int pageIndex, int pageSize) throws DaoException{
		return this.queryForPage("detail.selectAgent", query, pageIndex, pageSize);
	}
	
	public List<Detail> selectDetailList(DetailQuery query) throws DaoException{
		return this.sqlSession.selectList("detail.selectDetail2", query);
	}
	
	public Integer selectMax(String publishCode) throws DaoException{
		return this.sqlSession.selectOne("detail.selectMAX", publishCode);
	}
	
}
