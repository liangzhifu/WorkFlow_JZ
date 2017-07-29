package com.dpcoi.rr.service;

import com.dpcoi.rr.domain.RRProblem;
import com.dpcoi.rr.query.RRProblemQuery;
import com.success.sys.user.domain.User;
import com.success.web.framework.exception.ServiceException;

import javax.security.sasl.SaslServer;
import java.util.List;
import java.util.Map;

/**
 * Created by liangzhifu
 * DATE:2017/6/28.
 */
public interface RRProblemService {

    /**
     * 添加
     * @param rrProblem 参数
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public Integer addRRProblem(RRProblem rrProblem) throws Exception;

    /**
     * 更新
     * @param rrProblem 参数
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public Integer updateRRProblem(RRProblem rrProblem) throws ServiceException;

    /**
     * 查询分页列表
     * @param rrProblemQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryRRProblemPageList(RRProblemQuery rrProblemQuery) throws ServiceException;

    /**
     * 查询总数
     * @param rrProblemQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public Integer queryRRProblemCount(RRProblemQuery rrProblemQuery) throws ServiceException;

    /**
     * 获取发生日期的范围
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public Map<String, Object> getHappenDateRandom() throws ServiceException;

    /**
     * 查询责任人
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryPersionLiableList() throws ServiceException;

    /**
     * 查询RR问题点
     * @param rrProblem RR问题点
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public RRProblem queryRRProblem(RRProblem rrProblem) throws ServiceException;

    /**
     * 查询部长权限
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public Integer queryMinisterJurisdiction(User user) throws ServiceException;

    /**
     * 查询RR问题（正在处理）列表
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<RRProblem> queryJobRRProblemList() throws ServiceException;

    /**
     * 更新RR问题进度
     * @param rrProblem RR问题
     * @throws ServiceException 异常
     */
    public void updateSpeedOfProgress(RRProblem rrProblem) throws Exception;

    /**
     * 获取RR问题点延期的邮件
     * @param rrProblem RR问题点
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public String queryDelayEmails(RRProblem rrProblem) throws ServiceException;

    /**
     * 查询屏幕显示列表
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryRRProblemScreenShowList() throws ServiceException;
}
