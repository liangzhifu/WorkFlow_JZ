package com.dpcoi.rr.dao;/**
 * Created by liangzhifu
 * DATE:2017/6/28.
 */

import com.dpcoi.rr.domain.RRProblem;
import com.dpcoi.rr.query.RRProblemQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-06-28
 **/
@Repository("rRProblemDao")
public class RRProblemDao extends BaseDao {

    /**
     * 插入RR问题点
     * @param rrProblem RR问题点
     * @return 返回结果
     */
    public Integer insertRRProblem(RRProblem rrProblem){
        return this.sqlSession.insert("RRProblemMapper.insertSelective", rrProblem);
    }

    /**
     * 更新RR问题点
     * @param rrProblem RR问题点
     * @return 返回结果
     */
    public Integer updateRRProblem(RRProblem rrProblem){
        return this.sqlSession.update("RRProblemMapper.updateByPrimaryKeySelective", rrProblem);
    }

    /**
     * 查询RR问题点
     * @param rrProblem RR问题点
     * @return 返回结果
     */
    public RRProblem selectRRProblem(RRProblem rrProblem){
        return this.sqlSession.selectOne("RRProblemMapper.selectByPrimaryKey", rrProblem);
    }

    /**
     * 查询RR问题点数量
     * @param rrProblemQuery 查询条件
     * @return 返回结果
     */
    public Integer selectRRProblemCount(RRProblemQuery rrProblemQuery){
        return this.sqlSession.selectOne("RRProblemMapper.selectRRProblemCount", rrProblemQuery);
    }

    /**
     * 查询RR问题点分页列表
     * @param rrProblemQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectRRProblemListPage(RRProblemQuery rrProblemQuery){
        return this.sqlSession.selectList("RRProblemMapper.selectRRProblemListPage", rrProblemQuery);
    }

    /**
     * 查询RR问题点屏幕显示列表
     * @return 返回结果
     */
    public List<Map<String, Object>> selectRRProblemScreenShowList(){
        return this.sqlSession.selectList("RRProblemMapper.selectRRProblemScreenShowList", null);
    }

    /**
     * 查询RR问题点列表
     * @param rrProblemQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectRRProblemList(RRProblemQuery rrProblemQuery){
        return this.sqlSession.selectList("RRProblemMapper.selectRRProblemList", rrProblemQuery);
    }

    /**
     * 查询RR问题点列表
     * @return 返回结果
     */
    public List<RRProblem> selectJobRRProblemList(){
        return this.sqlSession.selectList("RRProblemMapper.selectJobRRProblemList", null);
    }

    /**
     * 查询责任人列表
     * @return 返回结果
     */
    public List<Map<String, Object>> selectPersionLiableList(){
        return this.sqlSession.selectList("user.selectPersionLiableList", null);
    }

    /**
     * 查询问题编号最大值
     * @param problemNo 编号
     * @return 返回结果
     */
    public Integer selectProblemNoMax(String problemNo){
        return this.sqlSession.selectOne("RRProblemMapper.selectProblemNoMax", problemNo);
    }

    /**
     * 查询RR问题点延期的邮件
     * @param rrProblem RR问题点
     * @return 返回结果
     */
    public String selectDelayEmails(RRProblem rrProblem) {
        return this.sqlSession.selectOne("RRProblemMapper.selectDelayEmails", rrProblem);
    }

    /**
     * 回滚RR问题点
     * @param map 条件
     * @return 返回结果
     */
    public Integer updateRollBackRRProblem(Map<String, Object> map) {
        return this.sqlSession.update("RRProblemMapper.updateRollBackRRProblem", map);
    }

    /**
     * 查询1/5的RR问题点列表
     * @return 返回结果
     */
    public List<RRProblem> selectJobRRProblemTrackingLevelList() {
        return this.sqlSession.selectList("RRProblemMapper.selectJobRRProblemTrackingLevelList", null);
    }
}
