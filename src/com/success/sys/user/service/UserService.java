package com.success.sys.user.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import com.success.sys.user.domain.User;
import com.success.sys.user.query.UserQuery;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;

public interface UserService {

	//用户登录验证
	public String loginSystem(String userName, String password) throws ServiceException;
	
	//查询用户，只能查询出一个用户，以用户的唯一标识进行查询
	public User queryUser(UserQuery query) throws ServiceException;
	
	//查询用户
	public Page getPageUser(UserQuery query, int pageIndex, int pageSize) throws ServiceException;

	public Integer insertUser(User user) throws ServiceException;
	
	public Integer updateUser(User user) throws ServiceException;
	
	public Integer deleteUser(User user) throws ServiceException;
	
	public Integer editPassword(User user) throws ServiceException;
	
	//加入查条件
	public UserQuery setUserQueryData(HttpServletRequest request);
	
	public List<User> queryUserByCode(UserQuery query) throws ServiceException;

	/**
	 * 查询用户列表
	 * @param query 查询条件
	 * @return 返回结果
	 */
	public List<Map<String, Object>> queryUserList(UserQuery query);

}
