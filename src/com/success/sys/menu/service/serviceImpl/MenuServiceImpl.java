package com.success.sys.menu.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.success.sys.menu.dao.MenuDao;
import com.success.sys.menu.domain.UserMenu;
import com.success.sys.menu.query.UserMenuQuery;
import com.success.sys.menu.service.MenuService;
import com.success.web.framework.exception.DaoException;
@Service("menuService")
public class MenuServiceImpl implements MenuService{
	@Resource(name = "menuDao")
	private MenuDao menuDao;

	@Override
	public List<UserMenu> selectUserMenu(UserMenuQuery query)  {
		
		return menuDao.selectUserMenu(query);
	}
	@Override
	public List<UserMenu> selectMenuByUserId(UserMenuQuery query)
			 {
		
		return menuDao.selectMenuByUserId(query);
	}

	
	@Override
	public Integer insertUserMenu(UserMenu userMenu) {
		
		return menuDao.insertUserMenu(userMenu);
	}
	@Override
	public Integer deleteUserMenu(UserMenu userMenu) {
	
		return menuDao.deleteUserMenu(userMenu);
	}

	@Override
	public UserMenuQuery getUserMenuQuery(HttpServletRequest request) {
		UserMenuQuery query = new UserMenuQuery();
		String userMenuId = request.getParameter("userMenuId");
		String userId = request.getParameter("userId");
		String menuId = request.getParameter("menuId");
		String checkHaveAccess = request.getParameter("checkHaveAccess");
		if(userMenuId != null && !userMenuId.equals("")){
			query.setMenuId(Integer.parseInt(userMenuId));
		}		
		if(userId != null && !userId.equals("")){
			query.setUserId(Integer.parseInt(userId));
		}
		if(menuId != null && !menuId.equals("")){
			query.setMenuId(Integer.parseInt(menuId));
		}		
		if(checkHaveAccess != null){
			query.setCheckHaveAccess(checkHaveAccess);
		}
		return query;
	}








}
