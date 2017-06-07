package com.success.sys.module.domain;

/**
 * 模块表
 * @author liang.zhifu
 *
 */
public class Module {

	private Integer id;
	
	//模块名称
	private String name;
	
	//模块优先级
	private Integer priority;
	
	//模块图标路径
	private String imagePath;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
}
