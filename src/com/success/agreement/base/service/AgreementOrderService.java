package com.success.agreement.base.service;

import javax.servlet.http.HttpServletRequest;

import com.success.agreement.base.query.AgreementOrderQuery;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;

public interface AgreementOrderService {

	public AgreementOrderQuery setAgreementOrderQueryData(HttpServletRequest request);
	
	public Page queryAgreementOrderPage(AgreementOrderQuery query, int pageIndex, int pageSize)throws ServiceException;
	
}
