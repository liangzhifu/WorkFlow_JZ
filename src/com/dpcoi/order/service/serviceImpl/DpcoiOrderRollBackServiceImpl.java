package com.dpcoi.order.service.serviceImpl;

import com.dpcoi.order.dao.DpcoiOrderDao;
import com.dpcoi.order.dao.DpcoiOrderRollBackDao;
import com.dpcoi.order.domain.DpcoiOrder;
import com.dpcoi.order.query.DpcoiOrderRollBackQuery;
import com.dpcoi.order.service.DpcoiOrderRollBackService;
import com.dpcoi.rr.dao.RRProblemDao;
import com.success.web.framework.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzf
 * @create 2017-10-23
 **/
@Service(value = "dpcoiOrderRollBackService")
public class DpcoiOrderRollBackServiceImpl implements DpcoiOrderRollBackService {

    @Resource(name = "dpcoiOrderDao")
    private DpcoiOrderDao dpcoiOrderDao;

    @Resource(name = "rRProblemDao")
    private RRProblemDao rRProblemDao;

    @Resource(name = "dpcoiOrderRollBackDao")
    private DpcoiOrderRollBackDao dpcoiOrderRollBackDao;

    @Override
    public List<Map<String, Object>> queryDpcoiOrderRollBackllListPage(DpcoiOrderRollBackQuery dpcoiOrderRollBackQuery) throws ServiceException {
        return this.dpcoiOrderRollBackDao.selectDpcoiOrderRollBackllListPage(dpcoiOrderRollBackQuery);
    }

    @Override
    public Integer queryDpcoiOrderRollBackCount(DpcoiOrderRollBackQuery dpcoiOrderRollBackQuery) throws ServiceException {
        return this.dpcoiOrderRollBackDao.selectDpcoiOrderRollBackCount(dpcoiOrderRollBackQuery);
    }

    @Override
    public List<Map<String, Object>> queryRRProblemOrderRollBackllListPage(DpcoiOrderRollBackQuery dpcoiOrderRollBackQuery) throws ServiceException {
        return this.dpcoiOrderRollBackDao.selectRRProblemOrderRollBackllListPage(dpcoiOrderRollBackQuery);
    }

    @Override
    public Integer queryRRProblemOrderRollBackCount(DpcoiOrderRollBackQuery dpcoiOrderRollBackQuery) throws ServiceException {
        return this.dpcoiOrderRollBackDao.selectRRProblemOrderRollBackCount(dpcoiOrderRollBackQuery);
    }

    @Override
    public void updateRollBackDpcoiOrder(Integer dpcoiOrderId, Integer dpcoiWoOrderType) throws ServiceException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dpcoiOrderId", dpcoiOrderId);
        map.put("dpcoiWoOrderType", dpcoiWoOrderType);

        //删除文件
        this.dpcoiOrderRollBackDao.deleteDpcoiRollBackWoOrderFile(map);

        //删除工单
        this.dpcoiOrderRollBackDao.deleteDpcoiRollBackWoOrder(map);

        //更新回滚到的工单状态
        this.dpcoiOrderRollBackDao.updateDpcoiWoOrder(map);

        DpcoiOrder dpcoiOrder = new DpcoiOrder();
        dpcoiOrder.setDpcoiOrderId(dpcoiOrderId);
        dpcoiOrder = this.dpcoiOrderDao.selectBySelf(dpcoiOrder);
        Integer rrProblemId = dpcoiOrder.getRrProblemId();
        if(rrProblemId != null){
            //更新RR问题点
            Map<String, Object> rrMap = new HashMap<String, Object>();
            rrMap.put("rrProblemId", rrProblemId);
            rrMap.put("dpcoiWoOrderType", dpcoiWoOrderType);
            this.rRProblemDao.updateRollBackRRProblem(rrMap);
        }

    }

}
