package com.dpcoi.woOrder.service;

import com.dpcoi.woOrder.domain.DpcoiWoOrder;
import com.dpcoi.woOrder.query.DpcoiWoOrderQuery;
import com.success.sys.user.domain.User;
import com.success.web.framework.exception.ServiceException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by 梁志福 on 2017/4/20.
 */
public interface DpcoiWoOrderService {
    
    /**
     * 添加一个dpcoiWoOrder订单
     * @param dpcoiWoOrder DpcoiWoOrder订单数据
     * @return 返回结果
     * @throws ServiceException 异常信息
     */
    public Integer addDpcoiWoOrder(DpcoiWoOrder dpcoiWoOrder) throws ServiceException;

    /**
     * 更新一个dpcoiWoOrder订单
     * @param dpcoiWoOrder DpcoiWoOrder订单数据
     * @return 返回结果
     * @throws ServiceException 异常信息
     */
    public Integer updateDpcoiWoOrder(DpcoiWoOrder dpcoiWoOrder) throws ServiceException;

    /**
     * 获取dpcoiWoOrder订单列表--分页
     * @param dpcoiWoOrderQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryDpcoiWoOrderListPage(DpcoiWoOrderQuery dpcoiWoOrderQuery) throws ServiceException;

    /**
     * 获取dpcoiWoOrder订单列表
     * @param dpcoiWoOrderQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryDpcoiWoOrderList(DpcoiWoOrderQuery dpcoiWoOrderQuery) throws ServiceException;


    /**
     * 获取dpcoiWoOrder订单数量
     * @param dpcoiWoOrderQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public Integer queryDpcoiWoOrderCount(DpcoiWoOrderQuery dpcoiWoOrderQuery) throws ServiceException;

    /**
     *根据订单ID查询实体
     * @param dpcoiWoOrder 订单ID
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public DpcoiWoOrder queryDpcoiWoOrder(DpcoiWoOrder dpcoiWoOrder) throws ServiceException;

    /**
     * 确认是否需要变更
     * @param dpcoiWoOrder 工单
     * @user 操作员
     * @param confirmResult 确认结果
     */
    public void editWoOrderConfirm(DpcoiWoOrder dpcoiWoOrder, Integer confirmResult, User user) throws ServiceException;

    /**
     * 上传文件完成
     * @param dpcoiWoOrder 工单
     * @param user 操作人
     * @throws ServiceException 异常
     */
    public void editWoOrderFileComplete(DpcoiWoOrder dpcoiWoOrder, User user) throws ServiceException;

    /**
     * 审核变更
     * @param dpcoiWoOrder 工单
     * @param confirmResult 审核结果
     * @param user 操作人
     * @param noPassedWoOrderFileIdStr 未通过的文件
     */
    public void editWoOrderManagerConfirm(DpcoiWoOrder dpcoiWoOrder, Integer confirmResult, User user, String noPassedWoOrderFileIdStr) throws ServiceException;

}
