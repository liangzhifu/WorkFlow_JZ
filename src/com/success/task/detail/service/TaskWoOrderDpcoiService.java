package com.success.task.detail.service;

import com.success.sys.user.domain.User;

/**
 * Created by L on 2018/9/2.
 */
public interface TaskWoOrderDpcoiService {

    /**
     * Dpcoi回传值
     * @param orderId 订单ID
     * @param woOrderInfoValue 回传信息
     * @param user 用户
     * @throws Exception 异常
     */
    void complateTaskWoOrder(Integer orderId, String woOrderInfoValue, User user) throws Exception;

}
