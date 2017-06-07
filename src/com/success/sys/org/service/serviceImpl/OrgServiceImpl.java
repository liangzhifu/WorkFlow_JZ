package com.success.sys.org.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.success.sys.org.dao.OrgDao;
import com.success.sys.org.domain.Org;
import com.success.sys.org.query.OrgQuery;
import com.success.sys.org.service.OrgService;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;

@Service("orgService")
public class OrgServiceImpl implements OrgService {
	
	@Resource(name = "orgDao")
	private OrgDao orgDao;

	@Override
	public Page getPageOrg(OrgQuery query, int pageIndex, int pageSize)
			throws ServiceException {
		// TODO Auto-generated method stub
		return this.orgDao.selectPageOrg(query, pageIndex, pageSize);
	}

	@Override
	public List<Org> queryOrgs(OrgQuery query) {
		// TODO Auto-generated method stub
		try{
			List<Org> orgs = this.orgDao.selectOrg(query);
			return orgs;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public JSONArray queryOrgList(OrgQuery query) throws ServiceException {
		// TODO Auto-generated method stub
		JSONArray jsonArray = new JSONArray();
		List<Org> orgList = new ArrayList<Org>();
		try {
			orgList = this.orgDao.selectOrg(query);
			try {
				for(int i = 0; i < orgList.size(); i++){
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("orgId", orgList.get(i).getOrgId());
					jsonObject.put("orgName", orgList.get(i).getOrgName());
					jsonObject.put("parentId", orgList.get(i).getParentId());
					jsonObject.put("orgPathCode", orgList.get(i).getOrgPathCode());
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
	public Integer insertOrg(Org org) throws ServiceException {
		// TODO Auto-generated method stub
		if(org == null) return null;
		return this.orgDao.insertOrg(org);
	}

	@Override
	public Integer updateOrg(Org org) throws ServiceException {
		// TODO Auto-generated method stub
		if(org == null) return null;
		return this.orgDao.updateOrg(org);
	}
	@Override
	public Integer updateOrgPath() throws ServiceException {
		// TODO Auto-generated method stub
		return this.orgDao.updateOrgPath();
	}
	@Override
	public Integer deleteOrg(Org org) throws ServiceException {
		// TODO Auto-generated method stub
		return this.orgDao.deleteOrg(org);
	}

	@Override
	public Org selectOrgByIdQuery(OrgQuery query) throws Exception {
		// TODO Auto-generated method stub
		return this.orgDao.selectOrgByIdQuery(query);
	}

	@Override
	public OrgQuery setOrgQueryData(HttpServletRequest request) {
		// TODO Auto-generated method stub
		OrgQuery query = new OrgQuery();
		String orgId = request.getParameter("orgId");
		String orgName = request.getParameter("orgName");
		String parentId = request.getParameter("parentId");
		String orgPathCode = request.getParameter("orgPathCode");
		String orgMark = request.getParameter("orgMark");
		System.out.println(orgId+","+orgName+","+parentId+","+orgPathCode);
		if(orgId != null && !orgId.equals("")){
			query.setOrgId(Integer.parseInt(orgId));
		}
		
		if(parentId != null && !parentId.equals("")){
			query.setParentId(Integer.parseInt(parentId));
		}
		
		if(orgName != null && !("").equals(orgName)){
			query.setOrgName(orgName);
		}
		
		if(orgPathCode != null){
			query.setOrgPathCode(orgPathCode);
		}
		if(orgMark != null){
			query.setOrgMark(orgMark);
		}

		return query;
	}

}
