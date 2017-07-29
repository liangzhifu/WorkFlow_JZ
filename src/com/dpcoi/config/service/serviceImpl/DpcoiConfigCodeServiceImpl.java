package com.dpcoi.config.service.serviceImpl;/**
 * Created by liangzhifu
 * DATE:2017/6/17.
 */

import com.dpcoi.config.dao.DpcoiConfigCodeDao;
import com.dpcoi.config.domain.DpcoiConfigCode;
import com.dpcoi.config.service.DpcoiConfigCodeService;
import com.success.web.framework.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author lzf
 * @create 2017-06-17
 **/
@Service("dpcoiConfigCodeService")
public class DpcoiConfigCodeServiceImpl implements DpcoiConfigCodeService {

    @Resource(name="dpcoiConfigCodeDao")
    private DpcoiConfigCodeDao dpcoiConfigCodeDao;

    @Override
    public List<DpcoiConfigCode> queryDpcoiConfigCodeList() throws ServiceException {
        return this.dpcoiConfigCodeDao.selectDpcoiConfigCodeList();
    }
}
