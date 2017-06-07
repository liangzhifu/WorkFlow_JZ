package com.success.agreement.base.service.serviceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.success.agreement.base.dao.AgreementOrderDao;
import com.success.agreement.base.query.AgreementOrderQuery;
import com.success.agreement.base.service.AgreementOrderService;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;

@Service("agreementOrderService")
public class AgreementOrderServiceImpl implements AgreementOrderService {

	@Resource(name = "agreementOrderDao")
	private AgreementOrderDao agreementOrderDao;
	
	@Override
	public Page queryAgreementOrderPage(AgreementOrderQuery query,
			int pageIndex, int pageSize) throws ServiceException {
		// TODO Auto-generated method stub
		return this.agreementOrderDao.selectPageAgreementOrder(query, pageIndex, pageSize);
	}

	@Override
	public AgreementOrderQuery setAgreementOrderQueryData(
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		AgreementOrderQuery query = new AgreementOrderQuery();
		
		String publishCode = request.getParameter("publishCode");
		String changeTime = request.getParameter("changeTime");
		
		if(publishCode != null && !"".equals(publishCode)){
			query.setPublishCode(publishCode);
		}
		
		if(changeTime != null && !"".equals(changeTime)){
			query.setChangeTime(changeTime);
		}
		
		return query;
	}

}
