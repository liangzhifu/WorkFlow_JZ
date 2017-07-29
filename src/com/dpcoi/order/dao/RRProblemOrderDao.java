package com.dpcoi.order.dao;/**
 * Created by liangzhifu
 * DATE:2017/6/29.
 */

import com.dpcoi.order.query.RRProblemOrderQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-06-29
 **/
@Repository("rRProblemOrderDao")
public class RRProblemOrderDao extends BaseDao {

    /**
     * 查询RR问题点订单列表--分页
     * @param rrProblemOrderQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectRRProblemOrderListPage(RRProblemOrderQuery rrProblemOrderQuery){
        return this.sqlSession.selectList("RRProblemOrderMapper.selectRRProblemOrderListPage", rrProblemOrderQuery);
    }

    /**
     * 查询RR问题点订单数量
     * @param rrProblemOrderQuery 查询条件
     * @return 返回结果
     */
    public Integer selectRRProblemOrderCount(RRProblemOrderQuery rrProblemOrderQuery){
        return this.sqlSession.selectOne("RRProblemOrderMapper.selectRRProblemOrderCount", rrProblemOrderQuery);
    }

}
