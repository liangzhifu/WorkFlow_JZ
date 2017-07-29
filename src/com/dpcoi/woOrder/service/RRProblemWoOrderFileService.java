package com.dpcoi.woOrder.service;

import com.dpcoi.woOrder.query.RRProblemWoOrderFileQuery;
import com.success.web.framework.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * Created by liangzhifu
 * DATE:2017/7/4.
 */
public interface RRProblemWoOrderFileService {

    /**
     * 获取RR问题点文件列表--分页
     * @param rrProblemWoOrderFileQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryRRProblemWoOrderFileListPage(RRProblemWoOrderFileQuery rrProblemWoOrderFileQuery) throws ServiceException;

    /**
     * 获取RR问题点文件数量
     * @param rrProblemWoOrderFileQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public Integer queryRRProblemWoOrderFileCount(RRProblemWoOrderFileQuery rrProblemWoOrderFileQuery) throws ServiceException;

}
