package com.success.sys.vehicle.service;/**
 * Created by liangzhifu
 * DATE:2017/5/30.
 */

import com.dpcoi.order.query.DpcoiOrderQuery;
import com.success.sys.vehicle.domain.Vehicle;
import com.success.sys.vehicle.query.VehicleQuery;
import com.success.web.framework.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-05-30
 **/

public interface VehicleService {

    /**
     * 删除车种
     * @param vehicle 车种
     * @return 返回结果
     */
    Integer deleteVehicle(Vehicle vehicle);

    /**
     * 添加车种
     * @param vehicle 车种
     * @return 返回结果
     */
    Integer addVehicle(Vehicle vehicle);

    /**
     * 获取车种列表
     * @param vehicleQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryVehicleList(VehicleQuery vehicleQuery) throws ServiceException;

    /**
     * 获取车种列表--分页
     * @param vehicleQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryVehicleListPage(VehicleQuery vehicleQuery) throws ServiceException;

    /**
     * 获取车种数量
     * @param vehicleQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public Integer queryVehicleCount(VehicleQuery vehicleQuery) throws ServiceException;
}
