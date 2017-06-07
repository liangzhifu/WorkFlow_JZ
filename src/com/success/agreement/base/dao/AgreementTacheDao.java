package com.success.agreement.base.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.agreement.base.domain.AgreementTache;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;

@Repository("agreementTacheDao")
public class AgreementTacheDao extends BaseDao {

	public Integer insertAgreementTache(AgreementTache agreementTache) throws DaoException{
		if(agreementTache == null) return -1;
		return this.sqlSession.insert("agreementTache.insertAgreementTache", agreementTache);
	}
	
	public List<AgreementTache> selectAgrrementTacheByAgreementId(Integer agreementId) throws DaoException{
		return this.sqlSession.selectList("agreementTache.selectAgrrementTacheByAgreementId", agreementId);
	}
	
}
