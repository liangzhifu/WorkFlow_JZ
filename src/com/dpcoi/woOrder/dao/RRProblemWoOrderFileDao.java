package com.dpcoi.woOrder.dao;/**
 * Created by liangzhifu
 * DATE:2017/7/4.
 */

import com.dpcoi.woOrder.query.RRProblemWoOrderFileQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-07-04
 **/
@Repository("rRProblemWoOrderFileDao")
public class RRProblemWoOrderFileDao extends BaseDao {

    /**
     * 查询RR问题点列表--分页
     * @param rrProblemWoOrderFileQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectRRProblemWoOrderFileListPage(RRProblemWoOrderFileQuery rrProblemWoOrderFileQuery){
        return this.sqlSession.selectList("RRProblemWoOrderFileMapper.selectRRProblemWoOrderFileListPage", rrProblemWoOrderFileQuery);
    }

    /**
     * 查询RR问题点数量
     * @param rrProblemWoOrderFileQuery 查询条件
     * @return 返回结果
     */
    public Integer selectRRProblemWoOrderFileCount(RRProblemWoOrderFileQuery rrProblemWoOrderFileQuery){
        return this.sqlSession.selectOne("RRProblemWoOrderFileMapper.selectRRProblemWoOrderFileCount", rrProblemWoOrderFileQuery);
    }
}
