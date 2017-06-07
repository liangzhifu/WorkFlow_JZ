package com.dpcoi.holiday.service;

import com.dpcoi.holiday.domain.Holiday;
import com.dpcoi.holiday.query.HolidayQuery;
import com.success.web.framework.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * Created by liangzhifu
 * DATE:2017/6/4.
 */
public interface HolidayService {
    /**
     * 删除节假日
     * @param holiday 节假日
     * @return 返回结果
     */
    Integer deleteHoliday(Holiday holiday);

    /**
     * 添加节假日
     * @param holiday 节假日
     * @return 返回结果
     */
    Integer addHoliday(Holiday holiday);

    /**
     * 获取节假日列表--分页
     * @param holidayQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryHolidayListPage(HolidayQuery holidayQuery) throws ServiceException;

    /**
     * 获取节假日数量
     * @param holidayQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public Integer queryHolidayCount(HolidayQuery holidayQuery) throws ServiceException;
}
