package com.success.sys.menu.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.success.sys.menu.domain.UserMenu;
import com.success.sys.menu.query.UserMenuQuery;
import com.success.web.framework.exception.DaoException;

public interface MenuService {
	public List<UserMenu> selectUserMenu(UserMenuQuery query) ;
	public List<UserMenu> selectMenuByUserId(UserMenuQuery query) ;
	
	public UserMenuQuery getUserMenuQuery(HttpServletRequest request);
	
	public Integer insertUserMenu(UserMenu userMenu);
	public Integer deleteUserMenu(UserMenu userMenu);
}
