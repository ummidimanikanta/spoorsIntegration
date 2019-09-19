/**
 * 
 */
package com.spoors.integration.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ayansys.effort.util.Api;
import com.spoors.integration.ConfigurationConstants;
import com.spoors.integration.beans.ConfigSearch;
import com.spoors.integration.beans.ConfigurationInfo;
import com.spoors.integration.beans.CustomerField;
import com.spoors.integration.beans.EmployeeField;
import com.spoors.integration.beans.FormAndWorkFieldMappingBean;
import com.spoors.integration.beans.FormField;
import com.spoors.integration.beans.FormFieldSpec;
import com.spoors.integration.beans.FormSpec;
import com.spoors.integration.beans.WorkSpec;

/**
 * @author Anand
 *
 */
@Repository
public class ConfigurationDAO {

	public static Logger log = LoggerFactory.getLogger(ConfigurationDAO.class); 
	
	@Autowired
	@Qualifier("spoorsJdbcTemplate")
	JdbcTemplate spoorsJdbcTemplate;
	
	@Autowired
	@Qualifier("spoorsNamedJdbcTemplate")
	NamedParameterJdbcTemplate spoorsNamedJdbcTemplate;
	
	@Autowired
	@Qualifier("effortJdbcTemplate")
	JdbcTemplate effortJdbcTemplate;
	
	public List<ConfigurationInfo> findAllPushConfiguration(Integer offset,int size,ConfigSearch configSearch) {
		
		String conditions = "";
	     String deleted = "pc.isDeleted=0";

		if(configSearch != null) {
		if(!Api.isEmptyString(configSearch.getName())){
	
		conditions = "pc.name LIKE '%"+configSearch.getName().trim()+"%'";
		}
	
		if(configSearch.getTriggerType() != 0) {
	
		if(!Api.isEmptyString(conditions)) {
		conditions +=" AND pc.triggerType ="+configSearch.getTriggerType();
		}else {
		conditions =" pc.triggerType ="+configSearch.getTriggerType();
		}
		}
	
		if(configSearch.getDeleted() != null && configSearch.getDeleted()) {
		deleted = "";
	
		if(!Api.isEmptyString(conditions)) {
		conditions +=" AND pc.isDeleted="+configSearch.getDeleted();
		}else {
		conditions ="pc.isDeleted="+configSearch.getDeleted();
		}
		}
	
		if(configSearch.getEnable() != null) {
		if(!Api.isEmptyString(conditions)) {
		conditions +=" AND ec.enable ="+configSearch.getEnable();
		}else {
	
		conditions ="ec.enable ="+configSearch.getEnable();
		}
		}
		}
	
		if(!Api.isEmptyString(conditions)) {
	
		conditions = " WHERE "+conditions;
	
		if(configSearch.getDeleted() == null) {
		conditions += " AND "+deleted;
		}
		} else {
		conditions = " WHERE "+deleted;
		}
		
		String sql = "SELECT pc.id, pc.name, comp.name company, "
				+ " pc.triggerType, ts.source triggersource,"
				+ " CASE WHEN ec.enable = 1 THEN 'TRUE' ELSE 'FALSE'"
				+ " END isEnabled, pc.retryCount "
				+ " FROM pushconfiguration pc LEFT JOIN company comp ON pc.companyId=comp.id"
				+ " LEFT JOIN triggersource ts ON pc.triggerId=ts.id "
				+ " LEFT JOIN enableconfiguration ec ON pc.enableId=ec.id "
				+ " :conditions "
				+ " ORDER BY pc.id DESC LIMIT ?,?";
		
		sql = sql.replace(":conditions",conditions);
		
        List<ConfigurationInfo> listConfig = spoorsJdbcTemplate.query(sql,new Object[] {offset,size}, new RowMapper<ConfigurationInfo>() {
 
            public ConfigurationInfo mapRow(ResultSet rs, int rowNumber) throws SQLException {
            	ConfigurationInfo configInfo = new ConfigurationInfo();
            	configInfo.setId(rs.getInt("id"));
            	configInfo.setName(rs.getString("name"));
            	configInfo.setCompany(rs.getString("company"));
            	configInfo.setIsEnabled(rs.getBoolean("isEnabled"));
            	configInfo.setTriggerType(rs.getInt("triggerType"));
            	configInfo.setTriggerSource(rs.getString("triggersource"));
            	configInfo.setRetryCount(rs.getInt("retryCount"));
                return configInfo;
            }
             
        });
        return listConfig;
	}
	
	public Integer findCountPushConfiguration(ConfigSearch configSearch) {

		String sql = "SELECT count(pc.id) as count "
				+ " FROM pushconfiguration pc LEFT JOIN company comp ON pc.companyId=comp.id"
				+ " LEFT JOIN triggersource ts ON pc.triggerId=ts.id "
				+ " LEFT JOIN enableconfiguration ec ON pc.enableId=ec.id "
				+ " :conditions";
		
		String conditions = "";
	    String deleted = "pc.isDeleted=0";
	     
		if(configSearch != null) {
			if(!Api.isEmptyString(configSearch.getName())){
		
			conditions = "pc.name LIKE '%"+configSearch.getName().trim()+"%'";
			}
		
			if(configSearch.getTriggerType() != 0) {
		
			if(!Api.isEmptyString(conditions)) {
			conditions +=" AND pc.triggerType ="+configSearch.getTriggerType();
			}else {
			conditions =" pc.triggerType ="+configSearch.getTriggerType();
			}
			}
		
			if(configSearch.getDeleted() != null && configSearch.getDeleted()) {
			deleted = "";
		
			if(!Api.isEmptyString(conditions)) {
			conditions +=" AND pc.isDeleted="+configSearch.getDeleted();
			}else {
			conditions ="pc.isDeleted="+configSearch.getDeleted();
			}
			}
		
			if(configSearch.getEnable() != null) {
			if(!Api.isEmptyString(conditions)) {
			conditions +=" AND ec.enable ="+configSearch.getEnable();
			}else {
		
			conditions ="ec.enable ="+configSearch.getEnable();
			}
			}
			}
		
			if(!Api.isEmptyString(conditions)) {
		
			conditions = " WHERE "+conditions;
		
			if(configSearch.getDeleted() == null) {
			conditions += " AND "+deleted;
			}
			} else {
			conditions = " WHERE "+deleted;
			}
		
			sql = sql.replace(":conditions",conditions);
			
		int pushConfiCountcount = spoorsJdbcTemplate.queryForObject(
				sql, new Object[] {  }, Integer.class);
	   
             
        return pushConfiCountcount;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Integer savePushConfig(ConfigurationInfo configInfo) throws SQLException {
		
		String enableSql = "INSERT INTO ENABLECONFIGURATION (ENABLE, CREATEDBY, CREATEDTIME) "
				+ "VALUES (:enable, :createdBy, UTC_TIMESTAMP())";
		KeyHolder holder = new GeneratedKeyHolder();	
		MapSqlParameterSource insertEnableParameters = new MapSqlParameterSource();
		insertEnableParameters.addValue("enable", configInfo.getConfigEnable().isEnable());
		insertEnableParameters.addValue("createdBy", configInfo.getCreatedBy());
		
		spoorsNamedJdbcTemplate.update(enableSql, insertEnableParameters,holder);				
	
		String sql = "INSERT INTO PUSHCONFIGURATION " +
				"(NAME, COMPANYID, ENABLEID, TRIGGERTYPE, TRIGGERID, INVOKETYPE, CONTENTTYPE, ACCEPTTYPE, SUBMITURL, URLFORUPDATES, IGNORESSL,"
				+ " AUTHENTICATIONTYPE, USERNAME, PASSWORD, FORMDATAPUSHTYPE, APPROVEFORMDATA, RETRYCOUNT, SUCCESSKEY, "
				+ " SUCCESSKEYVALUE, RESTRICTUPDATEURL, ESCALATIONTYPE, CREATEDBY, CREATEDTIME) "
				+ "VALUES (:name, :companyId, :enableId, :triggerType, :triggerId, :invokeType,:contentType, :acceptType, :submitURL,"
				+ " :urlForUpdate, :ignoreSSL, :authenticationType, :username, :password, :formDataPushType, :approveFormData, :retryCount,"
				+ " :successKey, :successKeyValue, :restrictUpdateURL,:escalationType, :createdBy, UTC_TIMESTAMP())";

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("name", configInfo.getName());
		parameters.put("companyId", configInfo.getCompanyId());
		parameters.put("enableId", holder.getKey().intValue());
		parameters.put("triggerType", configInfo.getTriggerType());
		parameters.put("triggerId", configInfo.getTriggerId());
		parameters.put("invokeType", configInfo.getInvokeType());
		parameters.put("contentType", configInfo.getContentType());
		parameters.put("acceptType", configInfo.getAcceptType());
		parameters.put("submitURL", configInfo.getSubmitUrl());
		parameters.put("urlForUpdate", configInfo.getUrlForUpdate());
		parameters.put("ignoreSSL", configInfo.getIgnoreSSL());
		parameters.put("authenticationType", configInfo.getAuthenticationType());
		parameters.put("username", configInfo.getUsername());
		parameters.put("password", configInfo.getPassword());
		parameters.put("formDataPushType", configInfo.getFormDataPushType());
		parameters.put("approveFormData", configInfo.getApproveFormData());
		parameters.put("retryCount", configInfo.getRetryCount());
		parameters.put("successKey", configInfo.getSuccessKey());
		parameters.put("successKeyValue", configInfo.getSuccessKeyValue());
		parameters.put("restrictUpdateURL", configInfo.getRestrictUpdateUrl());
		parameters.put("escalationType", configInfo.getEscalationType());
		parameters.put("createdBy", configInfo.getCreatedBy());
			
		int recordCnt = spoorsNamedJdbcTemplate.update(sql, parameters);
		

		return recordCnt;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Integer updatePushConfig(ConfigurationInfo configInfo) throws SQLException {
		
		
		String sql = "UPDATE PUSHCONFIGURATION SET NAME=:name, COMPANYID=:companyId, TRIGGERTYPE=:triggerType, TRIGGERID=:triggerId, "
				+ "  INVOKETYPE=:invokeType, CONTENTTYPE=:contentType, ACCEPTTYPE=:acceptType, SUBMITURL=:submitURL, URLFORUPDATES=:urlForUpdate,"
				+ " IGNORESSL=:ignoreSSL, AUTHENTICATIONTYPE=:authenticationType, USERNAME=:username, PASSWORD=:password, FORMDATAPUSHTYPE=:formDataPushType, "
				+ "APPROVEFORMDATA=:approveFormData, RETRYCOUNT=:retryCount, SUCCESSKEY=:successKey, SUCCESSKEYVALUE=:successKeyValue, "
				+ " RESTRICTUPDATEURL=:restrictUpdateURL, ESCALATIONTYPE=:escalationType, MODIFIEDBY=:modifiedBy, MODIFIEDTIME=UTC_TIMESTAMP()"
				+ " WHERE ID=:pushId";
				
		MapSqlParameterSource updateParameters = new MapSqlParameterSource();
		updateParameters.addValue("companyId", configInfo.getCompanyId());
		updateParameters.addValue("name", configInfo.getName());
		updateParameters.addValue("triggerType", configInfo.getTriggerType());
		updateParameters.addValue("triggerId", configInfo.getTriggerId());
		updateParameters.addValue("invokeType", configInfo.getInvokeType());
		updateParameters.addValue("contentType", configInfo.getContentType());
		updateParameters.addValue("acceptType", configInfo.getAcceptType());
		updateParameters.addValue("submitURL", configInfo.getSubmitUrl());
		updateParameters.addValue("urlForUpdate", configInfo.getUrlForUpdate());
		updateParameters.addValue("ignoreSSL", configInfo.getIgnoreSSL());
		updateParameters.addValue("authenticationType", configInfo.getAuthenticationType());
		updateParameters.addValue("username", configInfo.getUsername());
		updateParameters.addValue("password", configInfo.getPassword());
		updateParameters.addValue("formDataPushType", configInfo.getFormDataPushType());
		updateParameters.addValue("approveFormData", configInfo.getApproveFormData());
		updateParameters.addValue("retryCount", configInfo.getRetryCount());
		updateParameters.addValue("successKey", configInfo.getSuccessKey());
		updateParameters.addValue("successKeyValue", configInfo.getSuccessKeyValue());
		updateParameters.addValue("restrictUpdateURL", configInfo.getRestrictUpdateUrl());
		updateParameters.addValue("escalationType", configInfo.getEscalationType());		
		updateParameters.addValue("modifiedBy", configInfo.getModifiedBy());
		updateParameters.addValue("pushId", configInfo.getId());
				
		int recordCnt = spoorsNamedJdbcTemplate.update(sql, updateParameters);
		
		String enableSql = "UPDATE ENABLECONFIGURATION SET ENABLE=:enableVal, MODIFIEDBY=:modifiedBy, MODIFIEDTIME=UTC_TIMESTAMP()"
				+ " WHERE ID=(SELECT ENABLEID FROM PUSHCONFIGURATION WHERE id=:pushId)";
				
		MapSqlParameterSource updateEnableParameters = new MapSqlParameterSource();
		updateEnableParameters.addValue("enableVal", configInfo.getConfigEnable().isEnable());
		updateEnableParameters.addValue("modifiedBy", configInfo.getModifiedBy());
		updateEnableParameters.addValue("pushId", configInfo.getId());
		
		spoorsNamedJdbcTemplate.update(enableSql, updateEnableParameters);				
		return recordCnt;
	}

	public ConfigurationInfo findPushConfigurationById(String pushId) {
		String sql = "SELECT pc.id, pc.name NAME, comp.id companyId, comp.name company, pc.triggerType, pc.triggerId, ts.source, "
				+ " CASE WHEN ec.enable = 1 THEN 'TRUE' ELSE 'FALSE' "
				+ " END isEnabled, pc.retryCount,"
				+ " pc.invokeType, pc.contentType, pc.acceptType, pc.submitUrl, pc. urlForUpdates, pc.ignoreSsl,"
				+ " pc.authenticationType, pc.username, pc.password, pc.formDataPushType, pc.approveFormData, "
				+ " pc.successKey, pc.successKeyValue, pc.restrictUpdateUrl, pc.escalationType "
				+ " FROM pushconfiguration pc "
				+ " LEFT JOIN company comp ON pc.companyId=comp.id"
				+ " LEFT JOIN triggersource ts ON pc.triggerId=ts.id "
				+ " LEFT JOIN enableconfiguration ec ON pc.enableId=ec.id "
				+ " WHERE pc.id=:id AND  pc.isDeleted=0 ";
		List<ConfigurationInfo> listConfig = spoorsNamedJdbcTemplate.query(sql, new MapSqlParameterSource(
                "id", pushId), new RowMapper<ConfigurationInfo>() {
 
            public ConfigurationInfo mapRow(ResultSet rs, int rowNumber) throws SQLException {
            	ConfigurationInfo configInfo = new ConfigurationInfo();
            	configInfo.setId(rs.getInt("id"));
            	configInfo.setName(rs.getString("name"));
            	configInfo.setCompanyId(rs.getInt("companyId"));
            	configInfo.setCompany(rs.getString("company"));
            	configInfo.setIsEnabled(rs.getBoolean("isEnabled"));
            	configInfo.setTriggerType(rs.getInt("triggerType"));
            	configInfo.setTriggerId(rs.getString("triggerId"));
            	configInfo.setTriggerSource(rs.getString("source"));
            	configInfo.setRetryCount(rs.getInt("retryCount"));
            	configInfo.setInvokeType(rs.getInt("invokeType"));
            	configInfo.setContentType(rs.getInt("contentType"));
            	configInfo.setAcceptType(rs.getInt("acceptType"));
            	configInfo.setSubmitUrl(rs.getString("submitUrl"));
            	configInfo.setUrlForUpdate(rs.getString("urlForUpdates"));
            	configInfo.setIgnoreSSL(rs.getBoolean("ignoreSsl"));
            	configInfo.setAuthenticationType(rs.getInt("authenticationType"));
            	configInfo.setUsername(rs.getString("username"));
            	configInfo.setPassword(rs.getString("password"));
            	configInfo.setFormDataPushType(rs.getInt("formDataPushType"));
            	configInfo.setApproveFormData(rs.getInt("approveFormData"));
            	configInfo.setSuccessKey(rs.getInt("successKey"));
            	configInfo.setSuccessKeyValue(rs.getString("successKeyValue"));
            	configInfo.setRestrictUpdateUrl(rs.getBoolean("restrictUpdateUrl"));
            	configInfo.setEscalationType(rs.getInt("escalationType"));
           	
            	return configInfo;
            }             
        });
		if ( listConfig != null && listConfig.isEmpty() ){
			  return null;
			}else { // list contains exactly 1 element
			  return listConfig.get(0);
			}
	}

	public Map<String, String> findSourceByTriggerType(Integer companyId, Integer type) {
		String sql = null;
		if(ConfigurationConstants.TRIGGER_TYPE_WORKS.equals(type)){
			sql = "SELECT distinct workSpecId as id, workSpecTitle as source FROM `WorkSpecs` WHERE deleted=0 and companyId="+companyId;
		} else {
			sql = "SELECT distinct uniqueId as id, formTitle as source FROM `FormSpecs` WHERE deleted=0 and companyId="+companyId;
		}
		Map<String,String> triggerSourceMap = effortJdbcTemplate.query(sql, (ResultSet rs) -> {
		    Map<String,String> triggerSources = new HashMap<>();
		    while (rs.next()) {
		    	triggerSources.put(rs.getString("id"), rs.getString("source"));
		    }
		    return triggerSources;
		});
        return triggerSourceMap;
	}

	public Integer enableDisableConfigurationById(String pushId, Boolean enableVal, Integer userId) {
		String sql = "UPDATE ENABLECONFIGURATION SET ENABLE=:enableVal, "
				+ " MODIFIEDBY=:modifiedBy, MODIFIEDTIME=UTC_TIMESTAMP() "
				+ " WHERE ID= (SELECT ENABLEID FROM PUSHCONFIGURATION WHERE ID=:pushId)";
				
		MapSqlParameterSource updateParameters = new MapSqlParameterSource();
		updateParameters.addValue("enableVal", enableVal);
		updateParameters.addValue("modifiedBy", userId);
		updateParameters.addValue("pushId", pushId);
				
			int recordCnt = spoorsNamedJdbcTemplate.update(sql, updateParameters);
		return recordCnt;
	}

	public Map<Integer, String> findAllCompany() {
		String sql = "SELECT id, name FROM company";
		
		Map<Integer,String> companyMap = spoorsJdbcTemplate.query(sql, (ResultSet rs) -> {
		    Map<Integer,String> companys = new HashMap<>();
		    while (rs.next()) {
		    	companys.put(rs.getInt("id"), rs.getString("name"));
		    }
		    return companys;
		});
        return companyMap;
	}

	public Map<Integer, String> findCompanyById(Integer companyId) {
		String sql = "SELECT companyId, companyName FROM Companys WHERE companyId="+companyId;
		
		Map<Integer,String> companyMap = effortJdbcTemplate.query(sql, (ResultSet rs) -> {
		    Map<Integer,String> companys = new HashMap<>();
		    while (rs.next()) {
		    	companys.put(rs.getInt("companyId"), rs.getString("companyName"));
		    }
		    return companys;
		});
        return companyMap;
	}

	public List<ConfigurationInfo> getEnableOrDisableConfiguration(int enableVal) {
		
		String sql = "SELECT pc.id, pc.name, comp.name company, "
				+ " ts.type triggerType, ts.source triggersource,"
				+ " CASE WHEN ec.enable = 1 THEN 'TRUE' ELSE 'FALSE'"
				+ " END isEnabled, pc.retryCount "
				+ " FROM pushconfiguration pc LEFT JOIN company comp ON pc.companyId=comp.id"
				+ " LEFT JOIN triggersource ts ON pc.triggerId=ts.id "
				+ " JOIN enableconfiguration ec ON pc.enableId=ec.id "
				+ " WHERE pc.isDeleted=0 AND ec.enable="+enableVal
				+ " ORDER BY pc.id DESC ";
		
        List<ConfigurationInfo> listConfig = spoorsJdbcTemplate.query(sql, new RowMapper<ConfigurationInfo>() {
 
            public ConfigurationInfo mapRow(ResultSet rs, int rowNumber) throws SQLException {
            	ConfigurationInfo configInfo = new ConfigurationInfo();
            	configInfo.setId(rs.getInt("id"));
            	configInfo.setName(rs.getString("name"));
            	configInfo.setCompany(rs.getString("company"));
            	configInfo.setIsEnabled(rs.getBoolean("isEnabled"));
            	configInfo.setTriggerType(rs.getInt("triggerType"));
            	configInfo.setTriggerSource(rs.getString("triggersource"));
            	configInfo.setRetryCount(rs.getInt("retryCount"));
                return configInfo;
            }
        });
        return listConfig;
	}

	public ConfigurationInfo getConfigurationByKey(Map<String, String> configMap) {
		
		StringBuilder sql = new StringBuilder("SELECT pc.id, pc.name NAME, comp.id companyId, comp.name company, pc.triggerType, pc.triggerId, ts.source, "
				+ " CASE WHEN ec.enable = 1 THEN 'TRUE' ELSE 'FALSE' "
				+ " END isEnabled, pc.retryCount,"
				+ " pc.invokeType, pc.contentType, pc.acceptType, pc.submitUrl, pc. urlForUpdates, pc.ignoreSsl,"
				+ " pc.authenticationType, pc.username, pc.password, pc.formDataPushType, pc.approveFormData, "
				+ " pc.successKey, pc.successKeyValue, pc.restrictUpdateUrl, pc.escalationType "
				+ " FROM pushconfiguration pc "
				+ " LEFT JOIN company comp ON pc.companyId=comp.id"
				+ " LEFT JOIN triggersource ts ON pc.triggerId=ts.id "
				+ " LEFT JOIN enableconfiguration ec ON pc.enableId=ec.id "
				+ " WHERE pc.isDeleted=0 ");
		
				if(configMap.size()>0){
					AtomicInteger atomicCount = new AtomicInteger(0);
					configMap.forEach((k,v) -> {
						sql.append(" AND ");
						sql.append(" pc."+k+"='"+v+"'");
						atomicCount.getAndIncrement();
					});
				}
				log.info("get Connfiguration Key SQL Query"+sql.toString());
		 List<ConfigurationInfo> configInfoList = spoorsJdbcTemplate.query(sql.toString(), new RowMapper<ConfigurationInfo>() {
 
            public ConfigurationInfo mapRow(ResultSet rs, int rowNumber) throws SQLException {
            	ConfigurationInfo configInfo = new ConfigurationInfo();
            	configInfo.setId(rs.getInt("id"));
            	configInfo.setName(rs.getString("name"));
            	configInfo.setCompanyId(rs.getInt("companyId"));
            	configInfo.setCompany(rs.getString("company"));
            	configInfo.setIsEnabled(rs.getBoolean("isEnabled"));
            	configInfo.setTriggerType(rs.getInt("triggerType"));
            	configInfo.setTriggerId(rs.getString("triggerId"));
            	configInfo.setTriggerSource(rs.getString("source"));
            	configInfo.setRetryCount(rs.getInt("retryCount"));
            	configInfo.setInvokeType(rs.getInt("invokeType"));
            	configInfo.setContentType(rs.getInt("contentType"));
            	configInfo.setAcceptType(rs.getInt("acceptType"));
            	configInfo.setSubmitUrl(rs.getString("submitUrl"));
            	configInfo.setUrlForUpdate(rs.getString("urlForUpdates"));
            	configInfo.setIgnoreSSL(rs.getBoolean("ignoreSsl"));
            	configInfo.setAuthenticationType(rs.getInt("authenticationType"));
            	configInfo.setUsername(rs.getString("username"));
            	configInfo.setPassword(rs.getString("password"));
            	configInfo.setFormDataPushType(rs.getInt("formDataPushType"));
            	configInfo.setApproveFormData(rs.getInt("approveFormData"));
            	configInfo.setSuccessKey(rs.getInt("successKey"));
            	configInfo.setSuccessKeyValue(rs.getString("successKeyValue"));
            	configInfo.setRestrictUpdateUrl(rs.getBoolean("restrictUpdateUrl"));
            	configInfo.setEscalationType(rs.getInt("escalationType"));
           	
            	return configInfo;
            }     
        });
		 if ( configInfoList.isEmpty() ){
			  return null;
			}else { // list contains exactly 1 element
			  return configInfoList.get(0);
			}
	}	
	
	
	public List<FormAndWorkFieldMappingBean> getConfigFieldsById(Integer configId){
		String sql = "SELECT id, pushConfigId, sourceKey, destinationKey,fieldType,ignoreField, createdBy, createdTime, "
				+ " modifiedBy, modifiedTime "
				+ " FROM configurationfieldmappings "
				+ " WHERE pushConfigId=:id AND isDeleted = 0";
		return spoorsNamedJdbcTemplate.query(sql, new MapSqlParameterSource(
	            "id", configId), new RowMapper<FormAndWorkFieldMappingBean>() {

	        public FormAndWorkFieldMappingBean mapRow(ResultSet rs, int rowNumber) throws SQLException {
	        	FormAndWorkFieldMappingBean configField = new FormAndWorkFieldMappingBean();
	        	configField.setId(rs.getInt("id"));
	        	configField.setConfigId(rs.getInt("pushConfigId"));
	        	configField.setSourceKey(rs.getString("sourceKey"));
	        	configField.setDestinationKey(rs.getString("destinationKey"));
	        	configField.setCreatedBy(rs.getInt("createdBy"));
	        	configField.setCreatedDate(rs.getDate("createdTime"));
	        	configField.setModifiedBy(rs.getInt("modifiedBy"));
	        	configField.setModifiedDate(rs.getDate("modifiedTime"));
	        	configField.setFieldType(rs.getInt("fieldType"));
	        	configField.setIgnoreField(rs.getBoolean("ignoreField"));
	        	return configField;
	        }             
	    });
	}

	
	public List<FormField> getFormFieldByForm(long formId) {
		String sql = "SELECT fieldId, formId, formSpecId, fieldSpecId, fieldValue, createdTime, "
				+ "  modifiedTime, identifier, fieldType, uniqueId, displayValue, fieldLabel, "
				+ " skeletonFormFieldSpecId FROM FormFields WHERE formId = ?";
		List<FormField> formFields = effortJdbcTemplate.query(sql,
				new Object[] { formId }, new BeanPropertyRowMapper<FormField>(
						FormField.class));
		return formFields;
	}
	
	public List<FormFieldSpec> getFormFieldSpecs(long formSpecId) {
		
		String sql = "SELECT `FormFieldSpecs`.`fieldSpecId`, `uniqueId`, `FormFieldSpecs`.`formSpecId`, `FormFieldSpecs`.`fieldLabel`, "
				+ "`FormFieldSpecs`.`fieldType` FROM `FormFieldSpecs` "
				+ "LEFT JOIN `formValidations` ON( `FormFieldSpecs`.`fieldSpecId` = `formValidations`.`fieldSpecId`"
				+ " AND `formValidations`.`fieldType` = 1 ) WHERE `FormFieldSpecs`.`formSpecId` = ? ORDER BY `displayOrder`";
		List<FormFieldSpec> formFieldSpecs = effortJdbcTemplate.query(
				sql, new Object[] { formSpecId },
				new BeanPropertyRowMapper<FormFieldSpec>(FormFieldSpec.class));
		return formFieldSpecs;
	}
	
	public List<EmployeeField> getEmployeeFieldsByCompanyId(int companyId){
			
		    String sql = "SELECT efc.`id`, efc.`employeeStaticFieldId`, efc.`mandatory`  , efc.`companyId`, ef.`objectFieldName`, ef.`columnName`, "
		    		+ "ef.fieldType FROM `EmployeeStaticFieldsConfiguration` efc JOIN `EmployeeStaticFields` ef "
		    		+ "ON ef.`employeeStaticFieldId` = efc.`employeeStaticFieldId` WHERE efc.`companyId` = ?;";
			List<EmployeeField> employeeFields = new ArrayList<EmployeeField>();
			
			Object[] objArray = new Object[] {companyId};
			
			employeeFields = effortJdbcTemplate.query(sql, objArray,
	                 new BeanPropertyRowMapper<EmployeeField>(EmployeeField.class));
			return employeeFields;
		}
	public List<EmployeeField> getDefaultEmployeeFields(){
		
		List<EmployeeField> employeeFields = new ArrayList<EmployeeField>();
		String sql = "SELECT ef.`employeeStaticFieldId`, ef.`defualtMandatorycheck` as mandatory, ef.`objectFieldName`, ef.`columnName`, "
				+ "ef.fieldType FROM `EmployeeStaticFields` ef WHERE ef.`companyId` = -1";
		employeeFields = effortJdbcTemplate.query(sql, 
	             new BeanPropertyRowMapper<EmployeeField>(EmployeeField.class));
		return employeeFields;
	}
	
	public List<CustomerField> getCustomerFieldsForCompany(Integer companyId) {
	
		List<CustomerField> customerFields=new ArrayList<CustomerField>();
		String sql = "SELECT (Select cf.`fieldLabel` from `CustomerFields` cf where cf.`customerFieldId`=cc.customerFieldId)as `fieldLabel`,"
				+ "cc.`customerFieldId`,cc.`visibilityCheck`,cc.`mandatoryCheck`,cc.`uniqueCheck`,cc.`visibilityOnCheckin`,"
				+ "(Select cf.`disableMandatoryCheck` from `CustomerFields` cf where cf.`customerFieldId`=cc.customerFieldId)as "
				+ "`disableMandatoryCheck`,(Select cf.`disableVisibilityCheck` from `CustomerFields` cf "
				+ "where cf.`customerFieldId`=cc.customerFieldId)as `disableVisibilityCheck`,"
				+ "(Select cf.`disableUniqueCheck` from `CustomerFields` cf where cf.`customerFieldId`=cc.customerFieldId)as "
				+ "`disableUniqueCheck` FROM `CustomerFieldsConfiguration` cc WHERE cc.`companyId`=?";
		
		customerFields=effortJdbcTemplate.query(sql, new Object[]{companyId},
				new BeanPropertyRowMapper<CustomerField>(CustomerField.class));
	
		return customerFields;
	}
	
	
	public Integer deleteConfigFieldsById(Integer configId) {
		String sql = "UPDATE  `configurationfieldmappings` SET isDeleted = 1 WHERE pushConfigId = :configId";
				
		MapSqlParameterSource updateParameters = new MapSqlParameterSource();
		updateParameters.addValue("configId", configId);
				
			int recordCnt = spoorsNamedJdbcTemplate.update(sql, updateParameters);
		return recordCnt;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Integer saveConfigFields(FormAndWorkFieldMappingBean formAndWorkFieldMappingBean,int configureId) throws SQLException {
	
		String sql = "INSERT INTO `configurationfieldmappings` " +
				"(`pushConfigId`, `sourceKey`, `destinationKey`, `fieldType`, `ignoreField`, `createdBy`, `modifiedBy`,`createdTime`,`modifiedTime`)"
				+ " VALUES (:pushConfigId,:sourceKey,:destinationKey,:fieldType,:ignoreField,:createdBy,:modifiedBy,UTC_TIMESTAMP(),UTC_TIMESTAMP())";
			
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("pushConfigId",configureId);
		if(formAndWorkFieldMappingBean.getFieldType() == 7 || formAndWorkFieldMappingBean.getFieldType() == 15) {
			parameters.put("sourceKey", formAndWorkFieldMappingBean.getSourceSubKey());
		}else {
			
			parameters.put("sourceKey", formAndWorkFieldMappingBean.getSourceKey());
		}
		parameters.put("destinationKey", formAndWorkFieldMappingBean.getDestinationKey());
		parameters.put("fieldType", formAndWorkFieldMappingBean.getFieldType());
		parameters.put("ignoreField",formAndWorkFieldMappingBean.isIgnoreField());
		parameters.put("createdBy", 12345);
		parameters.put("modifiedBy", 12345);
		
		int recordCnt = spoorsNamedJdbcTemplate.update(sql, parameters);
		
	
		return recordCnt;
	}
	
	public int deleteConfiguration(String configId) {
		String sql = "UPDATE PUSHCONFIGURATION SET isDeleted=1 WHERE id = :configId";
		
		MapSqlParameterSource updateParameters = new MapSqlParameterSource();
		updateParameters.addValue("configId", configId);
		return spoorsNamedJdbcTemplate.update(sql, updateParameters);
	}	

	public WorkSpec findWorkSpecById(long id) {
		WorkSpec workSpec = null;
		try {
			
			String sql = "SELECT `workSpecId`, `workSpecTitle`, `workSpecDescription`, `formSpecUniqueId`, `createdBy`, `modifiedBy`, `createdTime`, "
					+ "`modifiedTime`, `companyId`, `isSystemDefined`, `purpose`, `allAccess`, `deleted`, `copiedFrom`, `skeletonWorkSpecId`, "
					+ "`productId`, `externalWorkSpecId`, `mobileLayout`, `removeBlankLines`, `rejectionFormSpecUniqueId`, `captureRejectionReasons`, "
					+ "`workSharing`, `enableWorkCheckIn`, `enableWorkOwnerToPerformActions`, `syncIncompletedWorksForMobile`,"
					+ " `canRejectWorkInvitation`, `enableAssignActions`, `canRejectWork`, `globalSearch`, `enableWorkCreatorToPerformActions`,"
					+ " `deleteOnEndTimeComplete`, `canModifyWork`, `cleanOnWorkComplete`, `cleanUpNotActionableWorks`,"
					+ " `restrictHierarchyToPerformActions`, `openToCustomer`, `sendOnlyActionableWorksToManager`, `allowWorkCreationFromMobile`,"
					+ " `onlineWork`, `enableEndTimeDurationCheck`, `workEndTimeDuration`, `enableEndTimeDurationCheck`, `workEndTimeDuration`"
					+ " FROM `WorkSpecs` WHERE `workSpecId`=?";
			workSpec = effortJdbcTemplate.queryForObject(sql,
					new Object[] { id }, new BeanPropertyRowMapper<WorkSpec>(
							WorkSpec.class));

		} catch (org.springframework.dao.EmptyResultDataAccessException empty) {
			return workSpec;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return workSpec;

	}

	public FormSpec getFormSpecByUniqueIdAndCompanyId(String uniqueId,
			Integer companyId) 
	{
		FormSpec formSpec=null;
		String sql = "SELECT * FROM `FormSpecs` WHERE `uniqueId` = ? AND `companyId` = ?  AND `deleted` = 0 ORDER BY `formSpecId` DESC LIMIT 1";
		try{
			return effortJdbcTemplate.queryForObject(
					sql, new Object[] {uniqueId,companyId},new BeanPropertyRowMapper<FormSpec>(FormSpec.class));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return formSpec;
	}

	public WorkSpec getFormIdFromWorkId(int workId , int companyId) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM `Works` WHERE `workId` = ? and companyId=? and deleted=0 ";
			return effortJdbcTemplate.queryForObject(
					sql, new Object[] {workId, companyId},new BeanPropertyRowMapper<WorkSpec>(WorkSpec.class));
	}

	public FormSpec getFormSpecByFormId(Long formId) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM `FormSpecs` WHERE `formSpecId` = (select formSpecId from Forms where formId=? and deleted=0) and deleted=0 ";
			return effortJdbcTemplate.queryForObject(
					sql, new Object[] {formId},new BeanPropertyRowMapper<FormSpec>(FormSpec.class));
	}

	
	
	public Integer enableDisableConfigurationByIds(String pushIds, Boolean enableVal, Integer userId) {
		String sql = "UPDATE ENABLECONFIGURATION SET ENABLE=:enableVal, "
				+ " MODIFIEDBY=:modifiedBy, MODIFIEDTIME=UTC_TIMESTAMP() "
				+ " WHERE ID IN (SELECT ENABLEID FROM PUSHCONFIGURATION WHERE ID IN (:pushIds))";
		
		sql = sql.replace(":pushIds", pushIds);
				
		MapSqlParameterSource updateParameters = new MapSqlParameterSource();
		updateParameters.addValue("enableVal", enableVal);
		updateParameters.addValue("modifiedBy", userId);
				
			int recordCnt = spoorsNamedJdbcTemplate.update(sql, updateParameters);
		return recordCnt;
	}
	
}
