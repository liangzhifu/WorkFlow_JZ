package com.success.task.detail.service.serviceImpl;

import com.success.sys.user.domain.User;
import com.success.task.base.dao.TaskWoOrderInfoDao;
import com.success.task.base.dao.TaskWoOrderSysInfoDao;
import com.success.task.base.domain.TaskWoOrderInfo;
import com.success.task.base.domain.TaskWoOrderSysInfo;
import com.success.task.base.query.TaskWoOrderDetailQuery;
import com.success.task.detail.domain.TaskWoOrder;
import com.success.task.detail.service.TaskWoOrderDpcoiService;
import com.success.task.detail.service.TaskWoOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by L on 2018/9/2.
 */
@Service("taskWoOrderDpcoiService")
public class TaskWoOrderDpcoiServiceImpl implements TaskWoOrderDpcoiService {

    @Resource(name = "taskWoOrderSysInfoDao")
    private TaskWoOrderSysInfoDao taskWoOrderSysInfoDao;

    @Resource(name = "taskWoOrderService")
    private TaskWoOrderService taskWoOrderService;

    @Resource(name = "taskWoOrderInfoDao")
    private TaskWoOrderInfoDao taskWoOrderInfoDao;

    @Override
    public void complateTaskWoOrder(Integer orderId, String woOrderInfoValue, User user) throws Exception {
        TaskWoOrderDetailQuery taskWoOrderDetailQuery = new TaskWoOrderDetailQuery();
        taskWoOrderDetailQuery.setOrderId(orderId);
        List<TaskWoOrder> taskWoOrderList = this.taskWoOrderService.queryTaskWoOrders(taskWoOrderDetailQuery);
        TaskWoOrder taskWoOrder = null;
        for (TaskWoOrder taskWoOrderTemp: taskWoOrderList){
            Integer tacheId = taskWoOrderTemp.getTacheId();
            String woOrderStateCode = taskWoOrderTemp.getWoOrderStateCode();
            if (tacheId == 64 && "10B".equals(woOrderStateCode)) {
                taskWoOrder = taskWoOrderTemp;
            }
        }

        if (taskWoOrder == null) {
            return;
        }

        List<TaskWoOrderInfo> taskWoOrderInfoList = this.taskWoOrderInfoDao.selectTaskWoOrderInfoListByOrderId(orderId);
        for (TaskWoOrderInfo taskWoOrderInfo : taskWoOrderInfoList) {
            Integer woOrderId = taskWoOrderInfo.getWoOrderId();
            if (woOrderId.intValue() == taskWoOrder.getWoOrderId()){
                taskWoOrderInfo.setWoInfoValue(woOrderInfoValue);
                this.taskWoOrderInfoDao.updateTaskWoOrderInfo(taskWoOrderInfo);
            }
        }

        //对象外
        TaskWoOrderSysInfo taskWoOrderSysInfo = new TaskWoOrderSysInfo();
        taskWoOrderSysInfo.setWoOrderId(taskWoOrder.getWoOrderId());
        taskWoOrderSysInfo.setSysInfoId(1069);
        if (woOrderInfoValue.indexOf("1069") >= 0) {
            taskWoOrderSysInfo.setWoInfoValue(1);
        } else {
            taskWoOrderSysInfo.setWoInfoValue(0);
        }
        this.taskWoOrderSysInfoDao.insertTaskWoOrderSysInfo(taskWoOrderSysInfo);
        //pfmea
        taskWoOrderSysInfo = new TaskWoOrderSysInfo();
        taskWoOrderSysInfo.setWoOrderId(taskWoOrder.getWoOrderId());
        taskWoOrderSysInfo.setSysInfoId(1070);
        if (woOrderInfoValue.indexOf("1070") >= 0) {
            taskWoOrderSysInfo.setWoInfoValue(1);
        } else {
            taskWoOrderSysInfo.setWoInfoValue(0);
        }
        this.taskWoOrderSysInfoDao.insertTaskWoOrderSysInfo(taskWoOrderSysInfo);
        //cp
        taskWoOrderSysInfo = new TaskWoOrderSysInfo();
        taskWoOrderSysInfo.setWoOrderId(taskWoOrder.getWoOrderId());
        taskWoOrderSysInfo.setSysInfoId(1071);
        if (woOrderInfoValue.indexOf("1071") >= 0) {
            taskWoOrderSysInfo.setWoInfoValue(1);
        } else {
            taskWoOrderSysInfo.setWoInfoValue(0);
        }
        this.taskWoOrderSysInfoDao.insertTaskWoOrderSysInfo(taskWoOrderSysInfo);
        //作业标准书
        taskWoOrderSysInfo = new TaskWoOrderSysInfo();
        taskWoOrderSysInfo.setWoOrderId(taskWoOrder.getWoOrderId());
        taskWoOrderSysInfo.setSysInfoId(1072);
        if (woOrderInfoValue.indexOf("1072") >= 0) {
            taskWoOrderSysInfo.setWoInfoValue(1);
        } else {
            taskWoOrderSysInfo.setWoInfoValue(0);
        }
        this.taskWoOrderSysInfoDao.insertTaskWoOrderSysInfo(taskWoOrderSysInfo);
        //流程图
        taskWoOrderSysInfo = new TaskWoOrderSysInfo();
        taskWoOrderSysInfo.setWoOrderId(taskWoOrder.getWoOrderId());
        taskWoOrderSysInfo.setSysInfoId(1073);
        taskWoOrderSysInfo.setWoInfoValue(0);
        this.taskWoOrderSysInfoDao.insertTaskWoOrderSysInfo(taskWoOrderSysInfo);

        //更新task_wo_order表
        taskWoOrder.setWoOrderStateCode("10R");
        taskWoOrder.setWoRefuseReason("");
        taskWoOrder.setReplyUser(user.getUserId());
        taskWoOrder.setReplyTime(new Date());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        taskWoOrder.setRequireCompleteTimeStr(format.format(new Date()));
        this.taskWoOrderService.editTaskWoOrder(taskWoOrder);

        //填写信息后，自动审核
        this.taskWoOrderService.editAcceptWoOrder(taskWoOrder.getWoOrderId());

        //判断是否都已经填写完信息
        boolean flag = true;
        taskWoOrderList = this.taskWoOrderService.queryTaskWoOrders(taskWoOrderDetailQuery);
        for (TaskWoOrder taskWoOrderTemp: taskWoOrderList){
            String woOrderState = taskWoOrderTemp.getWoOrderStateCode();
            if (taskWoOrderTemp.getWoOrderId().intValue() != taskWoOrder.getWoOrderId()) {
                if ("10B".equals(woOrderState) || "10R".equals(woOrderState)) {
                    flag = false;
                    break;
                }
            }
        }
        if (flag) {
            this.taskWoOrderService.editCompleteWoOrder(orderId);
        }
    }
}
