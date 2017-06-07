package com.success.task.statistics.dao;

import org.springframework.stereotype.Repository;

import com.success.task.statistics.query.StatisticsQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;
import com.success.web.framework.mybatis.Page;

@Repository("statisticsDao")
public class StatisticsDao extends BaseDao {

	public Page selectPageDetail(StatisticsQuery query, int pageIndex, int pageSize) throws DaoException{
		return this.queryForPage("statistics.selectStatistics", query, pageIndex, pageSize);
	}
	
	public Page selectPageOrgDetail(StatisticsQuery query, int pageIndex, int pageSize) throws DaoException{
		return this.queryForPage("statistics.selectOrgStatistics", query, pageIndex, pageSize);
	}
}
