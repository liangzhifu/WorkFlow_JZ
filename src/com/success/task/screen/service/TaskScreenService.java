package com.success.task.screen.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.success.sys.user.domain.User;
import com.success.task.base.domain.OrderContractState;
import com.success.task.screen.query.ScreenQuery;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;

public interface TaskScreenService {

	public List<OrderContractState> queryOrderContractStates();
	
	public List<User> queryOrderContractUser();
	
	public Page queryPage(int pageIndex, int pageSize, ScreenQuery query) throws ServiceException;
	
	public ScreenQuery setScreenQueryData(HttpServletRequest request);
}
