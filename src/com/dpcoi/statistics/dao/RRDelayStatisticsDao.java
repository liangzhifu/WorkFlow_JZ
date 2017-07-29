package com.dpcoi.statistics.dao;/**
 * Created by liangzhifu
 * DATE:2017/7/20.
 */

import com.dpcoi.statistics.domain.RRDelayStatistics;
import com.dpcoi.statistics.query.RRDelayStatisticsQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-07-20
 **/
@Repository("rRDelayStatisticsDao")
public class RRDelayStatisticsDao extends BaseDao{

    /**
     * 插入RR统计数据
     * @param rrDelayStatistics RR统计数据
     * @return 返回结果
     */
    public Integer insertRRDelayStatistics(RRDelayStatistics rrDelayStatistics){
        return this.sqlSession.insert("RRDelayStatisticsMapper.insertSelective", rrDelayStatistics);
    }

    /**
     * 获取统计数据
     * @param rrDelayStatisticsQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectStatisticsList(RRDelayStatisticsQuery rrDelayStatisticsQuery){
        return this.sqlSession.selectList("RRDelayStatisticsMapper.selectStatisticsList", rrDelayStatisticsQuery);
    }

    /**
     * 获取RR统计数据列表
     * @param rrDelayStatisticsQuery 查询条件
     * @return 返回结构
     */
    public List<Map<String, Object>> selectRRDelayStatisticsList(RRDelayStatisticsQuery rrDelayStatisticsQuery){
        return this.sqlSession.selectList("RRDelayStatisticsMapper.selectRRDelayStatisticsList", rrDelayStatisticsQuery);
    }

    /**
     * 获取统计的总数
     * @param rrDelayStatisticsQuery 查询条件
     * @return 返回结果
     */
    public Integer selectStatisticsCount(RRDelayStatisticsQuery rrDelayStatisticsQuery){
        return this.sqlSession.selectOne("RRDelayStatisticsMapper.selectStatisticsCount", rrDelayStatisticsQuery);
    }

}
