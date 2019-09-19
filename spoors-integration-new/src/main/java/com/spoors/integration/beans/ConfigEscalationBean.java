package com.spoors.integration.beans;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

public class ConfigEscalationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3413603828627961439L;
	
	private Integer id;
	private Integer configId;
	private Integer status;
	private Boolean addRequestInMail;
	private Boolean addResponseInMail;
	private String mailIds;
	private String subjectTemplate;
	private String bodyTemplate;
	private Integer bodyType;
	private Integer mailType;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Boolean getAddRequestInMail() {
		return addRequestInMail;
	}
	public void setAddRequestInMail(Boolean addRequestInMail) {
		this.addRequestInMail = addRequestInMail;
	}
	public Boolean getAddResponseInMail() {
		return addResponseInMail;
	}
	public void setAddResponseInMail(Boolean addResponseInMail) {
		this.addResponseInMail = addResponseInMail;
	}
	public String getMailIds() {
		return mailIds;
	}
	public void setMailIds(String mailIds) {
		this.mailIds = mailIds;
	}
	public String getSubjectTemplate() {
		return subjectTemplate;
	}
	public void setSubjectTemplate(String subjectTemplate) {
		this.subjectTemplate = subjectTemplate;
	}
	public String getBodyTemplate() {
		return bodyTemplate;
	}
	public void setBodyTemplate(String bodyTemplate) {
		this.bodyTemplate = bodyTemplate;
	}
	public Integer getBodyType() {
		return bodyType;
	}
	public void setBodyType(Integer bodyType) {
		this.bodyType = bodyType;
	}
	public Integer getMailType() {
		return mailType;
	}
	public void setMailType(Integer mailType) {
		this.mailType = mailType;
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
