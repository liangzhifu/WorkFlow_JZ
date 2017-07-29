package com.dpcoi.config.service.serviceImpl;/**
 * Created by liangzhifu
 * DATE:2017/6/17.
 */

import com.dpcoi.config.dao.DpcoiConfigVehicleDao;
import com.dpcoi.config.domain.DpcoiConfigVehicle;
import com.dpcoi.config.query.DpcoiConfigVehicleQuery;
import com.dpcoi.config.service.DpcoiConfigVehicleService;
import com.success.web.framework.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-06-17
 **/
@Service("dpcoiConfigVehicleService")
public class DpcoiConfigVehicleServiceImpl implements DpcoiConfigVehicleService {

    @Resource(name="dpcoiConfigVehicleDao")
    private DpcoiConfigVehicleDao dpcoiConfigVehicleDao;

    @Override
    public Integer addDpcoiConfigVehicle(DpcoiConfigVehicle dpcoiConfigVehicle) throws ServiceException {
        return this.dpcoiConfigVehicleDao.insertDpcoiConfigVehicle(dpcoiConfigVehicle);
    }

    @Override
    public Integer deleteDpcoiConfigVehicle(DpcoiConfigVehicle dpcoiConfigVehicle) throws ServiceException {
        dpcoiConfigVehicle.setDeleteState("1");
        return this.dpcoiConfigVehicleDao.updateDpcoiConfigVehicle(dpcoiConfigVehicle);
    }

    @Override
    public List<Map<String, Object>> queryDpcoiConfigVehicleList(DpcoiConfigVehicleQuery dpcoiConfigVehicleQuery) throws ServiceException {
        return this.dpcoiConfigVehicleDao.selectDpcoiConfigVehicleList(dpcoiConfigVehicleQuery);
    }

    @Override
    public List<Map<String, Object>> queryDpcoiConfigVehiclePageList(DpcoiConfigVehicleQuery dpcoiConfigVehicleQuery) throws ServiceException {
        return this.dpcoiConfigVehicleDao.selectDpcoiConfigVehicleListPage(dpcoiConfigVehicleQuery);
    }

    @Override
    public Integer queryDpcoiConfigVehicleCount(DpcoiConfigVehicleQuery dpcoiConfigVehicleQuery) throws ServiceException {
        return this.dpcoiConfigVehicleDao.selectDpcoiConfigVehicleCount(dpcoiConfigVehicleQuery);
    }
}
