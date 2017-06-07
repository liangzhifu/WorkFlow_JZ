package com.success.agreement.base.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.success.agreement.base.domain.Agreement;
import com.success.agreement.base.domain.AgreementContent;
import com.success.agreement.base.domain.AgreementState;
import com.success.agreement.base.domain.AgreementTache;
import com.success.agreement.base.query.AgreementQuery;
import com.success.common.ReturnInfo;
import com.success.sys.user.domain.User;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;


public interface AgreementService {

	public List<User> queryTacheUser(Integer tacheId);

	/**
	 * 查询追踪人员列表
	 * @return 返回结果
	 */
	public List<User> queryTrackUser();
	
	public List<User> queryUser();
	
	public List<AgreementState> queryAgreementState();
	
	public Integer addAgreement(HttpServletRequest request, int orderId, int userId) throws Exception;
	
	public AgreementQuery setAgreementQueryData(HttpServletRequest request);
	
	public Page queryAgreementPage(AgreementQuery query, int pageIndex, int pageSize) throws ServiceException;
	
	public Agreement queryAgreementById(Integer agreementId) throws ServiceException;
	
	public List<AgreementContent> queryAgreementContentByAgreementId(Integer agreementId, Integer responsible) throws ServiceException;
	
	public List<AgreementContent> queryAgreementContentByAgreementId2(Integer agreementId, Integer confirmId) throws ServiceException;
	
	public List<AgreementContent> queryAgreementContentByAgreementId3(Integer agreementId) throws ServiceException;
	
	public List<AgreementTache> queryAgrrementTacheByAgreementId(Integer agreementId) throws ServiceException;
	
	public Agreement queryAgreementInfo(Integer orderId, Integer agreementId, Integer responsible) throws ServiceException;
	
	public Agreement queryAgreementInfo2(Integer orderId, Integer agreementId, Integer confirmId) throws ServiceException;
	
	public Agreement queryAgreementInfo3(Integer orderId, Integer agreementId) throws ServiceException;
	
	public void updateAgreementContent(HttpServletRequest request) throws Exception;
	
	public void editRefuseAgreementContent(Integer orderId, Integer agreementId, Integer id, String refuseReason) throws ServiceException;
	
	public void editAcceptAgreementContent(Integer orderId, Integer agreementId, Integer id, String state) throws ServiceException;
	
	public void editCompleteAgreement(Integer orderId, Integer agreementId) throws ServiceException;
	
	public void editAgreementResultOK(Integer orderId, Integer agreementId) throws ServiceException;
	
	public void editAgreementResultNG(Integer orderId, Integer agreementId, String resultMessage) throws ServiceException;
	
	public void updateAgreement(HttpServletRequest request) throws Exception;
	
	public ReturnInfo deleteAgreement(Integer orderId, Integer agreementId, String invalidateText);
	
	public Integer queryAgreementIdByOrderId(Integer orderId) throws ServiceException;
	
}
