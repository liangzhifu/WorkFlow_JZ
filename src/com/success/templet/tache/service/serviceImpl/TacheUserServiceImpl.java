package com.success.templet.tache.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.springframework.stereotype.Service;
import com.success.templet.tache.dao.TacheUserDao;
import com.success.templet.tache.domain.TacheUser;
import com.success.templet.tache.query.TacheUserQuery;
import com.success.templet.tache.service.TacheUserService;
import com.success.web.framework.mybatis.Page;

@Service("tacheUserService")
public class TacheUserServiceImpl implements TacheUserService{
	@Resource(name = "tacheUserDao")
	private TacheUserDao tacheUserDao;
	@Override
	public Page getPageTache(TacheUserQuery query, int pageIndex,
			int pageSize) {
		// TODO Auto-generated method stub
		return tacheUserDao.selectPageTache(query, pageIndex, pageSize);
	}
	@Override
	public List<TacheUser> queryTache(TacheUserQuery query) {
		// TODO Auto-generated method stub
		return tacheUserDao.selectTache(query);
	}


	@Override
	public Page getPageTacheUser(TacheUserQuery query, int pageIndex,
			int pageSize) {
		// TODO Auto-generated method stub
		return tacheUserDao.selectPageTacheUser(query, pageIndex, pageSize);
	}

	@Override
	public List<TacheUser> queryTacheUsers(TacheUserQuery query) {
		// TODO Auto-generated method stub
		return tacheUserDao.selectTacheUser(query);
	}

	@Override
	public JSONArray queryTacheUserList(TacheUserQuery query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertTacheUser(TacheUser tacheUser) {
		// TODO Auto-generated method stub
		return tacheUserDao.insertTacheUser(tacheUser);
	}

	@Override
	public Integer deleteTacheUser(TacheUser tacheUser) {
		// TODO Auto-generated method stub
		return tacheUserDao.deleteTacheUser(tacheUser);
	}
	@Override
	public Integer updateManager(TacheUser tacheUser) {
		// TODO Auto-generated method stub
		return tacheUserDao.updateManager(tacheUser);
	}

	@Override
	public List<TacheUser> quereyTacheUserListOfOrder(TacheUserQuery tacheUserQuery) {
		return this.tacheUserDao.selectTacheUserListOfOrder(tacheUserQuery);
	}

	@Override
	public Page queryTacheManagerUserList(TacheUserQuery query, int pageIndex, int pageSize) {
		return this.tacheUserDao.selectTacheManagerUserList(query, pageIndex, pageSize);
	}

	@Override
	public Integer addManager(TacheUser tacheUser) {
		return this.tacheUserDao.insertManager(tacheUser);
	}

	@Override
	public Integer deleteManager(TacheUser tacheUser) {
		return this.tacheUserDao.deleteManager(tacheUser);
	}


}
