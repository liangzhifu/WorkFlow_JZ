package com.success.sys.config.domain;

public class SysConfig {

	private Integer id;
	
	private String configCode;
	
	private String configCommit;
	
	private String configValue;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConfigCode() {
		return configCode;
	}

	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}

	public String getConfigCommit() {
		return configCommit;
	}

	public void setConfigCommit(String configCommit) {
		this.configCommit = configCommit;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
	
}
