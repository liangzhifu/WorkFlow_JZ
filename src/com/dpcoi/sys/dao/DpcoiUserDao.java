package com.dpcoi.sys.dao;

import com.dpcoi.sys.domain.DpcoiUser;
import com.dpcoi.sys.query.DpcoiUserQuery;
import com.dpcoi.sys.query.DpcoiUserRoleQuery;
import com.success.sys.user.query.UserQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 梁志福 on 2017/4/20.
 */
@Repository("dpcoiUserDao")
public class DpcoiUserDao extends BaseDao{

    /**
     * 插入一个Dpcoi用户
     * @param dpcoiUser 用户数据
     * @return 1成功，0不成功
     */
    public Integer insertDpcoiUser(DpcoiUser dpcoiUser){
        return this.sqlSession.insert("dpcoiUser.insertSelective", dpcoiUser);
    }

    /**
     * 更新一个Dpcoi用户
     * @param dpcoiUser 用户数据
     * @return 1成功，0不成功
     */
    public Integer updateDpcoiUser(DpcoiUser dpcoiUser){
        return this.sqlSession.update("dpcoiUser.updateSelective", dpcoiUser);
    }

    /**
     * 获取dpcoi用户数量
     * @param dpcoiUserQuery 查询条件
     * @return 返回结果
     */
    public Integer selectDpcoiUserCount(DpcoiUserQuery dpcoiUserQuery){
        return this.sqlSession.selectOne("dpcoiUser.selectDpcoiUserCount", dpcoiUserQuery);
    }

    /**
     * 获取dpcoi用户列表--分页
     * @param dpcoiUserQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectDpcoiUserListPage(DpcoiUserQuery dpcoiUserQuery){
        return this.sqlSession.selectList("dpcoiUser.selectDpcoiUserListPage", dpcoiUserQuery);
    }

    /**
     * 获取系统中没有加入Dpcoi的用户列表
     * @param userQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectNoDpcoiUserList(UserQuery userQuery){
        return this.sqlSession.selectList("dpcoiUser.selectNoDpcoiUserList", userQuery);
    }

    /**
     * 查询所有的Dpcoi用户
     * @param dpcoiUserQuery  查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectDpcoiUserList(DpcoiUserQuery dpcoiUserQuery){
        return this.sqlSession.selectList("dpcoiUser.selectDpcoiUserList", dpcoiUserQuery);
    }

    /**
     * 查询dpcoi用户自动下拉框数据
     * @param dpcoiUserQuery  查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectAutocompleteDpcoiUserList(DpcoiUserQuery dpcoiUserQuery){
        return this.sqlSession.selectList("dpcoiUser.selectAutocompleteDpcoiUserList", dpcoiUserQuery);
    }
}
