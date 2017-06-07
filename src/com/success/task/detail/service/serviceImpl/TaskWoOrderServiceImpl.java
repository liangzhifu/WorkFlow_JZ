package com.success.task.detail.service.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.success.sys.email.dao.TimeTaskDao;
import com.success.sys.email.domain.TimeTask;
import com.success.sys.user.dao.UserDao;
import com.success.sys.user.domain.User;
import com.success.sys.user.query.UserQuery;
import com.success.task.base.dao.TaskConfirmOrderDao;
import com.success.task.base.dao.TaskOrderInfoDao;
import com.success.task.base.dao.TaskWoOrderDetailDao;
import com.success.task.base.domain.TaskConfirmOrder;
import com.success.task.base.domain.TaskOrderInfo;
import com.success.task.base.domain.TaskWoOrderDetail;
import com.success.task.base.domain.TaskWoOrderInfo;
import com.success.task.base.domain.WoOrderEmailUser;
import com.success.task.base.query.TaskConfirmOrderQuery;
import com.success.task.base.query.TaskOrderInfoQuery;
import com.success.task.base.query.TaskWoOrderDetailQuery;
import com.success.task.base.service.TaskConfirmOrderService;
import com.success.task.detail.dao.TaskOrderDao;
import com.success.task.detail.dao.TaskWoOrderDao;
import com.success.task.detail.domain.TaskOrder;
import com.success.task.detail.domain.TaskWoOrder;
import com.success.task.detail.query.TaskOrderQuery;
import com.success.task.detail.query.TaskWoOrderQuery;
import com.success.task.detail.service.TaskWoOrderService;
import com.success.templet.tache.dao.TacheUserDao;
import com.success.templet.tache.domain.TacheUser;
import com.success.templet.tache.query.TacheUserQuery;
import com.success.templet.task.dao.TaskTypeDao;
import com.success.templet.task.domain.TaskType;
import com.success.templet.task.query.TaskTypeQuery;
import com.success.web.framework.exception.ServiceException;

@Service("taskWoOrderService")
public class TaskWoOrderServiceImpl implements TaskWoOrderService {

	@Resource(name = "taskWoOrderDao")
	private TaskWoOrderDao taskWoOrderDao;
	
	@Resource(name = "taskWoOrderDetailDao")
	private TaskWoOrderDetailDao taskWoOrderDetailDao;
	
	@Resource(name = "taskConfirmOrderService")
	private TaskConfirmOrderService taskConfirmOrderService;
	
	@Resource(name = "timeTaskDao")
	private TimeTaskDao timeTaskDao;
	
	@Resource(name = "taskOrderInfoDao")
	private TaskOrderInfoDao taskOrderInfoDao;
	
	@Resource(name = "taskTypeDao")
	private TaskTypeDao taskTypeDao;
	
	@Resource(name = "taskOrderDao")
	private TaskOrderDao taskOrderDao;
	
	@Resource(name = "tacheUserDao")
	private TacheUserDao tacheUserDao;
	
	@Resource(name = "userDao")
	private UserDao userDao;
	
	@Resource(name = "taskConfirmOrderDao")
	private TaskConfirmOrderDao taskConfirmOrderDao;
	
	@Override
	public Integer addTaskWoOrder(TaskWoOrder taskWoOrder)
			throws ServiceException {
		// TODO Auto-generated method stub
		if(taskWoOrder == null) return null;
		return this.taskWoOrderDao.insertTaskWoOrder(taskWoOrder);
	}

	@Override
	public Integer editTaskWoOrder(TaskWoOrder taskWoOrder)
			throws ServiceException {
		// TODO Auto-generated method stub
		if(taskWoOrder == null) return null;
		return this.taskWoOrderDao.updateTaskWoOrder(taskWoOrder);
	}

	@Override
	public Integer deleteTaskWoOrder(TaskWoOrder taskWoOrder)
			throws ServiceException {
		// TODO Auto-generated method stub
		return this.taskWoOrderDao.deleteTaskWoOrder(taskWoOrder);
	}

	@Override
	public void startWoOrder(int orderId) throws ServiceException {
		// TODO Auto-generated method stub
		TaskWoOrderQuery query = new TaskWoOrderQuery();
		query.setOrderId(orderId);
		TaskOrderQuery taskOrderQuery = new TaskOrderQuery();
		taskOrderQuery.setOrderId(orderId);
		TaskOrder taskOrder = this.taskOrderDao.selectTaskOrderByIdQuery(taskOrderQuery);
		List<TaskWoOrder> taskWoOrderList = this.taskWoOrderDao.selectTaskWoOrder(query);
		if(taskWoOrderList == null){
			throw new ServiceException("通过变更单主键"+orderId+"查询工单为空，报错！");
		}else {
			TaskOrder newTaskOrder = new TaskOrder();
			newTaskOrder.setOrderId(orderId);
			newTaskOrder.setOrderStateCode("10B");
			newTaskOrder.setNoticeTime(new Date());
			this.taskOrderDao.updateTaskOrder(newTaskOrder);
			for(int i = 0; i < taskWoOrderList.size(); i++){
				TaskWoOrder taskWoOrder = taskWoOrderList.get(i);
				TaskWoOrder newTaskWoOrder = new TaskWoOrder();
				newTaskWoOrder.setWoOrderId(taskWoOrder.getWoOrderId());
				newTaskWoOrder.setWoOrderStateCode("10B");
				newTaskWoOrder.setCreateTime(new Date());
				this.taskWoOrderDao.updateTaskWoOrder(newTaskWoOrder);
			}
			
			//发邮件通知
			//List<WoOrderEmailUser> woOrderEmailUserList = this.taskWoOrderDao.selectWoOrderEmailUsers(query);
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
			String comment = "你好：" + productionLine + " " + changeTime + " " + carType + " " + mountingMat + " ";
			comment += changeContent + "<br>收到此提醒后，请相关负责担当准时在系统中，需要确认的项目前打勾并签字（电子签字）及预计完成日期";
//			if(woOrderEmailUserList != null){
//				TimeTask timeTask = new TimeTask();
//				String email = "";
//				int len = woOrderEmailUserList.size();
//				for(int j = 0; j < len; j++){
//					WoOrderEmailUser woOrderEmailUser = woOrderEmailUserList.get(j);
//					if(j % 50 == 0){		
//						timeTask.setOrderId(orderId);
//						timeTask.setNoticeColor(0);
//						timeTask.setNoticeType(1);
//						timeTask.setPublishCode(publishCode);
//						timeTask.setTaskTypeName(taskType.getTaskTypeName());
//						timeTask.setFailedNum(0);
//						timeTask.setComment(comment);
//						if(j != 0){
//							email = email.substring(1);
//							timeTask.setUserEmail(email);
//							this.timeTaskDao.insertTimeTask(timeTask);
//							email = "";
//							timeTask = new TimeTask();
//							timeTask.setOrderId(orderId);
//							timeTask.setNoticeColor(0);
//							timeTask.setNoticeType(1);
//							timeTask.setPublishCode(publishCode);
//							timeTask.setTaskTypeName(taskType.getTaskTypeName());
//							timeTask.setFailedNum(0);
//							timeTask.setComment(comment);
//						}
//					}
//					email += "," + woOrderEmailUser.getUserEmail();
//				}
//				if((len % 50) != 0){
//					email = email.substring(1);
//					timeTask.setUserEmail(email);
//					this.timeTaskDao.insertTimeTask(timeTask);
//				}
//			}
			
			//发邮件通知审核人
//			List<User> userList = this.userDao.selectVerifyUser();
//			if(userList != null){
//				String email = "";
//				for(int j = 0; j < userList.size(); j++){
//					email += "," + userList.get(j).getEmail();
//				}
//				if(!"".equals(email)){
//					email = email.substring(1);
//					TimeTask timeTask = new TimeTask();
//					timeTask.setOrderId(orderId);
//					timeTask.setNoticeColor(0);
//					timeTask.setNoticeType(1);
//					timeTask.setPublishCode(publishCode);
//					timeTask.setTaskTypeName(taskType.getTaskTypeName());
//					timeTask.setFailedNum(0);
//					timeTask.setUserEmail(email);
//					timeTask.setComment(comment);
//					this.timeTaskDao.insertTimeTask(timeTask);
//				}
//			}
			
			//发邮件通知所有人
			UserQuery userQuery = new UserQuery();
			List<User> userList = this.userDao.selectUser(userQuery);
			if(userList != null){
				TimeTask timeTask = new TimeTask();
				String email = "";
				int len = userList.size();
				for(int j = 0; j < len; j++){
					User user = userList.get(j);
					if(j % 50 == 0){		
						timeTask.setOrderId(orderId);
						timeTask.setNoticeColor(0);
						timeTask.setNoticeType(1);
						timeTask.setPublishCode(publishCode);
						timeTask.setTaskTypeName(taskType.getTaskTypeName());
						timeTask.setFailedNum(0);
						timeTask.setComment(comment);
						if(j != 0){
							email = email.substring(1);
							timeTask.setUserEmail(email);
							this.timeTaskDao.insertTimeTask(timeTask);
							email = "";
							timeTask = new TimeTask();
							timeTask.setOrderId(orderId);
							timeTask.setNoticeColor(0);
							timeTask.setNoticeType(1);
							timeTask.setPublishCode(publishCode);
							timeTask.setTaskTypeName(taskType.getTaskTypeName());
							timeTask.setFailedNum(0);
							timeTask.setComment(comment);
						}
					}
					email += "," + user.getEmail();
				}
				if((len % 50) != 0){
					email = email.substring(1);
					timeTask.setUserEmail(email);
					this.timeTaskDao.insertTimeTask(timeTask);
				}
			}
		}
	}

	@Override
	public List<TaskWoOrder> queryTaskWoOrders(TaskWoOrderDetailQuery query) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
		List<TaskWoOrder> taskWoOrderList = new LinkedList<TaskWoOrder>();
		try{
			List<TaskWoOrderDetail> taskWoOrderDetailList = this.taskWoOrderDetailDao.selectTaskWoOrderDetail(query);
			TaskWoOrder taskWoOrder = null;
			int woOrderId = 0;
			List<TaskWoOrderInfo> taskWoOrderInfoList = new LinkedList<TaskWoOrderInfo>();
			for(int i = 0; i < taskWoOrderDetailList.size(); i++){
				TaskWoOrderDetail taskWoOrderDetail = taskWoOrderDetailList.get(i);
				int tempWoOrderId = taskWoOrderDetail.getWoOrderId();
				if(woOrderId == tempWoOrderId){
					TaskWoOrderInfo taskWoOrderInfo = new TaskWoOrderInfo();
					taskWoOrderInfo.setId(taskWoOrderDetail.getId());
					taskWoOrderInfo.setTaskTacheInfoId(taskWoOrderDetail.getTaskTacheInfoId());
					taskWoOrderInfo.setWoInfoValue(taskWoOrderDetail.getWoInfoValue());
					taskWoOrderInfo.setWoOrderId(tempWoOrderId);
					taskWoOrderInfoList.add(taskWoOrderInfo);
				}else {
					if(taskWoOrder == null){
						
					}else {
						taskWoOrder.setTaskWoOrderInfoList(taskWoOrderInfoList);
						taskWoOrderList.add(taskWoOrder);
					}
					taskWoOrderInfoList = new LinkedList<TaskWoOrderInfo>();
					TaskWoOrderInfo taskWoOrderInfo = new TaskWoOrderInfo();
					taskWoOrderInfo.setId(taskWoOrderDetail.getId());
					taskWoOrderInfo.setTaskTacheInfoId(taskWoOrderDetail.getTaskTacheInfoId());
					taskWoOrderInfo.setWoInfoValue(taskWoOrderDetail.getWoInfoValue());
					taskWoOrderInfo.setWoOrderId(tempWoOrderId);
					taskWoOrderInfoList.add(taskWoOrderInfo);
					
					taskWoOrder = new TaskWoOrder();
					taskWoOrder.setWoOrderId(tempWoOrderId);
					taskWoOrder.setCompleteTime(taskWoOrderDetail.getCompleteTime());
					taskWoOrder.setCreateTime(taskWoOrderDetail.getCreateTime());
					taskWoOrder.setIsDelay(taskWoOrderDetail.getIsDelay());
					taskWoOrder.setOrderId(taskWoOrderDetail.getOrderId());
					taskWoOrder.setReplyTime(taskWoOrderDetail.getReplyTime());
					taskWoOrder.setReplyUser(taskWoOrderDetail.getReplyUser());
					taskWoOrder.setReplyUserName(taskWoOrderDetail.getReplyUserName());
					taskWoOrder.setTacheId(taskWoOrderDetail.getTacheId());
					taskWoOrder.setWoOrderStateCode(taskWoOrderDetail.getWoOrderStateCode());
					taskWoOrder.setWoRefuseReason(taskWoOrderDetail.getWoRefuseReason());
					if(taskWoOrderDetail.getRequireCompleteTime() == null){
						taskWoOrder.setRequireCompleteTimeStr("");
					}else {
						taskWoOrder.setRequireCompleteTimeStr(sdf.format(taskWoOrderDetail.getRequireCompleteTime()));
					}
					woOrderId = tempWoOrderId;
				}
			}
			if(taskWoOrder != null){
				taskWoOrder.setTaskWoOrderInfoList(taskWoOrderInfoList);
				taskWoOrderList.add(taskWoOrder);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return taskWoOrderList;
	}

	@Override
	public void editRefuseWoOrder(int woOrderId, String refuseReason) throws ServiceException {
		// TODO Auto-generated method stub
		TaskWoOrderQuery query = new TaskWoOrderQuery();
		query.setWoOrderId(woOrderId);
		TaskWoOrder taskWoOrder = this.taskWoOrderDao.selectTaskWoOrderByIdQuery(query); 
		TaskWoOrder newTaskWoOrder = new TaskWoOrder();
		newTaskWoOrder.setWoOrderId(taskWoOrder.getWoOrderId());
		newTaskWoOrder.setWoRefuseReason(refuseReason);
		newTaskWoOrder.setWoOrderStateCode("10B");
		this.taskWoOrderDao.updateTaskWoOrder(newTaskWoOrder);
		
		Integer tacheId = taskWoOrder.getTacheId();
		TacheUserQuery tacheUserQuery = new TacheUserQuery();
		tacheUserQuery.setTacheId(tacheId);
		List<TacheUser> tacheUserList = this.tacheUserDao.selectTache(tacheUserQuery);
		String manageEmail = "";
		if(tacheUserList != null && tacheUserList.size() > 0){
			for(int i = 0; i < tacheUserList.size(); i++){
				TacheUser tacheUser = tacheUserList.get(i);
				Integer userId = tacheUser.getUserId();
				if(userId != null){
					UserQuery userQuery = new UserQuery();
					userQuery.setUserId(userId);
					User user = this.userDao.selectByQuery(userQuery);
					manageEmail = user.getEmail();
				}
			}
		}
		
		//发邮件
		TaskWoOrderQuery taskWoOrderQuery = new TaskWoOrderQuery();
		taskWoOrderQuery.setWoOrderId(woOrderId);
		List<WoOrderEmailUser> woOrderEmailUserList = this.taskWoOrderDao.selectWoOrderEmailUsers(taskWoOrderQuery);
		if(woOrderEmailUserList == null){
			
		}else {
			int orderId = taskWoOrder.getOrderId();
			TaskOrderQuery taskOrderQuery = new TaskOrderQuery();
			taskOrderQuery.setOrderId(orderId);
			TaskOrder taskOrder = this.taskOrderDao.selectTaskOrderByIdQuery(taskOrderQuery);
			Integer taskTypeId = taskOrder.getTaskTypeId();
			TaskOrderInfoQuery taskOrderInfoQuery = new TaskOrderInfoQuery();
			taskOrderInfoQuery.setOrderId(orderId);
			List<TaskOrderInfo> TaskOrderInfoList = this.taskOrderInfoDao.selectTaskOrderInfo(taskOrderInfoQuery);
			String changeContent = "";
			String publishCode = "";
			for(int h = 0; h < TaskOrderInfoList.size(); h++){
				TaskOrderInfo taskOrderInfo = TaskOrderInfoList.get(h);
				Integer taskTypeInfoId = taskOrderInfo.getTaskTypeInfoId();
				if(taskTypeInfoId == 1){
					publishCode = taskOrderInfo.getTaskInfoValue();
				}else if(taskTypeInfoId == 9){
					changeContent = taskOrderInfo.getTaskInfoValue();
				}
			}
			TaskTypeQuery taskTypeQuery = new TaskTypeQuery();
			taskTypeQuery.setTaskTypeId(taskTypeId);
			TaskType taskType = this.taskTypeDao.selectTaskTypeByIdQuery(taskTypeQuery);
			TimeTask timeTask = new TimeTask();
			String email = "";
			if(!"".equals(manageEmail)){
				email += "," + manageEmail;
			}
			String comment = "你好，关于" + changeContent + "，你的填写有误已被拒收，拒收原因：" + refuseReason;
			int len = woOrderEmailUserList.size();
			for(int j = 0; j < len; j++){
				WoOrderEmailUser woOrderEmailUser = woOrderEmailUserList.get(j);
				if(j % 50 == 0){		
					timeTask.setOrderId(orderId);
					timeTask.setNoticeColor(0);
					timeTask.setNoticeType(3);
					timeTask.setPublishCode(publishCode);
					timeTask.setTaskTypeName(taskType.getTaskTypeName());
					timeTask.setFailedNum(0);
					timeTask.setComment(comment);
					if(j != 0){
						email = email.substring(1);
						timeTask.setUserEmail(email);
						this.timeTaskDao.insertTimeTask(timeTask);
						email = "";
						timeTask = new TimeTask();
						timeTask.setOrderId(orderId);
						timeTask.setNoticeColor(0);
						timeTask.setNoticeType(3);
						timeTask.setPublishCode(publishCode);
						timeTask.setTaskTypeName(taskType.getTaskTypeName());
						timeTask.setFailedNum(0);
						timeTask.setComment(comment);
					}
				}
				email += "," + woOrderEmailUser.getUserEmail();
			}
			if((len % 50) != 0){
				email = email.substring(1);
				timeTask.setUserEmail(email);
				this.timeTaskDao.insertTimeTask(timeTask);
			}
		}
	}
	
	@Override
	public void editRefuseWoOrder2(int woOrderId, String refuseReason) throws ServiceException {
		// TODO Auto-generated method stub
		TaskWoOrderQuery query = new TaskWoOrderQuery();
		query.setWoOrderId(woOrderId);
		TaskWoOrder taskWoOrder = this.taskWoOrderDao.selectTaskWoOrderByIdQuery(query); 
		
		Integer orderId = taskWoOrder.getOrderId();
		//获取所有的确认单
		TaskConfirmOrderQuery taskConfirmOrderQuery = new TaskConfirmOrderQuery();
		taskConfirmOrderQuery.setOrderId(orderId);
		List<TaskConfirmOrder> taskConfirmOrderList = this.taskConfirmOrderDao.selectTaskConfirmOrder(taskConfirmOrderQuery);
		Integer confirmUserSeq = 0;
		int m = 0;
		for(m = 0; m < taskConfirmOrderList.size(); m++){
			TaskConfirmOrder taskConfirmOrder = taskConfirmOrderList.get(m);
			String runType = taskConfirmOrder.getRunType();
			if("input".equals(runType)){
				confirmUserSeq = taskConfirmOrder.getConfirmUserSeq();
			}
		}
		for(m = 0; m < taskConfirmOrderList.size(); m++){
			TaskConfirmOrder taskConfirmOrder = taskConfirmOrderList.get(m);
			Integer confirmUserSeq2 = taskConfirmOrder.getConfirmUserSeq();
			if(confirmUserSeq2 > confirmUserSeq){
				TaskConfirmOrder taskConfirmOrder2 = new TaskConfirmOrder();
				taskConfirmOrder2.setConfirmOrderId(taskConfirmOrder.getConfirmOrderId());
				taskConfirmOrder2.setConfirmOrderStateCode("10A");
				this.taskConfirmOrderDao.updateTaskConfirmOrder(taskConfirmOrder2);
			}else if(confirmUserSeq2 == confirmUserSeq){
				TaskConfirmOrder taskConfirmOrder2 = new TaskConfirmOrder();
				taskConfirmOrder2.setConfirmOrderId(taskConfirmOrder.getConfirmOrderId());
				taskConfirmOrder2.setConfirmOrderStateCode("10B");
				this.taskConfirmOrderDao.updateTaskConfirmOrder(taskConfirmOrder2);
			}
		}
		TaskOrder taskOrder2 =  new TaskOrder();
		taskOrder2.setOrderId(orderId);
		taskOrder2.setOrderStateCode("10B");
		this.taskOrderDao.updateTaskOrder(taskOrder2);
		
		TaskWoOrder newTaskWoOrder = new TaskWoOrder();
		newTaskWoOrder.setWoOrderId(taskWoOrder.getWoOrderId());
		newTaskWoOrder.setWoRefuseReason(refuseReason);
		newTaskWoOrder.setWoOrderStateCode("10B");
		this.taskWoOrderDao.updateTaskWoOrder(newTaskWoOrder);
		
		Integer tacheId = taskWoOrder.getTacheId();
		TacheUserQuery tacheUserQuery = new TacheUserQuery();
		tacheUserQuery.setTacheId(tacheId);
		List<TacheUser> tacheUserList = this.tacheUserDao.selectTache(tacheUserQuery);
		String manageEmail = "";
		if(tacheUserList != null && tacheUserList.size() > 0){
			for(int i = 0; i < tacheUserList.size(); i++){
				TacheUser tacheUser = tacheUserList.get(i);
				Integer userId = tacheUser.getUserId();
				if(userId != null){
					UserQuery userQuery = new UserQuery();
					userQuery.setUserId(userId);
					User user = this.userDao.selectByQuery(userQuery);
					manageEmail = user.getEmail();
				}
			}
		}
		
		//发邮件
		TaskWoOrderQuery taskWoOrderQuery = new TaskWoOrderQuery();
		taskWoOrderQuery.setWoOrderId(woOrderId);
		List<WoOrderEmailUser> woOrderEmailUserList = this.taskWoOrderDao.selectWoOrderEmailUsers(taskWoOrderQuery);
		if(woOrderEmailUserList == null){
			
		}else {
			TaskOrderQuery taskOrderQuery = new TaskOrderQuery();
			taskOrderQuery.setOrderId(orderId);
			TaskOrder taskOrder = this.taskOrderDao.selectTaskOrderByIdQuery(taskOrderQuery);
			Integer taskTypeId = taskOrder.getTaskTypeId();
			TaskOrderInfoQuery taskOrderInfoQuery = new TaskOrderInfoQuery();
			taskOrderInfoQuery.setOrderId(orderId);
			List<TaskOrderInfo> TaskOrderInfoList = this.taskOrderInfoDao.selectTaskOrderInfo(taskOrderInfoQuery);
			String changeContent = "";
			String publishCode = "";
			for(int h = 0; h < TaskOrderInfoList.size(); h++){
				TaskOrderInfo taskOrderInfo = TaskOrderInfoList.get(h);
				Integer taskTypeInfoId = taskOrderInfo.getTaskTypeInfoId();
				if(taskTypeInfoId == 1){
					publishCode = taskOrderInfo.getTaskInfoValue();
				}else if(taskTypeInfoId == 9){
					changeContent = taskOrderInfo.getTaskInfoValue();
				}
			}
			TaskTypeQuery taskTypeQuery = new TaskTypeQuery();
			taskTypeQuery.setTaskTypeId(taskTypeId);
			TaskType taskType = this.taskTypeDao.selectTaskTypeByIdQuery(taskTypeQuery);
			TimeTask timeTask = new TimeTask();
			String email = "";
			if(!"".equals(manageEmail)){
				email += "," + manageEmail;
			}
			String comment = "你好，关于" + changeContent + "，你的填写有误已被拒收，拒收原因：" + refuseReason;
			int len = woOrderEmailUserList.size();
			for(int j = 0; j < len; j++){
				WoOrderEmailUser woOrderEmailUser = woOrderEmailUserList.get(j);
				if(j % 50 == 0){		
					timeTask.setOrderId(orderId);
					timeTask.setNoticeColor(0);
					timeTask.setNoticeType(3);
					timeTask.setPublishCode(publishCode);
					timeTask.setTaskTypeName(taskType.getTaskTypeName());
					timeTask.setFailedNum(0);
					timeTask.setComment(comment);
					if(j != 0){
						email = email.substring(1);
						timeTask.setUserEmail(email);
						this.timeTaskDao.insertTimeTask(timeTask);
						email = "";
						timeTask = new TimeTask();
						timeTask.setOrderId(orderId);
						timeTask.setNoticeColor(0);
						timeTask.setNoticeType(3);
						timeTask.setPublishCode(publishCode);
						timeTask.setTaskTypeName(taskType.getTaskTypeName());
						timeTask.setFailedNum(0);
						timeTask.setComment(comment);
					}
				}
				email += "," + woOrderEmailUser.getUserEmail();
			}
			if((len % 50) != 0){
				email = email.substring(1);
				timeTask.setUserEmail(email);
				this.timeTaskDao.insertTimeTask(timeTask);
			}
		}
	}

	@Override
	public void editAcceptWoOrder(int woOrderId) throws ServiceException {
		// TODO Auto-generated method stub
		TaskWoOrder taskWoOrder = new TaskWoOrder();
		taskWoOrder.setWoOrderId(woOrderId);
		taskWoOrder.setCompleteTime(new Date());
		taskWoOrder.setWoOrderStateCode("10C");
		this.taskWoOrderDao.updateTaskWoOrder(taskWoOrder);
		
//		//查看是否超时,超时发送邮件
//		TaskWoOrderQuery query = new TaskWoOrderQuery();
//		query.setWoOrderId(woOrderId);
//		taskWoOrder = this.taskWoOrderDao.selectTaskWoOrderByIdQuery(query);
//		TaskOrderQuery taskOrderQuery = new TaskOrderQuery();
//		taskOrderQuery.setOrderId(taskWoOrder.getOrderId());
//		String changTime = this.taskOrderInfoDao.selectTaskChangeTime(taskOrderQuery);
//		changTime = changTime.substring(0, 10);
//		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
//		String requireCompleteTime = sdf.format(taskWoOrder.getRequireCompleteTime());
//		if(requireCompleteTime.compareTo(changTime) > 0){
//			TaskOrder taskOrder = new TaskOrder();
//			taskOrder.setOrderId(taskWoOrder.getOrderId());
//			taskOrder.setIsDelay(1);
//			this.taskOrderDao.updateTaskOrder(taskOrder);
//
//			TaskWoOrder newTaskWoOrder = new TaskWoOrder();
//			newTaskWoOrder.setWoOrderId(woOrderId);
//			newTaskWoOrder.setIsDelay(1);
//			this.taskWoOrderDao.updateTaskWoOrder(newTaskWoOrder);
//
//			TaskWoOrderQuery taskWoOrderQuery = new TaskWoOrderQuery();
//			taskWoOrderQuery.setWoOrderId(woOrderId);
//			List<WoOrderEmailUser> woOrderEmailUserList = this.taskWoOrderDao.selectWoOrderEmailUsers(taskWoOrderQuery);
//
//			int orderId = taskWoOrder.getOrderId();
//			taskOrder = this.taskOrderDao.selectTaskOrderByIdQuery(taskOrderQuery);
//			Integer taskTypeId = taskOrder.getTaskTypeId();
//			String publishCode = this.taskOrderInfoDao.selectTaskPublishCode(taskOrderQuery);
//			TaskTypeQuery taskTypeQuery = new TaskTypeQuery();
//			taskTypeQuery.setTaskTypeId(taskTypeId);
//			TaskType taskType = this.taskTypeDao.selectTaskTypeByIdQuery(taskTypeQuery);
//			String comment = "你好，关于" + publishCode + "变更，已超时，请尽快完成。";
//			if(woOrderEmailUserList == null){
//
//			}else {
//				TimeTask timeTask = new TimeTask();
//				String email = "";
//				int len = woOrderEmailUserList.size();
//				for(int j = 0; j < len; j++){
//					WoOrderEmailUser woOrderEmailUser = woOrderEmailUserList.get(j);
//					if(j % 50 == 0){
//						timeTask.setOrderId(orderId);
//						timeTask.setNoticeColor(0);
//						timeTask.setNoticeType(2);
//						timeTask.setPublishCode(publishCode);
//						timeTask.setTaskTypeName(taskType.getTaskTypeName());
//						timeTask.setFailedNum(0);
//						timeTask.setComment(comment);
//						if(j != 0){
//							email = email.substring(1);
//							timeTask.setUserEmail(email);
//							this.timeTaskDao.insertTimeTask(timeTask);
//							email = "";
//							timeTask = new TimeTask();
//							timeTask.setOrderId(orderId);
//							timeTask.setNoticeColor(0);
//							timeTask.setNoticeType(2);
//							timeTask.setPublishCode(publishCode);
//							timeTask.setTaskTypeName(taskType.getTaskTypeName());
//							timeTask.setFailedNum(0);
//							timeTask.setComment(comment);
//						}
//					}
//					email += "," + woOrderEmailUser.getUserEmail();
//				}
//				if((len % 50) != 0){
//					email = email.substring(1);
//					timeTask.setUserEmail(email);
//					this.timeTaskDao.insertTimeTask(timeTask);
//				}
//			}
//		}
	}

	@Override
	public void editCompleteWoOrder(int orderId) throws ServiceException {
		// TODO Auto-generated method stub
		TaskWoOrderQuery query = new TaskWoOrderQuery();
		query.setOrderId(orderId);
		List<TaskWoOrder> taskWoOrderList = this.taskWoOrderDao.selectTaskWoOrder(query);
		if(taskWoOrderList == null){
			throw new ServiceException("查询工单结果为空!orderId="+orderId);
		}else {
			boolean flag = true;
			for(int i = 0; i < taskWoOrderList.size(); i++){
				TaskWoOrder taskWoOrder = taskWoOrderList.get(i);
				if(!("10C".equals(taskWoOrder.getWoOrderStateCode()))){
					flag = false;
				}
			}
			if(flag){
				TaskOrder taskOrder = new TaskOrder();
				taskOrder.setOrderStateCode("10C");
				taskOrder.setOrderId(orderId);
				this.taskOrderDao.updateTaskOrder(taskOrder);
				
				TaskConfirmOrderQuery taskConfirmOrderQuery = new TaskConfirmOrderQuery();
				taskConfirmOrderQuery.setOrderId(orderId);
				List<TaskConfirmOrder> taskConfirmOrderList = this.taskConfirmOrderService.queryTaskConfirmOrders(taskConfirmOrderQuery);
				if(taskConfirmOrderList == null){
					throw new ServiceException("查询确认工单结果为空!orderId="+orderId);
				}else {
					for(int j = 0; j < taskConfirmOrderList.size(); j++){
						TaskConfirmOrder taskConfirmOrder = taskConfirmOrderList.get(j);
						if("input".equals(taskConfirmOrder.getRunType())){
							taskConfirmOrder.setConfirmOrderStateCode("10C");
							taskConfirmOrder.setConfirmTime(new Date());
							this.taskConfirmOrderService.editTaskConfirmOrder(taskConfirmOrder);
							int confirmUserSeq = taskConfirmOrder.getConfirmUserSeq();
							this.taskConfirmOrderService.startTaskConfirmOrder(orderId, confirmUserSeq+1);
							break;
						}
					}
				}
			}
		}
	}

	@Override
	public void editRefuseWoOrder3(String woOrderIds, String refuseReason)
			throws ServiceException {
		// TODO Auto-generated method stub
		String[] woOrderIdsArray = woOrderIds.split(",");
		Integer orderId = null;
		for(int k = 0; k < woOrderIdsArray.length; k++){
			Integer woOrderId = Integer.valueOf(woOrderIdsArray[k]);
			TaskWoOrderQuery query = new TaskWoOrderQuery();
			query.setWoOrderId(woOrderId);
			TaskWoOrder taskWoOrder = this.taskWoOrderDao.selectTaskWoOrderByIdQuery(query); 
			
			orderId = taskWoOrder.getOrderId();
			TaskWoOrder newTaskWoOrder = new TaskWoOrder();
			newTaskWoOrder.setWoOrderId(taskWoOrder.getWoOrderId());
			newTaskWoOrder.setWoRefuseReason(refuseReason);
			newTaskWoOrder.setWoOrderStateCode("10B");
			this.taskWoOrderDao.updateTaskWoOrder(newTaskWoOrder);
			
			Integer tacheId = taskWoOrder.getTacheId();
			TacheUserQuery tacheUserQuery = new TacheUserQuery();
			tacheUserQuery.setTacheId(tacheId);
			List<TacheUser> tacheUserList = this.tacheUserDao.selectTache(tacheUserQuery);
			String manageEmail = "";
			if(tacheUserList != null && tacheUserList.size() > 0){
				for(int i = 0; i < tacheUserList.size(); i++){
					TacheUser tacheUser = tacheUserList.get(i);
					Integer userId = tacheUser.getUserId();
					if(userId != null){
						UserQuery userQuery = new UserQuery();
						userQuery.setUserId(userId);
						User user = this.userDao.selectByQuery(userQuery);
						manageEmail = user.getEmail();
					}
				}
			}
			
			//发邮件
			TaskWoOrderQuery taskWoOrderQuery = new TaskWoOrderQuery();
			taskWoOrderQuery.setWoOrderId(woOrderId);
			List<WoOrderEmailUser> woOrderEmailUserList = this.taskWoOrderDao.selectWoOrderEmailUsers(taskWoOrderQuery);
			if(woOrderEmailUserList == null){
				
			}else {
				TaskOrderQuery taskOrderQuery = new TaskOrderQuery();
				taskOrderQuery.setOrderId(orderId);
				TaskOrder taskOrder = this.taskOrderDao.selectTaskOrderByIdQuery(taskOrderQuery);
				Integer taskTypeId = taskOrder.getTaskTypeId();
				TaskOrderInfoQuery taskOrderInfoQuery = new TaskOrderInfoQuery();
				taskOrderInfoQuery.setOrderId(orderId);
				List<TaskOrderInfo> TaskOrderInfoList = this.taskOrderInfoDao.selectTaskOrderInfo(taskOrderInfoQuery);
				String changeContent = "";
				String publishCode = "";
				for(int h = 0; h < TaskOrderInfoList.size(); h++){
					TaskOrderInfo taskOrderInfo = TaskOrderInfoList.get(h);
					Integer taskTypeInfoId = taskOrderInfo.getTaskTypeInfoId();
					if(taskTypeInfoId == 1){
						publishCode = taskOrderInfo.getTaskInfoValue();
					}else if(taskTypeInfoId == 9){
						changeContent = taskOrderInfo.getTaskInfoValue();
					}
				}
				TaskTypeQuery taskTypeQuery = new TaskTypeQuery();
				taskTypeQuery.setTaskTypeId(taskTypeId);
				TaskType taskType = this.taskTypeDao.selectTaskTypeByIdQuery(taskTypeQuery);
				TimeTask timeTask = new TimeTask();
				String email = "";
				if(!"".equals(manageEmail)){
					email += "," + manageEmail;
				}
				String comment = "你好，关于" + changeContent + "，你的填写有误已被拒收，拒收原因：" + refuseReason;
				int len = woOrderEmailUserList.size();
				for(int j = 0; j < len; j++){
					WoOrderEmailUser woOrderEmailUser = woOrderEmailUserList.get(j);
					if(j % 50 == 0){		
						timeTask.setOrderId(orderId);
						timeTask.setNoticeColor(0);
						timeTask.setNoticeType(3);
						timeTask.setPublishCode(publishCode);
						timeTask.setTaskTypeName(taskType.getTaskTypeName());
						timeTask.setFailedNum(0);
						timeTask.setComment(comment);
						if(j != 0){
							email = email.substring(1);
							timeTask.setUserEmail(email);
							this.timeTaskDao.insertTimeTask(timeTask);
							email = "";
							timeTask = new TimeTask();
							timeTask.setOrderId(orderId);
							timeTask.setNoticeColor(0);
							timeTask.setNoticeType(3);
							timeTask.setPublishCode(publishCode);
							timeTask.setTaskTypeName(taskType.getTaskTypeName());
							timeTask.setFailedNum(0);
							timeTask.setComment(comment);
						}
					}
					email += "," + woOrderEmailUser.getUserEmail();
				}
				if((len % 50) != 0){
					email = email.substring(1);
					timeTask.setUserEmail(email);
					this.timeTaskDao.insertTimeTask(timeTask);
				}
			}
		}
		
		//获取所有的确认单
		TaskConfirmOrderQuery taskConfirmOrderQuery = new TaskConfirmOrderQuery();
		taskConfirmOrderQuery.setOrderId(orderId);
		List<TaskConfirmOrder> taskConfirmOrderList = this.taskConfirmOrderDao.selectTaskConfirmOrder(taskConfirmOrderQuery);
		Integer confirmUserSeq = 0;
		int m = 0;
		for(m = 0; m < taskConfirmOrderList.size(); m++){
			TaskConfirmOrder taskConfirmOrder = taskConfirmOrderList.get(m);
			String runType = taskConfirmOrder.getRunType();
			if("input".equals(runType)){
				confirmUserSeq = taskConfirmOrder.getConfirmUserSeq();
			}
		}
		for(m = 0; m < taskConfirmOrderList.size(); m++){
			TaskConfirmOrder taskConfirmOrder = taskConfirmOrderList.get(m);
			Integer confirmUserSeq2 = taskConfirmOrder.getConfirmUserSeq();
			if(confirmUserSeq2 > confirmUserSeq){
				TaskConfirmOrder taskConfirmOrder2 = new TaskConfirmOrder();
				taskConfirmOrder2.setConfirmOrderId(taskConfirmOrder.getConfirmOrderId());
				taskConfirmOrder2.setConfirmOrderStateCode("10A");
				this.taskConfirmOrderDao.updateTaskConfirmOrder(taskConfirmOrder2);
			}else if(confirmUserSeq2 == confirmUserSeq){
				TaskConfirmOrder taskConfirmOrder2 = new TaskConfirmOrder();
				taskConfirmOrder2.setConfirmOrderId(taskConfirmOrder.getConfirmOrderId());
				taskConfirmOrder2.setConfirmOrderStateCode("10B");
				this.taskConfirmOrderDao.updateTaskConfirmOrder(taskConfirmOrder2);
			}
		}
		TaskOrder taskOrder2 =  new TaskOrder();
		taskOrder2.setOrderId(orderId);
		taskOrder2.setOrderStateCode("10B");
		this.taskOrderDao.updateTaskOrder(taskOrder2);	
		
	}

}
