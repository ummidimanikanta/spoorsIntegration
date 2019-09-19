package com.ayansys.effort.beans.entity;

import lombok.Data;

public class Mail {
	public static final int BODY_TYPE_HTML=2;
	public static final int BODY_TYPE_TEXT=1;
	public static final int HIGH_PRIORITY_EMAIL = 2;
	private long id;
	private String mailFrom;
	private String mailTo;
	private String mailSubject;
	private String mailBody;
	private String attachmentPath;
	private int state;
	private String createTime;
	private String modifiedTime;
	private int mailBodyType;
	private String companyId;
	private boolean verificationRequired;
	private int senderType = -1; 
	private int priority = 1;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMailFrom() {
		return mailFrom;
	}
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}
	public String getMailTo() {
		return mailTo;
	}
	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
	public String getMailSubject() {
		return mailSubject;
	}
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}
	public String getMailBody() {
		return mailBody;
	}
	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}
	public String getAttachmentPath() {
		return attachmentPath;
	}
	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public int getMailBodyType() {
		return mailBodyType;
	}
	public void setMailBodyType(int mailBodyType) {
		this.mailBodyType = mailBodyType;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public boolean isVerificationRequired() {
		return verificationRequired;
	}
	public void setVerificationRequired(boolean verificationRequired) {
		this.verificationRequired = verificationRequired;
	}
	public int getSenderType() {
		return senderType;
	}
	public void setSenderType(int senderType) {
		this.senderType = senderType;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}	
}
