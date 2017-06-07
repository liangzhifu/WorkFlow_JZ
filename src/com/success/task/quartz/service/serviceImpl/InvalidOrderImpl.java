package com.success.task.quartz.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import com.success.sys.user.domain.User;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.success.task.base.dao.TaskConfirmOrderDao;
import com.success.task.base.domain.TaskConfirmOrder;
import com.success.task.base.query.TaskConfirmOrderQuery;
import com.success.task.detail.dao.TaskOrderDao;
import com.success.task.detail.domain.TaskOrder;
import com.success.task.detail.service.DetailService;

@Component("invalidOrderImpl")
public class InvalidOrderImpl {

	@Resource(name = "taskOrderDao")
	private TaskOrderDao taskOrderDao;
	
	@Resource(name = "taskConfirmOrderDao")
	private TaskConfirmOrderDao taskConfirmOrderDao;
	
	@Resource(name = "detailService")
	private DetailService detailService;
	
	@Scheduled(cron = "0 0/30 * * * ?")
	public void job(){
		String invalidateText = "任务单未得到承认人接受之前，变更单据的变更时间＜6h 时，自动作废此变更单";
		try{
			User user = new User();
			List<TaskOrder> taskOrderList = this.taskOrderDao.selectInvalidTaskOrder();
			if(taskOrderList == null){
				
			}else {
				for(int i = 0; i < taskOrderList.size(); i++){
					TaskOrder taskOrder = taskOrderList.get(i);
					TaskConfirmOrderQuery query = new TaskConfirmOrderQuery();
					query.setOrderId(taskOrder.getOrderId());
					List<TaskConfirmOrder> taskConfirmOrderList = this.taskConfirmOrderDao.selectTaskConfirmOrder(query);
					for(int j = 0; j < taskConfirmOrderList.size(); j++){
						TaskConfirmOrder taskConfirmOrder = taskConfirmOrderList.get(j);
						String confirmOrderStateCode = taskConfirmOrder.getConfirmOrderStateCode();
						if(!"10C".equals(confirmOrderStateCode)){
							String runCode = taskConfirmOrder.getRunCode();
							if("accept_confirm".equals(runCode)){
								invalidateText = "作成人员没有确认。" + invalidateText;
							}else if("group_confirm".equals(runCode)){
								invalidateText = "确认人员没有确认。" + invalidateText;
							}else {
								invalidateText = "承认人员没有确认。" + invalidateText;
							}
							break;
						}
					}
					this.detailService.deleteTask(taskOrder.getOrderId(), invalidateText, user);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		invalidateText = "任务单超时未立合，自动作废此变更单";
		try{
			User user = new User();
			List<TaskOrder> taskOrderList = this.taskOrderDao.selectInvalidTaskOrderByAgreement();
			if(taskOrderList == null){

			}else {
				for(int i = 0; i < taskOrderList.size(); i++){
					TaskOrder taskOrder = taskOrderList.get(i);
					this.detailService.deleteTask(taskOrder.getOrderId(), invalidateText, user);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
