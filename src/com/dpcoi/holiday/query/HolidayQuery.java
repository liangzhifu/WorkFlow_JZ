package com.dpcoi.holiday.query;/**
 * Created by liangzhifu
 * DATE:2017/6/4.
 */

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 *
 * @author lzf
 * @create 2017-06-04
 **/

public class HolidayQuery {

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date holidayStart;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date holidayEnd;

    private Integer start;

    private Integer size;

    public Integer getStart() {
        return start;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Date getHolidayStart() {
        return holidayStart;
    }

    public void setHolidayStart(Date holidayStart) {
        this.holidayStart = holidayStart;
    }

    public Date getHolidayEnd() {
        return holidayEnd;
    }

    public void setHolidayEnd(Date holidayEnd) {
        this.holidayEnd = holidayEnd;
    }
}
