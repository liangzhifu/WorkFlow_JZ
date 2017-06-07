package com.success.task.statistics.service;

import javax.servlet.http.HttpServletRequest;

import com.success.task.statistics.query.StatisticsQuery;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;

public interface StatisticsService {

	public Page queryStatisticsPage(StatisticsQuery query, int pageIndex, int pageSize) throws ServiceException;
	
	public Page queryOrgStatisticsPage(StatisticsQuery query, int pageIndex, int pageSize) throws ServiceException;
	
	public StatisticsQuery setStatisticsQueryData(HttpServletRequest request);
}
