package com.dpcoi.sys.service;

import com.dpcoi.sys.domain.DpcoiUser;
import com.dpcoi.sys.query.DpcoiUserQuery;
import com.dpcoi.sys.query.DpcoiUserRoleQuery;
import com.success.sys.user.domain.User;
import com.success.sys.user.query.UserQuery;
import com.success.web.framework.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * Created by 梁志福 on 2017/4/20.
 */
public interface DpcoiUserService {

    /**
     * 添加多个Dpcoi用户
     * @param userIdStr 系统用户ID字符串
     * @param user 操作人
     * @throws ServiceException 异常
     */
    public void addDpcoiUsers(String userIdStr, User user) throws ServiceException;

    /**
     * 添加一个dpcoi用户
     * @param dpcoiUser Dpcoi用户数据
     * @return 返回结果
     * @throws ServiceException 异常信息
     */
    public Integer addDpcoiUser(DpcoiUser dpcoiUser) throws ServiceException;

    /**
     * 更新一个dpcoi用户
     * @param dpcoiUser Dpcoi用户数据
     * @return 返回结果
     * @throws ServiceException 异常信息
     */
    public Integer updateDpcoiUser(DpcoiUser dpcoiUser) throws ServiceException;

    /**
     * 获取dpcoi用户列表--分页
     * @param dpcoiUserQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryDpcoiUserListPage(DpcoiUserQuery dpcoiUserQuery) throws ServiceException;

    /**
     * 获取dpcoi用户数量
     * @param dpcoiUserQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public Integer queryDpcoiUserCount(DpcoiUserQuery dpcoiUserQuery) throws ServiceException;

    /**
     * 获取系统中没有加入Dpcoi的用户列表
     * @param userQuery 条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryNoDpcoiUserList(UserQuery userQuery) throws ServiceException;

    /**
     * 查询Dpcoi用户
     * @param dpcoiUserQuery 查询条件
     * @return  返回结果
     * @throws ServiceException  异常
     */
    public List<Map<String, Object>> queryDpcoiUserList(DpcoiUserQuery dpcoiUserQuery) throws ServiceException;

    /**
     * 生成dpcoi用户自动下拉框
     * @param dpcoiUserQuery 查询条件
     * @return  返回结果
     * @throws ServiceException  异常
     */
   public List<Map<String, Object>> queryAutocompleteDpcoiUserList(DpcoiUserQuery dpcoiUserQuery) throws ServiceException;

}
