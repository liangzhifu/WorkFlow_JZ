package com.dpcoi.order.service;

import com.dpcoi.order.query.DpcoiOrderRollBackQuery;
import com.success.web.framework.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface DpcoiOrderRollBackService {

    /**
     * 查询可以回滚的DPCOI列表
     * @param dpcoiOrderRollBackQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryDpcoiOrderRollBackllListPage(DpcoiOrderRollBackQuery dpcoiOrderRollBackQuery) throws ServiceException;

    /**
     * 查询可以回滚的DPCOI数量
     * @param dpcoiOrderRollBackQuery
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public Integer queryDpcoiOrderRollBackCount(DpcoiOrderRollBackQuery dpcoiOrderRollBackQuery) throws ServiceException;

    /**
     * 查询可以回滚的RR问题点的DPCOI列表
     * @param dpcoiOrderRollBackQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryRRProblemOrderRollBackllListPage(DpcoiOrderRollBackQuery dpcoiOrderRollBackQuery) throws ServiceException;

    /**
     * 查询可以回滚的RR问题点的DPCOI数量
     * @param dpcoiOrderRollBackQuery
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public Integer queryRRProblemOrderRollBackCount(DpcoiOrderRollBackQuery dpcoiOrderRollBackQuery) throws ServiceException;

    /**
     * 回滚RR问题点的DPCOI
     * @param dpcoiOrderId 订单ID
     * @param dpcoiWoOrderType 工单类型
     * @throws ServiceException 异常
     */
    public void updateRollBackDpcoiOrder(Integer dpcoiOrderId, Integer dpcoiWoOrderType) throws ServiceException;

}
