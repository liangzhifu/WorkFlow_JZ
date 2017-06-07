package com.dpcoi.sys.query;

/**
 * Created by 梁志福 on 2017/4/20.
 */
public class DpcoiUserQuery {

    private String userName;

    private String userCode;

    private Integer start;

    private Integer size;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
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
}
