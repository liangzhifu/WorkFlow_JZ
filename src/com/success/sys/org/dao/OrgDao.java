package com.success.sys.org.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.sys.org.domain.Org;
import com.success.sys.org.query.OrgQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;
import com.success.web.framework.mybatis.Page;

@Repository("orgDao")
public class OrgDao extends BaseDao {

	public Page selectPageOrg(OrgQuery query, int pageIndex, int pageSize){
		return this.queryForPage("org.selectOrg", query, pageIndex, pageSize);
	}

	public List<Org> selectOrg(OrgQuery query) throws DaoException{
		return this.sqlSession.selectList("org.selectOrg", query);
	}
	
	/**
	 * 插入Org数据
	 * @param org
	 * @return
	 */
	public Integer insertOrg(Org org){
		if(org == null) return -1;
		return this.sqlSession.insert("org.insertOrg", org);
	}
	
	/**
	 * 更新Org数据
	 * @param org
	 * @return
	 */
	public Integer updateOrg(Org org){
		if(org == null) return -1;
		return this.sqlSession.update("org.updateOrg", org);
	}
	/**
	 * 更新Org路径数据
	 * @param org
	 * @return
	 */
	public Integer updateOrgPath(){
		return this.sqlSession.update("org.updateOrgPath");
	}
	
	/**
	 * 删除Org数据
	 * @param org
	 * @return
	 */
	public Integer deleteOrg(Org org){
		return this.sqlSession.delete("org.deleteOrg", org);
	}
	
	/**
	 * 查询指定ID的 Org Domain对象
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public Org selectOrgByIdQuery(OrgQuery query) throws DaoException{
		return this.sqlSession.selectOne("org.selectByIdQuery", query);
	}
	
}
