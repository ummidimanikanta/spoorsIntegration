package com.spoors.integration.manager;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBException;
import javax.xml.xpath.XPathExpressionException;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.ayansys.effort.beans.entity.Mail;
import com.ayansys.effort.util.Api;
import com.spoors.integration.ConfigurationConstants;
import com.spoors.integration.beans.ConfigEscalationBean;
import com.spoors.integration.beans.ConfigurationInfo;
import com.spoors.integration.beans.FormAndWorkFieldMappingBean;
import com.spoors.integration.beans.FormField;
import com.spoors.integration.beans.FormSpec;
import com.spoors.integration.beans.IntegrationCallLogsBean;
import com.spoors.integration.beans.WorkSpec;
import com.spoors.integration.dao.ConfigurationCallLogDAO;
import com.spoors.integration.dao.ConfigurationDAO;
import com.spoors.integration.util.ConfigurationMapper;
import com.spoors.integration.util.ConfigurationUtils;
import com.spoors.integration.util.MailTask;

@Service
public class ConfigurationCallLogManager {
	
	public static Logger log = LoggerFactory.getLogger(ConfigurationCallLogManager.class); 
	
	@Autowired
	ConfigurationCallLogDAO callLogDAO;

	@Autowired
	ConfigurationDAO configDAO;
	
	@Autowired
	ConfigEscalationManager escalationManager;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	MailTask mailTask;

	public List<IntegrationCallLogsBean> getIntegrationCallLogsList(int offset,int pageSize){
		return callLogDAO.getIntegrationCallLogsList(offset,pageSize);
	}
	
	public int getIntegrationCallAuditBeanCount() {
		return callLogDAO.getIntegrationCallAuditBeanCount();
	}

	public List<IntegrationCallLogsBean> getIntegrationCallAuditList(int offset,int pageSize) throws ParseException{
		List<IntegrationCallLogsBean> integrationLogBeanList =  callLogDAO.getIntegrationCallAuditList(offset,pageSize);
		
		if(integrationLogBeanList != null) {
			for(IntegrationCallLogsBean integrationLogBean:integrationLogBeanList) {
				String startTime = integrationLogBean.getStartTime();
				String endTime = integrationLogBean.getEndTime();
				if(!(StringUtils.isEmpty(startTime)) && !(StringUtils.isEmpty(endTime))) {
					long timDiff = Api.getTimeDifferenceOfDateTimeInMilies(startTime,endTime);
					int hours = (int) Math.floor(timDiff / 3600000);
					int minutes = (int) Math.floor((timDiff - hours * 3600000) / 60000);
		            
		            int seconds = (int) Math.floor((timDiff - hours * 3600000 - minutes * 60000) / 1000);
					
		            int milliSecs = (int) (timDiff - hours * 3600000 - minutes * 60000 - seconds * 1000) / 1000;
		            
					integrationLogBean.setDuration(minutes+":"+seconds);
				}
			}
		}
		return integrationLogBeanList;
	}
	
	public int getIntegrationCallLogsCount() {
		return callLogDAO.getIntegrationCallLogsCount();
	}
	
	public List<FormAndWorkFieldMappingBean> getConfigFieldsByConfigId(Integer configId){
	
		return configDAO.getConfigFieldsById(configId);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public ConfigurationInfo hitConfigurationIntegrationCall(Map<String, String> configIntegrationMap) throws JAXBException, IOException {

		Long formId=null;
		boolean isRetry= configIntegrationMap.get("isRetry")!=null ? Boolean.valueOf(configIntegrationMap.get("isRetry")):false;
		boolean isUpdate= configIntegrationMap.get("isUpdate")!=null?Boolean.valueOf(configIntegrationMap.get("isUpdate")):false;
		String companyId=configIntegrationMap.get("companyId")!=null?configIntegrationMap.get("companyId"):null;
		
		Map<String,String> configMap = new HashMap<>();
		String configFieldRequest = null;
		IntegrationCallLogsBean integrationLogBean = new IntegrationCallLogsBean();
		Integer configCallLogId;
		ConfigurationInfo  configInfo;
		int configId;
		if(!isRetry){
			String triggerId=null;
			String workId = configIntegrationMap.get("workId");
			if(!StringUtils.isEmpty(workId)){
				WorkSpec workSpec = configDAO.getFormIdFromWorkId(Integer.valueOf(workId), Integer.valueOf(companyId));
				triggerId = String.valueOf(workSpec.getWorkSpecId());
				formId = workSpec.getFormId();
			}else{
				formId = Long.valueOf(configIntegrationMap.get("formId"));
				FormSpec formSpec = configDAO.getFormSpecByFormId(formId);
				triggerId = formSpec.getUniqueId();
			}
			List<FormField> formFieldList = configDAO.getFormFieldByForm(formId);
			configMap.put("companyId", companyId);
			configMap.put("triggerId", triggerId);
			configInfo = configDAO.getConfigurationByKey(configMap);
			configId= configInfo.getId();
			// Construct the JSON based on the form table data with the destination key
			List<FormAndWorkFieldMappingBean> configFieldsList = getConfigFieldsByConfigId(configId);
			
			Map<String,String> fieldMap = new HashMap<String,String>();
			//Compare 2 field lists and add the data into Map
			configFieldsList.forEach(configField -> {
				if(!StringUtils.isEmpty(configField.getSourceKeyFieldType())){
					formFieldList.stream().filter(formField -> 
					(formField.getFieldType().equals(configField.getSourceKeyFieldType()) && configField.getSourceKey().equals(formField.getFieldLabel())))
					.findAny().ifPresent(formField -> {fieldMap.put(configField.getDestinationKey(), formField.getFieldValue());});
				}else{
					formFieldList.stream().filter(formField -> 
					(configField.getSourceKey().equals(formField.getFieldLabel())))
					.findAny().ifPresent(formField -> {fieldMap.put(configField.getDestinationKey(), formField.getFieldValue());});
				}
			});
			//Based on the content type create XML or JSON to send in the request
			if(!StringUtils.isEmpty(configInfo.getContentType()) && configInfo.getContentType().equals(ConfigurationConstants.TYPE_JSON)){
				configFieldRequest = ConfigurationMapper.createJSON(fieldMap);
			}else{
				configFieldRequest = ConfigurationMapper.createXML(fieldMap);
			}
			//save the request into configurationCallstatistics and call audit tables 
			integrationLogBean.setPushId(configId);
			integrationLogBean.setConfigurationName(configInfo.getName());
			integrationLogBean.setRequestData(configFieldRequest);
			integrationLogBean.setUpdate(isUpdate);
			integrationLogBean.setCreatedBy(1);
	
			configCallLogId = callLogDAO.insertIntegrationCallLog(integrationLogBean);
			integrationLogBean.setId(configCallLogId);
			callLogDAO.insertIntegrationCallStatistics(integrationLogBean);
			
		}else{
			integrationLogBean = callLogDAO.getIntegrationCallLogById(Integer.valueOf(configIntegrationMap.get("configCallId")));
			configMap.put("id", String.valueOf(integrationLogBean.getPushId()));
			configInfo = configDAO.getConfigurationByKey(configMap);
			configId = configInfo.getId();
			configCallLogId = integrationLogBean.getId();
			callLogDAO.insertIntegrationCallStatistics(integrationLogBean);
			isUpdate = integrationLogBean.isUpdate();
		}
		
		//hit the submission URL
		ResponseEntity<String> serviceResponseEntity = hitServiceURL(isRetry, configInfo, configFieldRequest, isUpdate);
		
		String serviceResponse = serviceResponseEntity.getBody();
		//MediaType acceptType = serviceResponseEntity.getHeaders().getContentType();
		HttpStatus statusCode = serviceResponseEntity.getStatusCode();

		List<ConfigEscalationBean> configEscalationList = escalationManager.getEscalationsByConfigId(configId);
		//Set escalation List to Configuration if required can use
		configInfo.setConfigEscalationList(configEscalationList);
		
		boolean isJSON = configInfo.getAcceptType().equals(1)? true:false;


		Integer successKey = configInfo.getSuccessKey();
		String successKeyValue = configInfo.getSuccessKeyValue();
		Integer callLogStatus = null;
		//fetch the response and check for success key
		if(ConfigurationConstants.CONFIG_SUCCESSKEY_MATCH.equals(successKey) 
				|| ConfigurationConstants.CONFIG_SUCCESSKEY_DOESNOT_MATCH.equals(successKey)){

			//Change to validate for tag
			JSONObject jsonOb = null;
			boolean key = false;
			if(isJSON){
				jsonOb = new JSONObject(serviceResponse);
				try{
					key = jsonOb.get(successKeyValue)!= null ? true: false;
				}catch(JSONException je){
					log.error(je.getMessage()+""+je);
				}
			}else{
				try {
					key = ConfigurationUtils.isTagExists(serviceResponse, successKeyValue);
				} catch (XPathExpressionException e) {
					// TODO Auto-generated catch block
					log.error(e.getMessage()+""+e);
				}
			}
			if(key & ConfigurationConstants.CONFIG_SUCCESSKEY_MATCH.equals(successKey)){
				callLogStatus = ConfigurationConstants.CALLLOG_STATUS_SUCCESS;
				
			}else if(!key & ConfigurationConstants.CONFIG_SUCCESSKEY_DOESNOT_MATCH.equals(successKey)){
				callLogStatus = ConfigurationConstants.CALLLOG_STATUS_SUCCESS;
			}else{
				//Updated status based on the HTTP status currently only failed considered
				if(HttpStatus.GATEWAY_TIMEOUT == statusCode){
					callLogStatus = ConfigurationConstants.CALLLOG_STATUS_TIMEOUT;
				}else{
					callLogStatus = ConfigurationConstants.CALLLOG_STATUS_FAILED;
				}
			}
		}else{
			if(serviceResponse.contains(successKeyValue) & ConfigurationConstants.CONFIG_SUCCESSKEY_PRESENT.equals(successKey)){
				callLogStatus = ConfigurationConstants.CALLLOG_STATUS_SUCCESS;
				
			}else if(!serviceResponse.contains(successKeyValue) & ConfigurationConstants.CONFIG_SUCCESSKEY_DOESNOT_PRESENT.equals(successKey)){
				callLogStatus = ConfigurationConstants.CALLLOG_STATUS_SUCCESS;
				
			}else{
				//Updated status based on the HTTP status currently only failed considered
				if(HttpStatus.GATEWAY_TIMEOUT == statusCode){
					callLogStatus = ConfigurationConstants.CALLLOG_STATUS_TIMEOUT;
				}else{
					callLogStatus = ConfigurationConstants.CALLLOG_STATUS_FAILED;
				}
			}
		}

		if(!StringUtils.isEmpty(callLogStatus)){
			Integer statusValue = callLogStatus;
			updateIntegrationCallResponse(integrationLogBean, configCallLogId, serviceResponse, statusValue);
			// Is failed escalation required
			if(isStatusFailed(configInfo, integrationLogBean)){
				configEscalationList = configEscalationList.stream().filter(configEscalation -> 
				(statusValue.equals(configEscalation.getStatus()) && 
									!ConfigurationConstants.ESCALATION_MAILTYPE_NONE.equals(configEscalation.getMailType()))).collect(Collectors.toList());
			}else{
				//any escalation mapping trigger them
				configEscalationList = configEscalationList.stream().filter(configEscalation -> 
				(statusValue.equals(configEscalation.getStatus()) && 
							!ConfigurationConstants.ESCALATION_MAILTYPE_NONE.equals(configEscalation.getMailType()))).collect(Collectors.toList());
			}
		}
		
		Mail mailParams = new Mail();
		//Send mail based on the escalation types
		final IntegrationCallLogsBean integrationCallLogBean = callLogDAO.getIntegrationCallLogById(configCallLogId);
		configEscalationList.forEach(configEscalation -> {
			if(ConfigurationConstants.ESCALATION_MAILTYPE_EVERYTIME.equals(configEscalation.getMailType())){
				ConfigurationUtils.setMailParams(mailParams,configEscalation,integrationCallLogBean);
				mailTask.sendMail(mailParams);
			}else if(ConfigurationConstants.ESCALATION_MAILTYPE_FIRSTTIME.equals(configEscalation.getMailType())
					&& integrationCallLogBean.getRetryCount() == 0){
				ConfigurationUtils.setMailParams(mailParams,configEscalation,integrationCallLogBean);
				mailTask.sendMail(mailParams);
			}else if(ConfigurationConstants.ESCALATION_MAILTYPE_RETRY_ONLY.equals(configEscalation.getMailType())
					&& integrationCallLogBean.getRetryCount() >= 1){
				ConfigurationUtils.setMailParams(mailParams,configEscalation,integrationCallLogBean);
				mailTask.sendMail(mailParams);
			}else if(ConfigurationConstants.ESCALATION_MAILTYPE_AFTER_LAST_RETRY.equals(configEscalation.getMailType())
					&& configInfo.getRetryCount().equals(integrationCallLogBean.getRetryCount())){
				ConfigurationUtils.setMailParams(mailParams,configEscalation,integrationCallLogBean);
				mailTask.sendMail(mailParams);
			}
		});

		callLogDAO.updateIntegrationCallStatistics(integrationLogBean);

		return configInfo;
	}
	
	private ResponseEntity<String> hitServiceURL(boolean isRetry, ConfigurationInfo configInfo, String configFieldRequest, boolean isUpdate){
		HttpHeaders headers = new HttpHeaders();
		if(!StringUtils.isEmpty(configInfo.getAcceptType()) && configInfo.getAcceptType().equals(ConfigurationConstants.TYPE_JSON)){ 
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		}else{
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
		}
		if(ConfigurationConstants.BASIC_AUTH.equals(configInfo.getAuthenticationType())){
			String basicCreds = configInfo.getUsername()+":"+configInfo.getPassword();
			byte[] plainCredsBytes = basicCreds.getBytes();
			byte[] base64CredsBytes = Base64.encode(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			headers.add("Authorization", " Basic " + base64Creds);
		}else{
			
		}
		HttpEntity<String> configRequest = new HttpEntity<>(configFieldRequest,headers);
		String url = isUpdate && configInfo.getRestrictUpdateUrl() ? configInfo.getUrlForUpdate(): configInfo.getSubmitUrl();
		
		ResponseEntity<String> serviceResponseEntity = restTemplate.postForEntity(url, configRequest, String.class);

		return serviceResponseEntity;
	}

	private void updateIntegrationCallResponse(IntegrationCallLogsBean integrationLogBean, 
			Integer configCallLogId, String serviceResponse, int status){
		//update the response into configurationCallstatistics and call audit tables
		integrationLogBean.setId(configCallLogId);
		integrationLogBean.setResponseData(serviceResponse);
		//Set the status value that is set in escalation mappings
		integrationLogBean.setStatus(status);
		integrationLogBean.setModifiedBy(1);
		callLogDAO.updateIntegrationCallLog(integrationLogBean);
	}
	
	//Escalation Type per failed/%failed/DurationFailed
	private boolean isStatusFailed(ConfigurationInfo configInfo, IntegrationCallLogsBean integrationLogBean ){
		boolean isFailedEscalationRequired =false;
		int statusValue = integrationLogBean.getStatus();
		if(ConfigurationConstants.CALLLOG_STATUS_FAILED.equals(statusValue)){
			if(configInfo.getEscalationType().equals(1)){ // Per Failed
				isFailedEscalationRequired = true;
			}else if(configInfo.getEscalationType().equals(2)){//% Failed
				//%failed based on maxretry count of configuration to integration failed numbers
				int retryPercent = (integrationLogBean.getRetryCount()/configInfo.getRetryCount())*100;
				if(ConfigurationConstants.ESCALATION_FAILED_PERCENT.equals(retryPercent)){
					isFailedEscalationRequired=true;
				}
			}else if(configInfo.getEscalationType().equals(3)){// Duration Failed
				int statusCount = callLogDAO.getCallLogsStatusCountByConfigId(configInfo.getId(),ConfigurationConstants.CALLLOG_STATUS_FAILED, ConfigurationConstants.ESCALATION_FAILED_DURATION_MINS);
				
				if(ConfigurationConstants.ESCALATION_FAILED_COUNT.equals(statusCount)){ 
					isFailedEscalationRequired=true;
				}
			}
		}
		return isFailedEscalationRequired;
	}

	public WorkSpec getFormIdFromWorkId(int workId , int companyId) {
		return configDAO.getFormIdFromWorkId(workId, companyId);
		
	}
}
