package com.dpcoi.config.service.serviceImpl;/**
 * Created by liangzhifu
 * DATE:2017/6/17.
 */

import com.dpcoi.config.dao.DpcoiConfigDao;
import com.dpcoi.config.domain.DpcoiConfig;
import com.dpcoi.config.query.DpcoiConfigQuery;
import com.dpcoi.config.service.DpcoiConfigService;
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
@Service("dpcoiConfigService")
public class DpcoiConfigServiceImpl implements DpcoiConfigService {

    @Resource(name="dpcoiConfigDao")
    private DpcoiConfigDao dpcoiConfigDao;

    @Override
    public Integer addDpcoiConfig(DpcoiConfig dpcoiConfig) throws ServiceException {
        return this.dpcoiConfigDao.insertDpcoiConfig(dpcoiConfig);
    }

    @Override
    public Integer deleteDpcoiConfig(DpcoiConfig dpcoiConfig) throws ServiceException {
        dpcoiConfig.setDeleteState("1");
        return this.dpcoiConfigDao.updateDpcoiConfig(dpcoiConfig);
    }

    @Override
    public List<Map<String, Object>> queryDpcoiConfigList(DpcoiConfigQuery dpcoiConfigQuery) throws ServiceException {
        return this.dpcoiConfigDao.selectDpcoiConfigList(dpcoiConfigQuery);
    }

    @Override
    public List<Map<String, Object>> queryDpcoiConfigPageList(DpcoiConfigQuery dpcoiConfigQuery) throws ServiceException {
        return this.dpcoiConfigDao.selectDpcoiConfigListPage(dpcoiConfigQuery);
    }

    @Override
    public Integer queryDpcoiConfigCount(DpcoiConfigQuery dpcoiConfigQuery) throws ServiceException {
        return this.dpcoiConfigDao.selectDpcoiConfigCount(dpcoiConfigQuery);
    }
}
