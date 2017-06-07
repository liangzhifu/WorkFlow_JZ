package com.dpcoi.sys.service.serviceImpl;

import com.dpcoi.sys.dao.DpcoiUserDao;
import com.dpcoi.sys.domain.DpcoiUser;
import com.dpcoi.sys.query.DpcoiUserQuery;
import com.dpcoi.sys.service.DpcoiUserService;
import com.success.sys.user.domain.User;
import com.success.sys.user.query.UserQuery;
import com.success.web.framework.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 梁志福 on 2017/4/20.
 */
@Service("dpcoiUserService")
public class DpcoiUserServiceImpl implements DpcoiUserService{

    @Resource(name="dpcoiUserDao")
    private DpcoiUserDao dpcoiUserDao;

    @Override
    public void addDpcoiUsers(String userIdStr, User user) throws ServiceException {
        String[] userIds = userIdStr.split(",");
        for (String userId : userIds){
            DpcoiUser dpcoiUse = new DpcoiUser();
            dpcoiUse.setSysUserId(Integer.valueOf(userId));
            dpcoiUse.setDpcoiUserState("1");
            dpcoiUse.setDelFlag("0");
            dpcoiUse.setCreateDate(new Date());
            dpcoiUse.setCreateBy(user.getUserId());
            dpcoiUse.setUpdateDate(new Date());
            dpcoiUse.setUpdateBy(user.getUserId());
            this.addDpcoiUser(dpcoiUse);
        }
    }

    @Override
    public Integer addDpcoiUser(DpcoiUser dpcoiUser) throws ServiceException {
        return this.dpcoiUserDao.insertDpcoiUser(dpcoiUser);
    }

    @Override
    public Integer updateDpcoiUser(DpcoiUser dpcoiUser) throws ServiceException {
        return this.dpcoiUserDao.updateDpcoiUser(dpcoiUser);
    }

    @Override
    public List<Map<String, Object>> queryDpcoiUserListPage(DpcoiUserQuery dpcoiUserQuery) throws ServiceException {
        return this.dpcoiUserDao.selectDpcoiUserListPage(dpcoiUserQuery);
    }

    @Override
    public Integer queryDpcoiUserCount(DpcoiUserQuery dpcoiUserQuery) throws ServiceException {
        return this.dpcoiUserDao.selectDpcoiUserCount(dpcoiUserQuery);
    }

    @Override
    public List<Map<String, Object>> queryNoDpcoiUserList(UserQuery userQuery) throws ServiceException {
        return this.dpcoiUserDao.selectNoDpcoiUserList(userQuery);
    }

    @Override
    public List<Map<String, Object>> queryDpcoiUserList(DpcoiUserQuery dpcoiUserQuery) throws ServiceException {
        return this.dpcoiUserDao.selectDpcoiUserList(dpcoiUserQuery);
    }

    @Override
    public List<Map<String, Object>> queryAutocompleteDpcoiUserList(DpcoiUserQuery dpcoiUserQuery) throws ServiceException {
        return this.dpcoiUserDao.selectAutocompleteDpcoiUserList(dpcoiUserQuery);
    }

}
