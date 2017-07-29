package com.dpcoi.config.service;/**
 * Created by liangzhifu
 * DATE:2017/6/17.
 */

import com.dpcoi.config.domain.DpcoiConfigCode;
import com.success.web.framework.exception.ServiceException;

import java.util.List;

/**
 *
 * @author lzf
 * @create 2017-06-17
 **/

public interface DpcoiConfigCodeService {

    /**
     * 查询列表
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<DpcoiConfigCode> queryDpcoiConfigCodeList() throws ServiceException;
}
