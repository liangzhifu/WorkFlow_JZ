package com.success.task.quartz.service.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.success.sys.email.dao.TimeTaskDao;
import com.success.sys.email.domain.TimeTask;
import com.success.task.base.dao.TaskOrderInfoDao;
import com.success.task.base.domain.TaskOrderInfo;
import com.success.task.base.domain.WoOrderEmailUser;
import com.success.task.base.query.TaskOrderInfoQuery;
import com.success.task.detail.dao.TaskOrderDao;
import com.success.task.detail.dao.TaskWoOrderDao;
import com.success.task.detail.domain.TaskOrder;
import com.success.task.detail.query.TaskOrderQuery;
import com.success.task.detail.query.TaskWoOrderQuery;
import com.success.templet.task.dao.TaskTypeDao;
import com.success.templet.task.domain.TaskType;
import com.success.templet.task.query.TaskTypeQuery;

@Component("createEmailImpl")
public class CreateEmailImpl {

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
	
	@Scheduled(cron = "0 0/1 * * * ?")
	public void job(){
		try{
			List<TaskOrder> taskOrderList = this.taskOrderDao.selectNoticeTaskOrder();
			if(taskOrderList == null){
				
			}else {
				for(int i = 0; i < taskOrderList.size(); i++){
					TaskOrder taskOrder = taskOrderList.get(i);
					
					TaskWoOrderQuery taskWoOrderQuery = new TaskWoOrderQuery();
					taskWoOrderQuery.setOrderId(taskOrder.getOrderId());
					taskWoOrderQuery.setWoOrderStateCode("10B");
					List<WoOrderEmailUser> woOrderEmailUserList = this.taskWoOrderDao.selectWoOrderEmailUsers(taskWoOrderQuery);
					if(woOrderEmailUserList == null){
						
					}else {
						int orderId = taskOrder.getOrderId();
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
						//判断当前时间与变更单变更时间比较
						SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String nowTimeStr = sdf.format(new Date());
						Integer noticeType = 1;
						String comment = "";
						if(nowTimeStr.compareTo(changeTime) > 0){
							noticeType = 5;
							comment = "你好，关于" + publishCode + "变更，已超时，请尽快完成。";
						}else {
							comment = "你好：" + productionLine + " " + changeTime + " " + carType + " " + mountingMat + " ";
							comment += changeContent + "<br>收到此提醒后，请相关负责担当准时在系统中，需要确认的项目前打勾并签字（电子签字）及预计完成日期";
						}
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
							//判断是否发给领导
							if(noticeType.intValue() == 5){
								String managerEmails = this.taskWoOrderDao.selectDelayManagerEmails(taskWoOrderQuery);
								timeTask.setUserEmail(managerEmails);
								this.timeTaskDao.insertTimeTask(timeTask);
							}
						}
					}

					TaskOrder newTaskOrder = new TaskOrder();
					newTaskOrder.setOrderId(taskOrder.getOrderId());
					newTaskOrder.setNoticeTime(new Date());
					this.taskOrderDao.updateTaskOrder(newTaskOrder);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
