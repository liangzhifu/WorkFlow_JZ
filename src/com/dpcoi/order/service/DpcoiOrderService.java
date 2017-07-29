package com.dpcoi.order.service;

import com.dpcoi.order.domain.DpcoiOrder;
import com.dpcoi.order.query.DpcoiOrderQuery;
import com.dpcoi.rr.domain.RRProblem;
import com.success.sys.user.domain.User;
import com.success.task.detail.domain.TaskOrder;
import com.success.web.framework.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * Created by 梁志福 on 2017/4/20.
 */
public interface DpcoiOrderService {

    /**
     * 添加一个dpcoiOrder订单
     * @param dpcoiOrder DpcoiOrder订单数据
     * @return 返回结果
     * @throws ServiceException 异常信息
     */
    public Integer addDpcoiOrder(DpcoiOrder dpcoiOrder) throws ServiceException;

    /**
     * 添加一个dpcoiOrder订单
     * @param rrProblem RR问题点
     * @param user 用户
     * @return 返回结果
     * @throws ServiceException 异常信息
     */
    public Integer addDpcoiOrder(RRProblem rrProblem, User user) throws ServiceException;

    /**
     * 更新一个dpcoiOrder订单
     * @param dpcoiOrder DpcoiOrder订单数据
     * @return 返回结果
     * @throws ServiceException 异常信息
     */
    public Integer updateDpcoiOrder(DpcoiOrder dpcoiOrder) throws ServiceException;

    /**
     * 获取dpcoiOrder订单列表--分页
     * @param dpcoiOrderQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryDpcoiOrderListPage(DpcoiOrderQuery dpcoiOrderQuery) throws ServiceException;

    /**
     * 获取dpcoiOrder订单数量
     * @param dpcoiOrderQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public Integer queryDpcoiOrderCount(DpcoiOrderQuery dpcoiOrderQuery) throws ServiceException;

    /**
     *根据订单ID查询实体
     * @param dpcoiOrder 订单ID
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public DpcoiOrder queryDpcoiOrder(DpcoiOrder dpcoiOrder) throws ServiceException;

    /**
     *根据RR问题ID查询实体
     * @param rrProblemId RR问题ID
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public DpcoiOrder queryDpcoiOrderByRRProblem(Integer rrProblemId) throws ServiceException;

    /**
     * 作废定单
     * @param dpcoiOrder 定单实体
     * @user 操作员
     * @throws ServiceException 异常
     */
    public void editDpcoiOrderToVoid(DpcoiOrder dpcoiOrder, User user) throws ServiceException;

    /**
     * 修改定单
     * @param dpcoiOrder  定单实体
     * @param user  操作员
     * @throws ServiceException 异常
     */
    public void editDpcoiOrder(DpcoiOrder dpcoiOrder, User user) throws ServiceException;

    /**
     * 获取定单的工单详情
     * @param dpcoiOrder 定单实体
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryWoOrderDetailList(DpcoiOrder dpcoiOrder) throws ServiceException;

    /**
     * 通过4M定单获取dpcoi定单
     * @param taskOrder 4M定单实体
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public DpcoiOrder quereyDpcoiOrderOfTaskOrder(TaskOrder taskOrder) throws ServiceException;

    /**
     * 通过4M定单编号获取dpcoi定单
     * @param taskOrderNo 4M定单编号
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public DpcoiOrder quereyDpcoiOrderOfTaskOrderNo(String taskOrderNo) throws ServiceException;

    /**
     * 删除定单
     * @param dpcoiOrder 定单实体
     * @param user 操作员
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public Integer deleteDpcoiOrder(DpcoiOrder dpcoiOrder, User user) throws ServiceException;

    /**
     * 查找有相同《设变通知书》编号或者设变号定单
     * @param dpcoiOrderQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public Integer querySameDpcoiOrder(DpcoiOrderQuery dpcoiOrderQuery) throws ServiceException;

    /**
     * 查询用户的增加、修改权限
     * @param user 用户
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public Integer queryDpcoiAddJurisdiction(User user) throws ServiceException;
}
