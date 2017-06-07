package com.success.task.statistics.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.task.detail.domain.Detail;
import com.success.task.statistics.query.StatisticsOrderQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;

@Repository("statisticsOrderDao")
public class StatisticsOrderDao extends BaseDao {

	public List<Detail> selectDetailForAll(StatisticsOrderQuery query) throws DaoException{
		return this.sqlSession.selectList("statisticsOrder.selectDetailForAll", query);
	}
	
	public List<Detail> selectDetailForTache(StatisticsOrderQuery query) throws DaoException{
		return this.sqlSession.selectList("statisticsOrder.selectDetailForTache", query);
	}
	
	public List<Detail> selectDetailForNoAgreement(StatisticsOrderQuery query) throws DaoException{
		return this.sqlSession.selectList("statisticsOrder.selectDetailForNoAgreement", query);
	}
	
	public List<Detail> selectDetailForImprove(StatisticsOrderQuery query) throws DaoException{
		return this.sqlSession.selectList("statisticsOrder.selectDetailForImprove", query);
	}
	
	public List<Detail> selectDetailForNG(StatisticsOrderQuery query) throws DaoException{
		return this.sqlSession.selectList("statisticsOrder.selectDetailForNG", query);
	}
	
}
