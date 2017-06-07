package com.success.task.detail.service.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.success.sys.email.dao.TimeTaskDao;
import com.success.sys.email.domain.TimeTask;
import com.success.task.base.dao.TaskNoticeCycleDao;
import com.success.task.base.dao.TaskOrderInfoDao;
import com.success.task.base.domain.TaskConfirmOrder;
import com.success.task.base.domain.TaskNoticeCycle;
import com.success.task.base.domain.TaskOrderInfo;
import com.success.task.base.domain.WoOrderEmailUser;
import com.success.task.base.query.TaskConfirmOrderQuery;
import com.success.task.base.query.TaskNoticeCycleQuery;
import com.success.task.base.query.TaskOrderInfoQuery;
import com.success.task.base.query.TaskWoOrderDetailQuery;
import com.success.task.base.service.TaskConfirmOrderService;
import com.success.task.detail.dao.TaskOrderDao;
import com.success.task.detail.dao.TaskWoOrderDao;
import com.success.task.detail.domain.TaskOrder;
import com.success.task.detail.domain.TaskWoOrder;
import com.success.task.detail.query.TaskOrderQuery;
import com.success.task.detail.query.TaskWoOrderQuery;
import com.success.task.detail.service.TaskOrderService;
import com.success.task.detail.service.TaskWoOrderService;
import com.success.templet.tache.domain.TaskTache;
import com.success.templet.task.dao.TaskTypeDao;
import com.success.templet.task.domain.TaskType;
import com.success.templet.task.query.TaskTypeQuery;
import com.success.templet.task.service.TaskTypeService;
import com.success.web.framework.exception.ServiceException;

@Service("taskOrderService")
public class TaskOrderServiceImpl implements TaskOrderService {

	@Resource(name = "taskOrderDao")
	private TaskOrderDao taskOrderDao;
	
	@Resource(name = "taskOrderInfoDao")
	private TaskOrderInfoDao taskOrderInfoDao;
	
	@Resource(name = "taskNoticeCycleDao")
	private TaskNoticeCycleDao taskNoticeCycleDao;
	
	@Resource(name = "taskWoOrderService")
	private TaskWoOrderService taskWoOrderService;
	
	@Resource(name = "taskTypeService")
	private TaskTypeService taskTypeService;
	
	@Resource(name = "taskConfirmOrderService")
	private TaskConfirmOrderService taskConfirmOrderService;
	
	@Resource(name = "taskWoOrderDao")
	private TaskWoOrderDao taskWoOrderDao;
	
	@Resource(name = "timeTaskDao")
	private TimeTaskDao timeTaskDao;
	
	@Resource(name = "taskTypeDao")
	private TaskTypeDao taskTypeDao;
	
	@Override
	public Integer addTaskOrder(TaskOrder taskOrder) throws ServiceException {
		// TODO Auto-generated method stub
		return this.taskOrderDao.insertTaskOrder(taskOrder);
	}

	@Override
	public Integer editTaskOrder(TaskOrder taskOrder) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteTaskOrder(TaskOrder taskOrder) throws ServiceException {
		// TODO Auto-generated method stub
		return this.taskOrderDao.deleteTaskOrder(taskOrder);
	}

	@Override
	public TaskOrder getTaskOrderDetail(TaskOrderQuery query)
			throws ServiceException {
		// TODO Auto-generated method stub
		TaskOrder taskOrder = this.selectTaskOrderByIdQuery(query);
		TaskOrderInfoQuery taskOrderInfoQuery = new TaskOrderInfoQuery();
		taskOrderInfoQuery.setOrderId(taskOrder.getOrderId());
		List<TaskOrderInfo> taskOrderInfoList = this.taskOrderInfoDao.selectTaskOrderInfo(taskOrderInfoQuery);
		taskOrder.setTaskOrderInfoList(taskOrderInfoList);
		TaskTypeQuery taskTypeQuery = new TaskTypeQuery();
		taskTypeQuery.setTaskTypeId(taskOrder.getTaskTypeId());
		taskTypeQuery.setVersion(taskOrder.getVersion());
		int orderId = query.getOrderId();
		TaskType taskType = new TaskType();
//		if(orderId > 159){
//			taskType = this.taskTypeService.selectTaskTypeByIdQuery(taskTypeQuery);
//		}else {
//			taskType = this.taskTypeService.selectTaskTypeByIdQueryOld(taskTypeQuery);
//		}
		taskType = this.taskTypeService.selectTaskTypeByIdQueryVersion(taskTypeQuery);
		
		taskOrder.setTaskType(taskType);
		TaskConfirmOrderQuery taskConfirmOrderQuery = new TaskConfirmOrderQuery();
		taskConfirmOrderQuery.setOrderId(taskOrder.getOrderId());
		List<TaskConfirmOrder> taskConfirmOrderList = this.taskConfirmOrderService.queryTaskConfirmOrders(taskConfirmOrderQuery);
		taskOrder.setTaskConfirmOrderList(taskConfirmOrderList);
		
		TaskWoOrderDetailQuery taskWoOrderDetailQuery = new TaskWoOrderDetailQuery();
		taskWoOrderDetailQuery.setOrderId(taskOrder.getOrderId());
		List<TaskWoOrder> taskWoOrderList = this.taskWoOrderService.queryTaskWoOrders(taskWoOrderDetailQuery);
		taskOrder.setTaskWoOrderList(taskWoOrderList);
		
		return taskOrder;
	}

	@Override
	public TaskOrder selectTaskOrderByIdQuery(TaskOrderQuery query)
			throws ServiceException {
		// TODO Auto-generated method stub
		return this.taskOrderDao.selectTaskOrderByIdQuery(query);
	}

	@Override
	public TaskOrder getStaffTaskOrderDetail(TaskOrderQuery query,
			Integer userId) throws ServiceException {
		// TODO Auto-generated method stub
		TaskOrder taskOrder = this.selectTaskOrderByIdQuery(query);
		TaskOrderInfoQuery taskOrderInfoQuery = new TaskOrderInfoQuery();
		taskOrderInfoQuery.setOrderId(taskOrder.getOrderId());
		List<TaskOrderInfo> taskOrderInfoList = this.taskOrderInfoDao.selectTaskOrderInfo(taskOrderInfoQuery);
		taskOrder.setTaskOrderInfoList(taskOrderInfoList);
		TaskConfirmOrderQuery taskConfirmOrderQuery = new TaskConfirmOrderQuery();
		taskConfirmOrderQuery.setOrderId(taskOrder.getOrderId());
		List<TaskConfirmOrder> taskConfirmOrderList = this.taskConfirmOrderService.queryTaskConfirmOrders(taskConfirmOrderQuery);
		taskOrder.setTaskConfirmOrderList(taskConfirmOrderList);
		TaskWoOrderDetailQuery taskWoOrderDetailQuery = new TaskWoOrderDetailQuery();
		taskWoOrderDetailQuery.setOrderId(taskOrder.getOrderId());
		taskWoOrderDetailQuery.setUserId(userId);
		List<TaskWoOrder> taskWoOrderList = this.taskWoOrderService.queryTaskWoOrders(taskWoOrderDetailQuery);
		taskOrder.setTaskWoOrderList(taskWoOrderList);
		
		TaskTypeQuery taskTypeQuery = new TaskTypeQuery();
		taskTypeQuery.setTaskTypeId(taskOrder.getTaskTypeId());
		taskTypeQuery.setVersion(taskOrder.getVersion());
		int orderId = query.getOrderId();
		TaskType taskType = new TaskType();
//		if(orderId > 159){
//			taskType = this.taskTypeService.selectTaskTypeByIdQuery(taskTypeQuery);
//		}else {
//			taskType = this.taskTypeService.selectTaskTypeByIdQueryOld(taskTypeQuery);
//		}
		taskType = this.taskTypeService.selectTaskTypeByIdQueryVersion(taskTypeQuery);
		
		List<TaskTache> taskTacheList = new LinkedList<TaskTache>();
		List<TaskTache> oldTaskTacheList = taskType.getTaskTache();
		for(int i = 0; i < taskWoOrderList.size(); i++){
			TaskWoOrder taskWoOrder = taskWoOrderList.get(i);
			int tacheId = taskWoOrder.getTacheId();
			for(int j = 0; j < oldTaskTacheList.size(); j++){
				TaskTache taskTache = oldTaskTacheList.get(j);
				if(tacheId == taskTache.getTacheId()){
					taskTacheList.add(taskTache);
					break;
				}
			}
		}
		taskType.setTaskTache(taskTacheList);
		taskOrder.setTaskType(taskType);
		return taskOrder;
	}

	@Override
	public void editTaskChangeTime(Map<String, Object> map)	throws Exception {
		// TODO Auto-generated method stub
		Integer orderId = (Integer)map.get("orderId");
		String publish_Code = (String)map.get("order_1");
		
		//判断发行编号是否唯一
		TaskOrderInfo publishTaskOrderInfo = new TaskOrderInfo();
		publishTaskOrderInfo.setOrderId(orderId);
		publishTaskOrderInfo.setTaskInfoValue(publish_Code);
		Integer publishCount = this.taskOrderInfoDao.selectCountPublishCode(publishTaskOrderInfo);
		if(publishCount > 0) throw new Exception("发行编号已存在！");
				
		String changeTimeStr = (String)map.get("order_11");
		TaskOrderInfo newTaskOrderInfo = new TaskOrderInfo();
		newTaskOrderInfo.setOrderId(orderId);
		newTaskOrderInfo.setTaskInfoValue(changeTimeStr);
		
		//修改通知时间
		TaskOrderQuery taskOrderQuery = new TaskOrderQuery();
		taskOrderQuery.setOrderId(orderId);
		TaskOrder taskOrder = this.taskOrderDao.selectTaskOrderByIdQuery(taskOrderQuery);
		Integer cycleId = taskOrder.getCycleId();
		TaskNoticeCycleQuery taskNoticeCycleQuery = new TaskNoticeCycleQuery();
		taskNoticeCycleQuery.setCycleId(cycleId);
		TaskNoticeCycle taskNoticeCycle = this.taskNoticeCycleDao.selectTaskNoticeCycleByIdQuery(taskNoticeCycleQuery);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date changeTime = sdf.parse(changeTimeStr);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(changeTime);
		calendar.add(12, (0 - taskNoticeCycle.getAlarmTime()));
		Date alarmTime = calendar.getTime();
		TaskOrder newTaskOrder = new TaskOrder();
		newTaskOrder.setOrderId(orderId);
		newTaskOrder.setAlarmTime(alarmTime);
		
		//判断是否需要发邮件
		boolean sendMainFlag = false;
		TaskConfirmOrderQuery taskConfirmOrderQuery = new TaskConfirmOrderQuery();
		taskConfirmOrderQuery.setOrderId(orderId);
		List<TaskConfirmOrder> taskConfirmOrderList = this.taskConfirmOrderService.queryTaskConfirmOrders(taskConfirmOrderQuery);
		for(int p = 0; p < taskConfirmOrderList.size(); p++){
			TaskConfirmOrder taskConfirmOrder = taskConfirmOrderList.get(p);
			String runType = taskConfirmOrder.getRunType();
			if("input".equals(runType)){
				String confirmOrderState = taskConfirmOrder.getConfirmOrderStateCode();
				if(!("10A".equals(confirmOrderState))){
					sendMainFlag = true;
				}
			}
		}
		
		if(sendMainFlag){
			//发邮件通知
			TaskWoOrderQuery taskWoOrderQuery = new TaskWoOrderQuery();
			taskWoOrderQuery.setOrderId(taskOrder.getOrderId());
			List<WoOrderEmailUser> woOrderEmailUserList = this.taskWoOrderDao.selectWoOrderEmailUsers(taskWoOrderQuery);
			if(woOrderEmailUserList == null){
				
			}else {
				Integer taskTypeId = taskOrder.getTaskTypeId();
				TaskOrderInfoQuery taskOrderInfoQuery = new TaskOrderInfoQuery();
				taskOrderInfoQuery.setOrderId(orderId);
				List<TaskOrderInfo> TaskOrderInfoList = this.taskOrderInfoDao.selectTaskOrderInfo(taskOrderInfoQuery);
				String publishCode = "";
				String oldChangeTime = "";
				String changeContent = "";
				for(int h = 0; h < TaskOrderInfoList.size(); h++){
					TaskOrderInfo taskOrderInfo = TaskOrderInfoList.get(h);
					Integer taskTypeInfoId = taskOrderInfo.getTaskTypeInfoId();
					if(taskTypeInfoId == 1){
						publishCode = taskOrderInfo.getTaskInfoValue();
					}else if(taskTypeInfoId == 11){
						oldChangeTime = taskOrderInfo.getTaskInfoValue();
					}else if(taskTypeInfoId == 9){
						changeContent = taskOrderInfo.getTaskInfoValue();
					}
				}
				TaskTypeQuery taskTypeQuery = new TaskTypeQuery();
				taskTypeQuery.setTaskTypeId(taskTypeId);
				TaskType taskType = this.taskTypeDao.selectTaskTypeByIdQuery(taskTypeQuery);
				TimeTask timeTask = new TimeTask();
				Integer noticeType = 6;
				String comment = "";
				comment = "你好，关于" + changeContent + "由原来的时间" + oldChangeTime + "修改为" + changeTimeStr + "，已切换，请大家知晓。 ";
				String email = "";
				int len = woOrderEmailUserList.size();
				for(int j = 0; j < len; j++){
					WoOrderEmailUser woOrderEmailUser = woOrderEmailUserList.get(j);
					if(j % 50 == 0){		
						timeTask.setOrderId(orderId);
						timeTask.setNoticeColor(0);
						timeTask.setNoticeType(noticeType);
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
							timeTask.setNoticeType(noticeType);
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
		this.taskOrderInfoDao.updateTaskChangeTime(newTaskOrderInfo);
		newTaskOrderInfo.setTaskInfoValue(publish_Code);
		this.taskOrderInfoDao.updateTaskPublishCode(newTaskOrderInfo);
		
		String order_2 = (String)map.get("order_2");
		newTaskOrderInfo = new TaskOrderInfo();
		newTaskOrderInfo.setOrderId(orderId);
		newTaskOrderInfo.setTaskInfoValue(order_2);
		newTaskOrderInfo.setTaskTypeInfoId(2);
		this.taskOrderInfoDao.updateTaskOrderInfo(newTaskOrderInfo);
		
		String order_3 = (String)map.get("order_3");
		newTaskOrderInfo = new TaskOrderInfo();
		newTaskOrderInfo.setOrderId(orderId);
		newTaskOrderInfo.setTaskInfoValue(order_3);
		newTaskOrderInfo.setTaskTypeInfoId(3);
		this.taskOrderInfoDao.updateTaskOrderInfo(newTaskOrderInfo);
		
		String order_4 = (String)map.get("order_4");
		newTaskOrderInfo = new TaskOrderInfo();
		newTaskOrderInfo.setOrderId(orderId);
		newTaskOrderInfo.setTaskInfoValue(order_4);
		newTaskOrderInfo.setTaskTypeInfoId(4);
		this.taskOrderInfoDao.updateTaskOrderInfo(newTaskOrderInfo);
		
		String order_5 = (String)map.get("order_5");
		newTaskOrderInfo = new TaskOrderInfo();
		newTaskOrderInfo.setOrderId(orderId);
		newTaskOrderInfo.setTaskInfoValue(order_5);
		newTaskOrderInfo.setTaskTypeInfoId(5);
		this.taskOrderInfoDao.updateTaskOrderInfo(newTaskOrderInfo);
		
		String order_6 = (String)map.get("order_6");
		newTaskOrderInfo = new TaskOrderInfo();
		newTaskOrderInfo.setOrderId(orderId);
		newTaskOrderInfo.setTaskInfoValue(order_6);
		newTaskOrderInfo.setTaskTypeInfoId(6);
		this.taskOrderInfoDao.updateTaskOrderInfo(newTaskOrderInfo);
		
		String order_7 = (String)map.get("order_7");
		newTaskOrderInfo = new TaskOrderInfo();
		newTaskOrderInfo.setOrderId(orderId);
		newTaskOrderInfo.setTaskInfoValue(order_7);
		newTaskOrderInfo.setTaskTypeInfoId(7);
		this.taskOrderInfoDao.updateTaskOrderInfo(newTaskOrderInfo);
		
		String order_8 = (String)map.get("order_8");
		newTaskOrderInfo = new TaskOrderInfo();
		newTaskOrderInfo.setOrderId(orderId);
		newTaskOrderInfo.setTaskInfoValue(order_8);
		newTaskOrderInfo.setTaskTypeInfoId(8);
		this.taskOrderInfoDao.updateTaskOrderInfo(newTaskOrderInfo);
		
		
		String order_9 = (String)map.get("order_9");
		newTaskOrderInfo = new TaskOrderInfo();
		newTaskOrderInfo.setOrderId(orderId);
		newTaskOrderInfo.setTaskInfoValue(order_9);
		newTaskOrderInfo.setTaskTypeInfoId(9);
		this.taskOrderInfoDao.updateTaskOrderInfo(newTaskOrderInfo);
		
		this.taskOrderDao.updateTaskOrder(newTaskOrder);
	}

	@Override
	public Integer updateTaskOrder(TaskOrder taskOrder) throws ServiceException {
		// TODO Auto-generated method stub
		return this.taskOrderDao.updateTaskOrder(taskOrder);
	}

	@Override
	public Integer updateTaskOrderContract(TaskOrder taskOrder)
			throws ServiceException {
		// TODO Auto-generated method stub
		return this.taskOrderDao.updateTaskOrderContract(taskOrder);
	}

	@Override
	public Integer getAgreementNum(TaskOrderQuery query) throws ServiceException {
		return this.taskOrderDao.selectAgreementNum(query);
	}

}
