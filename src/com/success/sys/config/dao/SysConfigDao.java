package com.success.sys.config.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.sys.config.domain.SysConfig;
import com.success.sys.config.query.SysConfigQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;

@Repository("sysConfigDao")
public class SysConfigDao extends BaseDao {

	public SysConfig selectSysConfig(String sysConfigCode) throws DaoException{
		return this.sqlSession.selectOne("sysConfig.selectSysConfig", sysConfigCode);
	}
	
	public List<SysConfig> selectSysConfigList(SysConfigQuery query){
		return this.sqlSession.selectList("sysConfig.selectByQuery", query);
	}
}
