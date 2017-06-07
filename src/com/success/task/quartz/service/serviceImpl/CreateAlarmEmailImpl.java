package com.success.task.quartz.service.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.success.sys.email.dao.TimeTaskDao;
import com.success.sys.email.domain.TimeTask;
import com.success.task.base.dao.TaskNoticeCycleDao;
import com.success.task.base.dao.TaskOrderInfoDao;
import com.success.task.base.domain.TaskConfirmOrder;
import com.success.task.base.domain.TaskNoticeCycle;
import com.success.task.base.domain.TaskOrderInfo;
import com.success.task.base.domain.WoOrderEmailUser;
import com.success.task.base.query.TaskConfirmOrderQuery;
import com.success.task.base.query.TaskOrderInfoQuery;
import com.success.task.base.service.TaskConfirmOrderService;
import com.success.task.detail.dao.TaskOrderDao;
import com.success.task.detail.dao.TaskWoOrderDao;
import com.success.task.detail.domain.TaskOrder;
import com.success.task.detail.query.TaskOrderQuery;
import com.success.task.detail.query.TaskWoOrderQuery;
import com.success.templet.task.dao.TaskTypeDao;
import com.success.templet.task.domain.TaskType;
import com.success.templet.task.query.TaskTypeQuery;

//@Component("createAlarmEmailImpl")
public class CreateAlarmEmailImpl {
	
	@Resource(name = "taskOrderDao")
	private TaskOrderDao taskOrderDao;
	
	@Resource(name = "taskWoOrderDao")
	private TaskWoOrderDao taskWoOrderDao;
	
	@Resource(name = "taskOrderInfoDao")
	private TaskOrderInfoDao taskOrderInfoDao;
	
	@Resource(name = "timeTaskDao")
	private TimeTaskDao timeTaskDao;
	
	@Resource(name = "taskTypeDao")
	private TaskTypeDao taskTypeDao;
	
	@Resource(name = "taskNoticeCycleDao")
	private TaskNoticeCycleDao taskNoticeCycleDao;
	
	@Resource(name = "taskConfirmOrderService")
	private TaskConfirmOrderService taskConfirmOrderService;
	
	@Scheduled(cron = "0 0/1 * * * ?")
	public void job(){
		try{
			List<TaskOrder> taskOrderList = this.taskOrderDao.selectAlarmTaskOrder();
			if(taskOrderList == null){
				
			}else {
				for(int i = 0; i < taskOrderList.size(); i++){
					TaskOrder taskOrder = taskOrderList.get(i);
					
					TaskWoOrderQuery taskWoOrderQuery = new TaskWoOrderQuery();
					taskWoOrderQuery.setOrderId(taskOrder.getOrderId());
					List<WoOrderEmailUser> woOrderEmailUserList = this.taskWoOrderDao.selectWoOrderEmailUsers(taskWoOrderQuery);
					if(woOrderEmailUserList == null){
						
					}else {
						int orderId = taskOrder.getOrderId();
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
							TaskOrderQuery taskOrderQuery = new TaskOrderQuery();
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
							comment += changeContent + "<br>此变更单即将实施，请知晓。";
							String email = "";
							int len = woOrderEmailUserList.size();
							for(int j = 0; j < len; j++){
								WoOrderEmailUser woOrderEmailUser = woOrderEmailUserList.get(j);
								if(j % 50 == 0){		
									timeTask.setOrderId(orderId);
									timeTask.setNoticeColor(0);
									timeTask.setNoticeType(4);
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
										timeTask.setNoticeType(4);
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

					TaskOrder newTaskOrder = new TaskOrder();
					newTaskOrder.setOrderId(taskOrder.getOrderId());
					newTaskOrder.setNoticeTime(new Date());
					this.taskOrderDao.updateTaskOrder(newTaskOrder);
					taskWoOrderQuery = null;
				
					//修改为，已通知
					Integer cycleId = taskOrder.getCycleId();
					TaskNoticeCycle taskNoticeCycle = new TaskNoticeCycle();
					taskNoticeCycle.setCycleId(cycleId);
					taskNoticeCycle.setIsAlarm(1);
					this.taskNoticeCycleDao.updateTaskNoticeCycle(taskNoticeCycle);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
