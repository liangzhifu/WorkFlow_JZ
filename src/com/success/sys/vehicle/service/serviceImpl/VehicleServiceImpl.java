package com.success.sys.vehicle.service.serviceImpl;/**
 * Created by liangzhifu
 * DATE:2017/5/30.
 */

import com.success.sys.vehicle.dao.VehicleDao;
import com.success.sys.vehicle.domain.Vehicle;
import com.success.sys.vehicle.query.VehicleQuery;
import com.success.sys.vehicle.service.VehicleService;
import com.success.web.framework.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-05-30
 **/
@Service("vehicleService")
public class VehicleServiceImpl implements VehicleService {

    @Resource(name="vehicleDao")
    private VehicleDao vehicleDao;

    @Override
    public Integer deleteVehicle(Vehicle vehicle) {
        return this.vehicleDao.deleteVehicle(vehicle);
    }

    @Override
    public Integer addVehicle(Vehicle vehicle) {
        return this.vehicleDao.insertVehicle(vehicle);
    }

    @Override
    public List<Map<String, Object>> queryVehicleList(VehicleQuery vehicleQuery) throws ServiceException {
        return this.vehicleDao.selectVehicleList(vehicleQuery);
    }

    @Override
    public List<Map<String, Object>> queryVehicleListPage(VehicleQuery vehicleQuery) throws ServiceException {
        return this.vehicleDao.selectVehicleListPage(vehicleQuery);
    }

    @Override
    public Integer queryVehicleCount(VehicleQuery vehicleQuery) throws ServiceException {
        return this.vehicleDao.selectVehicleCount(vehicleQuery);
    }
}
