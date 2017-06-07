package com.success.sys.access.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.sys.access.domain.Access;
import com.success.sys.access.query.AccessQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;
import com.success.web.framework.mybatis.Page;

@Repository("accessDao")
public class AccessDao extends BaseDao {

	public Page selectPageAccess(AccessQuery query, int pageIndex, int pageSize){
		return this.queryForPage("access.selectAccess", query, pageIndex, pageSize);
	}

	public List<Access> selectAccess(AccessQuery query) throws DaoException{
		return this.sqlSession.selectList("access.selectAccess", query);
	}
	
	/**
	 * 插入Access数据
	 * @param access
	 * @return
	 */
	public Integer insertAccess(Access access){
		if(access == null) return -1;
		return this.sqlSession.insert("access.insertAccess", access);
	}
	
	/**
	 * 更新Access数据
	 * @param access
	 * @return
	 */
	public Integer updateAccess(Access access){
		if(access == null) return -1;
		return this.sqlSession.update("access.updateAccess", access);
	}
	
	/**
	 * 删除Access数据
	 * @param access
	 * @return
	 */
	public Integer deleteAccess(Access access){
		return this.sqlSession.delete("access.deleteAccess", access);
	}
	
	/**
	 * 查询指定ID的 Access Domain对象
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public Access selectAccessByIdQuery(AccessQuery query) throws DaoException{
		return this.sqlSession.selectOne("access.selectByIdQuery", query);
	}
	
}
