package com.dpcoi.woOrder.service;

import com.dpcoi.woOrder.query.RRProblemWoOrderQuery;
import com.success.web.framework.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * Created by liangzhifu
 * DATE:2017/6/30.
 */
public interface RRProblemWoOrderService {

    /**
     * 获取dpcoiWoOrder订单列表--分页
     * @param rrProblemWoOrderQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryRRProblemWoOrderListPage(RRProblemWoOrderQuery rrProblemWoOrderQuery) throws ServiceException;

    /**
     * 获取rrProblemWoOrder订单数量
     * @param rrProblemWoOrderQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public Integer queryRRProblemWoOrderCount(RRProblemWoOrderQuery rrProblemWoOrderQuery) throws ServiceException;

}
