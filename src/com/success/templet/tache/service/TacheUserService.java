package com.success.templet.tache.service;

import java.util.List;

import org.json.JSONArray;

import com.success.templet.tache.domain.TacheUser;
import com.success.templet.tache.query.TacheUserQuery;
import com.success.web.framework.mybatis.Page;

public interface TacheUserService {

	public Page getPageTache(TacheUserQuery query,int pageIndex,
			int pageSize);
	public List<TacheUser> queryTache(TacheUserQuery query);

	public Page getPageTacheUser(TacheUserQuery query, int pageIndex,
			int pageSize);

	public List<TacheUser> queryTacheUsers(TacheUserQuery query);

	public JSONArray queryTacheUserList(TacheUserQuery query);

	public Integer insertTacheUser(TacheUser tacheUser);

	public Integer deleteTacheUser(TacheUser tacheUser);

	public Integer updateManager(TacheUser tacheUser);

	/**
	 * 查询定单所有工位的回填人员
	 * @param tacheUserQuery 查询条件 定单ID
	 * @return 返回结果
	 */
	public List<TacheUser> quereyTacheUserListOfOrder(TacheUserQuery tacheUserQuery);

	/**
	 * 查询工位管理人员
	 * @param query
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public Page queryTacheManagerUserList(TacheUserQuery query, int pageIndex,
								 int pageSize);

	/**
	 * 添加工位管理员
	 * @param tacheUser
	 * @return
	 */
	public Integer addManager(TacheUser tacheUser);

	/**
	 * 删除工位管理员
	 * @param tacheUser
	 * @return
	 */
	public Integer deleteManager(TacheUser tacheUser);
}
