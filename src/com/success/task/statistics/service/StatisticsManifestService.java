package com.success.task.statistics.service;

import java.util.List;

import com.success.task.detail.domain.Detail;
import com.success.web.framework.exception.ServiceException;

public interface StatisticsManifestService {

	public Long queryManifestCount(String param) throws ServiceException;
	
	public List<Detail> queryDetailForAll(String param);
	
}
