package com.success.history.base.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.history.base.domain.HistoryAgreement;
import com.success.history.base.query.HistoryAgreementQuery;
import com.success.sys.user.domain.User;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;
import com.success.web.framework.mybatis.Page;

@Repository("historyAgreementDao")
public class HistoryAgreementDao extends BaseDao {
	
	public List<User> selectAgreementEditUser() throws DaoException{
		return this.sqlSession.selectList("historyAgreement.selectAgreementEditUser");
	}

	public Page selectPageHistoryAgreement(HistoryAgreementQuery query, int pageIndex, int pageSize) throws DaoException{
		return this.queryForPage("historyAgreement.selectPageHistoryAgreement", query, pageIndex, pageSize);
	}
	
	public HistoryAgreement selectHistoryAgreementById(Integer historyAgreementId) throws DaoException{
		return this.sqlSession.selectOne("historyAgreement.selectHistoryAgreementById", historyAgreementId);
	}
	
	public Integer insertHistoryAgreement(HistoryAgreement historyAgreement) throws DaoException{
		if(historyAgreement == null) return -1;
		return this.sqlSession.insert("historyAgreement.insertHistoryAgreement", historyAgreement);
	}
	
}
