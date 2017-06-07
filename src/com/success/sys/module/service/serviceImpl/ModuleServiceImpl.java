package com.success.sys.module.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.success.sys.module.dao.ModuleDao;
import com.success.sys.module.domain.Module;
import com.success.sys.module.query.ModuleQuery;
import com.success.sys.module.service.ModuleService;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.exception.ServiceException;

@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {

	@Resource(name = "moduleDao")
	private ModuleDao moduleDao;
	
	/**
	 * 获取模块列表
	 */
	public List<Module> queryModuleList(ModuleQuery query)
			throws ServiceException {
		// TODO Auto-generated method stub
		try{
			return this.moduleDao.getModuleList(query);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<Module> queryUserModuleList(Integer userId)
			throws ServiceException {
		// TODO Auto-generated method stub
		return this.moduleDao.selectUserModuleList(userId);
	}

}
