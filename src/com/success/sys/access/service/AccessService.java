package com.success.sys.access.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;

import com.success.sys.access.domain.Access;
import com.success.sys.access.query.AccessQuery;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;

public interface AccessService {
	
	public Page getPageAccess(AccessQuery query, int pageIndex, int pageSize) throws ServiceException;
	
	public List<Access> queryAccesss(AccessQuery query);
	
	public JSONArray queryAccessList(AccessQuery query) throws ServiceException;
	
	public Integer insertAccess(Access access) throws ServiceException;
	
	public Integer updateAccess(Access access) throws ServiceException;
	
	public Integer deleteAccess(Access access) throws ServiceException;
	
	public Access selectAccessByIdQuery(AccessQuery query) throws Exception;
	
	public AccessQuery setAccessQueryData(HttpServletRequest request);
	
}
