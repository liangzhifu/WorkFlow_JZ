package com.success.agreement.base.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.success.agreement.base.domain.Agreement;
import com.success.agreement.base.domain.AgreementEmailUser;
import com.success.agreement.base.domain.AgreementState;
import com.success.agreement.base.query.AgreementQuery;
import com.success.sys.user.domain.User;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;
import com.success.web.framework.mybatis.Page;

@Repository("agreementDao")
public class AgreementDao extends BaseDao {

	public List<User> selectTacheUser(Integer tacheId) throws DaoException{
		return this.sqlSession.selectList("agreement.selectTacheUser", tacheId);
	}

	/**
	 * 查询追踪人员列表
	 * @return 返回结果
	 */
	public List<User> selectTrackUserList(){
		return this.sqlSession.selectList("agreement.selectTrackUserList");
	}
	
	public List<User> selectUser() throws DaoException{
		return this.sqlSession.selectList("agreement.selectUser");
	}
	
	public Integer insertAgreement(Agreement agreement) throws DaoException{
		if(agreement == null) return -1;
		return this.sqlSession.insert("agreement.insertAgreement", agreement);
	}
	
	public Integer updateAgreement(Agreement agreement) throws DaoException{
		if(agreement == null) return -1;
		return this.sqlSession.update("agreement.updateAgreement", agreement);
	}
	
	public Integer selectAgreementCount(Integer orderId) throws DaoException{
		return this.sqlSession.selectOne("agreement.selectAgreementCount", orderId);
	}
	
	public String selectAgrementPublishCode(Integer orderId) throws DaoException{
		return this.sqlSession.selectOne("agreement.selectAgrementPublishCode", orderId);
	}
	
	public List<AgreementEmailUser> selectAgreementMailUser(Integer agreementId) throws DaoException{
		return this.sqlSession.selectList("agreement.selectAgreementMailUser", agreementId);
	}
	
	public List<AgreementEmailUser> selectAgreementAllMailUser(Integer agreementId) throws DaoException{
		return this.sqlSession.selectList("agreement.selectAgreementAllMailUser", agreementId);
	}
	
	public List<AgreementState> selectAgreementState() throws DaoException{
		return this.sqlSession.selectList("agreement.selectAgreementState");
	}
	
	public Page selectPageAgreement(AgreementQuery query, int pageIndex, int pageSize) throws DaoException{
		return this.queryForPage("agreement.selectPageAgreement", query, pageIndex, pageSize);
	}
	
	public Integer selectOrderTacheKey(Map<String, Object> map) throws DaoException{
		return this.sqlSession.selectOne("agreement.selectOrderTacheKey", map);
	}
	
	public Agreement selectAgreementById(Integer agreementId) throws DaoException{
		return this.sqlSession.selectOne("agreement.selectAgreementById", agreementId);
	}
	
	public Integer selectAgreementIdByOrderId(Integer orderId) throws DaoException{
		return this.sqlSession.selectOne("agreement.selectAgreementIdByOrderId", orderId);
	}
	
}
