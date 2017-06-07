package com.success.sys.menu.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.sys.menu.domain.UserMenu;
import com.success.sys.menu.query.UserMenuQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;

@Repository("menuDao")
public class MenuDao extends BaseDao{
	
	/**
	 * 查询菜单权限数据
	 * @param query
	 * @return
	 * @throws DaoException
	 */
	public List<UserMenu> selectUserMenu(UserMenuQuery query) {
		return this.sqlSession.selectList("menu.selectUserMenu", query);
	}
	/**
	 * 查询尚未获得菜单权限
	 * @param query
	 * @return
	 * @throws DaoException
	 */
	public List<UserMenu> selectMenuByUserId(UserMenuQuery query) {
		return this.sqlSession.selectList("menu.selectMenuByUserId", query);
	}
	/**
	 * 插入UserMenu数据
	 * @param 
	 * @return
	 */
	public Integer insertUserMenu(UserMenu userMenu){
		if(userMenu == null) return -1;
		return this.sqlSession.insert("menu.insertUserMenu", userMenu);
	}
	/**
	 * 删除UserMenu数据
	 * @param org
	 * @return
	 */
	public Integer deleteUserMenu(UserMenu userMenu){
		return this.sqlSession.delete("menu.deleteUserMenu", userMenu);
	}
	
	
}
