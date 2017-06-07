package com.success.sys.menu.query;

public class UserMenuQuery {
	private Integer userMenuId;
	private Integer userId;
	private Integer menuId;
	private Integer moduleId;
	//查询有或无权限
	private String checkHaveAccess;
	
	public Integer getUserMenuId() {
		return userMenuId;
	}
	public void setUserMenuId(Integer userMenuId) {
		this.userMenuId = userMenuId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	
	public String getCheckHaveAccess() {
		return checkHaveAccess;
	}
	public void setCheckHaveAccess(String checkHaveAccess) {
		this.checkHaveAccess = checkHaveAccess;
	}
	@Override
	public String toString() {
		return "UserMenuQuery [userMenuId=" + userMenuId + ", userId=" + userId
				+ ", menuId=" + menuId + ", checkHaveAccess=" + checkHaveAccess + "]";
	}
	public Integer getModuleId() {
		return moduleId;
	}
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	
	
}
