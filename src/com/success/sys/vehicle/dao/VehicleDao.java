package com.success.sys.vehicle.dao;/**
 * Created by liangzhifu
 * DATE:2017/5/30.
 */

import com.success.sys.vehicle.domain.Vehicle;
import com.success.sys.vehicle.query.VehicleQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-05-30
 **/
@Repository
public class VehicleDao extends BaseDao{

    /**
     * 插入一个车种
     * @param vehicle 订单数据
     * @return 1成功，0不成功
     */
    public Integer insertVehicle(Vehicle vehicle){
        return this.sqlSession.insert("VehicleMapper.insertSelective", vehicle);
    }

    /**
     * 删除一个车种
     * @param vehicle 订单数据
     * @return 1成功，0不成功
     */
    public Integer deleteVehicle(Vehicle vehicle){
        return this.sqlSession.insert("VehicleMapper.deleteVehicle", vehicle);
    }


    /**
     * 获取车种数量
     * @param vehicleQuery 查询条件
     * @return 返回结果
     */
    public Integer selectVehicleCount(VehicleQuery vehicleQuery){
        return this.sqlSession.selectOne("VehicleMapper.selectVehicleCount", vehicleQuery);
    }

    /**
     * 获取车种列表--分页
     * @param vehicleQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectVehicleListPage(VehicleQuery vehicleQuery){
        return this.sqlSession.selectList("VehicleMapper.selectVehicleListPage", vehicleQuery);
    }

    /**
     * 获取车种列表
     * @param vehicleQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectVehicleList(VehicleQuery vehicleQuery){
        return this.sqlSession.selectList("VehicleMapper.selectVehicleList", vehicleQuery);
    }
}
