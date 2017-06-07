package com.success.sys.org.service.serviceImpl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.success.sys.org.dao.OrgFunDao;
import com.success.sys.org.domain.OrgFun;
import com.success.sys.org.service.OrgFunService;

@Service("orgFunService")
public class OrgFunServiceImpl implements OrgFunService {

	@Resource(name = "orgFunDao")
	private OrgFunDao orgFunDao;

	@Override
	public Integer insertOrgFun(OrgFun orgFun) {
		// TODO Auto-generated method stub
		if (orgFun == null)
			return null;
		return this.orgFunDao.insertOrgFun(orgFun);
	}

	@Override
	public Integer updateOrgFun(OrgFun orgFun) {
		// TODO Auto-generated method stub
		if (orgFun == null)
			return null;
		return this.orgFunDao.updateOrgFun(orgFun);
	}

	@Override
	public Integer deleteOrgFun(OrgFun orgFun) {
		// TODO Auto-generated method stub
		return this.orgFunDao.deleteOrgFun(orgFun);
	}

}
