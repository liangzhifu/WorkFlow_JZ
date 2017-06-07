package com.success.sys.module.service;

import java.util.List;

import com.success.sys.module.domain.Module;
import com.success.sys.module.query.ModuleQuery;
import com.success.web.framework.exception.ServiceException;

public interface ModuleService {

	//获取模块列表
	public List<Module> queryModuleList(ModuleQuery query) throws ServiceException;
	
	public List<Module> queryUserModuleList(Integer userId) throws ServiceException;
}
