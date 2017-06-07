package com.other.history.service;

import com.other.history.query.OperateHistoryQuery;
import com.success.web.framework.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * Created by liangzhifu
 * DATE:2017/5/12.
 */
public interface OperateHistoryService {

    /**
     * 查询操作记录
     * @param operateHistoryQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryOperateHistoryList(OperateHistoryQuery operateHistoryQuery) throws ServiceException;

}
