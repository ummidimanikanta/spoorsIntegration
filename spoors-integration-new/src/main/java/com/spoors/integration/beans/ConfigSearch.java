package com.spoors.integration.beans;

import lombok.Data;

public class ConfigSearch {
	
	private String name;
	private int triggerType;
	private Boolean deleted;
	private Boolean enable;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTriggerType() {
		return triggerType;
	}
	public void setTriggerType(int triggerType) {
		this.triggerType = triggerType;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
}
