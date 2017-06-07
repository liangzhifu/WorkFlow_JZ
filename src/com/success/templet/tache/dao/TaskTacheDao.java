package com.success.templet.tache.dao;

import com.success.templet.tache.domain.TaskTache;
import com.success.templet.tache.query.TaskTacheQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("taskTacheDao")
public class TaskTacheDao extends BaseDao {

    /**
     * 获取定单的工位列表
     * @param taskTacheQuery 查询条件 定单ID
     * @return 返回结果
     */
    public List<TaskTache> selectTaskTacheListOfOrder(TaskTacheQuery taskTacheQuery){
        return this.sqlSession.selectList("TaskTacheMapper.selectTaskTacheListOfOrder", taskTacheQuery);
    }

}
