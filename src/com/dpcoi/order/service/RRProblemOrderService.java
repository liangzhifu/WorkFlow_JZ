package com.dpcoi.order.service;/**
 * Created by liangzhifu
 * DATE:2017/6/29.
 */

import com.dpcoi.order.query.RRProblemOrderQuery;
import com.success.web.framework.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-06-29
 **/

public interface RRProblemOrderService {

    /**
     * 获取rrProblemOrder订单列表--分页
     * @param rrProblemOrderQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryRRProblemOrderListPage(RRProblemOrderQuery rrProblemOrderQuery) throws ServiceException;

    /**
     * 获取rrProblemOrder订单数量
     * @param rrProblemOrderQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public Integer queryRRProblemOrderCount(RRProblemOrderQuery rrProblemOrderQuery) throws ServiceException;

    /**
     * 获取RRproblem对应的DPCOI上传的文件
     * @param map 参数
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryDpcoiWoOrderFileList(Map<String, Object> map) throws ServiceException;

}
