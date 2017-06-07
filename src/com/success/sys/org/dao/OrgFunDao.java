package com.success.sys.org.dao;

import org.springframework.stereotype.Repository;

import com.success.sys.org.domain.OrgFun;
import com.success.web.framework.mybatis.BaseDao;

@Repository("orgFunDao")
public class OrgFunDao extends BaseDao {

	/**
	 * 插入OrgFun数据
	 * 
	 * @param orgFun
	 * @return
	 */
	public Integer insertOrgFun(OrgFun orgFun) {
		if (orgFun == null)
			return -1;
		return this.sqlSession.insert("orgFun.insertOrgFun", orgFun);
	}

	/**
	 * 更新OrgFun数据
	 * 
	 * @param orgFun
	 * @return
	 */
	public Integer updateOrgFun(OrgFun orgFun) {
		if (orgFun == null)
			return -1;
		return this.sqlSession.update("orgFun.updateOrgFun", orgFun);
	}

	/**
	 * 删除OrgFun数据
	 * 
	 * @param orgFun
	 * @return
	 */
	public Integer deleteOrgFun(OrgFun orgFun) {
		return this.sqlSession.delete("orgFun.deleteOrgFun", orgFun);
	}

}
