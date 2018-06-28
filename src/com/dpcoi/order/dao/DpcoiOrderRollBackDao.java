package com.dpcoi.order.dao;

import com.dpcoi.order.query.DpcoiOrderRollBackQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lzf
 * @create 2017-10-23
 **/
@Repository("dpcoiOrderRollBackDao")
public class DpcoiOrderRollBackDao extends BaseDao {

    /**
     *查询可以回滚的DPCOI列表
     * @param dpcoiOrderRollBackQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectDpcoiOrderRollBackllListPage(DpcoiOrderRollBackQuery dpcoiOrderRollBackQuery){
        return this.sqlSession.selectList("DpcoiOrderRollBackMapper.selectDpcoiOrderRollBackllListPage", dpcoiOrderRollBackQuery);
    }

    /**
     *查询可以回滚的DPCOI数量
     * @param dpcoiOrderRollBackQuery 查询条件
     * @return 返回结果
     */
    public Integer selectDpcoiOrderRollBackCount(DpcoiOrderRollBackQuery dpcoiOrderRollBackQuery){
        return this.sqlSession.selectOne("DpcoiOrderRollBackMapper.selectDpcoiOrderRollBackCount", dpcoiOrderRollBackQuery);
    }

    /**
     *查询可以回滚的RR问题点的DPCOI列表
     * @param dpcoiOrderRollBackQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectRRProblemOrderRollBackllListPage(DpcoiOrderRollBackQuery dpcoiOrderRollBackQuery){
        return this.sqlSession.selectList("DpcoiOrderRollBackMapper.selectRRProblemOrderRollBackllListPage", dpcoiOrderRollBackQuery);
    }

    /**
     *查询可以回滚的RR问题点的DPCOI数量
     * @param dpcoiOrderRollBackQuery 查询条件
     * @return 返回结果
     */
    public Integer selectRRProblemOrderRollBackCount(DpcoiOrderRollBackQuery dpcoiOrderRollBackQuery){
        return this.sqlSession.selectOne("DpcoiOrderRollBackMapper.selectRRProblemOrderRollBackCount", dpcoiOrderRollBackQuery);
    }

    /**
     * 删除需要回滚的文件
     * @param map 条件
     * @return 返回结果
     */
    public Integer deleteDpcoiRollBackWoOrderFile(Map<String, Object> map){
        return this.sqlSession.delete("DpcoiOrderRollBackMapper.deleteDpcoiRollBackWoOrderFile", map);
    }

    /**
     * 删除需要回滚的工单
     * @param map 条件
     * @return 返回结果
     */
    public Integer deleteDpcoiRollBackWoOrder(Map<String, Object> map){
        return this.sqlSession.delete("DpcoiOrderRollBackMapper.deleteDpcoiRollBackWoOrder", map);
    }

    /**
     * 更新需要回滚到工单状态为“待确认"
     * @param map 条件
     * @return 返回结果
     */
    public Integer updateDpcoiWoOrder(Map<String, Object> map){
        return this.sqlSession.update("DpcoiOrderRollBackMapper.updateDpcoiWoOrder", map);
    }

}
