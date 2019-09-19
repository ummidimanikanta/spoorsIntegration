package com.spoors.integration;

public class ConfigurationConstants {

	//Content & Accept Types
	public static final Integer TYPE_JSON = 1;
	public static final Integer TYPE_XML = 2;
	
	//Escalation status
	public static final Integer ESCALATION_FAILED_PERCENT = 30;
	public static final Integer ESCALATION_FAILED_DURATION_MINS = 30;
	public static final Integer ESCALATION_FAILED_COUNT = 0;
	
	//Call Log Status
	public static final Integer CALLLOG_STATUS_NOT_PROCESSED = 0;
	public static final Integer CALLLOG_STATUS_FAILED = -1;
	public static final Integer CALLLOG_STATUS_SUCCESS = 1;
	public static final Integer CALLLOG_STATUS_TIMEOUT = 2;
	public static final Integer CALLLOG_STATUS_HOLD = 3;

	//Trigger Type
	public static final Integer TRIGGER_TYPE_FORMS = 1;
	public static final Integer TRIGGER_TYPE_WORKS = 2;
	
	
	//Success Key
	public static final Integer CONFIG_SUCCESSKEY_PRESENT = 1;
	public static final Integer CONFIG_SUCCESSKEY_DOESNOT_PRESENT = 2;
	public static final Integer CONFIG_SUCCESSKEY_MATCH = 3;
	public static final Integer CONFIG_SUCCESSKEY_DOESNOT_MATCH = 4;

	//Escalation Mail Type
	public static final Integer ESCALATION_MAILTYPE_NONE = 0;//None
	public static final Integer ESCALATION_MAILTYPE_FIRSTTIME = 1;//First Time Only
	public static final Integer ESCALATION_MAILTYPE_RETRY_ONLY = 2;//Retry Only
	public static final Integer ESCALATION_MAILTYPE_EVERYTIME = 3;//Every Time
	public static final Integer ESCALATION_MAILTYPE_AFTER_LAST_RETRY = 4;//After Last Retry
	public static final Integer BASIC_AUTH = 1;
	public static final Integer JWT_AUTH = 2;
}

