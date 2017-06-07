package com.dpcoi.statistics.service.serviceImpl;/**
 * Created by liangzhifu
 * DATE:2017/5/18.
 */

import com.dpcoi.statistics.dao.DpcoiStatisticsDao;
import com.dpcoi.statistics.query.DpcoiStatisticsQuery;
import com.dpcoi.statistics.service.DpcoiStatisticsService;
import com.success.web.framework.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-05-18
 **/
@Service("dpcoiStatisticsService")
public class DpcoiStatisticsServiceImpl implements DpcoiStatisticsService{

    @Resource(name="dpcoiStatisticsDao")
    private DpcoiStatisticsDao dpcoiStatisticsDao;

    @Override
    public Integer queryDpcoiOrderCount(DpcoiStatisticsQuery dpcoiStatisticsQuery) throws ServiceException {
        return this.dpcoiStatisticsDao.selectDpcoiOrderCount(dpcoiStatisticsQuery);
    }

    @Override
    public List<Map<String, Object>> queryDpcoiWoOrderCount(DpcoiStatisticsQuery dpcoiStatisticsQuery) throws ServiceException {
        return this.dpcoiStatisticsDao.selectDpcoiWoOrderCount(dpcoiStatisticsQuery);
    }

    @Override
    public List<Map<String, Object>> queryDpcoiWoOrderNoComplete(DpcoiStatisticsQuery dpcoiStatisticsQuery) throws ServiceException {
        return this.dpcoiStatisticsDao.selectWoOrderNoCompleteList(dpcoiStatisticsQuery);
    }

    @Override
    public List<Map<String, Object>> queryDpcoiWoOrderCompleteDelay(DpcoiStatisticsQuery dpcoiStatisticsQuery) throws ServiceException {
        return this.dpcoiStatisticsDao.selectWoOrderCompleteDelayList(dpcoiStatisticsQuery);
    }
}
