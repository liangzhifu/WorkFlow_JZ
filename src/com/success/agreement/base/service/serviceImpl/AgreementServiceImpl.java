package com.success.agreement.base.service.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.success.task.detail.dao.TaskOrderDao;
import com.success.task.detail.domain.TaskOrder;
import com.success.templet.tache.dao.TaskTacheDao;
import com.success.templet.tache.domain.TaskTache;
import com.success.templet.tache.query.TaskTacheQuery;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Service;

import com.success.agreement.base.dao.AgreementContentDao;
import com.success.agreement.base.dao.AgreementDao;
import com.success.agreement.base.dao.AgreementTacheDao;
import com.success.agreement.base.domain.Agreement;
import com.success.agreement.base.domain.AgreementContent;
import com.success.agreement.base.domain.AgreementEmailUser;
import com.success.agreement.base.domain.AgreementState;
import com.success.agreement.base.domain.AgreementTache;
import com.success.agreement.base.query.AgreementContentQuery;
import com.success.agreement.base.query.AgreementQuery;
import com.success.agreement.base.service.AgreementService;
import com.success.common.Constant;
import com.success.common.ReturnInfo;
import com.success.sys.email.dao.TimeTaskDao;
import com.success.sys.email.domain.TimeTask;
import com.success.sys.user.dao.UserDao;
import com.success.sys.user.domain.User;
import com.success.sys.user.query.UserQuery;
import com.success.task.base.dao.TaskOrderInfoDao;
import com.success.task.base.domain.TaskOrderInfo;
import com.success.task.base.query.TaskOrderInfoQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;
import com.success.web.framework.util.ServletAPIUtil;

@Service("agreementService")
public class AgreementServiceImpl implements AgreementService {

	@Resource(name = "agreementDao")
	private AgreementDao agreementDao;
	
	@Resource(name = "agreementContentDao")
	private AgreementContentDao agreementContentDao;
	
	@Resource(name = "agreementTacheDao")
	private AgreementTacheDao agreementTacheDao;
	
	@Resource(name = "timeTaskDao")
	private TimeTaskDao timeTaskDao;
	
	@Resource(name = "taskOrderInfoDao")
	private TaskOrderInfoDao taskOrderInfoDao;

	@Resource(name = "taskOrderDao")
	private TaskOrderDao taskOrderDao;
	
	@Resource(name = "userDao")
	private UserDao userDao;

	@Resource(name = "taskTacheDao")
	private TaskTacheDao taskTacheDao;
	
	@Override
	public List<User> queryTacheUser(Integer tacheId) {
		// TODO Auto-generated method stub
		try{
			List<User> Users = this.agreementDao.selectTacheUser(tacheId);
			return Users;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> queryTrackUser() {
		return this.agreementDao.selectTrackUserList();
	}

	@Override
	public List<User> queryUser() {
		// TODO Auto-generated method stub
		try{
			List<User> Users = this.agreementDao.selectUser();
			return Users;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer addAgreement(HttpServletRequest request, int orderId, int userId)
			throws Exception {
		// TODO Auto-generated method stub
		//插入agreement_mange表
		Agreement agreement = new Agreement();
		agreement.setOrderId(orderId);
		String agreementName = this.getAgreementName(orderId);
		agreement.setAgreementName(agreementName);
		agreement.setAgreementState("10B");
		agreement.setCreateUserId(userId);
		String project = ServletAPIUtil.getStringParameter("agreement_project_code", request, "");
		String cutLOT = ServletAPIUtil.getStringParameter("agreement_cut_lot", request, "");
		Integer num =  ServletAPIUtil.getIntegerParameter("agreement_num", request);
		String conclusionState = ServletAPIUtil.getStringParameter("agreement_project_result", request, "");
		String conclusionMessage = ServletAPIUtil.getStringParameter("agreement_conclusion_message", request, "");
		Integer trackId = ServletAPIUtil.getIntegerParameter("agreement_track_id", request, null);
		if("NG".equals(conclusionState)){
			agreement.setIsClose(1);
		}else {
			agreement.setIsClose(0);
		}
		agreement.setProject(project);
		agreement.setCutLOT(cutLOT);
		agreement.setNum(num);
		agreement.setTrackId(trackId);
		agreement.setConclusionState(conclusionState);
		agreement.setConclusionMessage(conclusionMessage);
		this.agreementDao.insertAgreement(agreement);

		//更改订单立合状态
		TaskOrder taskOrder = new TaskOrder();
		taskOrder.setOrderId(orderId);
		taskOrder.setAgreementState("已完成");
		this.taskOrderDao.updateTaskOrder(taskOrder);
		
		//插入agreement_content
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setArrayMode(JsonConfig.MODE_OBJECT_ARRAY);
		
		String problemJson = ServletAPIUtil.getStringParameter("problemJson", request, "");
		JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(problemJson);
		Object[] problemArray = (Object[]) JSONSerializer.toJava(jsonArray, jsonConfig);
		
		String responsibleJson = ServletAPIUtil.getStringParameter("responsibleJson", request, "");
		jsonArray = (JSONArray) JSONSerializer.toJSON(responsibleJson);
		Object[] responsibleArray = (Object[]) JSONSerializer.toJava(jsonArray, jsonConfig);
		
		for(int i = 0; i < problemArray.length; i++){
			String problem = (String)problemArray[i];
			Integer responsible = Integer.valueOf((String)responsibleArray[i]);
			AgreementContent agreementContent = new AgreementContent();
			agreementContent.setAgreementId(agreement.getAgreementId());
			agreementContent.setSeq(i + 1);
			agreementContent.setProblem(problem);
			agreementContent.setResponsible(responsible);
			agreementContent.setContentState("10B");
			this.agreementContentDao.insertAgreementContent(agreementContent);
		}
		
		//插入agreemnt_tache
		AgreementTache agreementTache = new AgreementTache();
		agreementTache.setAgreementId(agreement.getAgreementId());
		TaskTacheQuery taskTacheQuery = new TaskTacheQuery();
		taskTacheQuery.setOrderId(orderId);
		List<TaskTache> taskTacheList = this.taskTacheDao.selectTaskTacheListOfOrder(taskTacheQuery);
		for(TaskTache taskTache : taskTacheList){
			agreementTache.setTacheId(taskTache.getTacheId());
			agreementTache.setUserId(ServletAPIUtil.getIntegerParameter("agreement_tache_" + taskTache.getTacheId() +"_id", request, null));
			this.agreementTacheDao.insertAgreementTache(agreementTache);
		}
		
		this.editCompleteAgreement(orderId, agreement.getAgreementId());
		
		//邮件通知
		TaskOrderInfoQuery taskOrderInfoQuery = new TaskOrderInfoQuery();
		taskOrderInfoQuery.setOrderId(orderId);
		List<TaskOrderInfo> TaskOrderInfoList = this.taskOrderInfoDao.selectTaskOrderInfo(taskOrderInfoQuery);
		String publishCode = "";
		String productionLine = "";
		String changeTime = "";
		String carType = "";
		String mountingMat = "";
		String changeContent = "";
		for(int h = 0; h < TaskOrderInfoList.size(); h++){
			TaskOrderInfo taskOrderInfo = TaskOrderInfoList.get(h);
			Integer taskTypeInfoId = taskOrderInfo.getTaskTypeInfoId();
			if(taskTypeInfoId == 1){
				publishCode = taskOrderInfo.getTaskInfoValue();
			}else if(taskTypeInfoId == 2){
				productionLine = taskOrderInfo.getTaskInfoValue();
			}else if(taskTypeInfoId == 11){
				changeTime = taskOrderInfo.getTaskInfoValue();
			}else if(taskTypeInfoId == 3){
				carType = taskOrderInfo.getTaskInfoValue();
			}else if(taskTypeInfoId == 4){
				mountingMat = taskOrderInfo.getTaskInfoValue();
			}else if(taskTypeInfoId == 9){
				changeContent = taskOrderInfo.getTaskInfoValue();
			}
		}
		String comment = "";
		comment = "你好：" + productionLine + " " + changeTime + " " + carType + " " + mountingMat + " ";
		comment += changeContent + "<br>此变更单已建立立合，请知晓。请及时确认问题点改善对策及完成时间.";
		String email = "";
		List<AgreementEmailUser> agreementEmailUserList =  this.agreementDao.selectAgreementMailUser(agreement.getAgreementId());
		for(int k = 0; k < agreementEmailUserList.size(); k++){
			AgreementEmailUser agreementEmailUser = agreementEmailUserList.get(k);
			email += "," + agreementEmailUser.getUserEmail();
		}
		if(!("".equals(email)))	email = email.substring(1);
		TimeTask timeTask = new TimeTask();
		timeTask.setOrderId(orderId);
		timeTask.setNoticeColor(0);
		timeTask.setNoticeType(12);
		timeTask.setPublishCode(publishCode);
		timeTask.setTaskTypeName("立合管理表");
		timeTask.setFailedNum(0);
		timeTask.setComment(comment);
		timeTask.setUserEmail(email);
		this.timeTaskDao.insertTimeTask(timeTask);
		
		return agreement.getAgreementId();
	}
	
	private String getAgreementName(int orderId)throws Exception{
		String agreementName = "";
		int num = this.agreementDao.selectAgreementCount(orderId);
		num ++;
		String publishCode = this.agreementDao.selectAgrementPublishCode(orderId);
		agreementName = publishCode + "-" + num;
		return agreementName;
	}

	@Override
	public List<AgreementState> queryAgreementState() {
		// TODO Auto-generated method stub
		try{
			List<AgreementState> agreementStates = this.agreementDao.selectAgreementState();
			return agreementStates;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public AgreementQuery setAgreementQueryData(HttpServletRequest request) {
		// TODO Auto-generated method stub
		AgreementQuery query = new AgreementQuery();
		
		String publishCode = request.getParameter("publishCode");
		String project = request.getParameter("project");
		String cutLOT = request.getParameter("cutLOT");
		String createUser = request.getParameter("createUser");
		String createTime = request.getParameter("createTime");
		String agreementState = request.getParameter("agreementState");
		
		if(publishCode != null && !"".equals(publishCode)){
			query.setPublishCode(publishCode);
		}
		
		if(project != null && !"".equals(project)){
			query.setProject(project);
		}
		
		if(cutLOT != null && !"".equals(cutLOT)){
			query.setCutLOT(cutLOT);
		}

		if(createUser != null && !"".equals(createUser)){
			query.setCreateUser(createUser);
		}
		
		if(createTime != null && !"".equals(createTime)){
			query.setCreateTime(createTime);
		}
		
		if(agreementState != null && !"".equals(agreementState)){
			query.setAgreementState(agreementState);
		}
		
		return query;
	}

	@Override
	public Page queryAgreementPage(AgreementQuery query, int pageIndex,
			int pageSize) throws ServiceException {
		// TODO Auto-generated method stub
		return this.agreementDao.selectPageAgreement(query, pageIndex, pageSize);
	}

	@Override
	public Agreement queryAgreementById(Integer agreementId)
			throws ServiceException {
		// TODO Auto-generated method stub
		return this.agreementDao.selectAgreementById(agreementId);
	}

	@Override
	public List<AgreementContent> queryAgreementContentByAgreementId(
			Integer agreementId, Integer responsible) throws ServiceException {
		// TODO Auto-generated method stub
		AgreementContentQuery query = new AgreementContentQuery();
		query.setAgreementId(agreementId);
		query.setResponsible(responsible);
		return this.agreementContentDao.selectAgreementContent(query);
	}

	@Override
	public List<AgreementTache> queryAgrrementTacheByAgreementId(
			Integer agreementId) throws ServiceException {
		// TODO Auto-generated method stub
		return this.agreementTacheDao.selectAgrrementTacheByAgreementId(agreementId);
	}

	@Override
	public Agreement queryAgreementInfo(Integer orderId, Integer agreementId, Integer responsible)
			throws ServiceException {
		// TODO Auto-generated method stub
		Agreement agreement = this.queryAgreementById(agreementId);
		List<AgreementContent> agreementContentList = this.queryAgreementContentByAgreementId(agreementId, responsible);
		List<AgreementTache> agreementTacheList = this.queryAgrrementTacheByAgreementId(agreementId);
		agreement.setAgreementContentList(agreementContentList);
		List<AgreementTache> agreementTacheList2 = new LinkedList<AgreementTache>();
		for(int i = 0; i < agreementTacheList.size(); i++){
			AgreementTache agreementTache = agreementTacheList.get(i);
			//判断是否对象外
			Integer tacheId = agreementTache.getTacheId();
			boolean flag = this.isOutside(orderId, tacheId);
			if(!flag){
				agreementTache.setUserName("/");
			}else {
				Integer tacheUserId = agreementTache.getUserId();
				if(tacheUserId == null){
					agreementTache.setUserName("noshow");
				}
			}
			agreementTacheList2.add(agreementTache);
		}
		agreement.setAgreementTacheList(agreementTacheList2);
		return agreement;
	}
	
	private boolean isOutside(Integer orderId, Integer tacheId)throws ServiceException{
		boolean flag = false;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		map.put("tacheId", tacheId);
		Integer num = this.agreementDao.selectOrderTacheKey(map);
		if(num > 0){
			flag = true;
		}
		return flag;
	}

	@Override
	public void updateAgreementContent(HttpServletRequest request)
			throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setArrayMode(JsonConfig.MODE_OBJECT_ARRAY);
		
		String idJson = ServletAPIUtil.getStringParameter("idJson", request, "");
		JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(idJson);
		Object[] idArray = (Object[]) JSONSerializer.toJava(jsonArray, jsonConfig);
		
		String termJson = ServletAPIUtil.getStringParameter("termJson", request, "");
		jsonArray = (JSONArray) JSONSerializer.toJSON(termJson);
		Object[] termArray = (Object[]) JSONSerializer.toJava(jsonArray, jsonConfig);
		
		String confirmJson = ServletAPIUtil.getStringParameter("confirmJson", request, "");
		jsonArray = (JSONArray) JSONSerializer.toJSON(confirmJson);
		Object[] confirmArray = (Object[]) JSONSerializer.toJava(jsonArray, jsonConfig);
		
		String improveJson = ServletAPIUtil.getStringParameter("improveJson", request, "");
		jsonArray = (JSONArray) JSONSerializer.toJSON(improveJson);
		Object[] imporvArray = (Object[]) JSONSerializer.toJava(jsonArray, jsonConfig);
		
		for(int i = 0; i < idArray.length; i++){
			AgreementContent agreementContent = new AgreementContent();
			agreementContent.setId((Integer)idArray[i]);
			String trmeStr = (String)termArray[i];
			agreementContent.setTerm(sdf.parse(trmeStr));
			agreementContent.setConfirm(Integer.valueOf((String)confirmArray[i]));
			agreementContent.setImprove((String)imporvArray[i]);
			agreementContent.setContentState("10R");
			this.agreementContentDao.updateAgreementContent(agreementContent);
		}
		
		//发邮件通知确认人
		Integer orderId = ServletAPIUtil.getIntegerParameter("orderId", request);
		Integer agreementId = ServletAPIUtil.getIntegerParameter("agreementId", request);
		TaskOrderInfoQuery taskOrderInfoQuery = new TaskOrderInfoQuery();
		taskOrderInfoQuery.setOrderId(orderId);
		List<TaskOrderInfo> TaskOrderInfoList = this.taskOrderInfoDao.selectTaskOrderInfo(taskOrderInfoQuery);
		String publishCode = "";
		String productionLine = "";
		String changeTime = "";
		String carType = "";
		String mountingMat = "";
		String changeContent = "";
		for(int h = 0; h < TaskOrderInfoList.size(); h++){
			TaskOrderInfo taskOrderInfo = TaskOrderInfoList.get(h);
			Integer taskTypeInfoId = taskOrderInfo.getTaskTypeInfoId();
			if(taskTypeInfoId == 1){
				publishCode = taskOrderInfo.getTaskInfoValue();
			}else if(taskTypeInfoId == 2){
				productionLine = taskOrderInfo.getTaskInfoValue();
			}else if(taskTypeInfoId == 11){
				changeTime = taskOrderInfo.getTaskInfoValue();
			}else if(taskTypeInfoId == 3){
				carType = taskOrderInfo.getTaskInfoValue();
			}else if(taskTypeInfoId == 4){
				mountingMat = taskOrderInfo.getTaskInfoValue();
			}else if(taskTypeInfoId == 9){
				changeContent = taskOrderInfo.getTaskInfoValue();
			}
		}
		String comment = "";
		comment = "你好：" + productionLine + " " + changeTime + " " + carType + " " + mountingMat + " ";
		comment += changeContent + "<br>此变更单对应的立合，需要您确认，请知晓。";
		String email = "";
		AgreementContentQuery query = new AgreementContentQuery();
		query.setAgreementId(agreementId);
		User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
		Integer userId = user.getUserId();
		query.setResponsible(userId);
		List<AgreementEmailUser> agreementEmailUserList = this.agreementContentDao.selectAgreementMailUser(query);
		for(int k = 0; k < agreementEmailUserList.size(); k++){
			AgreementEmailUser agreementEmailUser = agreementEmailUserList.get(k);
			email += "," + agreementEmailUser.getUserEmail();
		}
		if(!("".equals(email)))	email = email.substring(1);
		TimeTask timeTask = new TimeTask();
		timeTask.setOrderId(orderId);
		timeTask.setNoticeColor(0);
		timeTask.setNoticeType(13);
		timeTask.setPublishCode(publishCode);
		timeTask.setTaskTypeName("立合管理表");
		timeTask.setFailedNum(0);
		timeTask.setComment(comment);
		timeTask.setUserEmail(email);
		this.timeTaskDao.insertTimeTask(timeTask);
	}

	@Override
	public List<AgreementContent> queryAgreementContentByAgreementId2(
			Integer agreementId, Integer confirmId) throws ServiceException {
		// TODO Auto-generated method stub
		AgreementContentQuery query = new AgreementContentQuery();
		query.setAgreementId(agreementId);
		query.setConfirm(confirmId);
		return this.agreementContentDao.selectAgreementContent2(query);
	}

	@Override
	public List<AgreementContent> queryAgreementContentByAgreementId3(
			Integer agreementId) throws ServiceException {
		// TODO Auto-generated method stub
		return this.agreementContentDao.selectAgreementContent3(agreementId);
	}

	@Override
	public Agreement queryAgreementInfo2(Integer orderId, Integer agreementId,
			Integer confirmId) throws ServiceException {
		// TODO Auto-generated method stub
		Agreement agreement = this.queryAgreementById(agreementId);
		List<AgreementContent> agreementContentList = this.queryAgreementContentByAgreementId2(agreementId, confirmId);
		List<AgreementTache> agreementTacheList = this.queryAgrrementTacheByAgreementId(agreementId);
		agreement.setAgreementContentList(agreementContentList);
		List<AgreementTache> agreementTacheList2 = new LinkedList<AgreementTache>();
		for(int i = 0; i < agreementTacheList.size(); i++){
			AgreementTache agreementTache = agreementTacheList.get(i);
			//判断是否对象外
			Integer tacheId = agreementTache.getTacheId();
			boolean flag = this.isOutside(orderId, tacheId);
			if(!flag){
				agreementTache.setUserName("/");
			}else {
				Integer tacheUserId = agreementTache.getUserId();
				if(tacheUserId == null){
					agreementTache.setUserName("noshow");
				}
			}
			agreementTacheList2.add(agreementTache);
		}
		agreement.setAgreementTacheList(agreementTacheList2);
		return agreement;
	}

	@Override
	public Agreement queryAgreementInfo3(Integer orderId, Integer agreementId)
			throws ServiceException {
		// TODO Auto-generated method stub
		Agreement agreement = this.queryAgreementById(agreementId);
		List<AgreementContent> agreementContentList = this.queryAgreementContentByAgreementId3(agreementId);
		List<AgreementTache> agreementTacheList = this.queryAgrrementTacheByAgreementId(agreementId);
		agreement.setAgreementContentList(agreementContentList);
		List<AgreementTache> agreementTacheList2 = new LinkedList<AgreementTache>();
		for(int i = 0; i < agreementTacheList.size(); i++){
			AgreementTache agreementTache = agreementTacheList.get(i);
			//判断是否需要现场立合
			Integer tacheId = agreementTache.getTacheId();
			boolean flag = this.isOutside(orderId, tacheId);
			if(flag){
				Integer tacheUserId = agreementTache.getUserId();
				if(tacheUserId == null){
					agreementTache.setUserName("noshow");
				}
			}else {
				agreementTache.setUserName("/");
			}
			agreementTacheList2.add(agreementTache);
		}
		agreement.setAgreementTacheList(agreementTacheList2);
		return agreement;
	}

	@Override
	public void editRefuseAgreementContent(Integer orderId, Integer agreementId, Integer id, String refuseReason)
			throws ServiceException {
		// TODO Auto-generated method stub
		AgreementContent agreementContent = new AgreementContent();
		agreementContent.setId(id);
		agreementContent.setContentState("10B");
		agreementContent.setRefuseReason(refuseReason);
		this.agreementContentDao.updateAgreementContent(agreementContent);
		
		//发邮件通知确认人
		TaskOrderInfoQuery taskOrderInfoQuery = new TaskOrderInfoQuery();
		taskOrderInfoQuery.setOrderId(orderId);
		List<TaskOrderInfo> TaskOrderInfoList = this.taskOrderInfoDao.selectTaskOrderInfo(taskOrderInfoQuery);
		String publishCode = "";
		String productionLine = "";
		String changeTime = "";
		String carType = "";
		String mountingMat = "";
		String changeContent = "";
		for(int h = 0; h < TaskOrderInfoList.size(); h++){
			TaskOrderInfo taskOrderInfo = TaskOrderInfoList.get(h);
			Integer taskTypeInfoId = taskOrderInfo.getTaskTypeInfoId();
			if(taskTypeInfoId == 1){
				publishCode = taskOrderInfo.getTaskInfoValue();
			}else if(taskTypeInfoId == 2){
				productionLine = taskOrderInfo.getTaskInfoValue();
			}else if(taskTypeInfoId == 11){
				changeTime = taskOrderInfo.getTaskInfoValue();
			}else if(taskTypeInfoId == 3){
				carType = taskOrderInfo.getTaskInfoValue();
			}else if(taskTypeInfoId == 4){
				mountingMat = taskOrderInfo.getTaskInfoValue();
			}else if(taskTypeInfoId == 9){
				changeContent = taskOrderInfo.getTaskInfoValue();
			}
		}
		String comment = "";
		comment = "你好：" + productionLine + " " + changeTime + " " + carType + " " + mountingMat + " ";
		comment += changeContent + "<br>此变更单对应的立合，您填写的内容已被拒绝，请知晓。";
		AgreementContent agreementContent2 = this.agreementContentDao.selectAgreementContentById(id);
		Integer resposnible = agreementContent2.getResponsible();
		UserQuery query = new UserQuery();
		query.setUserId(resposnible);
		User user = this.userDao.selectByQuery(query);
		String email = user.getEmail();
		TimeTask timeTask = new TimeTask();
		timeTask.setOrderId(orderId);
		timeTask.setNoticeColor(0);
		timeTask.setNoticeType(17);
		timeTask.setPublishCode(publishCode);
		timeTask.setTaskTypeName("立合管理表");
		timeTask.setFailedNum(0);
		timeTask.setComment(comment);
		timeTask.setUserEmail(email);
		this.timeTaskDao.insertTimeTask(timeTask);
	}

	@Override
	public void editAcceptAgreementContent(Integer orderId, Integer agreementId, Integer id, String state)
			throws ServiceException {
		// TODO Auto-generated method stub
		AgreementContent agreementContent = new AgreementContent();
		agreementContent.setId(id);
		agreementContent.setContentState("10C");
		agreementContent.setState(state);
		this.agreementContentDao.updateAgreementContent(agreementContent);
	}

	@Override
	public void editCompleteAgreement(Integer orderId, Integer agreementId)
			throws ServiceException {
		// TODO Auto-generated method stub
		List<AgreementContent> agreementContentList = this.agreementContentDao.selectAgreementContent3(agreementId);
		if(agreementContentList == null){
			throw new ServiceException("查询立合结果为空!agreementId="+agreementId);
		}else {
			boolean flag = true;
			boolean isCloseFlag = true;
			for(int i = 0; i < agreementContentList.size(); i++){
				AgreementContent agreementContent = agreementContentList.get(i);
				if(!("10C".equals(agreementContent.getContentState()))){
					flag = false;
				}
				if(!("OK".equals(agreementContent.getState()))){
					isCloseFlag = false;
				}
			}
			if(isCloseFlag){
				Agreement agreement = this.agreementDao.selectAgreementById(agreementId);
				if(agreement.getIsClose().equals(0)){
					Agreement agreement2 = new Agreement();
					agreement2.setAgreementId(agreementId);
					agreement2.setIsClose(1);
					this.agreementDao.updateAgreement(agreement2);
				}
			}
			if(flag){
				Agreement agreement = new Agreement();
				agreement.setAgreementId(agreementId);
				agreement.setAgreementState("10C");
				this.agreementDao.updateAgreement(agreement);
				//发邮件通知创建人
//				TaskOrderInfoQuery taskOrderInfoQuery = new TaskOrderInfoQuery();
//				taskOrderInfoQuery.setOrderId(orderId);
//				List<TaskOrderInfo> TaskOrderInfoList = this.taskOrderInfoDao.selectTaskOrderInfo(taskOrderInfoQuery);
//				String publishCode = "";
//				String productionLine = "";
//				String changeTime = "";
//				String carType = "";
//				String mountingMat = "";
//				String changeContent = "";
//				for(int h = 0; h < TaskOrderInfoList.size(); h++){
//					TaskOrderInfo taskOrderInfo = TaskOrderInfoList.get(h);
//					Integer taskTypeInfoId = taskOrderInfo.getTaskTypeInfoId();
//					if(taskTypeInfoId == 1){
//						publishCode = taskOrderInfo.getTaskInfoValue();
//					}else if(taskTypeInfoId == 2){
//						productionLine = taskOrderInfo.getTaskInfoValue();
//					}else if(taskTypeInfoId == 11){
//						changeTime = taskOrderInfo.getTaskInfoValue();
//					}else if(taskTypeInfoId == 3){
//						carType = taskOrderInfo.getTaskInfoValue();
//					}else if(taskTypeInfoId == 4){
//						mountingMat = taskOrderInfo.getTaskInfoValue();
//					}else if(taskTypeInfoId == 9){
//						changeContent = taskOrderInfo.getTaskInfoValue();
//					}
//				}
//				String comment = "";
//				comment = "你好：" + productionLine + " " + changeTime + " " + carType + " " + mountingMat + " ";
//				comment += changeContent + "<br>此变更单对应的立合，需要你填写结论，请知晓。";
//				Agreement agreement2 = this.agreementDao.selectAgreementById(agreementId);
//				Integer userId = agreement2.getCreateUserId();
//				UserQuery query = new UserQuery();
//				query.setUserId(userId);
//				User user = this.userDao.selectByQuery(query);
//				String email = user.getEmail();
//				TimeTask timeTask = new TimeTask();
//				timeTask.setOrderId(orderId);
//				timeTask.setNoticeColor(0);
//				timeTask.setNoticeType(14);
//				timeTask.setPublishCode(publishCode);
//				timeTask.setTaskTypeName("立合管理表");
//				timeTask.setFailedNum(0);
//				timeTask.setComment(comment);
//				timeTask.setUserEmail(email);
//				this.timeTaskDao.insertTimeTask(timeTask);
			}
		}
	}

	@Override
	public void editAgreementResultOK(Integer orderId, Integer agreementId)
			throws ServiceException {
		// TODO Auto-generated method stub
		Agreement agreement = new Agreement();
		agreement.setAgreementId(agreementId);
		agreement.setAgreementState("10C");
		agreement.setConclusionState("OK");
		this.agreementDao.updateAgreement(agreement);
	}

	@Override
	public void editAgreementResultNG(Integer orderId, Integer agreementId,
			String resultMessage) throws ServiceException {
		// TODO Auto-generated method stub
		Agreement agreement = new Agreement();
		agreement.setAgreementId(agreementId);
		agreement.setAgreementState("10N");
		agreement.setConclusionState("NG");
		agreement.setConclusionMessage(resultMessage);
		this.agreementDao.updateAgreement(agreement);
	}

	@Override
	public void updateAgreement(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		Integer orderId = ServletAPIUtil.getIntegerParameter("orderId", request, null);
		Integer agreementId = ServletAPIUtil.getIntegerParameter("agreementId", request, null);
		Agreement agreement = new Agreement();
		agreement.setAgreementId(agreementId);
		String project = ServletAPIUtil.getStringParameter("agreement_project_code", request, "");
		String cutLOT = ServletAPIUtil.getStringParameter("agreement_cut_lot", request, "");
		Integer num =  ServletAPIUtil.getIntegerParameter("agreement_num", request);
		Integer trackId =  ServletAPIUtil.getIntegerParameter("agreement_track_id", request);
		String conclusionState = ServletAPIUtil.getStringParameter("agreement_project_result", request, "");
		String conclusionMessage = ServletAPIUtil.getStringParameter("agreement_conclusion_message", request, "");
		agreement.setProject(project);
		agreement.setCutLOT(cutLOT);
		agreement.setNum(num);
		agreement.setTrackId(trackId);
		if("".equals(conclusionState)){
			
		}else {
			agreement.setConclusionState(conclusionState);
			agreement.setConclusionMessage(conclusionMessage);
		}
		this.agreementDao.updateAgreement(agreement);
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setArrayMode(JsonConfig.MODE_OBJECT_ARRAY);
		
		String idJson = ServletAPIUtil.getStringParameter("idJson", request, "");
		JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(idJson);
		Object[] idArray = (Object[]) JSONSerializer.toJava(jsonArray, jsonConfig);
		
		String problemJson = ServletAPIUtil.getStringParameter("problemJson", request, "");
		jsonArray = (JSONArray) JSONSerializer.toJSON(problemJson);
		Object[] problemArray = (Object[]) JSONSerializer.toJava(jsonArray, jsonConfig);
		
		String responsibleJson = ServletAPIUtil.getStringParameter("responsibleJson", request, "");
		jsonArray = (JSONArray) JSONSerializer.toJSON(responsibleJson);
		Object[] responsibleArray = (Object[]) JSONSerializer.toJava(jsonArray, jsonConfig);
		
		String stateJson = ServletAPIUtil.getStringParameter("stateJson", request, "");
		jsonArray = (JSONArray) JSONSerializer.toJSON(stateJson);
		Object[] stateArray = (Object[]) JSONSerializer.toJava(jsonArray, jsonConfig);
		
		String confirmJson = ServletAPIUtil.getStringParameter("confirmJson", request, "");
		jsonArray = (JSONArray) JSONSerializer.toJSON(confirmJson);
		Object[] confirmArray = (Object[]) JSONSerializer.toJava(jsonArray, jsonConfig);
		
		for(int i = 0; i < idArray.length; i++){
			AgreementContent agreementContent = new AgreementContent();
			agreementContent.setId((Integer)idArray[i]);
			agreementContent.setProblem((String)problemArray[i]);
			agreementContent.setResponsible(Integer.valueOf((String)responsibleArray[i]));
			if(!"".equals((String)stateArray[i])){
				agreementContent.setState((String)stateArray[i]);
			}
			if(Integer.valueOf((String)confirmArray[i]) != 0){
				agreementContent.setConfirm(Integer.valueOf((String)confirmArray[i]));
			}
			this.agreementContentDao.updateAgreementContent(agreementContent);
		}
		
		//删除agreementContent
		List<AgreementContent> agreementContentList = this.agreementContentDao.selectAgreementContent3(agreementId);
		for(int i = 0; i < agreementContentList.size(); i++){
			AgreementContent agreementContent = agreementContentList.get(i);
			Integer id = agreementContent.getId();
			boolean flag = true;
			for(int j = 0; j < idArray.length; j++){
				if(id.equals((Integer)idArray[j])){
					flag = false;
					break;
				}
			}
			if(flag){
				this.agreementContentDao.deleteAgreementContent(id);
			}
		}
		//调整seq
		agreementContentList = this.agreementContentDao.selectAgreementContent3(agreementId);
		for(int i = 0; i < agreementContentList.size(); i++){
			AgreementContent agreementContent = agreementContentList.get(i);
			Integer seq = agreementContent.getSeq();
			if(seq.equals((i + 1))){
				
			}else {
				AgreementContent AgreementContent2 = new AgreementContent();
				AgreementContent2.setId(agreementContent.getId());
				AgreementContent2.setSeq(i + 1);
				this.agreementContentDao.updateAgreementContent(AgreementContent2);
			}
		}
		
		this.editCompleteAgreement(orderId, agreementId);
		
		//邮件通知
		TaskOrderInfoQuery taskOrderInfoQuery = new TaskOrderInfoQuery();
		taskOrderInfoQuery.setOrderId(orderId);
		List<TaskOrderInfo> TaskOrderInfoList = this.taskOrderInfoDao.selectTaskOrderInfo(taskOrderInfoQuery);
		String publishCode = "";
		String productionLine = "";
		String changeTime = "";
		String carType = "";
		String mountingMat = "";
		String changeContent = "";
		for(int h = 0; h < TaskOrderInfoList.size(); h++){
			TaskOrderInfo taskOrderInfo = TaskOrderInfoList.get(h);
			Integer taskTypeInfoId = taskOrderInfo.getTaskTypeInfoId();
			if(taskTypeInfoId == 1){
				publishCode = taskOrderInfo.getTaskInfoValue();
			}else if(taskTypeInfoId == 2){
				productionLine = taskOrderInfo.getTaskInfoValue();
			}else if(taskTypeInfoId == 11){
				changeTime = taskOrderInfo.getTaskInfoValue();
			}else if(taskTypeInfoId == 3){
				carType = taskOrderInfo.getTaskInfoValue();
			}else if(taskTypeInfoId == 4){
				mountingMat = taskOrderInfo.getTaskInfoValue();
			}else if(taskTypeInfoId == 9){
				changeContent = taskOrderInfo.getTaskInfoValue();
			}
		}
		String comment = "";
		comment = "你好：" + productionLine + " " + changeTime + " " + carType + " " + mountingMat + " ";
		comment += changeContent + "<br>此变更单对应的立合单，已修改，请知晓。";
		String email = "";
		List<AgreementEmailUser> agreementEmailUserList =  this.agreementDao.selectAgreementAllMailUser(agreementId);
		for(int k = 0; k < agreementEmailUserList.size(); k++){
			AgreementEmailUser agreementEmailUser = agreementEmailUserList.get(k);
			email += "," + agreementEmailUser.getUserEmail();
		}
		if(!("".equals(email)))	email = email.substring(1);
		TimeTask timeTask = new TimeTask();
		timeTask.setOrderId(orderId);
		timeTask.setNoticeColor(0);
		timeTask.setNoticeType(15);
		timeTask.setPublishCode(publishCode);
		timeTask.setTaskTypeName("立合管理表");
		timeTask.setFailedNum(0);
		timeTask.setComment(comment);
		timeTask.setUserEmail(email);
		this.timeTaskDao.insertTimeTask(timeTask);
	}

	@Override
	public ReturnInfo deleteAgreement(Integer orderId, Integer agreementId,
			String invalidateText) {
		// TODO Auto-generated method stub
		ReturnInfo returnInfo = new ReturnInfo();
		try{
			Agreement agreement = new Agreement();
			agreement.setAgreementId(agreementId);
			agreement.setAgreementState("10X");
			agreement.setInvalidateText(invalidateText);
			this.agreementDao.updateAgreement(agreement);

			//更改订单立合状态
			TaskOrder taskOrder = new TaskOrder();
			taskOrder.setOrderId(orderId);
			taskOrder.setAgreementState("待立合");
			this.taskOrderDao.updateTaskOrder(taskOrder);
			
			List<AgreementContent> agreementContentList = this.agreementContentDao.selectAgreementContent3(agreementId);
			if(agreementContentList == null){
				
			}else {
				for(int i = 0; i < agreementContentList.size(); i++){
					AgreementContent agreementContent = agreementContentList.get(i);
					AgreementContent agreementContent2 = new AgreementContent();
					agreementContent2.setId(agreementContent.getId());
					agreementContent2.setContentState("10X");
					this.agreementContentDao.updateAgreementContent(agreementContent2);
				}
			}
			
			//邮件通知
			TaskOrderInfoQuery taskOrderInfoQuery = new TaskOrderInfoQuery();
			taskOrderInfoQuery.setOrderId(orderId);
			List<TaskOrderInfo> TaskOrderInfoList = this.taskOrderInfoDao.selectTaskOrderInfo(taskOrderInfoQuery);
			String publishCode = "";
			String productionLine = "";
			String changeTime = "";
			String carType = "";
			String mountingMat = "";
			String changeContent = "";
			for(int h = 0; h < TaskOrderInfoList.size(); h++){
				TaskOrderInfo taskOrderInfo = TaskOrderInfoList.get(h);
				Integer taskTypeInfoId = taskOrderInfo.getTaskTypeInfoId();
				if(taskTypeInfoId == 1){
					publishCode = taskOrderInfo.getTaskInfoValue();
				}else if(taskTypeInfoId == 2){
					productionLine = taskOrderInfo.getTaskInfoValue();
				}else if(taskTypeInfoId == 11){
					changeTime = taskOrderInfo.getTaskInfoValue();
				}else if(taskTypeInfoId == 3){
					carType = taskOrderInfo.getTaskInfoValue();
				}else if(taskTypeInfoId == 4){
					mountingMat = taskOrderInfo.getTaskInfoValue();
				}else if(taskTypeInfoId == 9){
					changeContent = taskOrderInfo.getTaskInfoValue();
				}
			}
			String comment = "";
			comment = "你好：" + productionLine + " " + changeTime + " " + carType + " " + mountingMat + " ";
			comment += changeContent + "<br>此变更单对应的立合单，已作废，请知晓。";
			String email = "";
			List<AgreementEmailUser> agreementEmailUserList =  this.agreementDao.selectAgreementAllMailUser(agreementId);
			for(int k = 0; k < agreementEmailUserList.size(); k++){
				AgreementEmailUser agreementEmailUser = agreementEmailUserList.get(k);
				email += "," + agreementEmailUser.getUserEmail();
			}
			if(!("".equals(email)))	email = email.substring(1);
			TimeTask timeTask = new TimeTask();
			timeTask.setOrderId(orderId);
			timeTask.setNoticeColor(0);
			timeTask.setNoticeType(16);
			timeTask.setPublishCode(publishCode);
			timeTask.setTaskTypeName("立合管理表");
			timeTask.setFailedNum(0);
			timeTask.setComment(comment);
			timeTask.setUserEmail(email);
			this.timeTaskDao.insertTimeTask(timeTask);
		}catch(Exception e){
			e.printStackTrace();
			returnInfo.returnFlag = false;
			returnInfo.returnMessage = e.getMessage();
		}
		return returnInfo;
	}

	@Override
	public Integer queryAgreementIdByOrderId(Integer orderId)
			throws ServiceException {
		// TODO Auto-generated method stub
		return this.agreementDao.selectAgreementIdByOrderId(orderId);
	}

}
