package com.dpcoi.holiday.service.serviceImpl;/**
 * Created by liangzhifu
 * DATE:2017/6/4.
 */

import com.dpcoi.holiday.dao.HolidayDao;
import com.dpcoi.holiday.domain.Holiday;
import com.dpcoi.holiday.query.HolidayQuery;
import com.dpcoi.holiday.service.HolidayService;
import com.success.web.framework.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-06-04
 **/
@Service("holidayService")
public class HolidayServiceImpl implements HolidayService{

    @Resource(name="holidayDao")
    private HolidayDao holidayDao;

    @Override
    public Integer deleteHoliday(Holiday holiday) {
        return this.holidayDao.deleteHoliday(holiday);
    }

    @Override
    public Integer addHoliday(Holiday holiday) {
        return this.holidayDao.insertHoliday(holiday);
    }

    @Override
    public List<Map<String, Object>> queryHolidayListPage(HolidayQuery holidayQuery) throws ServiceException {
        return this.holidayDao.selectHolidayListPage(holidayQuery);
    }

    @Override
    public Integer queryHolidayCount(HolidayQuery holidayQuery) throws ServiceException {
        return this.holidayDao.selectHolidayCount(holidayQuery);
    }
}
