package com.dpcoi.woOrder.service.serviceImpl;/**
 * Created by liangzhifu
 * DATE:2017/6/30.
 */

import com.dpcoi.order.dao.DpcoiOrderDao;
import com.dpcoi.woOrder.dao.RRProblemWoOrderDao;
import com.dpcoi.woOrder.query.RRProblemWoOrderQuery;
import com.dpcoi.woOrder.service.RRProblemWoOrderService;
import com.success.web.framework.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-06-30
 **/
@Service("rRProblemWoOrderService")
public class RRProblemWoOrderServiceImpl implements RRProblemWoOrderService {

    @Resource(name="rRProblemWoOrderDao")
    private RRProblemWoOrderDao rRProblemWoOrderDao;

    @Override
    public List<Map<String, Object>> queryRRProblemWoOrderListPage(RRProblemWoOrderQuery rrProblemWoOrderQuery) throws ServiceException {
        return this.rRProblemWoOrderDao.selectRRProblemWoOrderListPage(rrProblemWoOrderQuery);
    }

    @Override
    public Integer queryRRProblemWoOrderCount(RRProblemWoOrderQuery rrProblemWoOrderQuery) throws ServiceException {
        return this.rRProblemWoOrderDao.selectRRProblemWoOrderCount(rrProblemWoOrderQuery);
    }
}
