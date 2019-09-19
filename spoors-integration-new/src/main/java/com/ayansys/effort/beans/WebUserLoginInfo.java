package com.ayansys.effort.beans;

public class WebUserLoginInfo 
{
	private Long id;
	private String userName;
	private String oppUserName;
	private String loginTime;
	private String ipAddress;
	private boolean concurrentLogin;
	private String sessionId;
	private String browserInfo;
	private String osName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOppUserName() {
		return oppUserName;
	}
	public void setOppUserName(String oppUserName) {
		this.oppUserName = oppUserName;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public boolean isConcurrentLogin() {
		return concurrentLogin;
	}
	public void setConcurrentLogin(boolean concurrentLogin) {
		this.concurrentLogin = concurrentLogin;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getBrowserInfo() {
		return browserInfo;
	}
	public void setBrowserInfo(String browserInfo) {
		this.browserInfo = browserInfo;
	}
	public String getOsName() {
		return osName;
	}
	public void setOsName(String osName) {
		this.osName = osName;
	}

}
