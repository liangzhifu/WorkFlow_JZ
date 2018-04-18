package com.success.sys.user.service.serviceImpl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.success.sys.user.dao.UserDao;
import com.success.sys.user.domain.User;
import com.success.sys.user.query.UserQuery;
import com.success.sys.user.service.UserService;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name = "userDao")
	private UserDao userDao;

	/**
	 * 用户登录验证
	 */
	public String loginSystem(String userCode, String password)
			throws ServiceException {
		UserQuery query = new UserQuery();
		query.setUserCode(userCode);
		User userInfo = null;
		try {
			userInfo = this.userDao.queryPasswordInfo(query);
			if (userInfo == null || userInfo.getPassword() == null)
				return "UnknowUser";
			if (password.equals(userInfo.getPassword())) {
				return "success";
			} else {
				return "WrongPassword";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}

	/**
	 * 查询用户，只能查询出一个用户，以用户的唯一标识进行查询
	 */
	public User queryUser(UserQuery query) throws ServiceException {
		try {
			return this.userDao.selectByQuery(query);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Page getPageUser(UserQuery query, int pageIndex, int pageSize)
			throws ServiceException {
		return this.userDao.selectPageUser(query, pageIndex, pageSize);
	}
	
	public Integer insertUser(User user) throws ServiceException {
		// TODO Auto-generated method stub
		if(user == null) return null;
		return this.userDao.insertUser(user);
	}

	@Override
	public Integer updateUser(User user) throws ServiceException {
		// TODO Auto-generated method stub
		if(user == null) return null;
		return this.userDao.updateUser(user);
	}
	@Override
	public Integer deleteUser(User user) throws ServiceException {
		// TODO Auto-generated method stub
		return this.userDao.deleteUser(user);
	}
	
	public Integer editPassword(User user) throws ServiceException {
		return this.userDao.updateUser(user);
	}

	@Override
	public UserQuery setUserQueryData(HttpServletRequest request) {
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		UserQuery query = new UserQuery();
		String userId = request.getParameter("userId");
		String userCode = request.getParameter("userCode");
		String userName = request.getParameter("userName");
		String mobileTel = request.getParameter("mobileTel");
		String orgId = request.getParameter("orgId");
		String userWorkId = request.getParameter("userWorkId");
		String isCheckAll = request.getParameter("isCheckAll");
		String isHeader = request.getParameter("isHeader");
		if (userId != null && !userId.equals("")) {
			query.setUserId(Integer.parseInt(userId));
		}

		if (userCode != null && !userCode.equals("")) {
			query.setUserCode(userCode);
		}

		if (userName != null) {
			query.setUserName(userName);
		}

		if (mobileTel != null) {
			query.setMobileTel(mobileTel);
		}
		if (orgId != null) {
			query.setOrgId(orgId);
		}
		if (userWorkId != null) {
			query.setUserWorkId(userWorkId);
		}
		if (isCheckAll != null) {
			query.setIsCheckAll(isCheckAll);
		}
		if (isHeader != null) {
			query.setIsHeader(isHeader);
		}
		return query;
	}

	@Override
	public List<User> queryUserByCode(UserQuery query) throws ServiceException {
		// TODO Auto-generated method stub
		return this.userDao.selectUserByCode(query);
	}

	@Override
	public List<Map<String, Object>> queryUserList(UserQuery query) {
		return this.userDao.selectUserList(query);
	}
}
