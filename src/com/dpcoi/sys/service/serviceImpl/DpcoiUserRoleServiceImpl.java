package com.dpcoi.sys.service.serviceImpl;/**
 * Created by liangzhifu
 * DATE:2017/5/3.
 */

import com.dpcoi.sys.dao.DpcoiUserRoleDao;
import com.dpcoi.sys.domain.DpcoiUserRole;
import com.dpcoi.sys.query.DpcoiUserRoleQuery;
import com.dpcoi.sys.service.DpcoiUserRoleService;
import com.success.sys.user.domain.User;
import com.success.web.framework.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-05-03
 **/
@Service("dpcoiUserRoleService")
public class DpcoiUserRoleServiceImpl implements DpcoiUserRoleService {

    @Resource(name="dpcoiUserRoleDao")
    private DpcoiUserRoleDao dpcoiUserRoleDao;

    @Override
    public List<Map<String, Object>> queryDpcoiRoleList(DpcoiUserRoleQuery dpcoiUserRoleQuery) throws ServiceException {
        return this.dpcoiUserRoleDao.selectDpcoiRoleList(dpcoiUserRoleQuery);
    }

    @Override
    public void addDpcoiUserRoles(String dpcoiRoleIdStr, String dpcoiUserId, User user) throws ServiceException {
        DpcoiUserRole dpcoiUseRole = new DpcoiUserRole();
        dpcoiUseRole.setDpcoiUserId(Integer.valueOf(dpcoiUserId));
        dpcoiUseRole.setUpdateBy(user.getUserId());
        dpcoiUseRole.setUpdateDate(new Date());
        dpcoiUseRole.setDelFlag("1");
        this.updateDpcoiUserRole(dpcoiUseRole);
        String[] dpcoiRoleIds = dpcoiRoleIdStr.split(",");
        for (String dpcoiRoleId : dpcoiRoleIds){
            dpcoiUseRole = new DpcoiUserRole();
            dpcoiUseRole.setDpcoiUserId(Integer.valueOf(dpcoiUserId));
            dpcoiUseRole.setDpcoiRoleId(Integer.valueOf(dpcoiRoleId));
            dpcoiUseRole.setDelFlag("0");
            dpcoiUseRole.setCreateBy(user.getUserId());
            dpcoiUseRole.setCreateDate(new Date());
            dpcoiUseRole.setUpdateBy(user.getUserId());
            dpcoiUseRole.setUpdateDate(new Date());
            this.addDpcoiUserRole(dpcoiUseRole);
        }
    }

    @Override
    public void addDpcoiUserRole(DpcoiUserRole dpcoiUserRole) throws ServiceException {
        this.dpcoiUserRoleDao.insertDpcoiUserRole(dpcoiUserRole);
    }

    @Override
    public Integer updateDpcoiUserRole(DpcoiUserRole dpcoiUserRole) throws ServiceException {
        return this.dpcoiUserRoleDao.updateDpcoiUserRole(dpcoiUserRole);
    }
}
