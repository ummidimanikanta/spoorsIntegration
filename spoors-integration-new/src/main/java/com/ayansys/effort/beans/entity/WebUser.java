package com.ayansys.effort.beans.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import com.ayansys.effort.util.Api;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class WebUser implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int TYPE_EMPLOYEE = 1;
	public static final int TYPE_CUSTOMER = 2;
	public static final int TYPE_CUSTOM_ENTITY = 3;

	private String username;
	private String password;
	private String email;
	private boolean enabled;
	private Long empId;
	private String empFirstName;
	private String empLastName;
	private Long customerId;
	private String customerName;
	private Integer companyId;
	private int type;
	private Integer manager;
	// kiran code for timezone
	private String timezoneName;
	private Integer tzo;
	private Integer timezoneId;
	private String customerIds;
	//private ServiceCallConstants serviceCallConstants=new  ServiceCallConstants();
	
	private String ipAddress;
	private boolean oppUser;
	private String oppUserName;
	private Integer concurrentLoginCount;
	

	private Collection<GrantedAuthority> authorities;
	
	private Set<String> accessibleEmpIdsList;
	private String lastAccessibleEmpIdsListUpdateTime;;
	
	private String salt;
	
	private Long customEntityId;
	private String customEntityName;
	
	private boolean hasWebAndConfiguratorAppAccess;
	
	private Long appId;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getEmpFirstName() {
		return empFirstName;
	}

	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public String getEmpLastName() {
		return empLastName;
	}

	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public String getEmpName() {
		String empName = "";

		if (!Api.isEmptyString(getEmpFirstName())) {
			empName += getEmpFirstName();
		}

		if (!Api.isEmptyString(getEmpLastName())) {
			if (!Api.isEmptyString(empName)) {
				empName += (" " + getEmpLastName());
			} else {
				empName += getEmpLastName();
			}
		}

		return empName;
	}

	public boolean hasRole(String role) {
		for (GrantedAuthority auth : authorities) {
			if (role.equals(auth.getAuthority())) {
				return true;
			}
		}

		return false;
	}

	public String toCSV() {
		return "[username=" + username + ", password=" + password + ", empId="
				+ empId + ", empFirstName=" + empFirstName + ", customerId="
				+ customerId + ", customerName=" + customerName
				+ ", companyId=" + companyId + ", type=" + type + ", enabled="
				+ enabled + ", authorities=" + authorities + "]";
	}

	public Integer getManager() {
		return manager;
	}

	public void setManager(Integer manager) {
		this.manager = manager;
	}

	public String getTimezoneName() {
		return timezoneName;
	}

	public void setTimezoneName(String timezoneName) {
		this.timezoneName = timezoneName;
	}

	public Integer getTzo() {
		return tzo;
	}

	public void setTzo(Integer tzo) {
		this.tzo = tzo;
	}

	public Integer getTimezoneId() {
		return timezoneId;
	}

	public void setTimezoneId(Integer timezoneId) {
		this.timezoneId = timezoneId;
	}

	public String getCustomerIds() {
		return customerIds;
	}

	public void setCustomerIds(String customerIds) {
		this.customerIds = customerIds;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public boolean isOppUser() {
		return oppUser;
	}

	public void setOppUser(boolean oppUser) {
		this.oppUser = oppUser;
	}

	public String getOppUserName() {
		return oppUserName;
	}

	public void setOppUserName(String oppUserName) {
		this.oppUserName = oppUserName;
	}

	public Integer getConcurrentLoginCount() {
		return concurrentLoginCount;
	}

	public void setConcurrentLoginCount(Integer concurrentLoginCount) {
		this.concurrentLoginCount = concurrentLoginCount;
	}

	public Set<String> getAccessibleEmpIdsList() {
		return accessibleEmpIdsList;
	}

	public void setAccessibleEmpIdsList(Set<String> accessibleEmpIdsList) {
		this.accessibleEmpIdsList = accessibleEmpIdsList;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getLastAccessibleEmpIdsListUpdateTime() {
		return lastAccessibleEmpIdsListUpdateTime;
	}

	public void setLastAccessibleEmpIdsListUpdateTime(
			String lastAccessibleEmpIdsListUpdateTime) {
		this.lastAccessibleEmpIdsListUpdateTime = lastAccessibleEmpIdsListUpdateTime;
	}

	public Long getCustomEntityId() {
		return customEntityId;
	}

	public void setCustomEntityId(Long customEntityId) {
		this.customEntityId = customEntityId;
	}

	public String getCustomEntityName() {
		return customEntityName;
	}

	public void setCustomEntityName(String customEntityName) {
		this.customEntityName = customEntityName;
	}

	public boolean isHasWebAndConfiguratorAppAccess() {
		return hasWebAndConfiguratorAppAccess;
	}

	public void setHasWebAndConfiguratorAppAccess(
			boolean hasWebAndConfiguratorAppAccess) {
		this.hasWebAndConfiguratorAppAccess = hasWebAndConfiguratorAppAccess;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}
	
	
	
	

	
}
