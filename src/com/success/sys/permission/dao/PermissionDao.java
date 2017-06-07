package com.success.sys.permission.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.sys.org.domain.Org;
import com.success.sys.user.domain.User;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;

@Repository("permissionDao")
public class PermissionDao extends BaseDao {

	public List<Org> selectPermissionOrgsByUser(Integer userId) throws DaoException{
		return this.sqlSession.selectList("permission.selectPermissionOrgsByUser", userId);
	}
	
	public List<User> selectPermissionUsersByOrg(List<String> orgList) throws DaoException{
		return this.sqlSession.selectList("permission.selectPermissionUsersByOrg", orgList);
	}
}
