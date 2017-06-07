package com.success.sys.config.service;

import java.util.List;

import com.success.sys.config.domain.SysConfig;
import com.success.sys.config.query.SysConfigQuery;
import com.success.web.framework.exception.ServiceException;

public interface SysConfigService {

	public SysConfig querySysConfig(String sysConfigCode) throws Exception;
	
	public List<SysConfig> querySysConfigList(SysConfigQuery query) throws ServiceException;
}
