package com.success.sys.module.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.sys.module.domain.Module;
import com.success.sys.module.query.ModuleQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;

@Repository("moduleDao")
public class ModuleDao extends BaseDao {

	/**
	 * 获取模块列表
	 * @param query
	 * @return
	 * @throws DaoException
	 */
	public List<Module> getModuleList(ModuleQuery query) throws DaoException{
		return this.sqlSession.selectList("module.selectByQuery", query);
	}
	
	public List<Module> selectUserModuleList(Integer userId) throws DaoException{
		return this.sqlSession.selectList("module.selectUserModule", userId);
	}
}
