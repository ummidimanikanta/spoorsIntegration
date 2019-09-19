package com.spoors.integration.beans;

import java.util.Date;

import lombok.Data;

public class FormAndWorkFieldMappingBean {
	
	private Integer id;
	private Integer configId;
	private String sourceKey;
	private String sourceSubKey;
	private boolean ignoreObj;
	private Integer sourceKeyFieldType;
	private String destinationKey;
	private Integer FieldType;
	private boolean ignoreField;
	private Integer createdBy;
	private Date createdDate;
	private Integer modifiedBy;
	private Date modifiedDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getConfigId() {
		return configId;
	}
	public void setConfigId(Integer configId) {
		this.configId = configId;
	}
	public String getSourceKey() {
		return sourceKey;
	}
	public void setSourceKey(String sourceKey) {
		this.sourceKey = sourceKey;
	}
	public String getSourceSubKey() {
		return sourceSubKey;
	}
	public void setSourceSubKey(String sourceSubKey) {
		this.sourceSubKey = sourceSubKey;
	}
	public boolean isIgnoreObj() {
		return ignoreObj;
	}
	public void setIgnoreObj(boolean ignoreObj) {
		this.ignoreObj = ignoreObj;
	}
	public Integer getSourceKeyFieldType() {
		return sourceKeyFieldType;
	}
	public void setSourceKeyFieldType(Integer sourceKeyFieldType) {
		this.sourceKeyFieldType = sourceKeyFieldType;
	}
	public String getDestinationKey() {
		return destinationKey;
	}
	public void setDestinationKey(String destinationKey) {
		this.destinationKey = destinationKey;
	}
	public Integer getFieldType() {
		return FieldType;
	}
	public void setFieldType(Integer fieldType) {
		FieldType = fieldType;
	}
	public boolean isIgnoreField() {
		return ignoreField;
	}
	public void setIgnoreField(boolean ignoreField) {
		this.ignoreField = ignoreField;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
