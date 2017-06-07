package com.dpcoi.holiday.domain;/**
 * Created by liangzhifu
 * DATE:2017/6/4.
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 *
 * @author lzf
 * @create 2017-06-04
 **/

public class Holiday {

    private Integer id;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date holiday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getHoliday() {
        return holiday;
    }

    public void setHoliday(Date holiday) {
        this.holiday = holiday;
    }
}
