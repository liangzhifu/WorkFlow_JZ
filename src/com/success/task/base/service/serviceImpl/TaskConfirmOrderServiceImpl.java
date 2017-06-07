package com.success.task.base.service.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.dpcoi.order.domain.DpcoiOrder;
import com.dpcoi.order.service.DpcoiOrderService;
import org.springframework.stereotype.Service;

import com.success.sys.config.dao.SysConfigDao;
import com.success.sys.config.domain.SysConfig;
import com.success.sys.email.dao.TimeTaskDao;
import com.success.sys.email.domain.TimeTask;
import com.success.sys.staff.dao.StaffDao;
import com.success.sys.staff.query.StaffQuery;
import com.success.sys.user.dao.UserDao;
import com.success.sys.user.domain.User;
import com.success.sys.user.query.UserQuery;
import com.success.task.base.dao.TaskConfirmOrderDao;
import com.success.task.base.dao.TaskOrderInfoDao;
import com.success.task.base.domain.TaskConfirmOrder;
import com.success.task.base.domain.TaskOrderInfo;
import com.success.task.base.query.TaskConfirmOrderQuery;
import com.success.task.base.query.TaskOrderInfoQuery;
import com.success.task.base.service.TaskConfirmOrderService;
import com.success.task.detail.dao.TaskOrderDao;
import com.success.task.detail.domain.TaskOrder;
import com.success.task.detail.query.TaskOrderQuery;
import com.success.task.detail.service.TaskOrderService;
import com.success.task.detail.service.TaskWoOrderService;
import com.success.templet.task.dao.TaskTypeDao;
import com.success.templet.task.domain.TaskType;
import com.success.templet.task.query.TaskTypeQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.exception.ServiceException;

@Service("taskConfirmOrderService")
public class TaskConfirmOrderServiceImpl implements TaskConfirmOrderService {

	@Resource(name = "taskOrderService")
	private TaskOrderService taskOrderService;
	
	@Resource(name = "taskConfirmOrderDao")
	private TaskConfirmOrderDao taskConfirmOrderDao;
	
	@Resource(name = "taskOrderDao")
	private TaskOrderDao taskOrderDao;
	
	@Resource(name = "staffDao")
	private StaffDao staffDao;
	
	@Resource(name = "userDao")
	private UserDao userDao;
	
	@Resource(name = "sysConfigDao")
	private SysConfigDao sysConfigDao;
	
	@Resource(name = "timeTaskDao")
	private TimeTaskDao timeTaskDao;
	
	@Resource(name = "taskTypeDao")
	private TaskTypeDao taskTypeDao;
	
	@Resource(name = "taskOrderInfoDao")
	private TaskOrderInfoDao taskOrderInfoDao;
	
	@Resource(name = "taskWoOrderService")
	private TaskWoOrderService taskWoOrderService;

	@Resource(name = "dpcoiOrderService")
	private DpcoiOrderService dpcoiOrderService;
	
	@Override
	public Integer addTaskConfirmOrder(TaskConfirmOrder taskConfirmOrder)
			throws ServiceException {
		// TODO Auto-generated method stub
		if(taskConfirmOrder == null) return null;
		return this.taskConfirmOrderDao.insertTaskConfirmOrder(taskConfirmOrder);
	}

	@Override
	public void startTaskConfirmOrder(Integer orderId, Integer confirmUserSeq)
			throws ServiceException {
		// TODO Auto-generated method stub
		TaskConfirmOrderQuery query = new TaskConfirmOrderQuery();
		query.setOrderId(orderId);
		query.setConfirmUserSeq(confirmUserSeq);
		List<TaskConfirmOrder> TaskConfirmOrderList = this.queryTaskConfirmOrders(query);
		if(TaskConfirmOrderList == null || (TaskConfirmOrderList.size() == 0)){
			TaskOrder taskOrder = new TaskOrder();
			taskOrder.setOrderId(orderId);
			taskOrder.setConfirmOrderStateCode("10C");
			taskOrder.setRealCompleteTime(new Date());
			this.taskOrderDao.updateTaskOrder(taskOrder);
		}else if(TaskConfirmOrderList.size() > 1){
			throw new ServiceException("查询确认单"+orderId+","+confirmUserSeq+"出错");
		}else {
			TaskConfirmOrder taskConfirmOrder = TaskConfirmOrderList.get(0);
			TaskConfirmOrder newTaskConfirmOrder = new TaskConfirmOrder();
			
			String runType = taskConfirmOrder.getRunType();
			if("input".equals(runType)){
				newTaskConfirmOrder.setConfirmOrderId(taskConfirmOrder.getConfirmOrderId());
				newTaskConfirmOrder.setConfirmOrderStateCode("10B");
				this.editTaskConfirmOrder(newTaskConfirmOrder);
				this.taskWoOrderService.startWoOrder(orderId);
			}else{
				String runCode = taskConfirmOrder.getRunCode();
				if("accept_confirm".equals(runCode)){
					newTaskConfirmOrder.setConfirmOrderId(taskConfirmOrder.getConfirmOrderId());
					newTaskConfirmOrder.setConfirmOrderStateCode("10C");
					this.editTaskConfirmOrder(newTaskConfirmOrder);
					//发送邮件通知所有领导，变更单已经做成
					TimeTask timeTask = new TimeTask();
					UserQuery userQuery = new UserQuery();
					userQuery.setIsHeader("1");
					List<User> userList = this.userDao.selectUser(userQuery); 
					String userEmain = "";
					for(int i = 0; i < userList.size(); i++){
						User user = userList.get(i);
						userEmain += "," + user.getEmail();
					}
					if(userEmain.length() > 0){
						userEmain = userEmain.substring(1);
					}
					timeTask.setUserEmail(userEmain);
					
					timeTask.setConfirmOrderId(taskConfirmOrder.getConfirmOrderId());
					
					TaskOrderQuery taskOrderQuery = new TaskOrderQuery();
					taskOrderQuery.setOrderId(orderId);
					TaskOrder taskOrder = this.taskOrderDao.selectTaskOrderByIdQuery(taskOrderQuery);
					Integer taskTypeId = taskOrder.getTaskTypeId();
					timeTask.setOrderId(taskOrder.getOrderId());
					timeTask.setNoticeColor(1);
					timeTask.setNoticeType(9);
					
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
					timeTask.setPublishCode(publishCode);
					String comment = "";
					comment = "你好：" + productionLine + " " + changeTime + " " + carType + " " + mountingMat + " ";
					comment += changeContent + "<br>此变更单已作成，请知晓。";
					timeTask.setComment(comment);
					
					TaskTypeQuery taskTypeQuery = new TaskTypeQuery();
					taskTypeQuery.setTaskTypeId(taskTypeId);
					TaskType taskType = this.taskTypeDao.selectTaskTypeByIdQuery(taskTypeQuery);
					timeTask.setTaskTypeName(taskType.getTaskTypeName());
					
					timeTask.setFailedNum(0);
					this.timeTaskDao.insertTimeTask(timeTask);
					
					this.startTaskConfirmOrder(orderId, confirmUserSeq+1);
				}else{
					Integer userId = taskConfirmOrder.getConfirmUserId();
					if(userId == null){
						newTaskConfirmOrder.setConfirmOrderId(taskConfirmOrder.getConfirmOrderId());
						newTaskConfirmOrder.setConfirmOrderStateCode("10C");
						this.editTaskConfirmOrder(newTaskConfirmOrder);
						this.startTaskConfirmOrder(orderId, confirmUserSeq+1);
					}else {
						newTaskConfirmOrder.setConfirmOrderId(taskConfirmOrder.getConfirmOrderId());
						newTaskConfirmOrder.setConfirmOrderStateCode("10B");
						this.editTaskConfirmOrder(newTaskConfirmOrder);
						
						//发送邮件通知
						SysConfig SysConfig = this.sysConfigDao.selectSysConfig("config_notice");
						String configValue = SysConfig.getConfigValue();
						if("1".equals(configValue)){
							TimeTask timeTask = new TimeTask();
							StaffQuery staffQuery = new StaffQuery();
							staffQuery.setId(userId);
							User user = this.staffDao.selectStaffByIdQuery(staffQuery);
							timeTask.setUserId(user.getUserId());
							timeTask.setUserName(user.getUserName());
							timeTask.setUserEmail(user.getEmail());
							
							timeTask.setConfirmOrderId(taskConfirmOrder.getConfirmOrderId());
							
							TaskOrderQuery taskOrderQuery = new TaskOrderQuery();
							taskOrderQuery.setOrderId(orderId);
							TaskOrder taskOrder = this.taskOrderDao.selectTaskOrderByIdQuery(taskOrderQuery);
							Integer taskTypeId = taskOrder.getTaskTypeId();
							timeTask.setOrderId(taskOrder.getOrderId());
							timeTask.setNoticeColor(1);
							timeTask.setNoticeType(7);
							
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
							timeTask.setPublishCode(publishCode);
							String comment = "";
							comment = "你好：" + productionLine + " " + changeTime + " " + carType + " " + mountingMat + " ";
							comment += changeContent + "<br>此变更单需要你的确认，请知晓。";
							timeTask.setComment(comment);
							
							TaskTypeQuery taskTypeQuery = new TaskTypeQuery();
							taskTypeQuery.setTaskTypeId(taskTypeId);
							TaskType taskType = this.taskTypeDao.selectTaskTypeByIdQuery(taskTypeQuery);
							timeTask.setTaskTypeName(taskType.getTaskTypeName());
							
							timeTask.setFailedNum(0);
							this.timeTaskDao.insertTimeTask(timeTask);
						}
					}
				}
			}
		}
	}

	@Override
	public Integer editTaskConfirmOrder(TaskConfirmOrder taskConfirmOrder)
			throws ServiceException {
		// TODO Auto-generated method stub
		if(taskConfirmOrder == null) return null;
		return this.taskConfirmOrderDao.updateTaskConfirmOrder(taskConfirmOrder);
	}

	@Override
	public Integer deleteTaskConfirmOrder(TaskConfirmOrder taskConfirmOrder)
			throws ServiceException {
		// TODO Auto-generated method stub
		return this.taskConfirmOrderDao.deleteTaskConfirmOrder(taskConfirmOrder);
	}

	@Override
	public TaskConfirmOrder selectTaskConfirmOrderByIdQuery(
			TaskConfirmOrderQuery query) throws ServiceException {
		// TODO Auto-generated method stub
		return this.taskConfirmOrderDao.selectTaskConfirmOrderByIdQuery(query);
	}

	@Override
	public List<TaskConfirmOrder> queryTaskConfirmOrders(
			TaskConfirmOrderQuery query) {
		// TODO Auto-generated method stub
		try{
			List<TaskConfirmOrder> taskConfirmOrders = this.taskConfirmOrderDao.selectTaskConfirmOrder(query);
			return taskConfirmOrders;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void editConfirmTaskConfirmOrder(Integer confirmOrderId) throws ServiceException, Exception {
		// TODO Auto-generated method stub
		TaskConfirmOrderQuery query = new TaskConfirmOrderQuery();
		query.setConfirmOrderId(confirmOrderId);
		TaskConfirmOrder taskConfirmOrder = this.taskConfirmOrderDao.selectTaskConfirmOrderByIdQuery(query);
		String confirmOrderState = taskConfirmOrder.getConfirmOrderStateCode();
		if("10C".equals(confirmOrderState)){
			throw new ServiceException("此工单已经确认过，不能再次确认！");
		}else if(("10A".equals(confirmOrderState))||("10B".equals(confirmOrderState))){
			
		}else {
			throw new ServiceException("此工单状态不对！");
		}

		String RunCode = taskConfirmOrder.getRunCode();
		if("quality_confirm".equals(RunCode)){//添加dpcoi定单
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			TaskOrderQuery taskOrderQuery = new TaskOrderQuery();
			taskOrderQuery.setOrderId(taskConfirmOrder.getOrderId());
			TaskOrder taskOrder = this.taskOrderDao.selectTaskOrderByIdQuery(taskOrderQuery);
			TaskOrderInfoQuery taskOrderInfoQuery = new TaskOrderInfoQuery();
			taskOrderInfoQuery.setOrderId(taskConfirmOrder.getOrderId());
			List<TaskOrderInfo> taskOrderInfoList = this.taskOrderInfoDao.selectTaskOrderInfo(taskOrderInfoQuery);
			DpcoiOrder dpcoiOrder = new DpcoiOrder();
			for(TaskOrderInfo taskOrderInfo : taskOrderInfoList){
				int taskTypeInfoId = taskOrderInfo.getTaskTypeInfoId().intValue();
				if(taskTypeInfoId == 1){//发行编号
					dpcoiOrder.setDesignChangeNo(taskOrderInfo.getTaskInfoValue());
				}else if(taskTypeInfoId == 2){//生产线
					dpcoiOrder.setProductLine(taskOrderInfo.getTaskInfoValue());
				}else if(taskTypeInfoId == 3){//车种名
					dpcoiOrder.setVehicleName(taskOrderInfo.getTaskInfoValue());
				}else if(taskTypeInfoId == 4){//安装席

				}else if(taskTypeInfoId == 9){//变更内容
					dpcoiOrder.setChangeContent(taskOrderInfo.getTaskInfoValue());
				}else if(taskTypeInfoId == 10){//发行日期
					dpcoiOrder.setReleaseDate(sdf.parse(taskOrderInfo.getTaskInfoValue()));
				}else if(taskTypeInfoId == 11){//变更时间
					dpcoiOrder.setHopeCuttingDate(taskOrderInfo.getTaskInfoValue());
				}else if(taskTypeInfoId == 12){//品号
					dpcoiOrder.setProductNo(taskOrderInfo.getTaskInfoValue());
				}

			}
			dpcoiOrder.setDpcoiOrderType(2);
			dpcoiOrder.setTaskOrderId(taskConfirmOrder.getOrderId());
			dpcoiOrder.setDelFlag("0");
			dpcoiOrder.setDpcoiOrderState(1);
			dpcoiOrder.setCreateDate(new Date());
			dpcoiOrder.setCreateBy(taskOrder.getCreateUserId());
			dpcoiOrder.setUpdateDate(new Date());
			dpcoiOrder.setUpdateBy(taskOrder.getCreateUserId());
			dpcoiOrder.setPfmeaCreateDate(new Date());
			dpcoiOrder.setPfmeaEmailDate(new Date());
			dpcoiOrder.setPfmeaDelay(0);
			dpcoiOrder.setCpDelay(0);
			dpcoiOrder.setStandardBookDelay(0);
			this.dpcoiOrderService.addDpcoiOrder(dpcoiOrder);
		}
		
		TaskConfirmOrder newTaskConfirmOrder = new TaskConfirmOrder();
		newTaskConfirmOrder.setConfirmOrderId(confirmOrderId);
		newTaskConfirmOrder.setConfirmOrderStateCode("10C");
		newTaskConfirmOrder.setConfirmTime(new Date());
		this.editTaskConfirmOrder(newTaskConfirmOrder);
		
		int orderId = taskConfirmOrder.getOrderId();
		int confirmUserSeq = taskConfirmOrder.getConfirmUserSeq();

		this.startTaskConfirmOrder(orderId, confirmUserSeq+1);
	}

	@Override
	public TaskConfirmOrder selectTaskConfirmOrderByUserSeq(
			TaskConfirmOrderQuery query) throws ServiceException {
		// TODO Auto-generated method stub
		return this.taskConfirmOrderDao.selectTaskConfirmOrderByUserSeq(query);
	}

	@Override
	public void editConfirmOrderUser(Integer orderId, Integer userId,
			String runCode) throws ServiceException {
		// TODO Auto-generated method stub
		TaskConfirmOrderQuery query = new TaskConfirmOrderQuery();
		query.setOrderId(orderId);
		List<TaskConfirmOrder> taskConfirmOrderList = this.taskConfirmOrderDao.selectTaskConfirmOrder(query);
		for(int i = 0; i < taskConfirmOrderList.size(); i++){
			TaskConfirmOrder taskConfirmOrder = taskConfirmOrderList.get(i);
			String runCode2 = taskConfirmOrder.getRunCode();
			if(runCode.equals(runCode2)){
				TaskConfirmOrder taskConfirmOrder2 = new TaskConfirmOrder();
				taskConfirmOrder2.setConfirmOrderId(taskConfirmOrder.getConfirmOrderId());
				taskConfirmOrder2.setConfirmUserId(userId);
				this.taskConfirmOrderDao.updateTaskConfirmOrder(taskConfirmOrder2);
			}
		}
	}

	@Override
	public void editSendEmail(TaskConfirmOrder taskConfirmOrder)
			throws ServiceException {
		// TODO Auto-generated method stub
		//发邮件通知做成人，变更单已修改确认人
		Integer confirmOrderId = taskConfirmOrder.getConfirmOrderId();
		TaskConfirmOrderQuery taskConfirmOrderQuery = new TaskConfirmOrderQuery();
		taskConfirmOrderQuery.setConfirmOrderId(confirmOrderId);
		TaskConfirmOrder taskConfirmOrder2 = this.taskConfirmOrderDao.selectTaskConfirmOrderByIdQuery(taskConfirmOrderQuery);
		Integer orderId = taskConfirmOrder2.getOrderId();
		TaskOrderQuery taskOrderQuery = new TaskOrderQuery();
		taskOrderQuery.setOrderId(orderId);
		TaskOrder taskOrder = this.taskOrderService.getTaskOrderDetail(taskOrderQuery);
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
		comment += changeContent + "<br>此变更单需要你的确认，请知晓。";
		String email = "";
		Integer userId = taskConfirmOrder.getConfirmUserId();
		UserQuery userQuery = new UserQuery();
		userQuery.setUserId(userId);
		User user = this.userDao.selectByQuery(userQuery);
		email = user.getEmail();
		timeTask.setUserEmail(email);
		timeTask.setOrderId(orderId);
		timeTask.setNoticeColor(0);
		timeTask.setNoticeType(11);
		timeTask.setPublishCode(publishCode);
		timeTask.setTaskTypeName(taskType.getTaskTypeName());
		timeTask.setFailedNum(0);
		timeTask.setComment(comment);
		this.timeTaskDao.insertTimeTask(timeTask);
	}

}
