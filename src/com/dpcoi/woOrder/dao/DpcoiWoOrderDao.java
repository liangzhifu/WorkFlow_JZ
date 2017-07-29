package com.dpcoi.woOrder.dao;

import com.dpcoi.woOrder.domain.DpcoiWoOrder;
import com.dpcoi.woOrder.query.DpcoiWoOrderQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 梁志福 on 2017/4/20.
 */
@Repository
public class DpcoiWoOrderDao extends BaseDao {
    /**
     * 插入一个DpcoiWoWoOrder订单
     * @param dpcoiWoOrder 订单数据
     * @return 1成功，0不成功
     */
    public Integer insertDpcoiWoOrder(DpcoiWoOrder dpcoiWoOrder){
        return this.sqlSession.insert("dpcoiWoOrder.insertSelective", dpcoiWoOrder);
    }

    /**
     * 更新一个DpcoiWoWoOrder订单
     * @param dpcoiWoOrder 用订单数据
     * @return 1成功，0不成功
     */
    public Integer updateDpcoiWoOrder(DpcoiWoOrder dpcoiWoOrder){
        return this.sqlSession.update("dpcoiWoOrder.updateSelective", dpcoiWoOrder);
    }

    /**
     * 获取dpcoiWoOrder订单数量
     * @param dpcoiWoOrderQuery 查询条件
     * @return 返回结果
     */
    public Integer selectDpcoiWoOrderCount(DpcoiWoOrderQuery dpcoiWoOrderQuery){
        return this.sqlSession.selectOne("dpcoiWoOrder.selectDpcoiWoOrderCount", dpcoiWoOrderQuery);
    }

    /**
     * 获取dpcoiWoOrder订单列表--分页
     * @param dpcoiWoOrderQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectDpcoiWoOrderListPage(DpcoiWoOrderQuery dpcoiWoOrderQuery){
        return this.sqlSession.selectList("dpcoiWoOrder.selectDpcoiWoOrderListPage", dpcoiWoOrderQuery);
    }

    /**
     * 获取dpcoiWoOrder订单列表
     * @param dpcoiWoOrderQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectDpcoiWoOrderList(DpcoiWoOrderQuery dpcoiWoOrderQuery){
        return this.sqlSession.selectList("dpcoiWoOrder.selectDpcoiWoOrderList", dpcoiWoOrderQuery);
    }

    /**
     * 根据dpcoiWoOrderId查询订单实体
     * @param dpcoiWoOrder 订单ID
     * @return 返回结果
     */
    public DpcoiWoOrder selectBySelf(DpcoiWoOrder dpcoiWoOrder){
        return this.sqlSession.selectOne("dpcoiWoOrder.selectBySelf", dpcoiWoOrder);
    }

    /**
     * 查询DpcoiOrder对应的所有DpcoiWoOrder
     * @param dpcoiWoOrderQuery 查询条件
     * @return 返回结果
     */
    public List<DpcoiWoOrder> selectDpcoiWoOrderOfDpcoiOrder(DpcoiWoOrderQuery dpcoiWoOrderQuery){
        return this.sqlSession.selectList("dpcoiWoOrder.selectDpcoiWoOrderOfDpcoiOrder", dpcoiWoOrderQuery);
    }

    /**
     * 查询工单需要发邮件的人员（代办人员）
     * @param dpcoiWoOrderQuery 工单实体
     * @return 返回结果
     */
    public String selectWoOrderEmailUsers(DpcoiWoOrderQuery dpcoiWoOrderQuery){
        return this.sqlSession.selectOne("dpcoiWoOrder.selectWoOrderEmailUsers", dpcoiWoOrderQuery);
    }

    /**
     * 查询工单需要发邮件的人员（所有人员）
     * @param dpcoiWoOrder 工单实体
     * @return 返回结果
     */
    public String selectWoOrderAllEmailUsers(DpcoiWoOrder dpcoiWoOrder){
        return this.sqlSession.selectOne("dpcoiWoOrder.selectWoOrderAllEmailUsers", dpcoiWoOrder);
    }

    /**
     * 查询待确认超时工单
     * @return 返回结果
     */
    public List<Map<String, Object>> selectConfirmDelayWoOrderList(){
        return this.sqlSession.selectList("dpcoiWoOrder.selectConfirmDelayWoOrderList");
    }

    /**
     * 查询变更超时工单
     * @return 返回结果
     */
    public List<Map<String, Object>> selectChangeDelayWoOrderList(){
        return this.sqlSession.selectList("dpcoiWoOrder.selectChangeDelayWoOrderList");
    }

    public List<Map<String, Object>> selectDpcoiWoOrder4MConfrim(DpcoiWoOrder dpcoiWoOrder){
        return this.sqlSession.selectList("dpcoiWoOrder.selectDpcoiWoOrder4MConfrim", dpcoiWoOrder);
    }

    /**
     * 查看日期是否节假日
     * @param date 日期
     * @return 返回结果
     */
    public Integer selectHoliday(Date date){
        return this.sqlSession.selectOne("dpcoiWoOrder.selectHoliday", date);
    }

}
