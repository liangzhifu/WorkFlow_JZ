package com.dpcoi.config.domain;/**
 * Created by liangzhifu
 * DATE:2017/6/17.
 */

/**
 *
 * @author lzf
 * @create 2017-06-17
 **/

public class DpcoiConfigVehicle {

    private Integer configVehicleId;

    private Integer configId;

    private String configVehicleValue;

    private String deleteState;

    public Integer getConfigVehicleId() {
        return configVehicleId;
    }

    public void setConfigVehicleId(Integer configVehicleId) {
        this.configVehicleId = configVehicleId;
    }

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    public String getConfigVehicleValue() {
        return configVehicleValue;
    }

    public void setConfigVehicleValue(String configVehicleValue) {
        this.configVehicleValue = configVehicleValue;
    }

    public String getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(String deleteState) {
        this.deleteState = deleteState;
    }
}
