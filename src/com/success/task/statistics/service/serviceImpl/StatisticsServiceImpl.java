package com.success.task.statistics.service.serviceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.success.task.statistics.dao.StatisticsDao;
import com.success.task.statistics.query.StatisticsQuery;
import com.success.task.statistics.service.StatisticsService;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;

@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {

	@Resource(name = "statisticsDao")
	private StatisticsDao statisticsDao;
	
	@Override
	public Page queryStatisticsPage(StatisticsQuery query, int pageIndex,
			int pageSize) throws ServiceException {
		// TODO Auto-generated method stub
		return this.statisticsDao.selectPageDetail(query, pageIndex, pageSize);
	}

	@Override
	public StatisticsQuery setStatisticsQueryData(HttpServletRequest request) {
		// TODO Auto-generated method stub
		StatisticsQuery query = new StatisticsQuery();

		String createUser = request.getParameter("createUser");
		String createTimeBeginStr = request.getParameter("createTimeBegin");
		String createTimeEndStr = request.getParameter("createTimeEnd");
		String changeTimeBeginStr = request.getParameter("changeTimeBegin");
		String changeTimeEndStr = request.getParameter("changeTimeEnd");
		String improveTimeBeginStr = request.getParameter("improveTimeBegin");
		String improveTimeEndStr = request.getParameter("improveTimeEnd");
		String orgId = request.getParameter("orgId");
		String isDelay = request.getParameter("isDelay");
		String orderStateCode = request.getParameter("orderStateCode");
		
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
		
		if(improveTimeBeginStr != null && !"".equals(improveTimeBeginStr)){
			query.setImproveTimeBegin(improveTimeBeginStr);
		}
		
		if(improveTimeEndStr != null && !"".equals(improveTimeEndStr)){
			query.setImproveTimeEnd(improveTimeEndStr);
		}
		
		if(orgId != null && !"".equals(orgId)){
			query.setOrgId(Integer.valueOf(orgId));
		}
		
		if(isDelay != null && !"".equals(isDelay)){
			query.setIsDelay(Integer.valueOf(isDelay));
		}
		
		if(orderStateCode != null && !"".equals(orderStateCode)){
			query.setOrderStateCode(orderStateCode);
		}
		
		
		return query;
	}

	@Override
	public Page queryOrgStatisticsPage(StatisticsQuery query, int pageIndex,
			int pageSize) throws ServiceException {
		// TODO Auto-generated method stub
		return this.statisticsDao.selectPageOrgDetail(query, pageIndex, pageSize);
	}

}
