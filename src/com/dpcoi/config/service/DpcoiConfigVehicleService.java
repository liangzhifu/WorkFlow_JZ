package com.dpcoi.config.service;/**
 * Created by liangzhifu
 * DATE:2017/6/17.
 */

import com.dpcoi.config.domain.DpcoiConfigVehicle;
import com.dpcoi.config.query.DpcoiConfigVehicleQuery;
import com.success.web.framework.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-06-17
 **/

public interface DpcoiConfigVehicleService {

    /**
     * 新增
     * @param dpcoiConfigVehicle 参数
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public Integer addDpcoiConfigVehicle(DpcoiConfigVehicle dpcoiConfigVehicle) throws ServiceException;

    /**
     * 删除
     * @param dpcoiConfigVehicle 参数
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public Integer deleteDpcoiConfigVehicle(DpcoiConfigVehicle dpcoiConfigVehicle) throws ServiceException;

    /**
     * 查询列表
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryDpcoiConfigVehicleList(DpcoiConfigVehicleQuery dpcoiConfigVehicleQuery) throws ServiceException;

    /**
     * 查询分页列表
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryDpcoiConfigVehiclePageList(DpcoiConfigVehicleQuery dpcoiConfigVehicleQuery) throws ServiceException;

    /**
     * 查询总数
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public Integer queryDpcoiConfigVehicleCount(DpcoiConfigVehicleQuery dpcoiConfigVehicleQuery) throws ServiceException;
}
