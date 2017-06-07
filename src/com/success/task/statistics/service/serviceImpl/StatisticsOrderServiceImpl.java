package com.success.task.statistics.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.success.task.detail.domain.Detail;
import com.success.task.statistics.dao.StatisticsOrderDao;
import com.success.task.statistics.query.StatisticsOrderQuery;
import com.success.task.statistics.service.StatisticsOrderService;
import com.success.web.framework.exception.ServiceException;

@Service("statisticsOrderService")
public class StatisticsOrderServiceImpl implements StatisticsOrderService {

	@Resource(name = "statisticsOrderDao")
	private StatisticsOrderDao statisticsOrderDao;
	
	@Override
	public StatisticsOrderQuery setStatisticsOrderQueryData(
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		StatisticsOrderQuery query = new StatisticsOrderQuery();

		String isComplete = request.getParameter("isComplete");
		String isDelay = request.getParameter("isDelay");
		String tacheId = request.getParameter("tacheId");
		String createUser = request.getParameter("createUser");
		String createTimeBeginStr = request.getParameter("createTimeBegin");
		String createTimeEndStr = request.getParameter("createTimeEnd");
		String changeTimeBeginStr = request.getParameter("changeTimeBegin");
		String changeTimeEndStr = request.getParameter("changeTimeEnd");
		String orgId = request.getParameter("orgId");
		
		if(isComplete != null && !("".equals(isComplete))){
			query.setIsComplete(Integer.valueOf(isComplete));
		}
		
		if(isDelay != null && !("".equals(isDelay))){
			query.setIsDelay(Integer.valueOf(isDelay));
		}
		
		if(tacheId != null && !("".equals(tacheId))){
			query.setTacheId(Integer.valueOf(tacheId));
		}
		
		if(createUser != null && !"".equals(createUser)){
			query.setCreateUser(createUser);
		}

		if(createTimeBeginStr != null && !"".equals(createTimeBeginStr)){
			query.setCreateTimeBegin(createTimeBeginStr);
		}
		
		if(createTimeEndStr != null && !"".equals(createTimeEndStr)){
			query.setCreateTimeEnd(createTimeEndStr);
		}
		
		if(changeTimeBeginStr != null && !"".equals(changeTimeBeginStr)){
			query.setChangeTimeBegin(changeTimeBeginStr);
		}
		
		if(changeTimeEndStr != null && !"".equals(changeTimeEndStr)){
			query.setChangeTimeEnd(changeTimeEndStr);
		}
		
		if(orgId != null && !"".equals(orgId)){
			query.setOrgId(Integer.valueOf(orgId));
		}
		
		return query;
	}

	@Override
	public List<Detail> queryDetailForAll(StatisticsOrderQuery query)
			throws ServiceException {
		// TODO Auto-generated method stub
		return this.statisticsOrderDao.selectDetailForAll(query);
	}

	@Override
	public List<Detail> queryDetailForTache(StatisticsOrderQuery query)
			throws ServiceException {
		// TODO Auto-generated method stub
		return this.statisticsOrderDao.selectDetailForTache(query);
	}

	@Override
	public List<Detail> queryDetailForNoAgreement(StatisticsOrderQuery query)
			throws ServiceException {
		// TODO Auto-generated method stub
		return this.statisticsOrderDao.selectDetailForNoAgreement(query);
	}

	@Override
	public List<Detail> queryDetailForImprove(StatisticsOrderQuery query)
			throws ServiceException {
		// TODO Auto-generated method stub
		return this.statisticsOrderDao.selectDetailForImprove(query);
	}

	@Override
	public List<Detail> queryDetailForNG(StatisticsOrderQuery query)
			throws ServiceException {
		// TODO Auto-generated method stub
		return this.statisticsOrderDao.selectDetailForNG(query);
	}

}
