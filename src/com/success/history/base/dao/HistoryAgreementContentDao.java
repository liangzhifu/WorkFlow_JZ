package com.success.history.base.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.history.base.domain.HistoryAgreementContent;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;

@Repository("historyAgreementContentDao")
public class HistoryAgreementContentDao extends BaseDao {

	public Integer insertHistoryAgreementContent(HistoryAgreementContent historyAgreementContent) throws DaoException{
		if(historyAgreementContent == null) return -1;
		return this.sqlSession.insert("historyAgreementContent.insertHistoryAgreementContent", historyAgreementContent);
	}
	
	public List<HistoryAgreementContent> selectHistoryAgreementContent(Integer historyAgreementId) throws DaoException{
		return this.sqlSession.selectList("historyAgreementContent.selectHistoryAgreementContent", historyAgreementId);
	}
	
}
