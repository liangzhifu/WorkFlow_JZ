package com.dpcoi.order.service.serviceImpl;

import com.dpcoi.rr.dao.RRProblemDao;
import com.dpcoi.rr.domain.RRProblem;
import com.dpcoi.util.EmailUtil;
import com.dpcoi.order.dao.DpcoiOrderDao;
import com.dpcoi.order.domain.DpcoiOrder;
import com.dpcoi.order.query.DpcoiOrderQuery;
import com.dpcoi.order.service.DpcoiOrderService;
import com.dpcoi.woOrder.dao.DpcoiWoOrderDao;
import com.dpcoi.woOrder.domain.DpcoiWoOrder;
import com.dpcoi.woOrder.query.DpcoiWoOrderQuery;
import com.other.history.dao.OperateHistoryDao;
import com.other.history.domain.OperateHistory;
import com.success.sys.email.dao.TimeTaskDao;
import com.success.sys.email.domain.TimeTask;
import com.success.sys.user.domain.User;
import com.success.task.detail.domain.TaskOrder;
import com.success.web.framework.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 梁志福 on 2017/4/20.
 */
@Service("dpcoiOrderService")
public class DpcoiOrderServiceImpl implements DpcoiOrderService {

    @Resource(name="dpcoiOrderDao")
    private DpcoiOrderDao dpcoiOrderDao;

    @Resource(name="dpcoiWoOrderDao")
    private DpcoiWoOrderDao dpcoiWoOrderDao;

    @Resource(name="timeTaskDao")
    private TimeTaskDao timeTaskDao;

    @Resource(name="operateHistoryDao")
    private OperateHistoryDao operateHistoryDao;

    @Resource(name="rRProblemDao")
    private RRProblemDao rRProblemDao;

    @Override
    public Integer addDpcoiOrder(DpcoiOrder dpcoiOrder) throws ServiceException {
        Integer num = this.dpcoiOrderDao.insertDpcoiOrder(dpcoiOrder);

        //添加操作记录
        Integer dpcoiOrderType = dpcoiOrder.getDpcoiOrderType();
        OperateHistory operateHistory = new OperateHistory();
        operateHistory.setSystermType(1);
        operateHistory.setBusinessId(dpcoiOrder.getDpcoiOrderId());
        operateHistory.setBusinessType(1);
        operateHistory.setOperateBy(dpcoiOrder.getCreateBy());
        operateHistory.setOperateDate(new Date());
        if(dpcoiOrderType.intValue() == 1){
            operateHistory.setOperateType(1);
        }else {
            operateHistory.setOperateType(2);
        }
        this.operateHistoryDao.insertOperateHistory(operateHistory);

        dpcoiOrder = this.queryDpcoiOrder(dpcoiOrder);
        DpcoiWoOrder dpcoiWoOrder = new DpcoiWoOrder();
        dpcoiWoOrder.setDpcoiOrderId(dpcoiOrder.getDpcoiOrderId());
        dpcoiWoOrder.setDelFlag("0");
        dpcoiWoOrder.setDpcoiWoOrderState(1);
        dpcoiWoOrder.setDpcoiWoOrderType(1);
        dpcoiWoOrder.setCreateDate(new Date());
        this.dpcoiWoOrderDao.insertDpcoiWoOrder(dpcoiWoOrder);

        //发邮件通知人员
        DpcoiWoOrderQuery dpcoiWoOrderQery = new DpcoiWoOrderQuery();
        dpcoiWoOrderQery.setDpcoiWoOrderState(dpcoiWoOrder.getDpcoiWoOrderState());
        dpcoiWoOrderQery.setDpcoiWoOrderType(dpcoiWoOrder.getDpcoiWoOrderType());
        String emaliUser = this.dpcoiWoOrderDao.selectWoOrderEmailUsers(dpcoiWoOrderQery);
        dpcoiOrder = this.queryDpcoiOrder(dpcoiOrder);
        TimeTask timeTask = EmailUtil.generateTimeTask(dpcoiOrder, null, 18, emaliUser);
        this.timeTaskDao.insertTimeTask(timeTask);
        return num;
    }

    @Override
    public Integer addDpcoiOrder(RRProblem rrProblem, User user) throws ServiceException {
        DpcoiOrder dpcoiOrder = new DpcoiOrder();
        dpcoiOrder.setRrProblemId(rrProblem.getId());
        dpcoiOrder.setDpcoiOrderType(3);
        dpcoiOrder.setDelFlag("0");
        dpcoiOrder.setDpcoiOrderState(1);
        dpcoiOrder.setCreateDate(new Date());
        dpcoiOrder.setCreateBy(user.getUserId());
        dpcoiOrder.setUpdateDate(new Date());
        dpcoiOrder.setUpdateBy(user.getUserId());
        dpcoiOrder.setPfmeaCreateDate(new Date());
        dpcoiOrder.setPfmeaEmailDate(new Date());
        dpcoiOrder.setPfmeaDelay(0);
        dpcoiOrder.setCpDelay(0);
        dpcoiOrder.setStandardBookDelay(0);
        Integer num = this.dpcoiOrderDao.insertDpcoiOrder(dpcoiOrder);

        //添加操作记录
        Integer dpcoiOrderType = dpcoiOrder.getDpcoiOrderType();
        OperateHistory operateHistory = new OperateHistory();
        operateHistory.setSystermType(1);
        operateHistory.setBusinessId(dpcoiOrder.getDpcoiOrderId());
        operateHistory.setBusinessType(1);
        operateHistory.setOperateBy(dpcoiOrder.getCreateBy());
        operateHistory.setOperateDate(new Date());
        operateHistory.setOperateType(5);
        this.operateHistoryDao.insertOperateHistory(operateHistory);

        dpcoiOrder = this.queryDpcoiOrder(dpcoiOrder);
        DpcoiWoOrder dpcoiWoOrder = new DpcoiWoOrder();
        dpcoiWoOrder.setDpcoiOrderId(dpcoiOrder.getDpcoiOrderId());
        dpcoiWoOrder.setDelFlag("0");
        dpcoiWoOrder.setDpcoiWoOrderState(1);
        dpcoiWoOrder.setDpcoiWoOrderType(1);
        dpcoiWoOrder.setCreateDate(new Date());
        this.dpcoiWoOrderDao.insertDpcoiWoOrder(dpcoiWoOrder);

        //发邮件通知人员
        DpcoiWoOrderQuery dpcoiWoOrderQery = new DpcoiWoOrderQuery();
        dpcoiWoOrderQery.setDpcoiWoOrderState(dpcoiWoOrder.getDpcoiWoOrderState());
        dpcoiWoOrderQery.setDpcoiWoOrderType(dpcoiWoOrder.getDpcoiWoOrderType());
        String emaliUser = this.dpcoiWoOrderDao.selectWoOrderEmailUsers(dpcoiWoOrderQery);
        dpcoiOrder = this.queryDpcoiOrder(dpcoiOrder);
        TimeTask timeTask = EmailUtil.generateTimeTask(dpcoiOrder, rrProblem, 18, emaliUser);
        this.timeTaskDao.insertTimeTask(timeTask);
        return num;
    }

    @Override
    public Integer updateDpcoiOrder(DpcoiOrder dpcoiOrder) throws ServiceException {
        return this.dpcoiOrderDao.updateDpcoiOrder(dpcoiOrder);
    }

    @Override
    public List<Map<String, Object>> queryDpcoiOrderListPage(DpcoiOrderQuery dpcoiOrderQuery) throws ServiceException {
        return this.dpcoiOrderDao.selectDpcoiOrderListPage(dpcoiOrderQuery);
    }

    @Override
    public Integer queryDpcoiOrderCount(DpcoiOrderQuery dpcoiOrderQuery) throws ServiceException {
        return this.dpcoiOrderDao.selectDpcoiOrderCount(dpcoiOrderQuery);
    }

    @Override
    public DpcoiOrder queryDpcoiOrder(DpcoiOrder dpcoiOrder) throws ServiceException {
        return this.dpcoiOrderDao.selectBySelf(dpcoiOrder);
    }

    @Override
    public DpcoiOrder queryDpcoiOrderByRRProblem(Integer rrProblemId) throws ServiceException {
        return this.dpcoiOrderDao.selectDpcoiOrderByRRProblem(rrProblemId);
    }

    @Override
    public void editDpcoiOrderToVoid(DpcoiOrder dpcoiOrder, User user) throws ServiceException {
        dpcoiOrder.setDpcoiOrderState(3);
        dpcoiOrder.setUpdateBy(user.getUserId());
        dpcoiOrder.setUpdateDate(new Date());
        this.dpcoiOrderDao.updateDpcoiOrder(dpcoiOrder);

        //添加操作记录
        OperateHistory operateHistory = new OperateHistory();
        operateHistory.setSystermType(1);
        operateHistory.setBusinessId(dpcoiOrder.getDpcoiOrderId());
        operateHistory.setBusinessType(1);
        operateHistory.setOperateBy(user.getUserId());
        operateHistory.setOperateDate(new Date());
        operateHistory.setOperateType(3);
        this.operateHistoryDao.insertOperateHistory(operateHistory);

        DpcoiWoOrderQuery dpcoiWoOrderQuery = new DpcoiWoOrderQuery();
        dpcoiWoOrderQuery.setDpcoiOrderId(dpcoiOrder.getDpcoiOrderId());
        List<DpcoiWoOrder> woOrderList = this.dpcoiWoOrderDao.selectDpcoiWoOrderOfDpcoiOrder(dpcoiWoOrderQuery);
        for (DpcoiWoOrder dpcoiWoOrder: woOrderList) {
            int woOrderState = dpcoiWoOrder.getDpcoiWoOrderState().intValue();
            if(woOrderState == 1 || woOrderState == 2 || woOrderState == 3){
                dpcoiWoOrder.setDpcoiWoOrderState(6);
                this.dpcoiWoOrderDao.updateDpcoiWoOrder(dpcoiWoOrder);
            }
        }

        dpcoiOrder = this.queryDpcoiOrder(dpcoiOrder);
        Integer noticeType = 36;
        Integer orderType = dpcoiOrder.getDpcoiOrderType();
        if(orderType.intValue() == 2){
            noticeType = 37;
        }
        //发送作废邮件
        dpcoiOrder = this.queryDpcoiOrder(dpcoiOrder);
        String emaliUser = this.dpcoiOrderDao.selectOrderToVodiEmailUsers(dpcoiOrder);
        dpcoiOrder = this.queryDpcoiOrder(dpcoiOrder);
        Integer rrProblemId = dpcoiOrder.getRrProblemId();
        RRProblem rrProblem;
        if(rrProblemId == null){
            rrProblem = null;
        }else {
            rrProblem = new RRProblem();
            rrProblem.setId(rrProblemId);
            rrProblem = this.rRProblemDao.selectRRProblem(rrProblem);
        }
        TimeTask timeTask = EmailUtil.generateTimeTask(dpcoiOrder, rrProblem, noticeType, emaliUser);
        this.timeTaskDao.insertTimeTask(timeTask);
    }

    @Override
    public void editDpcoiOrder(DpcoiOrder dpcoiOrder, User user) throws ServiceException {
        dpcoiOrder.setUpdateDate(new Date());
        dpcoiOrder.setUpdateBy(user.getUserId());
        this.updateDpcoiOrder(dpcoiOrder);

        //添加操作记录
        OperateHistory operateHistory = new OperateHistory();
        operateHistory.setSystermType(1);
        operateHistory.setBusinessId(dpcoiOrder.getDpcoiOrderId());
        operateHistory.setBusinessType(1);
        operateHistory.setOperateBy(user.getUserId());
        operateHistory.setOperateDate(new Date());
        operateHistory.setOperateType(4);
        this.operateHistoryDao.insertOperateHistory(operateHistory);
    }

    @Override
    public List<Map<String, Object>> queryWoOrderDetailList(DpcoiOrder dpcoiOrder) throws ServiceException {
        return this.dpcoiOrderDao.seletDpcoiWoOrderDetailList(dpcoiOrder);
    }

    @Override
    public DpcoiOrder quereyDpcoiOrderOfTaskOrder(TaskOrder taskOrder) throws ServiceException {
        return this.dpcoiOrderDao.selectDpcoiOrderOfTaskOrder(taskOrder);
    }

    @Override
    public DpcoiOrder quereyDpcoiOrderOfTaskOrderNo(String taskOrderNo) throws ServiceException {
        return this.dpcoiOrderDao.selectDpcoiOrderOfTaskOrderNo(taskOrderNo);
    }

    @Override
    public Integer deleteDpcoiOrder(DpcoiOrder dpcoiOrder, User user) throws ServiceException {
        dpcoiOrder.setDelFlag("1");
        dpcoiOrder.setUpdateBy(user.getUserId());
        dpcoiOrder.setUpdateDate(new Date());
        Integer num = this.dpcoiOrderDao.updateDpcoiOrder(dpcoiOrder);

        DpcoiWoOrderQuery dpcoiWoOrderQuery = new DpcoiWoOrderQuery();
        dpcoiWoOrderQuery.setDpcoiOrderId(dpcoiOrder.getDpcoiOrderId());
        List<DpcoiWoOrder> woOrderList = this.dpcoiWoOrderDao.selectDpcoiWoOrderOfDpcoiOrder(dpcoiWoOrderQuery);
        for (DpcoiWoOrder dpcoiWoOrder: woOrderList) {
            dpcoiWoOrder.setDelFlag("1");
            this.dpcoiWoOrderDao.updateDpcoiWoOrder(dpcoiWoOrder);
        }
        return num;
    }

    @Override
    public Integer querySameDpcoiOrder(DpcoiOrderQuery dpcoiOrderQuery) throws ServiceException {
        return this.dpcoiOrderDao.selectSameDpcoiOrder(dpcoiOrderQuery);
    }

    @Override
    public Integer queryDpcoiAddJurisdiction(User user) throws ServiceException {
        return this.dpcoiOrderDao.selectDpcoiAddJurisdiction(user);
    }
}
