package com.dpcoi.woOrder.dao;/**
 * Created by liangzhifu
 * DATE:2017/6/30.
 */

import com.dpcoi.woOrder.query.RRProblemWoOrderQuery;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-06-30
 **/
@Repository("rRProblemWoOrderDao")
public class RRProblemWoOrderDao extends BaseDao {

    /**
     * 查询RR工单了列表--分页
     * @param rrProblemWoOrderQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectRRProblemWoOrderListPage(RRProblemWoOrderQuery rrProblemWoOrderQuery) {
        return this.sqlSession.selectList("RRProblemWoOrderMapper.selectRRProblemWoOrderListPage", rrProblemWoOrderQuery);
    }

    /**
     * 查询RR工单数量
     * @param rrProblemWoOrderQuery 查询条件
     * @return 返回结果
     */
    public Integer selectRRProblemWoOrderCount(RRProblemWoOrderQuery rrProblemWoOrderQuery) {
        return this.sqlSession.selectOne("RRProblemWoOrderMapper.selectRRProblemWoOrderCount", rrProblemWoOrderQuery);
    }
}
