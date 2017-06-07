package com.dpcoi.quartz.service.serviceImpl;/**
 * Created by liangzhifu
 * DATE:2017/6/4.
 */

import com.dpcoi.holiday.domain.Holiday;
import com.dpcoi.holiday.query.HolidayQuery;
import com.dpcoi.holiday.service.HolidayService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author lzf
 * @create 2017-06-04
 **/
@Component("createHolidayServiceImpl")
public class CreateHolidayServiceImpl {

    @Resource(name="holidayService")
    private HolidayService holidayService;

    @Scheduled(cron = "0 0 1 1 * ?")
    public void job(){
        try {
            Date nowDate = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(nowDate);
            cal.add(Calendar.MONTH, 1);
            int maxDay = cal.getActualMaximum(Calendar.DATE);
            for(int i = 0; i < maxDay; i++){
                if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
                    Date date = cal.getTime();
                    HolidayQuery holidayQuery = new HolidayQuery();
                    holidayQuery.setHolidayStart(date);
                    holidayQuery.setHolidayEnd(date);
                    Integer num = this.holidayService.queryHolidayCount(holidayQuery);
                    if(num == 0){
                        Holiday holiday = new Holiday();
                        holiday.setHoliday(date);
                        this.holidayService.addHoliday(holiday);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
