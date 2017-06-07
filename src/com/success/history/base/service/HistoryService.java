package com.success.history.base.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.success.history.base.domain.HistoryAgreement;
import com.success.history.base.query.HistoryAgreementQuery;
import com.success.sys.user.domain.User;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;

public interface HistoryService {

	public void addCopyAgreement(Integer agreementId, Integer editUser, String editType) throws Exception;
	
	public HistoryAgreementQuery setHistoryAgreementQueryData(HttpServletRequest request);
	
	public Page queryHistoryAgreementPage(HistoryAgreementQuery query, int pageIndex, int pageSize) throws ServiceException;
	
	public HistoryAgreement queryHistoryAgreementInfo(Integer historyAgreementId) throws ServiceException;
	
	public List<User> queryAgreementEditUser();
	
	public HistoryAgreement queryHistoryAgreementById(Integer historyAgreementId) throws ServiceException;
}
