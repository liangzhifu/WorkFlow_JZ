package com.success.history.base.service.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.success.agreement.base.dao.AgreementContentDao;
import com.success.agreement.base.dao.AgreementDao;
import com.success.agreement.base.dao.AgreementTacheDao;
import com.success.agreement.base.domain.Agreement;
import com.success.agreement.base.domain.AgreementContent;
import com.success.agreement.base.domain.AgreementTache;
import com.success.history.base.dao.HistoryAgreementContentDao;
import com.success.history.base.dao.HistoryAgreementDao;
import com.success.history.base.domain.HistoryAgreement;
import com.success.history.base.domain.HistoryAgreementContent;
import com.success.history.base.query.HistoryAgreementQuery;
import com.success.history.base.service.HistoryService;
import com.success.sys.user.domain.User;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;

@Service("historyService")
public class HistoryServiceImpl implements HistoryService {

	@Resource(name = "historyAgreementDao")
	private HistoryAgreementDao historyAgreementDao;
	
	@Resource(name = "historyAgreementContentDao")
	private HistoryAgreementContentDao historyAgreementContentDao;
	
	@Resource(name = "agreementTacheDao")
	private AgreementTacheDao agreementTacheDao;
	
	@Resource(name = "agreementDao")
	private AgreementDao agreementDao;
	
	@Resource(name = "agreementContentDao")
	private AgreementContentDao agreementContentDao;
	
	@Override
	public void addCopyAgreement(Integer agreementId, Integer editUser, String editType) throws Exception{
		// TODO Auto-generated method stub
		Agreement agreement = this.agreementDao.selectAgreementById(agreementId);
		HistoryAgreement historyAgreement = new HistoryAgreement();
		historyAgreement.setAgreementId(agreement.getAgreementId());
		historyAgreement.setOrderId(agreement.getOrderId());
		historyAgreement.setAgreementState(agreement.getAgreementState());
		historyAgreement.setAgreementName(agreement.getAgreementName());
		historyAgreement.setEditUser(editUser);
		historyAgreement.setProject(agreement.getProject());
		historyAgreement.setCutLOT(agreement.getCutLOT());
		historyAgreement.setNum(agreement.getNum());
		historyAgreement.setConclusionState(agreement.getConclusionState());
		historyAgreement.setConclusionMessage(agreement.getConclusionMessage());
		historyAgreement.setInvalidateText(agreement.getInvalidateText());
		historyAgreement.setEditType(editType);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		historyAgreement.setCreateTime(sdf.parse(agreement.getCreateTime()));
		historyAgreement.setCreateUserId(agreement.getCreateUserId());
		historyAgreement.setTrackId(agreement.getTrackId());
		this.historyAgreementDao.insertHistoryAgreement(historyAgreement);
		
		List<AgreementContent> agreementContentList = this.agreementContentDao.selectAgreementContent3(agreementId);
		for(int i = 0; i < agreementContentList.size(); i++){
			AgreementContent AgreementContent = agreementContentList.get(i);
			HistoryAgreementContent historyAgreementContent = new HistoryAgreementContent();
			historyAgreementContent.setHistoryAgreementId(historyAgreement.getId());
			historyAgreementContent.setSeq(AgreementContent.getSeq());
			historyAgreementContent.setProblem(AgreementContent.getProblem());
			historyAgreementContent.setImprove(AgreementContent.getImprove());
			historyAgreementContent.setResponsible(AgreementContent.getResponsible());
			if(AgreementContent.getTermStr() == null || ("".equals(AgreementContent.getTermStr()))){
				
			}else {
				historyAgreementContent.setTerm(sdf.parse(AgreementContent.getTermStr()));
			}
			historyAgreementContent.setState(AgreementContent.getState());
			historyAgreementContent.setConfirm(AgreementContent.getConfirm());
			historyAgreementContent.setContentState(AgreementContent.getContentState());
			historyAgreementContent.setRefuseReason(AgreementContent.getRefuseReason());
			this.historyAgreementContentDao.insertHistoryAgreementContent(historyAgreementContent);
		}
	}

	@Override
	public HistoryAgreementQuery setHistoryAgreementQueryData(
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		HistoryAgreementQuery query = new HistoryAgreementQuery();
		
		String publishCode = request.getParameter("publishCode");
		String editUser = request.getParameter("editUser");
		String agreementName = request.getParameter("agreementName");
		
		if(publishCode != null && !"".equals(publishCode)){
			query.setPublishCode(publishCode);
		}
		
		if(agreementName != null && !"".equals(agreementName)){
			query.setAgreementName(agreementName);
		}
		
		if(editUser != null && !"".equals(editUser)){
			query.setEditUser(Integer.valueOf(editUser));
		}
		
		return query;
	}

	@Override
	public Page queryHistoryAgreementPage(HistoryAgreementQuery query,
			int pageIndex, int pageSize) throws ServiceException {
		// TODO Auto-generated method stub
		return this.historyAgreementDao.selectPageHistoryAgreement(query, pageIndex, pageSize);
	}

	@Override
	public HistoryAgreement queryHistoryAgreementInfo(Integer historyAgreementId)
			throws ServiceException {
		// TODO Auto-generated method stub
		HistoryAgreement historyAgreement = this.queryHistoryAgreementById(historyAgreementId);
		List<HistoryAgreementContent> historyAgreementContentList = this.historyAgreementContentDao.selectHistoryAgreementContent(historyAgreementId);
		List<AgreementTache> agreementTacheList = this.agreementTacheDao.selectAgrrementTacheByAgreementId(historyAgreement.getAgreementId());
		historyAgreement.setHistoryAgreementContentList(historyAgreementContentList);
		List<AgreementTache> agreementTacheList2 = new LinkedList<AgreementTache>();
		Integer orderId = historyAgreement.getOrderId();
		for(int i = 0; i < agreementTacheList.size(); i++){
			AgreementTache agreementTache = agreementTacheList.get(i);
			//判断是否对象外
			Integer tacheId = agreementTache.getTacheId();
			boolean flag = this.isOutside(orderId, tacheId);
			if(flag){
				agreementTache.setUserName("/");
			}else {
				Integer tacheUserId = agreementTache.getUserId();
				if(tacheUserId == null){
					agreementTache.setUserName("noshow");
				}
			}
			agreementTacheList2.add(agreementTache);
		}
		historyAgreement.setAgreementTacheList(agreementTacheList2);
		return historyAgreement;
	}

	@Override
	public List<User> queryAgreementEditUser() {
		// TODO Auto-generated method stub
		try{
			List<User> Users = this.historyAgreementDao.selectAgreementEditUser();
			return Users;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public HistoryAgreement queryHistoryAgreementById(Integer historyAgreementId)
			throws ServiceException {
		// TODO Auto-generated method stub
		return this.historyAgreementDao.selectHistoryAgreementById(historyAgreementId);
	}
	
	private boolean isOutside(Integer orderId, Integer tacheId)throws ServiceException{
		boolean flag = true;
		Integer keyId = 0;
		if(tacheId == 12){
			keyId = 78;
		}else if(tacheId == 13){
			keyId = 84;
		}else if(tacheId == 14){
			keyId = 94;
		}else if(tacheId == 15){
			keyId = 103;
		}else if(tacheId == 16){
			keyId = 112;
		}else if(tacheId == 17){
			keyId = 121;
		}else if(tacheId == 18){
			keyId = 127;
		}else if(tacheId == 19){
			keyId = 134;
		}else if(tacheId == 20){
			keyId = 0;
		}else if(tacheId == 21){
			keyId = 0;
		}else {
			return true;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		map.put("tacheId", tacheId);
		map.put("keyId", ","+keyId+",");
		Integer num = this.agreementDao.selectOrderTacheKey(map);
		if(num > 0){
			flag = false;
		}
		return flag;
	}

}
