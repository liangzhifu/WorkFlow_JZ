package com.success.sys.vehicle.domain;/**
 * Created by liangzhifu
 * DATE:2017/5/30.
 */

/**
 *
 * @author lzf
 * @create 2017-05-30
 **/

public class Vehicle {

    private Integer id;

    private String vehicleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName == null ? null : vehicleName.trim();
    }

}
