package com.other.history.service.serviceImpl;/**
 * Created by liangzhifu
 * DATE:2017/5/12.
 */

import com.other.history.dao.OperateHistoryDao;
import com.other.history.query.OperateHistoryQuery;
import com.other.history.service.OperateHistoryService;
import com.success.web.framework.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-05-12
 **/
@Service("operateHistoryService")
public class OperateHistoryServiceImpl implements OperateHistoryService {

    @Resource(name="operateHistoryDao")
    private OperateHistoryDao operateHistoryDao;

    @Override
    public List<Map<String, Object>> queryOperateHistoryList(OperateHistoryQuery operateHistoryQuery) throws ServiceException {
        return this.operateHistoryDao.selectOperateHistoryList(operateHistoryQuery);
    }
}
