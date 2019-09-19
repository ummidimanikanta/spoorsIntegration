package com.spoors.integration.beans;

import java.util.Date;

import lombok.Data;

public class IntegrationCallLogsBean {
	
	private int id;
	private int callStatiscticsId;
	private int pushId;
	private String configurationName;
	private String requestData;
	private String responseData;
	private int status;
	private int retryCount;
	private boolean isUpdate;
	private String externalSystemId;
	private String startTime;
	private String endTime;
	private String duration;
	private int createdBy;
	private Date createdDate;
	private int modifiedBy;
	private Date modifiedDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCallStatiscticsId() {
		return callStatiscticsId;
	}
	public void setCallStatiscticsId(int callStatiscticsId) {
		this.callStatiscticsId = callStatiscticsId;
	}
	public int getPushId() {
		return pushId;
	}
	public void setPushId(int pushId) {
		this.pushId = pushId;
	}
	public String getConfigurationName() {
		return configurationName;
	}
	public void setConfigurationName(String configurationName) {
		this.configurationName = configurationName;
	}
	public String getRequestData() {
		return requestData;
	}
	public void setRequestData(String requestData) {
		this.requestData = requestData;
	}
	public String getResponseData() {
		return responseData;
	}
	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRetryCount() {
		return retryCount;
	}
	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}
	public boolean isUpdate() {
		return isUpdate;
	}
	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
	public String getExternalSystemId() {
		return externalSystemId;
	}
	public void setExternalSystemId(String externalSystemId) {
		this.externalSystemId = externalSystemId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public int getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
