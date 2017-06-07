package com.success.sys.fun.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.sys.fun.domain.UserFun;
import com.success.sys.fun.query.UserFunQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;

@Repository("funDao")
public class FunDao extends BaseDao {
	/**
	 * 查询菜单权限数据
	 * @param query
	 * @return
	 * @throws DaoException
	 */
	public List<UserFun> selectUserFun(UserFunQuery query) {
		return this.sqlSession.selectList("fun.selectUserFun", query);
	}
	/**
	 * 查询尚未获得菜单权限
	 * @param query
	 * @return
	 * @throws DaoException
	 */
	public List<UserFun> selectFunByUserId(UserFunQuery query) {
		return this.sqlSession.selectList("fun.selectFunByUserId", query);
	}
	/**
	 * 插入UserFun数据
	 * @param 
	 * @return
	 */
	public Integer insertUserFun(UserFun userFun){
		if(userFun == null) return -1;
		return this.sqlSession.insert("fun.insertUserFun", userFun);
	}
	/**
	 * 删除UserFun数据
	 * @param org
	 * @return
	 */
	public Integer deleteUserFun(UserFun userFun){
		return this.sqlSession.delete("fun.deleteUserFun", userFun);
	}
}
