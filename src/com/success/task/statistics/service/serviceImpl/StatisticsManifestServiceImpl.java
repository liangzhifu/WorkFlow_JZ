package com.success.task.statistics.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.success.task.detail.domain.Detail;
import com.success.task.statistics.dao.StatisticsManifestDao;
import com.success.task.statistics.service.StatisticsManifestService;
import com.success.web.framework.exception.ServiceException;

@Service("statisticsManifestService")
public class StatisticsManifestServiceImpl implements StatisticsManifestService {

	@Resource(name = "statisticsManifestDao")
	private StatisticsManifestDao statisticsManifestDao;
	
	@Override
	public Long queryManifestCount(String param) throws ServiceException {
		// TODO Auto-generated method stub
		return this.statisticsManifestDao.selectManifestCount(param);
	}

	@Override
	public List<Detail> queryDetailForAll(String param) {
		// TODO Auto-generated method stub
		return this.statisticsManifestDao.selectDetailForAll(param);
	}

}
