package com.success.sys.config.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.success.sys.config.dao.SysConfigDao;
import com.success.sys.config.domain.SysConfig;
import com.success.sys.config.query.SysConfigQuery;
import com.success.sys.config.service.SysConfigService;
import com.success.web.framework.exception.ServiceException;

@Service("sysConfigService")
public class SysConfigServiceImpl implements SysConfigService {

	@Resource(name = "sysConfigDao")
	private SysConfigDao sysConfigDao;
	
	@Override
	public SysConfig querySysConfig(String sysConfigCode) throws Exception {
		// TODO Auto-generated method stub
		return this.sysConfigDao.selectSysConfig(sysConfigCode);
	}

	@Override
	public List<SysConfig> querySysConfigList(SysConfigQuery query)
			throws ServiceException {
		// TODO Auto-generated method stub
		return this.sysConfigDao.selectSysConfigList(query);
	}

}
