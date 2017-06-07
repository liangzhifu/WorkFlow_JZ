package com.success.task.base.dao;/**
 * Created by liangzhifu
 * DATE:2017/5/8.
 */

import com.success.task.base.domain.TaskWoOrderSysInfo;
import com.success.task.detail.domain.TaskWoOrder;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-05-08
 **/
@Repository
public class TaskWoOrderSysInfoDao extends BaseDao {

    /**
     * 增加工单sysInfo
     * @param taskWoOrderSysInfo 实体
     * @return 返回结果
     * @throws DaoException 异常
     */
    public Integer insertTaskWoOrderSysInfo(TaskWoOrderSysInfo taskWoOrderSysInfo) throws DaoException {
        return this.sqlSession.insert("taskWoOrderSysInfo.insertTaskWoOrderSysInfo", taskWoOrderSysInfo);
    }

    /**
     * 删除工单的所有sysInfo信息
     * @param taskWoOrder 工单
     * @return 返回结果
     * @throws DaoException 异常
     */
    public Integer deleteTaskWoOrderSysInfo(TaskWoOrder taskWoOrder) throws DaoException{
        return this.sqlSession.delete("taskWoOrderSysInfo.deleteTaskWoOrderSysInfo", taskWoOrder);
    }

    public List<Map<String, Object>> selectDpcoiWoOrder(TaskWoOrder taskWoOrder){
        return this.sqlSession.selectList("taskWoOrderSysInfo.selectDpcoiWoOrder", taskWoOrder);
    }

}
