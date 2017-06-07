package com.success.task.screen.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.success.sys.user.dao.UserDao;
import com.success.sys.user.domain.User;
import com.success.task.base.domain.OrderContractState;
import com.success.task.screen.dao.TaskScreenDao;
import com.success.task.screen.query.ScreenQuery;
import com.success.task.screen.service.TaskScreenService;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;

@Service("screenService")
public class TaskScreenServiceImpl implements TaskScreenService {

	@Resource(name = "screenDao")
	private TaskScreenDao screenDao;
	
	@Resource(name = "userDao")
	private UserDao userDao;
	
	@Override
	public List<OrderContractState> queryOrderContractStates() {
		// TODO Auto-generated method stub
		try{
			List<OrderContractState> orderContractStates = this.screenDao.selectOrderContractState();
			return orderContractStates;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Page queryPage(int pageIndex, int pageSize, ScreenQuery query) throws ServiceException {
		// TODO Auto-generated method stub
		return this.screenDao.selectPage(pageIndex, pageSize, query);
	}

	@Override
	public ScreenQuery setScreenQueryData(HttpServletRequest request) {
		// TODO Auto-generated method stub
		ScreenQuery query = new ScreenQuery();
		String orderContractStateCode = request.getParameter("orderContractStateCode");
		String publishCode = request.getParameter("publishCode");
		String changeTimeBeginStr = request.getParameter("changeTimeBegin");
		String changeTimeEndStr = request.getParameter("changeTimeEnd");
		
		if(orderContractStateCode != null && !"".equals(orderContractStateCode)){
			query.setOrderContractStateCode(orderContractStateCode);
		}
		
		if(publishCode != null && !"".equals(publishCode)){
			query.setPublishCode(publishCode);
		}
		
		if(changeTimeBeginStr != null && !"".equals(changeTimeBeginStr)){
			query.setChangeTimeBegin(changeTimeBeginStr);
		}
		
		if(changeTimeEndStr != null && !"".equals(changeTimeEndStr)){
			query.setChangeTimeEnd(changeTimeEndStr);
		}
		
		return query;
	}

	@Override
	public List<User> queryOrderContractUser() {
		// TODO Auto-generated method stub
		try{
			List<User> userList = this.userDao.selectOrderContractUser();
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
