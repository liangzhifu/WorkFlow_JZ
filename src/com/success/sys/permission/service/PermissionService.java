package com.success.sys.permission.service;

import java.util.List;

import com.success.sys.org.domain.Org;
import com.success.sys.user.domain.User;
import com.success.web.framework.exception.ServiceException;

public interface PermissionService {

	public List<Org> queryPermissionOrgsByUser(Integer userId) throws ServiceException;
	
	public List<User> queryPermissionUsersByOrg(List<String> orgList) throws ServiceException;
	
}
