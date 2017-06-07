package com.success.agreement.base.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.agreement.base.domain.AgreementContent;
import com.success.agreement.base.domain.AgreementEmailUser;
import com.success.agreement.base.query.AgreementContentQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;

@Repository("agreementContentDao")
public class AgreementContentDao extends BaseDao {

	public Integer insertAgreementContent(AgreementContent agreementContent) throws DaoException{
		if(agreementContent == null) return -1;
		return this.sqlSession.insert("agreementContent.insertAgreementContent", agreementContent);
	}
	
	public Integer updateAgreementContent(AgreementContent agreementContent) throws DaoException{
		if(agreementContent == null) return -1;
		return this.sqlSession.update("agreementContent.updateAgreementContent", agreementContent);
	}
	
	public Integer deleteAgreementContent(Integer id) throws DaoException{
		if(id == null) return -1;
		return this.sqlSession.delete("agreementContent.deleteAgreementContent", id);
	}
	
	public AgreementContent selectAgreementContentById(Integer id) throws DaoException{
		return this.sqlSession.selectOne("agreementContent.selectAgreementContentById", id);
	}
	
	public List<AgreementContent> selectAgreementContent(AgreementContentQuery query) throws DaoException{
		return this.sqlSession.selectList("agreementContent.selectAgreementContent", query);
	}
	
	public List<AgreementContent> selectAgreementContent2(AgreementContentQuery query) throws DaoException{
		return this.sqlSession.selectList("agreementContent.selectAgreementContent2", query);
	}
	
	public List<AgreementContent> selectAgreementContent3(Integer agreementId) throws DaoException{
		return this.sqlSession.selectList("agreementContent.selectAgreementContent3", agreementId);
	}
	
	public List<AgreementEmailUser> selectAgreementMailUser(AgreementContentQuery query) throws DaoException{
		return this.sqlSession.selectList("agreementContent.selectAgreementMailUser", query);
	}
	
}
