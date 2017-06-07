package com.dpcoi.statistics.service;/**
 * Created by liangzhifu
 * DATE:2017/5/18.
 */

import com.dpcoi.statistics.query.DpcoiStatisticsQuery;
import com.success.web.framework.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-05-18
 **/

public interface DpcoiStatisticsService {

    /**
     * 查询DPCOI定单数量
     * @param dpcoiStatisticsQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public Integer queryDpcoiOrderCount(DpcoiStatisticsQuery dpcoiStatisticsQuery) throws ServiceException;

    /**
     * 查询DPCOI工单数量
     * @param dpcoiStatisticsQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryDpcoiWoOrderCount(DpcoiStatisticsQuery dpcoiStatisticsQuery) throws ServiceException;

    /**
     * 获取未完成的工单列表
     * @param dpcoiStatisticsQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryDpcoiWoOrderNoComplete(DpcoiStatisticsQuery dpcoiStatisticsQuery) throws ServiceException;

    /**
     * 获取已完成超时的工单列表
     * @param dpcoiStatisticsQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryDpcoiWoOrderCompleteDelay(DpcoiStatisticsQuery dpcoiStatisticsQuery) throws ServiceException;
}
