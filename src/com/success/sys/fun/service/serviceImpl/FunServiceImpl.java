package com.success.sys.fun.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.success.sys.fun.dao.FunDao;
import com.success.sys.fun.domain.UserFun;
import com.success.sys.fun.query.UserFunQuery;
import com.success.sys.fun.service.FunService;
import com.success.web.framework.exception.DaoException;

@Service("funService")
public class FunServiceImpl implements FunService {
	@Resource(name = "funDao")
	private FunDao funDao;

	@Override
	public List<UserFun> selectUserFun(UserFunQuery query) {

		return funDao.selectUserFun(query);
	}

	@Override
	public List<UserFun> selectFunByUserId(UserFunQuery query) {

		return funDao.selectFunByUserId(query);
	}

	@Override
	public Integer insertUserFun(UserFun userFun) {

		return funDao.insertUserFun(userFun);
	}

	@Override
	public Integer deleteUserFun(UserFun userFun) {

		return funDao.deleteUserFun(userFun);
	}

	@Override
	public UserFunQuery getUserFunQuery(HttpServletRequest request) {
		UserFunQuery query = new UserFunQuery();
		String userFunId = request.getParameter("userFunId");
		String userId = request.getParameter("userId");
		String funId = request.getParameter("funId");
		String checkHaveAccess = request.getParameter("checkHaveAccess");
		if (userFunId != null && !userFunId.equals("")) {
			query.setFunId(Integer.parseInt(userFunId));
		}
		if (userId != null && !userId.equals("")) {
			query.setUserId(Integer.parseInt(userId));
		}
		if (funId != null && !funId.equals("")) {
			query.setFunId(Integer.parseInt(funId));
		}
		if (checkHaveAccess != null) {
			query.setCheckHaveAccess(checkHaveAccess);
		}
		return query;
	}

}
