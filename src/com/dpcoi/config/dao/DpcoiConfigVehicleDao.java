package com.dpcoi.config.dao;/**
 * Created by liangzhifu
 * DATE:2017/6/17.
 */

import com.dpcoi.config.domain.DpcoiConfigVehicle;
import com.dpcoi.config.query.DpcoiConfigVehicleQuery;
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
public class DpcoiConfigVehicleDao extends BaseDao{

    /**
     * 插入车型
     * @param dpcoiConfigVehicle 车型
     * @return 返回结果
     */
    public Integer insertDpcoiConfigVehicle(DpcoiConfigVehicle dpcoiConfigVehicle){
        return this.sqlSession.insert("dpcoiConfigVehicleMapper.insertSelective", dpcoiConfigVehicle);
    }

    /**
     * 更新车型
     * @param dpcoiConfigVehicle 车型
     * @return 返回结果
     */
    public Integer updateDpcoiConfigVehicle(DpcoiConfigVehicle dpcoiConfigVehicle){
        return this.sqlSession.update("dpcoiConfigVehicleMapper.updateByPrimaryKeySelective", dpcoiConfigVehicle);
    }

    /**
     * 查询车型数量
     * @param dpcoiConfigVehicleQuery 查询条件
     * @return 返回结果
     */
    public Integer selectDpcoiConfigVehicleCount(DpcoiConfigVehicleQuery dpcoiConfigVehicleQuery){
        return this.sqlSession.selectOne("dpcoiConfigVehicleMapper.selectDpcoiConfigVehicleCount", dpcoiConfigVehicleQuery);
    }

    /**
     * 查询车型分页列表
     * @param dpcoiConfigVehicleQuery 查询条件
     * @return
     */
    public List<Map<String, Object>> selectDpcoiConfigVehicleListPage(DpcoiConfigVehicleQuery dpcoiConfigVehicleQuery){
        return this.sqlSession.selectList("dpcoiConfigVehicleMapper.selectDpcoiConfigVehicleListPage", dpcoiConfigVehicleQuery);
    }

    /**
     * 查询车型列表
     * @param dpcoiConfigVehicleQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectDpcoiConfigVehicleList(DpcoiConfigVehicleQuery dpcoiConfigVehicleQuery){
        return this.sqlSession.selectList("dpcoiConfigVehicleMapper.selectDpcoiConfigVehicleList", dpcoiConfigVehicleQuery);
    }

    /**
     * 根据车型名称查询车型
     * @param dpcoiConfigVehicleQuery
     * @return
     */
    public List<Map<String, Object>> selectDpcoiConfigVehicle(DpcoiConfigVehicleQuery dpcoiConfigVehicleQuery){
        return this.sqlSession.selectList("dpcoiConfigVehicleMapper.selectDpcoiConfigVehicle", dpcoiConfigVehicleQuery);
    }
}
