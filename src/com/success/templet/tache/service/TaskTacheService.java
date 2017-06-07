package com.success.templet.tache.service;

import com.success.templet.tache.domain.TaskTache;
import com.success.templet.tache.query.TaskTacheQuery;
import com.success.web.framework.exception.ServiceException;

import java.util.List;

/**
 * Created by liangzhifu
 * DATE:2017/5/24.
 */
public interface TaskTacheService {

    /**
     * 查询定单所有工位列表
     * @param taskTacheQuery 定单ID
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<TaskTache> queryTaskTacheListOfOrder(TaskTacheQuery taskTacheQuery) throws ServiceException;
}
