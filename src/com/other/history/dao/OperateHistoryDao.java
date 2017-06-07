package com.other.history.dao;/**
 * Created by liangzhifu
 * DATE:2017/5/12.
 */

import com.other.history.domain.OperateHistory;
import com.other.history.query.OperateHistoryQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-05-12
 **/
@Repository
public class OperateHistoryDao extends BaseDao{

    /**
     * 插入一个operateHistory操作记录
     * @param operateHistory 操作记录数据
     * @return 1成功，0不成功
     */
    public Integer insertOperateHistory(OperateHistory operateHistory){
        return this.sqlSession.insert("operateHistory.insertSelective", operateHistory);
    }

    /**
     * 获取操作记录列表
     * @param operateHistoryQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectOperateHistoryList(OperateHistoryQuery operateHistoryQuery){
        return this.sqlSession.selectList("operateHistory.selectOperateHistoryList", operateHistoryQuery);
    }
}
