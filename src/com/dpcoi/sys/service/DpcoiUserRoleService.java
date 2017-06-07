package com.dpcoi.sys.service;

import com.dpcoi.sys.domain.DpcoiUserRole;
import com.dpcoi.sys.query.DpcoiUserRoleQuery;
import com.success.sys.user.domain.User;
import com.success.web.framework.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * Created by liangzhifu
 * DATE:2017/5/3.
 */
public interface DpcoiUserRoleService {

    /**
     * 查询dpcoi用户权限
     * @param dpcoiUserRoleQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryDpcoiRoleList(DpcoiUserRoleQuery dpcoiUserRoleQuery) throws ServiceException;

    /**
     * 添加多个DpcoiRole权限
     * @param dpcoiRoleIdStr  系统DpcoiRoleID字符串
     * @param dpcoiUserId 系统DpcoiUserId
     * @param user 操作人
     * @throws ServiceException 异常
     */
    public void addDpcoiUserRoles(String dpcoiRoleIdStr, String dpcoiUserId, User user) throws ServiceException;

    /**
     * 添加DpcoiRole权限
     * @param dpcoiUserRole  权限实体
     * @throws ServiceException  异常
     */
    public void addDpcoiUserRole(DpcoiUserRole dpcoiUserRole) throws ServiceException;

    /**
     * 更新DpcoiRole权限
     * @param dpcoiUserRole 权限实体
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public Integer updateDpcoiUserRole(DpcoiUserRole dpcoiUserRole) throws ServiceException;

}
