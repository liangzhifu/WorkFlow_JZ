package com.success.task.statistics.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.success.task.detail.domain.Detail;
import com.success.task.statistics.query.StatisticsOrderQuery;
import com.success.web.framework.exception.ServiceException;

public interface StatisticsOrderService {

	public StatisticsOrderQuery setStatisticsOrderQueryData(HttpServletRequest request);
	
	public List<Detail> queryDetailForAll(StatisticsOrderQuery query) throws ServiceException;
	
	public List<Detail> queryDetailForTache(StatisticsOrderQuery query) throws ServiceException;
	
	//为立合
	public List<Detail> queryDetailForNoAgreement(StatisticsOrderQuery query) throws ServiceException;
	
	//立合未完成
	public List<Detail> queryDetailForImprove(StatisticsOrderQuery query) throws ServiceException;
	
	//立合NG
	public List<Detail> queryDetailForNG(StatisticsOrderQuery query) throws ServiceException;
	
}
