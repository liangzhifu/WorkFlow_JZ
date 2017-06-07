package com.success.templet.tache.service.serviceImpl;/**
 * Created by liangzhifu
 * DATE:2017/5/24.
 */

import com.success.templet.tache.dao.TaskTacheDao;
import com.success.templet.tache.domain.TaskTache;
import com.success.templet.tache.query.TaskTacheQuery;
import com.success.templet.tache.service.TaskTacheService;
import com.success.web.framework.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author lzf
 * @create 2017-05-24
 **/
@Service("taskTacheService")
public class TaskTacheServiceImpl implements TaskTacheService{

    @Resource(name = "taskTacheDao")
    private TaskTacheDao taskTacheDao;

    @Override
    public List<TaskTache> queryTaskTacheListOfOrder(TaskTacheQuery taskTacheQuery) throws ServiceException {
        return this.taskTacheDao.selectTaskTacheListOfOrder(taskTacheQuery);
    }
}
