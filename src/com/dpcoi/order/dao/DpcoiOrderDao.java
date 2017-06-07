package com.dpcoi.order.dao;

import com.dpcoi.order.domain.DpcoiOrder;
import com.dpcoi.order.query.DpcoiOrderQuery;
import com.success.sys.user.domain.User;
import com.success.task.detail.domain.TaskOrder;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 梁志福 on 2017/4/20.
 */
@Repository
public class DpcoiOrderDao extends BaseDao {
    /**
     * 插入一个DpcoiOder订单
     * @param dpcoiOrder 订单数据
     * @return 1成功，0不成功
     */
    public Integer insertDpcoiOrder(DpcoiOrder dpcoiOrder){
        return this.sqlSession.insert("dpcoiOrder.insertSelective", dpcoiOrder);
    }

    /**
     * 更新一个DpcoiOder订单
     * @param dpcoiOrder 用订单数据
     * @return 1成功，0不成功
     */
    public Integer updateDpcoiOrder(DpcoiOrder dpcoiOrder){
        return this.sqlSession.update("dpcoiOrder.updateSelective", dpcoiOrder);
    }

    /**
     * 获取dpcoiOrder订单数量
     * @param dpcoiOrderQuery 查询条件
     * @return 返回结果
     */
    public Integer selectDpcoiOrderCount(DpcoiOrderQuery dpcoiOrderQuery){
        return this.sqlSession.selectOne("dpcoiOrder.selectDpcoiOrderCount", dpcoiOrderQuery);
    }

    /**
     * 获取dpcoiOrder订单列表--分页
     * @param dpcoiOrderQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectDpcoiOrderListPage(DpcoiOrderQuery dpcoiOrderQuery){
        return this.sqlSession.selectList("dpcoiOrder.selectDpcoiOrderListPage", dpcoiOrderQuery);
    }

    /**
     * 根据dpcoiOrderId查询订单实体
     * @param dpcoiOrder 订单ID
     * @return 返回结果
     */
    public DpcoiOrder selectBySelf(DpcoiOrder dpcoiOrder){
        return this.sqlSession.selectOne("dpcoiOrder.selectBySelf", dpcoiOrder);
    }

    /**
     * 查询定单的工单详情
     * @param dpcoiOrder 定单实体
     * @return 返回结果
     */
    public List<Map<String, Object>> seletDpcoiWoOrderDetailList(DpcoiOrder dpcoiOrder){
        return this.sqlSession.selectList("dpcoiOrder.selectDpcoiOrderDetailList", dpcoiOrder);
    }

    /**
     * 通过4M定单获取dpcoi定单
     * @param taskOrder 4M定单实体
     * @return 返回结果
     */
    public DpcoiOrder selectDpcoiOrderOfTaskOrder(TaskOrder taskOrder) {
        return this.sqlSession.selectOne("dpcoiOrder.selectDpcoiOrderOfTaskOrder", taskOrder);
    }

    /**
     *查找有相同《设变通知书》编号或者设变号定单
     * @param dpcoiOrderQuery 查询条件
     * @return 返回结果
     */
    public Integer selectSameDpcoiOrder(DpcoiOrderQuery dpcoiOrderQuery){
        return this.sqlSession.selectOne("dpcoiOrder.selectSameDpcoiOrder", dpcoiOrderQuery);
    }

    /**
     * 查询作废DPCOI定单需要发送人员的信息
     * @param dpcoiOrder 定单ID
     * @return 返回结果
     */
    public String selectOrderToVodiEmailUsers(DpcoiOrder dpcoiOrder){
        return this.sqlSession.selectOne("dpcoiOrder.selectOrderToVodiEmailUsers", dpcoiOrder);
    }

    /**
     * 查询用的DPCOI增加权限
     * @param user 用户
     * @return 返回结果
     */
    public Integer selectDpcoiAddJurisdiction(User user){
        return this.sqlSession.selectOne("dpcoiOrder.selectDpcoiAddJurisdiction", user);
    }
}
