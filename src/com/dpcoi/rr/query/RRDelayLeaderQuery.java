package com.dpcoi.rr.query;/**
 * Created by liangzhifu
 * DATE:2017/7/28.
 */

/**
 *
 * @author lzf
 * @create 2017-07-28
 **/

public class RRDelayLeaderQuery {

    private Integer start;

    private Integer size;

    private String userName;

    private String leaderName;

    private String leaderType;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getLeaderType() {
        return leaderType;
    }

    public void setLeaderType(String leaderType) {
        this.leaderType = leaderType;
    }
}
