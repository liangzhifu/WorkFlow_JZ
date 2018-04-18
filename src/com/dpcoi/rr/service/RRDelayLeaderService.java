package com.dpcoi.rr.service;

import com.dpcoi.rr.domain.RRDelayLeader;
import com.dpcoi.rr.query.RRDelayLeaderQuery;

import java.util.List;
import java.util.Map;

/**
 * Created by liangzhifu
 * DATE:2017/7/28.
 */
public interface RRDelayLeaderService {

    /**
     * 插入新的RR问题责任人领导
     * @param rrDelayLeader 实体
     * @return 返回结果
     */
    public Integer addRRDelayLeader(RRDelayLeader rrDelayLeader);

    /**
     * 更新RR问题责任人领导
     * @param rrDelayLeader 实体
     * @return 返回结果
     */
    public Integer updateRRDelayLeader(RRDelayLeader rrDelayLeader);

    /**
     * 查询RR问题责任人领导数量
     * @param rrDelayLeaderQuery 查询条件
     * @return 返回结果
     */
    public Integer quereyRRDelayLeaderCount(RRDelayLeaderQuery rrDelayLeaderQuery);

    /**
     * 查询RR问题责任人领导列表--分页
     * @param rrDelayLeaderQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> queryRRDelayLeaderPageList(RRDelayLeaderQuery rrDelayLeaderQuery);

    /**
     * 查询RR问题点4级延时的发邮件人
     * @param userNames 责任人
     * @return 返回结果
     */
    public String queryDelay4Email(String userNames);

    /**
     * 查询RR问题点4级延时的发邮件人
     * @param userNames 责任人
     * @return 返回结果
     */
    public String queryDelay3Email(String userNames);

    /**
     * 查询RR问题点4级延时的发邮件人
     * @param userNames 责任人
     * @return 返回结果
     */
    public String queryDelay2Email(String userNames);

}
