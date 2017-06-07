package com.success.templet.tache.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.templet.tache.domain.TacheUser;
import com.success.templet.tache.query.TacheUserQuery;
import com.success.web.framework.mybatis.BaseDao;
import com.success.web.framework.mybatis.Page;

@Repository("tacheUserDao")
public class TacheUserDao extends BaseDao{
	
	public Page selectPageTache(TacheUserQuery query, int pageIndex, int pageSize) {
		return this.queryForPage("tacheUser.selectTache", query, pageIndex, pageSize);
	}
	public List<TacheUser> selectTache(TacheUserQuery query) {
		return this.sqlSession.selectList("tacheUser.selectTache", query);
	}
	
	
	public Page selectPageTacheUser(TacheUserQuery query, int pageIndex, int pageSize){
		return this.queryForPage("tacheUser.selectTacheUser", query, pageIndex, pageSize);
	}

	/**
	 * 查询工位管理者
	 * @param query
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public Page selectTacheManagerUserList(TacheUserQuery query, int pageIndex, int pageSize){
		return this.queryForPage("tacheUser.selectTacheManagerUserList", query, pageIndex, pageSize);
	}

	public List<TacheUser> selectTacheUser(TacheUserQuery query) {
		return this.sqlSession.selectList("tacheUser.selectTacheUser", query);
	}
	
	
	/**
	 * 插入TacheUser数据
	 * @param tacheUser
	 * @return
	 */
	public Integer insertTacheUser(TacheUser tacheUser){
		if(tacheUser == null) return -1;
		return this.sqlSession.insert("tacheUser.insertTacheUser", tacheUser);
	}
	
	/**
	 * 更新TacheUser数据
	 * @param tacheUser
	 * @return
	 */
	public Integer updateTacheUser(TacheUser tacheUser){
		if(tacheUser == null) return -1;
		return this.sqlSession.update("tacheUser.updateTacheUser", tacheUser);
	}
	/**
	 * 删除TacheUser数据
	 * @param tacheUser
	 * @return
	 */
	public Integer deleteTacheUser(TacheUser tacheUser){
		return this.sqlSession.delete("tacheUser.deleteTacheUser", tacheUser);
	}
	/**
	 * 更新Tache管理员数据
	 * @param tacheUser
	 * @return
	 */
	public Integer updateManager(TacheUser tacheUser){
		return this.sqlSession.update("tacheUser.updateManager", tacheUser);
	}

	/**
	 * 查询定单所有工位的回填人员
	 * @param tacheUserQuery 查询条件 定单ID
	 * @return 返回结果
	 */
	public List<TacheUser> selectTacheUserListOfOrder(TacheUserQuery tacheUserQuery){
		return this.sqlSession.selectList("tacheUser.selectTacheUserListOfOrder", tacheUserQuery);
	}

	/**
	 * 添加管理员
	 * @param tacheUser
	 * @return
	 */
	public Integer insertManager(TacheUser tacheUser){
		return this.sqlSession.update("tacheUser.insertManager", tacheUser);
	}

	/**
	 * 删除管理员
	 * @param tacheUser
	 * @return
	 */
	public Integer deleteManager(TacheUser tacheUser){
		return this.sqlSession.update("tacheUser.deleteManager", tacheUser);
	}
}
