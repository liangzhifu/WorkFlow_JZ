package com.dpcoi.statistics.service;

import com.dpcoi.statistics.domain.RRDelayStatistics;
import com.dpcoi.statistics.query.RRDelayStatisticsQuery;

import java.util.List;
import java.util.Map;

/**
 * Created by liangzhifu
 * DATE:2017/7/20.
 */
public interface RRDelayStatisticsService {

    /**
     * 增加新的RR问题点延时统计
     * @param rrDelayStatistics 延时信息
     * @return 返回结果
     */
    public Integer addRRDelayStatistics(RRDelayStatistics rrDelayStatistics);

    /**
     * 查询延时的总数
     * @param rrDelayStatisticsQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> queryStatisticsList(RRDelayStatisticsQuery rrDelayStatisticsQuery);

    /**
     * 查询延时统计，按照人员分组
     * @param rrDelayStatisticsQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> queryRRDelayStatisticsList(RRDelayStatisticsQuery rrDelayStatisticsQuery);

    /**
     * 查询延时列表
     * @param rrDelayStatisticsQuery 查询条件
     * @return 返回结果
     */
    public Integer queryStatisticsCount(RRDelayStatisticsQuery rrDelayStatisticsQuery);

}
