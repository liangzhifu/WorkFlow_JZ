package com.dpcoi.sys.dao;

import com.dpcoi.sys.domain.DpcoiUser;
import com.dpcoi.sys.domain.DpcoiUserRole;
import com.dpcoi.sys.query.DpcoiUserRoleQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 梁志福 on 2017/4/20.
 */
@Repository("dpcoiUserRoleDao")
public class DpcoiUserRoleDao extends BaseDao {

    /**
     * 查询dpcoi用户权限
     * @param dpcoiUserRoleQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectDpcoiRoleList(DpcoiUserRoleQuery dpcoiUserRoleQuery){
        return this.sqlSession.selectList("dpcoiUserRole.selectDpcoiRoleList", dpcoiUserRoleQuery);
    }
    /**
     * 插入一个DpcoiUserRole权限
     * @param dpcoiUserRole 用户数据
     * @return 1成功，0不成功
     */
    public Integer insertDpcoiUserRole(DpcoiUserRole dpcoiUserRole){
        return this.sqlSession.insert("dpcoiUserRole.insertSelective", dpcoiUserRole);
    }

    /**
     * 更新DpcoiUserRole权限
     * @param dpcoiUserRole 用户数据
     * @return 返回结果
     */
    public Integer updateDpcoiUserRole(DpcoiUserRole dpcoiUserRole){
        return this.sqlSession.update("dpcoiUserRole.updateSelective", dpcoiUserRole);
    }
}
