package com.dpcoi.holiday.dao;/**
 * Created by liangzhifu
 * DATE:2017/6/4.
 */


import com.dpcoi.holiday.domain.Holiday;
import com.dpcoi.holiday.query.HolidayQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-06-04
 **/
@Repository
public class HolidayDao extends BaseDao{

    /**
     * 插入一个节假日
     * @param holiday 订单数据
     * @return 1成功，0不成功
     */
    public Integer insertHoliday(Holiday holiday){
        return this.sqlSession.insert("HolidayMapper.insertSelective", holiday);
    }

    /**
     * 删除一个节假日
     * @param holiday 订单数据
     * @return 1成功，0不成功
     */
    public Integer deleteHoliday(Holiday holiday){
        return this.sqlSession.insert("HolidayMapper.deleteHoliday", holiday);
    }


    /**
     * 获取节假日数量
     * @param holidayQuery 查询条件
     * @return 返回结果
     */
    public Integer selectHolidayCount(HolidayQuery holidayQuery){
        return this.sqlSession.selectOne("HolidayMapper.selectHolidayCount", holidayQuery);
    }

    /**
     * 获取节假日列表--分页
     * @param holidayQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectHolidayListPage(HolidayQuery holidayQuery){
        return this.sqlSession.selectList("HolidayMapper.selectHolidayListPage", holidayQuery);
    }

    /**
     * 查询从当前时间的节假日
     * @return 返回结果
     */
    public List<Date> selectHolidayList(){
        return this.sqlSession.selectList("HolidayMapper.selectHolidayList");
    }

    /**
     * 查询从某个时间开始的节假日
     * @param beginDate 开始时间
     * @return 返回结果
     */
    public List<Date> selectHolidayListFromBeginDate(String beginDate){
        return this.sqlSession.selectList("HolidayMapper.selectHolidayListFromBeginDate", beginDate);
    }
}
