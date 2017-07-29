package com.dpcoi.config.service;

import com.dpcoi.config.domain.DpcoiConfig;
import com.dpcoi.config.query.DpcoiConfigQuery;
import com.success.web.framework.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * Created by liangzhifu
 * DATE:2017/6/17.
 */
public interface DpcoiConfigService {

    /**
     * 添加
     * @param dpcoiConfig 参数
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public Integer addDpcoiConfig(DpcoiConfig dpcoiConfig) throws ServiceException;

    /**
     * 删除
     * @param dpcoiConfig 参数
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public Integer deleteDpcoiConfig(DpcoiConfig dpcoiConfig) throws ServiceException;

    /**
     * 查询列表
     * @param dpcoiConfigQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryDpcoiConfigList(DpcoiConfigQuery dpcoiConfigQuery) throws ServiceException;

    /**
     * 查询分页列表
     * @param dpcoiConfigQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryDpcoiConfigPageList(DpcoiConfigQuery dpcoiConfigQuery) throws ServiceException;

    /**
     * 查询总数
     * @param dpcoiConfigQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public Integer queryDpcoiConfigCount(DpcoiConfigQuery dpcoiConfigQuery) throws ServiceException;
}
