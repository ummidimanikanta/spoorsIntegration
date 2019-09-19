package com.spoors.integration.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * @author DevUser
 *
 */
public class ConfigurationInfo implements Serializable {

	private static final long serialVersionUID = -6772454837509926049L;
	
	private Integer id;
	private String name;
	private Integer companyId;
	private String company;
	private Integer triggerType;
	private String triggerSource;
	private String triggerId;
	private Integer retryCount;
	private Boolean isEnabled;
	private ConfigEnable configEnable;
	private Integer invokeType;
	private Integer contentType;
	private Integer acceptType;
	private String submitUrl;
	private String urlForUpdate;
	private Boolean ignoreSSL;
	private Integer authenticationType;
	private String username;
	private String password;
	private Integer formDataPushType;
	private Integer approveFormData;
	private Integer successKey;
	private String successKeyValue;
	private Boolean restrictUpdateUrl;
	private Integer escalationType;
	private Integer createdBy;
	private Date createdTime;
	private Integer modifiedBy;
	private Date modifiedTime;
	
	private List<ConfigEscalationBean> configEscalationList;
	private List<FormAndWorkFieldMappingBean> configFieldsList;

	private IntegrationCallLogsBean configCallLogsBean;

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

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Integer getTriggerType() {
		return triggerType;
	}

	public void setTriggerType(Integer triggerType) {
		this.triggerType = triggerType;
	}

	public String getTriggerSource() {
		return triggerSource;
	}

	public void setTriggerSource(String triggerSource) {
		this.triggerSource = triggerSource;
	}

	public String getTriggerId() {
		return triggerId;
	}

	public void setTriggerId(String triggerId) {
		this.triggerId = triggerId;
	}

	public Integer getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public ConfigEnable getConfigEnable() {
		return configEnable;
	}

	public void setConfigEnable(ConfigEnable configEnable) {
		this.configEnable = configEnable;
	}

	public Integer getInvokeType() {
		return invokeType;
	}

	public void setInvokeType(Integer invokeType) {
		this.invokeType = invokeType;
	}

	public Integer getContentType() {
		return contentType;
	}

	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}

	public Integer getAcceptType() {
		return acceptType;
	}

	public void setAcceptType(Integer acceptType) {
		this.acceptType = acceptType;
	}

	public String getSubmitUrl() {
		return submitUrl;
	}

	public void setSubmitUrl(String submitUrl) {
		this.submitUrl = submitUrl;
	}

	public String getUrlForUpdate() {
		return urlForUpdate;
	}

	public void setUrlForUpdate(String urlForUpdate) {
		this.urlForUpdate = urlForUpdate;
	}

	public Boolean getIgnoreSSL() {
		return ignoreSSL;
	}

	public void setIgnoreSSL(Boolean ignoreSSL) {
		this.ignoreSSL = ignoreSSL;
	}

	public Integer getAuthenticationType() {
		return authenticationType;
	}

	public void setAuthenticationType(Integer authenticationType) {
		this.authenticationType = authenticationType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getFormDataPushType() {
		return formDataPushType;
	}

	public void setFormDataPushType(Integer formDataPushType) {
		this.formDataPushType = formDataPushType;
	}

	public Integer getApproveFormData() {
		return approveFormData;
	}

	public void setApproveFormData(Integer approveFormData) {
		this.approveFormData = approveFormData;
	}

	public Integer getSuccessKey() {
		return successKey;
	}

	public void setSuccessKey(Integer successKey) {
		this.successKey = successKey;
	}

	public String getSuccessKeyValue() {
		return successKeyValue;
	}

	public void setSuccessKeyValue(String successKeyValue) {
		this.successKeyValue = successKeyValue;
	}

	public Boolean getRestrictUpdateUrl() {
		return restrictUpdateUrl;
	}

	public void setRestrictUpdateUrl(Boolean restrictUpdateUrl) {
		this.restrictUpdateUrl = restrictUpdateUrl;
	}

	public Integer getEscalationType() {
		return escalationType;
	}

	public void setEscalationType(Integer escalationType) {
		this.escalationType = escalationType;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public List<ConfigEscalationBean> getConfigEscalationList() {
		return configEscalationList;
	}

	public void setConfigEscalationList(List<ConfigEscalationBean> configEscalationList) {
		this.configEscalationList = configEscalationList;
	}

	public List<FormAndWorkFieldMappingBean> getConfigFieldsList() {
		return configFieldsList;
	}

	public void setConfigFieldsList(List<FormAndWorkFieldMappingBean> configFieldsList) {
		this.configFieldsList = configFieldsList;
	}

	public IntegrationCallLogsBean getConfigCallLogsBean() {
		return configCallLogsBean;
	}

	public void setConfigCallLogsBean(IntegrationCallLogsBean configCallLogsBean) {
		this.configCallLogsBean = configCallLogsBean;
	}

}
