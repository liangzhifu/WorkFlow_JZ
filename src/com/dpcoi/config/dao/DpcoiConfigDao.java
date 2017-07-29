package com.dpcoi.config.dao;/**
 * Created by liangzhifu
 * DATE:2017/6/17.
 */

import com.dpcoi.config.domain.DpcoiConfig;
import com.dpcoi.config.query.DpcoiConfigQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-06-17
 **/
@Repository
public class DpcoiConfigDao extends BaseDao{

    /**
     * 插入下拉菜单
     * @param dpcoiConfig 下拉菜单
     * @return 返回结果
     */
    public Integer insertDpcoiConfig(DpcoiConfig dpcoiConfig){
        return this.sqlSession.insert("dpcoiConfigMapper.insertSelective", dpcoiConfig);
    }

    /**
     * 更新下拉菜单
     * @param dpcoiConfig 下拉菜单
     * @return 返回结果
     */
    public Integer updateDpcoiConfig(DpcoiConfig dpcoiConfig){
        return this.sqlSession.update("dpcoiConfigMapper.updateByPrimaryKeySelective", dpcoiConfig);
    }

    /**
     * 查询下拉菜单数量
     * @param dpcoiConfigQuery 查询条件
     * @return 返回结果
     */
    public Integer selectDpcoiConfigCount(DpcoiConfigQuery dpcoiConfigQuery){
        return this.sqlSession.selectOne("dpcoiConfigMapper.selectDpcoiConfigCount", dpcoiConfigQuery);
    }

    /**
     * 查询下拉菜单分页列表
     * @param dpcoiConfigQuery 查询条件
     * @return
     */
    public List<Map<String, Object>> selectDpcoiConfigListPage(DpcoiConfigQuery dpcoiConfigQuery){
        return this.sqlSession.selectList("dpcoiConfigMapper.selectDpcoiConfigListPage", dpcoiConfigQuery);
    }

    /**
     * 查询下拉菜单列表
     * @param dpcoiConfigQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectDpcoiConfigList(DpcoiConfigQuery dpcoiConfigQuery){
        return this.sqlSession.selectList("dpcoiConfigMapper.selectDpcoiConfigList", dpcoiConfigQuery);
    }
}
