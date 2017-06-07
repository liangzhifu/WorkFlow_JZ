package com.success.sys.menu.domain;

public class MenuCatalog {

private Integer id;
	
	private String name;
	
	private String code;
	
	private String url;//目录默认的URL，针对特定的模块，例如首页，是没有菜单的，url就配置到模块中
	
	private String imageUrl;
	
	public MenuCatalog(){
	}
	
	public MenuCatalog(Integer id, String name){
		this.id = id;
		this.name = name;
	}
	
	public MenuCatalog(Integer id, String name, String url){
		this(id, name);
		this.url = url;
	}
	
	public MenuCatalog(Integer id, String name, String code, String url){
		this(id, name, url);
		this.code = code;
	}
	
	public MenuCatalog(Integer id, String name, String code, String url, String imageUrl){
		this(id, name, code, url);
		this.imageUrl = imageUrl;
	}

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}
