package com.dpcoi.order.service.serviceImpl;/**
 * Created by liangzhifu
 * DATE:2017/6/29.
 */

import com.dpcoi.order.dao.RRProblemOrderDao;
import com.dpcoi.order.query.RRProblemOrderQuery;
import com.dpcoi.order.service.DpcoiOrderService;
import com.dpcoi.order.service.RRProblemOrderService;
import com.dpcoi.rr.dao.RRProblemDao;
import com.success.web.framework.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-06-29
 **/
@Service("rRProblemOrderService")
public class RRProblemOrderServiceImpl implements RRProblemOrderService {

    @Resource(name="rRProblemOrderDao")
    private RRProblemOrderDao rRProblemOrderDao;

    @Override
    public List<Map<String, Object>> queryRRProblemOrderListPage(RRProblemOrderQuery rrProblemOrderQuery) throws ServiceException {
        return this.rRProblemOrderDao.selectRRProblemOrderListPage(rrProblemOrderQuery);
    }

    @Override
    public Integer queryRRProblemOrderCount(RRProblemOrderQuery rrProblemOrderQuery) throws ServiceException {
        return this.rRProblemOrderDao.selectRRProblemOrderCount(rrProblemOrderQuery);
    }
}
