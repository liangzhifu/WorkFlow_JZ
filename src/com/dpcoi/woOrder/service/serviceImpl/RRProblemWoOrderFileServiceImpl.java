package com.dpcoi.woOrder.service.serviceImpl;/**
 * Created by liangzhifu
 * DATE:2017/7/4.
 */

import com.dpcoi.woOrder.dao.RRProblemWoOrderFileDao;
import com.dpcoi.woOrder.query.RRProblemWoOrderFileQuery;
import com.dpcoi.woOrder.service.RRProblemWoOrderFileService;
import com.success.web.framework.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-07-04
 **/
@Service("rRProblemWoOrderFileService")
public class RRProblemWoOrderFileServiceImpl implements RRProblemWoOrderFileService {

    @Resource(name="rRProblemWoOrderFileDao")
    private RRProblemWoOrderFileDao rRProblemWoOrderFileDao;

    @Override
    public List<Map<String, Object>> queryRRProblemWoOrderFileListPage(RRProblemWoOrderFileQuery rrProblemWoOrderFileQuery) throws ServiceException {
        return this.rRProblemWoOrderFileDao.selectRRProblemWoOrderFileListPage(rrProblemWoOrderFileQuery);
    }

    @Override
    public Integer queryRRProblemWoOrderFileCount(RRProblemWoOrderFileQuery rrProblemWoOrderFileQuery) throws ServiceException {
        return this.rRProblemWoOrderFileDao.selectRRProblemWoOrderFileCount(rrProblemWoOrderFileQuery);
    }
}
