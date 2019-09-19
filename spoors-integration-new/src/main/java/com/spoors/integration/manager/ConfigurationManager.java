/**
 * 
 */
package com.spoors.integration.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spoors.integration.beans.ConfigEnable;
import com.spoors.integration.beans.ConfigSearch;
import com.spoors.integration.beans.ConfigurationInfo;
import com.spoors.integration.beans.CustomerField;
import com.spoors.integration.beans.EmployeeField;
import com.spoors.integration.beans.FormAndWorkFieldMappingBean;
import com.spoors.integration.beans.FormAndWorkFieldMappingListBean;
import com.spoors.integration.beans.FormFieldSpec;
import com.spoors.integration.beans.FormSpec;
import com.spoors.integration.beans.WorkSpec;
import com.spoors.integration.dao.ConfigurationDAO;

/**
 * @author DevUser
 *
 */
@Service
public class ConfigurationManager {

	public static Logger log = LoggerFactory.getLogger(ConfigurationManager.class); 
	
	@Autowired
	ConfigurationDAO configDAO;
	
	/**
	 * 
	 * @return
	 */
	public List<ConfigurationInfo> getAllConfiguration(int offset,int displayRecords,ConfigSearch configSearch){
		
		return (List<ConfigurationInfo>)configDAO.findAllPushConfiguration(offset,displayRecords,configSearch);
	}
	
	public Integer findCountPushConfiguration(ConfigSearch configSearch) {
		return configDAO.findCountPushConfiguration(configSearch);
	}

	public boolean addConfiguration(ConfigurationInfo configInfo) throws SQLException {
		ConfigEnable configEnable = new ConfigEnable();
		configEnable.setEnable(configInfo.getIsEnabled());
		configEnable.setCreatedBy(configInfo.getCreatedBy());
		configInfo.setConfigEnable(configEnable);		
		int count = configDAO.savePushConfig(configInfo);	
		return count!=0 ? true: false;
	}

	public boolean updateConfiguration(ConfigurationInfo configInfo) throws SQLException {
		
		ConfigEnable configEnable = new ConfigEnable();
		configEnable.setEnable(configInfo.getIsEnabled());
		configEnable.setModifiedBy(configInfo.getModifiedBy());
		configInfo.setConfigEnable(configEnable);
		int count = configDAO.updatePushConfig(configInfo);	
		return count!=0 ? true: false;
		
	}

	public ConfigurationInfo getConfigurationById(String pushId) {
		return configDAO.findPushConfigurationById(pushId);
	}

	public Map<String,String> findSourceByTriggerType(Integer companyId, Integer type) {
		return configDAO.findSourceByTriggerType(companyId, type);
		
	}

	public Integer enableDisableConfigurationById(String pushId, String isEnabled, Integer userId) {
		// TODO Auto-generated method stub
		boolean enableVal = Boolean.valueOf(isEnabled);
		return configDAO.enableDisableConfigurationById(pushId, enableVal, userId);
	}

	public Map<Integer, String> findAllCompany() {
		return configDAO.findAllCompany();
	}
	
	public Map<Integer, String> findCompanyById(Integer companyId) {
		return configDAO.findCompanyById(companyId);
	}

	public List<ConfigurationInfo> getEnableOrDisableConfiguration(String isEnabled) {
		int enableVal = isEnabled.equalsIgnoreCase("true") ? 1: 0;
		return configDAO.getEnableOrDisableConfiguration(enableVal);
	}
	
	public List<FormFieldSpec> removeUnSupportedFieldTypes(List<FormFieldSpec> formFieldSpecs) {
        List<FormFieldSpec> resolvedFormFieldSpecs = new ArrayList<FormFieldSpec>();
		for(FormFieldSpec formFieldSpec :formFieldSpecs) {
			if(formFieldSpec.getFieldType() == 1 || formFieldSpec.getFieldType() == 2 || formFieldSpec.getFieldType() == 3 ||
					formFieldSpec.getFieldType() == 4 || formFieldSpec.getFieldType() == 14 || formFieldSpec.getFieldType() == 7 || 
					formFieldSpec.getFieldType() == 8 || formFieldSpec.getFieldType() == 9 || formFieldSpec.getFieldType() == 10 || 
					formFieldSpec.getFieldType() == 11 || formFieldSpec.getFieldType() == 14 || formFieldSpec.getFieldType() == 15 || 
					formFieldSpec.getFieldType() == 31) {
				
				resolvedFormFieldSpecs.add(formFieldSpec);
			}
			
		}
		return resolvedFormFieldSpecs;
	}
	
	public List<FormFieldSpec> getFormFieldSpecs(long formSpecId) {
		return configDAO.getFormFieldSpecs(formSpecId);
	}
	
	 public List<EmployeeField> getEmployeeFieldsByCompanyId(int companyId){
	    	
	    	List<EmployeeField> employeeFields = configDAO.getEmployeeFieldsByCompanyId(companyId);
	    	if(employeeFields.isEmpty()) {
	    		employeeFields = configDAO.getDefaultEmployeeFields();
	    	}
	    	return employeeFields;
	    			
	    }
	 public List<CustomerField> getCustomerFieldsForCompany(Integer companyId) {
				List<CustomerField> customerFields=new ArrayList<CustomerField>();
				customerFields=configDAO.getCustomerFieldsForCompany(companyId);
			return customerFields;
		}

	 public void saveConfigureFieldMapping(FormAndWorkFieldMappingListBean formAndWorkFieldMappingListBean,int configId) throws SQLException {
		 
		 configDAO.deleteConfigFieldsById(configId);
		 
		if (formAndWorkFieldMappingListBean != null
				&& formAndWorkFieldMappingListBean.getFormAndWorkFieldMappingBeans() != null
				&& formAndWorkFieldMappingListBean.getFormAndWorkFieldMappingBeans().size() > 0) {
			 
			for(FormAndWorkFieldMappingBean formAndWorkFieldMappingBean: formAndWorkFieldMappingListBean.getFormAndWorkFieldMappingBeans()) {
				if(formAndWorkFieldMappingBean.getFieldType() != null && formAndWorkFieldMappingBean.getFieldType() != 0)
					configDAO.saveConfigFields(formAndWorkFieldMappingBean,configId);
			}
		 }
	 }

	public int deleteConfiguration(String configId) {
		return configDAO.deleteConfiguration(configId);
	}
	
	public WorkSpec findWorkSpecById(long id) {
		WorkSpec workSpec = null;
		try {
			workSpec = configDAO.findWorkSpecById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workSpec;

	}

	public FormSpec getFormSpecByUniqueIdAndCompanyId(String uniqueId,Integer companyId) 
	{
		return configDAO.getFormSpecByUniqueIdAndCompanyId(uniqueId,companyId);
	}

	
	public Integer enableDisableConfigurationByIds(String pushIds, String isEnabled, Integer userId) {
		boolean enableVal = Boolean.valueOf(isEnabled);
		return configDAO.enableDisableConfigurationByIds(pushIds, enableVal, userId);
	}
	
	
}
