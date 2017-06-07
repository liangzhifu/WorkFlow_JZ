package com.success.sys.permission.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.success.sys.org.domain.Org;
import com.success.sys.permission.dao.PermissionDao;
import com.success.sys.permission.service.PermissionService;
import com.success.sys.user.domain.User;
import com.success.web.framework.exception.ServiceException;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

	@Resource(name = "permissionDao")
	private PermissionDao permissionDao;
	
	@Override
	public List<Org> queryPermissionOrgsByUser(Integer userId)
			throws ServiceException {
		// TODO Auto-generated method stub
		return this.permissionDao.selectPermissionOrgsByUser(userId);
	}

	@Override
	public List<User> queryPermissionUsersByOrg(List<String> orgList)
			throws ServiceException {
		// TODO Auto-generated method stub
		return this.permissionDao.selectPermissionUsersByOrg(orgList);
	}

}
