package com.success.sys.access.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.success.sys.access.dao.AccessDao;
import com.success.sys.access.domain.Access;
import com.success.sys.access.query.AccessQuery;
import com.success.sys.access.service.AccessService;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;

public class AccessServiceImpl implements AccessService {
	
	@Resource(name = "accessDao")
	private AccessDao accessDao;

	@Override
	public Page getPageAccess(AccessQuery query, int pageIndex, int pageSize)
			throws ServiceException {
		// TODO Auto-generated method stub
		return this.accessDao.selectPageAccess(query, pageIndex, pageSize);
	}

	@Override
	public List<Access> queryAccesss(AccessQuery query) {
		// TODO Auto-generated method stub
		try{
			List<Access> accesses = this.accessDao.selectAccess(query);
			return accesses;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public JSONArray queryAccessList(AccessQuery query) throws ServiceException {
		// TODO Auto-generated method stub
		JSONArray jsonArray = new JSONArray();
		List<Access> accessList = new ArrayList<Access>();
		try {
			accessList = this.accessDao.selectAccess(query);
			try {
				for(int i = 0; i < accessList.size(); i++){
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("orgId", accessList.get(i).getOrgId());
					jsonObject.put("orgName", accessList.get(i).getOrgName());
					jsonObject.put("parentId", accessList.get(i).getParentId());
					jsonObject.put("orgPathCode", accessList.get(i).getOrgPathCode());
					jsonArray.put(jsonObject);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			return jsonArray;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer insertAccess(Access access) throws ServiceException {
		// TODO Auto-generated method stub
		if(access == null) return null;
		return this.accessDao.insertAccess(access);
	}

	@Override
	public Integer updateAccess(Access access) throws ServiceException {
		// TODO Auto-generated method stub
		if(access == null) return null;
		return this.accessDao.updateAccess(access);
	}

	@Override
	public Integer deleteAccess(Access access) throws ServiceException {
		// TODO Auto-generated method stub
		return this.accessDao.deleteAccess(access);
	}

	@Override
	public Access selectAccessByIdQuery(AccessQuery query) throws Exception {
		// TODO Auto-generated method stub
		return this.accessDao.selectAccessByIdQuery(query);
	}

	@Override
	public AccessQuery setAccessQueryData(HttpServletRequest request) {
		// TODO Auto-generated method stub
		AccessQuery query = new AccessQuery();
		String orgId = request.getParameter("orgId");
		String orgName = request.getParameter("orgName");
		String parentId = request.getParameter("parentId");
		String orgPathCode = request.getParameter("orgPathCode");
		
		if(orgId != null && !orgId.equals("")){
			query.setOrgId(Integer.parseInt(orgId));
		}
		
		if(parentId != null && !parentId.equals("")){
			query.setParentId(Integer.parseInt(parentId));
		}
		
		if(orgName != null){
			query.setOrgName(orgName);
		}
		
		if(orgPathCode != null){
			query.setOrgPathCode(orgPathCode);
		}

		return query;
	}

}
