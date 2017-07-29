package com.success.sys.menu.domain;

public class UserMenu {
	//id
	private Integer userMenuId;
	//用户id
	private Integer userId;
	//用户名称
	private String userName;
	//菜单id
	private Integer menuId;
	//菜单名称
	private String menuName;
	//菜单类别
	private String menuModule;
	
	private String menuUrl;

	//数量
	private Integer woOrderNumber;
	
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuModule() {
		return menuModule;
	}
	public void setMenuModule(String menuModule) {
		this.menuModule = menuModule;
	}
	@Override
	public String toString() {
		return "UserMenu [userMenuId=" + userMenuId + ", userId=" + userId
				+ ", userName=" + userName + ", menuId=" + menuId
				+ ", menuName=" + menuName + ", menuModule=" + menuModule + "]";
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public Integer getWoOrderNumber() {
		return woOrderNumber;
	}

	public void setWoOrderNumber(Integer woOrderNumber) {
		this.woOrderNumber = woOrderNumber;
	}
}
