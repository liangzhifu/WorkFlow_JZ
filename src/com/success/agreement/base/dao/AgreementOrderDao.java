package com.success.agreement.base.dao;

import org.springframework.stereotype.Repository;

import com.success.agreement.base.query.AgreementOrderQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;
import com.success.web.framework.mybatis.Page;

@Repository("agreementOrderDao")
public class AgreementOrderDao extends BaseDao {

	public Page selectPageAgreementOrder(AgreementOrderQuery query, int pageIndex, int pageSize) throws DaoException{
		return this.queryForPage("agreementOrder.selectAgreementOrder", query, pageIndex, pageSize);
	}
	
}
