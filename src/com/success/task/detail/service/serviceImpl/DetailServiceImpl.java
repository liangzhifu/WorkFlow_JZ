package com.success.task.detail.service.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.dpcoi.order.domain.DpcoiOrder;
import com.dpcoi.order.service.DpcoiOrderService;
import com.dpcoi.woOrder.domain.DpcoiWoOrder;
import com.dpcoi.woOrder.service.DpcoiWoOrderService;
import com.success.task.base.dao.TaskWoOrderSysInfoDao;
import com.success.task.base.domain.*;
import com.success.task.detail.dao.TaskOrderDao;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import com.success.common.ReturnInfo;
import com.success.common.info.domain.InfoValue;
import com.success.sys.email.dao.TimeTaskDao;
import com.success.sys.email.domain.TimeTask;
import com.success.sys.org.domain.Org;
import com.success.sys.permission.service.PermissionService;
import com.success.sys.user.dao.UserDao;
import com.success.sys.user.domain.User;
import com.success.sys.user.query.UserQuery;
import com.success.task.base.dao.TaskOrderInfoDao;
import com.success.task.base.query.TaskNoticeCycleQuery;
import com.success.task.base.query.TaskOrderInfoQuery;
import com.success.task.base.query.TaskWoOrderDetailQuery;
import com.success.task.base.service.TaskConfirmOrderService;
import com.success.task.base.service.TaskNoticeCycleService;
import com.success.task.base.service.TaskOrderInfoService;
import com.success.task.base.service.TaskWoOrderInfoService;
import com.success.task.derived.service.ExportExcel;
import com.success.task.detail.dao.DetailDao;
import com.success.task.detail.domain.Detail;
import com.success.task.detail.domain.TaskOrder;
import com.success.task.detail.domain.TaskWoOrder;
import com.success.task.detail.query.DetailQuery;
import com.success.task.detail.query.TaskOrderQuery;
import com.success.task.detail.service.DetailService;
import com.success.task.detail.service.TaskOrderService;
import com.success.task.detail.service.TaskWoOrderService;
import com.success.templet.content.domain.TaskTacheInfo;
import com.success.templet.content.domain.TaskTypeInfo;
import com.success.templet.run.domain.TaskTypeRun;
import com.success.templet.run.query.TaskTypeRunQuery;
import com.success.templet.run.service.TaskTypeRunService;
import com.success.templet.tache.domain.TaskTache;
import com.success.templet.task.dao.TaskTypeDao;
import com.success.templet.task.domain.TaskType;
import com.success.templet.task.query.TaskTypeQuery;
import com.success.templet.task.service.TaskTypeService;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;
import com.success.web.framework.util.PageParamJSON;
import com.success.web.framework.util.ServletAPIUtil;

@Service("detailService")
public class DetailServiceImpl implements DetailService {

	@Resource(name = "detailDao")
	private DetailDao detailDao;
	
	@Resource(name = "taskOrderService")
	private TaskOrderService taskOrderService;
	
	@Resource(name = "taskTypeService")
	private TaskTypeService taskTypeService;
	
	@Resource(name = "taskOrderInfoService")
	private TaskOrderInfoService taskOrderInfoService;
	
	@Resource(name = "taskTypeRunService")
	private TaskTypeRunService taskTypeRunService;
	
	@Resource(name = "taskConfirmOrderService")
	private TaskConfirmOrderService taskConfirmOrderService;
	
	@Resource(name = "taskNoticeCycleService")
	private TaskNoticeCycleService taskNoticeCycleService;
	
	@Resource(name = "taskWoOrderService")
	private TaskWoOrderService taskWoOrderService;
	
	@Resource(name = "taskWoOrderInfoService")
	private TaskWoOrderInfoService taskWoOrderInfoService;
	
	@Resource(name = "permissionService")
	private PermissionService permissionService;
	
	@Resource(name = "timeTaskDao")
	private TimeTaskDao timeTaskDao;
	
	@Resource(name = "userDao")
	private UserDao userDao;
	
	@Resource(name = "exportExcel")
	private ExportExcel exportExcel;
	
	@Resource(name = "taskOrderInfoDao")
	private TaskOrderInfoDao taskOrderInfoDao;
	
	@Resource(name = "taskTypeDao")
	private TaskTypeDao taskTypeDao;

	@Resource(name = "taskOrderDao")
	private TaskOrderDao taskOrderDao;

	@Resource(name = "dpcoiOrderService")
	private DpcoiOrderService dpcoiOrderService;

	@Resource(name = "dpcoiWoOrderService")
	private DpcoiWoOrderService dpcoiWoOrderService;

	@Resource(name = "taskWoOrderSysInfoDao")
	private TaskWoOrderSysInfoDao taskWoOrderSysInfoDao;

	@Override
	public void addTask(HttpServletRequest request, int taskTypeId) throws Exception{
		// TODO Auto-generated method stub	
		TaskTypeQuery taskTypeQuery = new TaskTypeQuery();
		taskTypeQuery.setTaskTypeId(taskTypeId);
		TaskType taskType = this.taskTypeService.selectTaskTypeByIdQuery(taskTypeQuery);
		
		//插入task_notice_cycle
		TaskNoticeCycleQuery taskNoticeCycleQuery = new TaskNoticeCycleQuery();
		taskNoticeCycleQuery.setCycleId(taskType.getCycleId());
		TaskNoticeCycle taskNoticeCycle = this.taskNoticeCycleService.selectTaskNoticeCycleByIdQuery(taskNoticeCycleQuery);
		TaskNoticeCycle newTaskNoticeCycle = new TaskNoticeCycle();
		newTaskNoticeCycle.setIsNotice(taskNoticeCycle.getIsNotice());
		newTaskNoticeCycle.setNoticeCycle(taskNoticeCycle.getNoticeCycle());
		newTaskNoticeCycle.setIsAlarm(taskNoticeCycle.getIsAlarm());
		newTaskNoticeCycle.setAlarmTime(taskNoticeCycle.getAlarmTime());
		this.taskNoticeCycleService.addTaskNoticeCycle(newTaskNoticeCycle);
		
		//插入task_order表
		TaskOrder taskOrder = new TaskOrder();
		taskOrder.setTaskTypeId(taskTypeId);
		taskOrder.setCycleId(newTaskNoticeCycle.getCycleId());
		Integer createUserId =  ServletAPIUtil.getIntegerParameter("userId", request);
		UserQuery userQuery = new UserQuery();
		userQuery.setUserId(createUserId);
		User user = this.userDao.selectByQuery(userQuery);
		//User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
		taskOrder.setCreateUserId(user.getUserId());
		taskOrder.setOrderStateCode("10A");
		taskOrder.setConfirmOrderStateCode("10B");
		//获取变更时间
		String changeTimeStr = ServletAPIUtil.getStringParameter("order_11", request, "");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date changeTime = sdf.parse(changeTimeStr);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(changeTime);
		calendar.add(12, (0 - taskNoticeCycle.getAlarmTime()));
		Date alarmTime = calendar.getTime();
		taskOrder.setAlarmTime(alarmTime);
		taskOrder.setVersion(taskType.getVersion());
		this.taskOrderService.addTaskOrder(taskOrder);
			
		//插入task_order_info表
		List<TaskTypeInfo> taskTypeInfoList = taskType.getTaskTypeInfo();
		for(int i = 0; i < taskTypeInfoList.size(); i++){
			TaskOrderInfo taskOrderInfo = new TaskOrderInfo();
			
			TaskTypeInfo taskTypeInfo = taskTypeInfoList.get(i);
			int taskTypeInfoId = taskTypeInfo.getTaskTypeInfoId();
			int infoTypeId = taskTypeInfo.getInfoTypeId();
			String value = null;
			if(infoTypeId == 1){
				if(taskTypeInfoId == 1){//发行编号
					Calendar now = Calendar.getInstance();  
					int year = now.get(Calendar.YEAR);
					int month = now.get(Calendar.MONTH) + 1;
			        if(month > 3){
			        	year += 1;
			        }
			        //获取当前年的最大流水号
			        String shortYear = String.valueOf(year).substring(2);
			        //天津版本
					Integer max = this.detailDao.selectMax("TT-CH-"+shortYear+"-");
					if(max == null){
						value = "0001";
					}else {
						max += 1;
						if(max < 10){
							value = "000" + max;
						}else if(max < 100){
							value = "00" + max;
						}else if(max < 1000){
							value = "0" + max;
						}else{
							value = "" + max;
						}
					}
					value = "TT-CH-" + shortYear + "-" + value;
					//荆州版本
//					Integer max = this.detailDao.selectMax("TJ-PM-"+shortYear+"-");
//			        if(max == null){
//			        	value = "00001";
//			        }else {
//			        	max += 1;
//			        	if(max < 10){
//			        		value = "0000" + max;
//			        	}else if(max < 100){
//			        		value = "000" + max;
//			        	}else if(max < 1000){
//			        		value = "00" + max;
//			        	}else if(max < 10000){
//							value = "0" + max;
//						}else{
//			        		value = "" + max;
//			        	}
//			        }
//			        value = "TJ-PM-" + shortYear + "-" + value;
				}else {
					value = ServletAPIUtil.getStringParameter("order_"+taskTypeInfoId, request, null);
				}
			}else if(infoTypeId == 2){
				value = ServletAPIUtil.getStringParameter("order_"+taskTypeInfoId, request, null);
			}else if(infoTypeId == 3){
				int sysInfoId = 0;
				boolean flag = false;
				List<InfoValue> infoValueList = taskTypeInfo.getInfoValueList();
				for(int j = 0; j < infoValueList.size(); j++){
					InfoValue infoValue = infoValueList.get(j);
					
					if(infoValue.getInfoKey() != null && infoValue.getInfoKey() == 1){
						flag = true;
						sysInfoId = infoValue.getInfoId();
					}
				}
				String[] checkArray = request.getParameterValues("order_check_"+taskTypeInfoId);
				String checkStr = "";
				if(checkArray == null){
					
				}else {
					for(int l = 0; l < checkArray.length; l++){
						checkStr += "," + checkArray[l];
					}
					checkStr = checkStr.substring(1);
				}
				if(flag){
					String infoText = ServletAPIUtil.getStringParameter("order_check_"+taskTypeInfoId+"_"+sysInfoId+"_value", request, "");
					if(infoText == null) infoText = "";
					value = checkStr + "<<?><?>>" + infoText;
				}else {
					value = checkStr;
				}
			}else if(infoTypeId == 4){
				value = ServletAPIUtil.getStringParameter("order_"+taskTypeInfoId, request, null);
			}else {
				value = ServletAPIUtil.getStringParameter("order_"+taskTypeInfoId, request, null);
			}
			taskOrderInfo.setOrderId(taskOrder.getOrderId());
			taskOrderInfo.setTaskTypeInfoId(taskTypeInfoId);
			taskOrderInfo.setTaskInfoValue(value);
			this.taskOrderInfoService.addTaskOrderInfo(taskOrderInfo);
		}
	
		//插入task_confim_order表
		TaskTypeRunQuery taskTypeRunQuery = new TaskTypeRunQuery();
		taskTypeRunQuery.setTaskTypeId(taskTypeId);
		List<TaskTypeRun> taskTypeRunList = this.taskTypeRunService.queryTaskTypeRuns(taskTypeRunQuery);
		for (int i = 0; i < taskTypeRunList.size(); i++){
			TaskConfirmOrder taskConfirmOrder = new TaskConfirmOrder();
			TaskTypeRun taskTypeRun = taskTypeRunList.get(i);
			String runType = taskTypeRun.getRunType();
			String runCode = taskTypeRun.getRunCode();
			if(runType.equals("input")){

			}else {
				if("accept2_confirm".equals(runCode)){
					//审核组领导人
					UserQuery userQuery2 = new UserQuery();
					userQuery2.setIsHeader("1");
					userQuery2.setOrgId("237");
					User user2 = this.userDao.selectByQuery(userQuery2);
					taskConfirmOrder.setConfirmUserId(user2.getUserId());
				}else if("confirm_confirm".equals(runCode) || "quality2_confirm".equals(runCode)){
					
				}else{
					String userId = ServletAPIUtil.getStringParameter("order_"+taskTypeRun.getRunCode()+"_staff", request, "");
					if(!("".equals(userId))) taskConfirmOrder.setConfirmUserId(Integer.valueOf(userId));	
				}
			}
			taskConfirmOrder.setOrderId(taskOrder.getOrderId());
			taskConfirmOrder.setConfirmOrderStateCode("10A");
			taskConfirmOrder.setConfirmUserSeq(taskTypeRun.getSeq());
			taskConfirmOrder.setRunType(taskTypeRun.getRunType());
			taskConfirmOrder.setRunCode(runCode);
			this.taskConfirmOrderService.addTaskConfirmOrder(taskConfirmOrder);
		}
	
		//插入task_wo_order表
		List<TaskTache> taskTacheList = taskType.getTaskTache();
		for(int i = 0; i < taskTacheList.size(); i++){
			TaskTache taskTache = taskTacheList.get(i);
			TaskWoOrder taskWoOrder = new TaskWoOrder();
			taskWoOrder.setOrderId(taskOrder.getOrderId());
			taskWoOrder.setTacheId(taskTache.getTacheId());
			taskWoOrder.setWoOrderStateCode("10A");
			this.taskWoOrderService.addTaskWoOrder(taskWoOrder);
			
			//插入task_wo_order_info表
			List<TaskTacheInfo> taskTacheInfoList = taskTache.getTaskTacheInfo();
			for(int j = 0; j < taskTacheInfoList.size(); j++){
				TaskTacheInfo taskTacheInfo = taskTacheInfoList.get(j);
				TaskWoOrderInfo taskWoOrderInfo = new TaskWoOrderInfo();
				int taskTacheInfoId = taskTacheInfo.getTaskTacheInfoId();
				int infoTypeId = taskTacheInfo.getInfoTypeId();
				String value = null;
				if(infoTypeId == 1){
					value = "";
				}else if(infoTypeId == 2){
					value = "";
				}else if(infoTypeId == 3){
					boolean flag = false;
					List<InfoValue> infoValueList = taskTacheInfo.getInfoValueList();
					for(int l = 0; l < infoValueList.size(); l++){
						InfoValue infoValue = infoValueList.get(l);
						if(infoValue.getInfoKey() != null && infoValue.getInfoKey() == 1){
							flag = true;
						}
					}
					if(flag){
						value = "<<?><?>>";
					}else {
						value = "";
					}
				}else {
					value = "";
				}
				taskWoOrderInfo.setWoOrderId(taskWoOrder.getWoOrderId());
				taskWoOrderInfo.setTaskTacheInfoId(taskTacheInfoId);
				taskWoOrderInfo.setWoInfoValue(value);
				this.taskWoOrderInfoService.addTaskWoOrderInfo(taskWoOrderInfo);
			}
		}
		this.taskConfirmOrderService.startTaskConfirmOrder(taskOrder.getOrderId(), 1);
	}
	
	@Override
	public ReturnInfo deleteTask(int orderId, String invalidateText, User user){
		ReturnInfo returnInfo = new ReturnInfo();
		TaskOrderQuery taskOrderQuery = new TaskOrderQuery();
		taskOrderQuery.setOrderId(orderId);
		try{
			TaskOrder taskOrder = this.taskOrderService.getTaskOrderDetail(taskOrderQuery);
			List<TaskConfirmOrder> TaskConfirmOrderList = taskOrder.getTaskConfirmOrderList();
			if(TaskConfirmOrderList == null){
				
			}else {
				for(int i = 0; i < TaskConfirmOrderList.size(); i++){
					TaskConfirmOrder taskConfirmOrder = TaskConfirmOrderList.get(i);
					this.taskConfirmOrderService.deleteTaskConfirmOrder(taskConfirmOrder);
				}
			}
			List<TaskWoOrder> taskWoOrderList = taskOrder.getTaskWoOrderList();
			if(taskWoOrderList == null){
				
			}else {
				for(int i = 0; i < taskWoOrderList.size(); i++){
					TaskWoOrder taskWoOrder = taskWoOrderList.get(i);
					this.taskWoOrderService.deleteTaskWoOrder(taskWoOrder);
				}
			}
			taskOrder.setInvalidateText(invalidateText);
			this.taskOrderService.deleteTaskOrder(taskOrder);
			
			//发邮件通知做成人，变更单已作废
			taskOrderQuery = new TaskOrderQuery();
			taskOrderQuery.setOrderId(orderId);
			Integer taskTypeId = taskOrder.getTaskTypeId();
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
			TaskTypeQuery taskTypeQuery = new TaskTypeQuery();
			taskTypeQuery.setTaskTypeId(taskTypeId);
			TaskType taskType = this.taskTypeDao.selectTaskTypeByIdQuery(taskTypeQuery);
			TimeTask timeTask = new TimeTask();
			String comment = "";
			comment = "你好：" + productionLine + " " + changeTime + " " + carType + " " + mountingMat + " ";
			comment += changeContent + "<br>此变更单已作废，请知晓。";
			String email = "";
			for(int i = 0; i < TaskConfirmOrderList.size(); i++){
				TaskConfirmOrder taskConfirmOrder = TaskConfirmOrderList.get(i);
				String runCode = taskConfirmOrder.getRunCode();
				if("accept_confirm".equals(runCode)){
					Integer userId = taskConfirmOrder.getConfirmUserId();
					if(userId != null){
						UserQuery userQuery = new UserQuery();
						userQuery.setUserId(userId);
						User user1 = this.userDao.selectByQuery(userQuery);
						email = user1.getEmail();
					}
				}
				this.taskConfirmOrderService.deleteTaskConfirmOrder(taskConfirmOrder);
			}
			timeTask.setUserEmail(email);
			timeTask.setOrderId(orderId);
			timeTask.setNoticeColor(0);
			timeTask.setNoticeType(10);
			timeTask.setPublishCode(publishCode);
			timeTask.setTaskTypeName(taskType.getTaskTypeName());
			timeTask.setFailedNum(0);
			timeTask.setComment(comment);
			this.timeTaskDao.insertTimeTask(timeTask);

			DpcoiOrder dpociOrder = this.dpcoiOrderService.quereyDpcoiOrderOfTaskOrder(taskOrder);
			if(dpociOrder != null ){
				this.dpcoiOrderService.editDpcoiOrderToVoid(dpociOrder, user);
			}
			
			returnInfo.returnMessage = "作废变更单成功！";
		}catch(Exception e){
			e.printStackTrace();
			returnInfo.returnFlag = false;
			returnInfo.returnMessage = e.getMessage();
		}
		return returnInfo;
	}
	
	@Override
	public Page queryDetailPage(DetailQuery query, int pageIndex, int pageSize)
			throws ServiceException {
		// TODO Auto-generated method stub
		return this.detailDao.selectPageDetail(query, pageIndex, pageSize);
	}


	@Override
	public DetailQuery setDetailQueryData(HttpServletRequest request) {
		// TODO Auto-generated method stub
		DetailQuery query = new DetailQuery();
		String orderStateCode = request.getParameter("orderStateCode");
		String publishCode = request.getParameter("publishCode");
		String createUser = request.getParameter("createUser");
		String isDelay = request.getParameter("isDelay");
		String createTimeBeginStr = request.getParameter("createTimeBegin");
		String createTimeEndStr = request.getParameter("createTimeEnd");
		String changeTime = request.getParameter("changeTime");
		String changeTimeBegin = request.getParameter("changeTimeBegin");
		String changeTimeEnd = request.getParameter("changeTimeEnd");
		String orgId = request.getParameter("orgId");
		String issueDate = request.getParameter("issueDate");
		String changeContent = request.getParameter("changeContent");
		String productionLine = request.getParameter("productionLine");
		String carType = request.getParameter("carType");
		String mountingMat = request.getParameter("mountingMat");
		
		if(orderStateCode != null && !"".equals(orderStateCode)){
			query.setOrderStateCode(orderStateCode);
		}
		if(issueDate != null && !"".equals(issueDate)){
			query.setIssueDate(issueDate);
		}
		if(changeContent != null && !"".equals(changeContent)){
			query.setChangeContent(changeContent);
		}
		if(productionLine != null && !"".equals(productionLine)){
			query.setProductionLine(productionLine);
		}
		if(carType != null && !"".equals(carType)){
			query.setCarType(carType);
		}
		if(mountingMat != null && !"".equals(mountingMat)){
			query.setMountingMat(mountingMat);
		}
		
		if(publishCode != null && !"".equals(publishCode)){
			query.setPublishCode(publishCode);
		}
		
		if(createUser != null && !"".equals(createUser)){
			query.setCreateUser(createUser);
		}
		
		if(isDelay != null && !"".equals(isDelay)){
			query.setIsDelay(Integer.valueOf(isDelay));
		}
		
		if(createTimeBeginStr != null && !"".equals(createTimeBeginStr)){
			query.setCreateTimeBegin(createTimeBeginStr);
		}
		
		if(createTimeEndStr != null && !"".equals(createTimeEndStr)){
			query.setCreateTimeEnd(createTimeEndStr);
		}
		
		if(changeTime != null && !"".equals(changeTime)){
			query.setChangeTime(changeTime);
		}
		
		if(changeTimeBegin != null && !"".equals(changeTimeBegin)){
			query.setChangeTimeBegin(changeTimeBegin);
		}
		
		if(changeTimeEnd != null && !"".equals(changeTimeEnd)){
			query.setChangeTimeEnd(changeTimeEnd);
		}
		
		if(orgId != null && !"".equals(orgId)){
			query.setOrgId(Integer.valueOf(orgId));
		}
		
		return query;
	}


	@Override
	public TaskOrder getTaskOrder(TaskOrderQuery query) throws ServiceException {
		// TODO Auto-generated method stub
		return this.taskOrderService.getTaskOrderDetail(query);
	}


	@Override
	public TaskType getTaskType(TaskTypeQuery query) throws ServiceException {
		// TODO Auto-generated method stub
		return this.taskTypeService.selectTaskTypeByIdQuery(query);
	}


	@Override
	public TaskOrder getStaffTaskOrder(TaskOrderQuery query, Integer userId)
			throws ServiceException {
		// TODO Auto-generated method stub
		return this.taskOrderService.getStaffTaskOrderDetail(query, userId);
	}


	@Override
	public void editWoOrder(HttpServletRequest request,
			Integer orderId, Integer userId) throws ServiceException, JSONException{
		// TODO Auto-generated method stub
		String pageParamJson = request.getParameter("pageParamJson");
		Map<String, String> paramMap = PageParamJSON.parseParam(pageParamJson);
		
		TaskOrderQuery taskOrderQuery = new TaskOrderQuery();
		taskOrderQuery.setOrderId(orderId);
		TaskOrder taskOrder = this.taskOrderService.getStaffTaskOrderDetail(taskOrderQuery, userId);
		String agreementState = taskOrder.getAgreementState();
		List<TaskWoOrder> taskWoOrderList = taskOrder.getTaskWoOrderList();
		TaskType taskType = taskOrder.getTaskType();
		List<TaskTache> taskTacheList = taskType.getTaskTache();
		if(taskWoOrderList == null){
			
		}else {
			int i = 0;
			for(i = 0; i < taskWoOrderList.size(); i++){
				TaskWoOrder taskWoOrder = taskWoOrderList.get(i);
				String woOrderState = taskWoOrder.getWoOrderStateCode();
				List<TaskWoOrderInfo> taskWoOrderInfoList = taskWoOrder.getTaskWoOrderInfoList();
				if("10B".equals(woOrderState)){
					//进行更新
					int tacheId = taskWoOrder.getTacheId();
					for(int j = 0; j < taskTacheList.size(); j++){
						TaskTache taskTache = taskTacheList.get(j);
						if(tacheId == taskTache.getTacheId()){
							//插入task_wo_order_info表
							List<TaskTacheInfo> taskTacheInfoList = taskTache.getTaskTacheInfo();
							for(int l = 0; l < taskTacheInfoList.size(); l++){
								TaskTacheInfo taskTacheInfo = taskTacheInfoList.get(l);
								int taskTacheInfoId = taskTacheInfo.getTaskTacheInfoId();
								int infoTypeId = taskTacheInfo.getInfoTypeId();
								String value = null;
								String checkStr = paramMap.get("wo_check_"+tacheId+"_"+taskTacheInfoId);
								if(checkStr == null) checkStr = "";

//								//更新订单待立合
//								if(tacheId==28 || tacheId==39) {
//									String checkStrTemp = "," + checkStr + ",";
//									if (checkStrTemp.indexOf(",192,") >= 0 || checkStrTemp.indexOf(",364,") >= 0) {
//										TaskOrder taskOrderTemp = new TaskOrder();
//										taskOrderTemp.setOrderId(orderId);
//										taskOrderTemp.setAgreementState("待立合");
//										this.taskOrderDao.updateTaskOrder(taskOrderTemp);
//									}else{
//										TaskOrder taskOrderTemp = new TaskOrder();
//										taskOrderTemp.setOrderId(orderId);
//										taskOrderTemp.setAgreementState("NA");
//										this.taskOrderDao.updateTaskOrder(taskOrderTemp);
//									}
//								}
								
								if(infoTypeId == 1){
									value = checkStr;
								}else if(infoTypeId == 2){
									value = checkStr;
								}else if(infoTypeId == 3){
									boolean flag = false;
									int sysInfoId = 0;
									List<InfoValue> infoValueList = taskTacheInfo.getInfoValueList();
									for(int m = 0; m < infoValueList.size(); m++){
										InfoValue infoValue = infoValueList.get(m);
										if(infoValue.getInfoKey() != null && infoValue.getInfoKey() == 1){
											flag = true;
											sysInfoId = infoValue.getInfoId();
										}
									}
									String infoText = paramMap.get("wo_check_"+tacheId+"_"+taskTacheInfoId+"_"+sysInfoId+"_value");
									if(infoText == null) infoText = "";
									if(flag){
										value = checkStr+"<<?><?>>"+infoText;
									}else {
										value = checkStr;
									}
								}else {
									value = "";
								}
								for(int g = 0; g < taskWoOrderInfoList.size(); g++){
									TaskWoOrderInfo taskWoOrderInfo = taskWoOrderInfoList.get(i);
									if(taskTacheInfoId == taskWoOrderInfo.getTaskTacheInfoId()){
										taskWoOrderInfo.setWoInfoValue(value);
										this.taskWoOrderInfoService.editTaskWoOrderInfo(taskWoOrderInfo);
										break;
									}
								}

								//删除工单的TaskWoOrderSysInfo
								this.taskWoOrderSysInfoDao.deleteTaskWoOrderSysInfo(taskWoOrder);
								List<InfoValue> infoValueList = taskTacheInfo.getInfoValueList();
								for(InfoValue infoValue : infoValueList){
									TaskWoOrderSysInfo taskWoOrderSysInfo = new TaskWoOrderSysInfo();
									taskWoOrderSysInfo.setWoOrderId(taskWoOrder.getWoOrderId());
									taskWoOrderSysInfo.setSysInfoId(infoValue.getInfoId());
									String checkStrTemp = "," + checkStr + ",";
									if (checkStrTemp.indexOf(","+infoValue.getInfoId()+",") >= 0){
										taskWoOrderSysInfo.setWoInfoValue(1);
										Integer agreemntFlag = infoValue.getAgreementFlag();
										if(agreemntFlag.intValue() == 1){//更新立合状态
											if(agreementState == null || "".equals(agreementState) || "NA".equals(agreementState)){
												agreementState = "待立合";
											}
										}
									}else {
										taskWoOrderSysInfo.setWoInfoValue(0);
									}
									this.taskWoOrderSysInfoDao.insertTaskWoOrderSysInfo(taskWoOrderSysInfo);
								}
//功能不用暂时注解
//								//查询dpcoi需要确认的工单
//								List<Map<String, Object>> dpcoiWoOrderList = this.taskWoOrderSysInfoDao.selectDpcoiWoOrder(taskWoOrder);
//								for(Map<String, Object> map : dpcoiWoOrderList){
//									Integer dpcoiWoOrderId = (Integer)map.get("dpcoiWoOrderId");
//									Integer sysInfoId = (Integer)map.get("sysInfoId");
//									DpcoiWoOrder dpcoiWoOrder = new DpcoiWoOrder();
//									dpcoiWoOrder.setDpcoiWoOrderId(dpcoiWoOrderId);
//									dpcoiWoOrder = this.dpcoiWoOrderService.queryDpcoiWoOrder(dpcoiWoOrder);
//									Integer confrirmResult = 0;
//									String checkStrTemp = "," + checkStr + ",";
//									if (checkStrTemp.indexOf(","+sysInfoId+",") >= 0){
//										confrirmResult = 1;
//									}
//									User user = new User();
//									user.setUserId(userId);
//									this.dpcoiWoOrderService.editWoOrderConfirm(dpcoiWoOrder, confrirmResult, user);
//								}
							}
							break;
						}
					}

					//立合状态修改
					if(agreementState == null || "".equals(agreementState)){
						agreementState = "NA";
					}
					TaskOrder taskOrderTemp = new TaskOrder();
					taskOrderTemp.setOrderId(orderId);
					taskOrderTemp.setAgreementState(agreementState);
					this.taskOrderDao.updateTaskOrder(taskOrderTemp);

					//更新task_wo_order表
					taskWoOrder.setWoOrderStateCode("10R");
					taskWoOrder.setWoRefuseReason("");
					taskWoOrder.setReplyUser(userId);
					taskWoOrder.setReplyTime(new Date());
					taskWoOrder.setRequireCompleteTimeStr(paramMap.get("tache_require_time_"+tacheId));
					this.taskWoOrderService.editTaskWoOrder(taskWoOrder);
					
					//填写信息后，自动审核
					this.taskWoOrderService.editAcceptWoOrder(taskWoOrder.getWoOrderId());
				}
			}
			//判断是否都已经填写完信息
			boolean flag = true;
			TaskWoOrderDetailQuery taskWoOrderDetailQuery = new TaskWoOrderDetailQuery();
			taskWoOrderDetailQuery.setOrderId(orderId);
			taskWoOrderList = this.taskWoOrderService.queryTaskWoOrders(taskWoOrderDetailQuery);
			for(i = 0; i < taskWoOrderList.size(); i++){
				TaskWoOrder taskWoOrder = taskWoOrderList.get(i);
				String woOrderState = taskWoOrder.getWoOrderStateCode();
				if("10B".equals(woOrderState) || "10R".equals(woOrderState)){
					flag = false;
					break;
				}
			}
			if(flag){
				this.taskWoOrderService.editCompleteWoOrder(orderId);
			}

//			if(flag){
//				//通知审核人员
//				TaskOrderInfoQuery taskOrderInfoQuery = new TaskOrderInfoQuery();
//				taskOrderInfoQuery.setOrderId(orderId);
//				List<TaskOrderInfo> TaskOrderInfoList = this.taskOrderInfoService.selectTaskOrderInfo(taskOrderInfoQuery);
//				String publishCode = "";
//				for(int h = 0; h < TaskOrderInfoList.size(); h++){
//					TaskOrderInfo taskOrderInfo = TaskOrderInfoList.get(h);
//					Integer taskTypeInfoId = taskOrderInfo.getTaskTypeInfoId();
//					if(taskTypeInfoId == 1){
//						publishCode = taskOrderInfo.getTaskInfoValue();
//					}
//				}
//				String comment = "你好：变更单" + publishCode + "已经全部填写完毕，请审核回复。";
//				List<User> userList = this.userDao.selectVerifyUser();
//				if(userList != null){
//					String email = "";
//					for(int j = 0; j < userList.size(); j++){
//						email += "," + userList.get(j).getEmail();
//					}
//					if(!"".equals(email)){
//						email = email.substring(1);
//						TimeTask timeTask = new TimeTask();
//						timeTask.setOrderId(orderId);
//						timeTask.setNoticeColor(0);
//						timeTask.setNoticeType(8);
//						timeTask.setPublishCode(publishCode);
//						timeTask.setTaskTypeName(taskType.getTaskTypeName());
//						timeTask.setFailedNum(0);
//						timeTask.setUserEmail(email);
//						timeTask.setComment(comment);
//						this.timeTaskDao.insertTimeTask(timeTask);
//					}
//				}
//			}
		}
	}


	@Override
	public Page queryTaskAgentPage(DetailQuery query, int pageIndex,
			int pageSize) throws ServiceException {
		// TODO Auto-generated method stub
		return this.detailDao.selectPageTaskAgent(query, pageIndex, pageSize);
	}

	@Override
	public List<Integer> queryPermisssionUsers(int userId)
			throws ServiceException {
		// TODO Auto-generated method stub
		List<Integer> userIdList = new LinkedList<Integer>();
		List<Org> orgList = this.permissionService.queryPermissionOrgsByUser(userId);
		if(orgList == null || orgList.size() == 0){
			
		}else {
			List<String> orgPathList = new LinkedList<String>();
			int i = 0;
			for(i = 0; i < orgList.size(); i++){
				Org org = orgList.get(i);
				orgPathList.add(org.getOrgPathCode());
			}
			List<User> userList = this.permissionService.queryPermissionUsersByOrg(orgPathList);
			for(i = 0; i < userList.size(); i++){
				User user = userList.get(i);
				userIdList.add(user.getUserId());
			}
		}
		userIdList.add(userId);
		return userIdList;
	}

	@Override
	public void editTaskNoticeCycle(HttpServletRequest request, Integer orderId, Integer cycleId)
			throws Exception {
		// TODO Auto-generated method stub
		
		TaskNoticeCycle taskNoticeCycle = this.taskNoticeCycleService.setTaskNoticeCycleData(request);
		taskNoticeCycle.setCycleId(cycleId);
		this.taskNoticeCycleService.editTaskNoticeCycle(taskNoticeCycle);
		
		TaskOrderQuery taskOrderQuery = new TaskOrderQuery();
		taskOrderQuery.setOrderId(orderId);
		
		String changeTimeStr = this.taskOrderInfoService.selectTaskChangeTime(taskOrderQuery);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date changeTime = sdf.parse(changeTimeStr);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(changeTime);
		calendar.add(12, (0 - taskNoticeCycle.getAlarmTime()));
		Date alarmTime = calendar.getTime();
		TaskOrder newTaskOrder = new TaskOrder();
		newTaskOrder.setOrderId(orderId);
		newTaskOrder.setAlarmTime(alarmTime);
		this.taskOrderService.updateTaskOrder(newTaskOrder);
	}

	@Override
	public String doExportExcle(DetailQuery query, String path) throws Exception {
		// TODO Auto-generated method stub
		List<Detail> detailList = this.detailDao.selectDetailList(query);
		String fileName = this.exportExcel.excelService(detailList, path);
		return fileName;
	}

	@Override
	public Page queryDetailPage2(DetailQuery query, int pageIndex, int pageSize)
			throws ServiceException {
		// TODO Auto-generated method stub
		return this.detailDao.selectPageDetail2(query, pageIndex, pageSize);
	}

}
