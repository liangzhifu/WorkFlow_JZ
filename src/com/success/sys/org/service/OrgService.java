package com.success.sys.org.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;

import com.success.sys.org.domain.Org;
import com.success.sys.org.query.OrgQuery;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;

public interface OrgService {

	public Page getPageOrg(OrgQuery query, int pageIndex, int pageSize) throws ServiceException;
	
	public List<Org> queryOrgs(OrgQuery query);
	
	public JSONArray queryOrgList(OrgQuery query) throws ServiceException;
	
	public Integer insertOrg(Org org) throws ServiceException;
	
	public Integer updateOrg(Org org) throws ServiceException;
	
	public Integer deleteOrg(Org org) throws ServiceException;
	
	public Org selectOrgByIdQuery(OrgQuery query) throws Exception;
	
	public OrgQuery setOrgQueryData(HttpServletRequest request);
	public Integer updateOrgPath()throws ServiceException;
	
}
