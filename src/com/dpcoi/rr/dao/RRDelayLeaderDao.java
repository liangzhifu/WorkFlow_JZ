package com.dpcoi.rr.dao;/**
 * Created by liangzhifu
 * DATE:2017/7/28.
 */

import com.dpcoi.rr.domain.RRDelayLeader;
import com.dpcoi.rr.query.RRDelayLeaderQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-07-28
 **/
@Repository("rRDelayLeaderDao")
public class RRDelayLeaderDao extends BaseDao {

    /**
     * 插入新的RR问题责任人领导
     * @param rrDelayLeader 实体
     * @return 返回结果
     */
    public Integer insertRRDelayLeader(RRDelayLeader rrDelayLeader){
        return this.sqlSession.insert("RRDelayLeaderMapper.insertSelective", rrDelayLeader);
    }

    /**
     * 更新RR问题责任人领导
     * @param rrDelayLeader 实体
     * @return 返回结果
     */
    public Integer updateRRDelayLeader(RRDelayLeader rrDelayLeader){
        return this.sqlSession.update("RRDelayLeaderMapper.updateByPrimaryKeySelective", rrDelayLeader);
    }

    /**
     * 查询RR问题责任人列表数量
     * @param rrDelayLeaderQuery 查询条件
     * @return 返回结果
     */
    public Integer selectRRDelayLeaderCount(RRDelayLeaderQuery rrDelayLeaderQuery){
        return this.sqlSession.selectOne("RRDelayLeaderMapper.selectRRDelayLeaderCount", rrDelayLeaderQuery);
    }

    /**
     * 查询RR问题责任人列表--分页
     * @param rrDelayLeaderQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectRRDelayLeaderPageList(RRDelayLeaderQuery rrDelayLeaderQuery){
        return this.sqlSession.selectList("RRDelayLeaderMapper.selectRRDelayLeaderPageList", rrDelayLeaderQuery);
    }

    /**
     * 查询RR问题点4级延时的发邮件人
     * @param userNames 责任人
     * @return 返回结果
     */
    public String selectDelay4Email(String userNames){
        return this.sqlSession.selectOne("RRDelayLeaderMapper.selectDelay4Email", userNames);
    }

    /**
     * 查询RR问题点3级延时的发邮件人
     * @param userNames 责任人
     * @return 返回结果
     */
    public String selectDelay3Email(String userNames){
        return this.sqlSession.selectOne("RRDelayLeaderMapper.selectDelay3Email", userNames);
    }

    /**
     * 查询RR问题点2级延时的发邮件人
     * @param userNames 责任人
     * @return 返回结果
     */
    public String selectDelay2Email(String userNames){
        return this.sqlSession.selectOne("RRDelayLeaderMapper.selectDelay2Email", userNames);
    }

    /**
     * 查询部长邮箱
     * @return 返回结果
     */
    public String selectMinisteEmail(){
        return this.sqlSession.selectOne("RRDelayLeaderMapper.selectMinisteEmail");
    }

    /**
     * RR问题点新增时，需要发邮件人员
     * @param userId 用户ID
     * @param userNames 责任人名字（多个）
     * @return 返回结果
     */
    public String selectAddRREmail(Integer userId, String userNames){
        Map<String, Object> map = new HashedMap();
        map.put("userId", userId);
        map.put("userNames", userNames);
        return this.sqlSession.selectOne("RRDelayLeaderMapper.selectAddRREmail", map);
    }
}
