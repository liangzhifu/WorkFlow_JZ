package com.dpcoi.statistics.dao;/**
 * Created by liangzhifu
 * DATE:2017/5/18.
 */

import com.dpcoi.statistics.query.DpcoiStatisticsQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-05-18
 **/
@Repository
public class DpcoiStatisticsDao extends BaseDao {

    /**
     * 查询Dpcoi定单数量
     * @param dpcoiStatisticsQuery 查询条件
     * @return 返回结果
     */
    public Integer selectDpcoiOrderCount(DpcoiStatisticsQuery dpcoiStatisticsQuery) {
        return this.sqlSession.selectOne("dpcoiStatics.selectDpcoiOrderCount", dpcoiStatisticsQuery);
    }

    /**
     * 查询Dpcoi工单数量
     * @param dpcoiStatisticsQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectDpcoiWoOrderCount(DpcoiStatisticsQuery dpcoiStatisticsQuery){
        return this.sqlSession.selectList("dpcoiStatics.selectDpcoiWoOrderCount",  dpcoiStatisticsQuery);
    }

    /**
     * 查询完工超时列表
     * @param dpcoiStatisticsQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectWoOrderCompleteDelayList(DpcoiStatisticsQuery dpcoiStatisticsQuery){
        return this.sqlSession.selectList("dpcoiStatics.selectWoOrderCompleteDelayList",  dpcoiStatisticsQuery);
    }

    /**
     * 查询未完工列表
     * @param dpcoiStatisticsQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>>selectWoOrderNoCompleteList(DpcoiStatisticsQuery dpcoiStatisticsQuery){
        return this.sqlSession.selectList("dpcoiStatics.selectWoOrderNoCompleteList",  dpcoiStatisticsQuery);
    }
}
