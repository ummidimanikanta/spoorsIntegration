/**
 * 
 */
package com.spoors.integration.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ayansys.effort.beans.ConfigurationListFilterJson;
import com.ayansys.effort.util.Api;
import com.ayansys.effort.util.SecurityUtils;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.spoors.integration.ConfigurationConstants;
import com.spoors.integration.beans.ConfigSearch;
import com.spoors.integration.beans.ConfigurationInfo;
import com.spoors.integration.beans.CustomerField;
import com.spoors.integration.beans.EmployeeField;
import com.spoors.integration.beans.FormAndWorkFieldMappingBean;
import com.spoors.integration.beans.FormAndWorkFieldMappingListBean;
import com.spoors.integration.beans.FormFieldSpec;
import com.spoors.integration.beans.FormSpec;
import com.spoors.integration.beans.WorkSpec;
import com.spoors.integration.manager.ConfigurationCallLogManager;
import com.spoors.integration.manager.ConfigurationManager;

/**
 * @author DevUser
 *
 */
@RequestMapping("/configure")
@Controller
public class ConfigurationController {

	public static Logger log = LoggerFactory.getLogger(ConfigurationController.class); 
	
	@Autowired
	ConfigurationManager configManager;
	
	@Autowired
	ConfigurationCallLogManager configCallLogManager;
		
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView  getAllConfiguration(){
		ModelAndView modelAndView = new ModelAndView("configurationList");
		return modelAndView;
	}	
	
	@RequestMapping(value="/{id}/{isEnabled}", method=RequestMethod.GET,produces="application/json")
	public  ResponseEntity<String> enableDisableConfigurationById(HttpServletRequest  request,
			@PathVariable("id") String pushId, @PathVariable("isEnabled") String isEnabled){
		//get the user id val from sessiondata
		Integer userId=1;
		int resultCnt = configManager.enableDisableConfigurationById(pushId, isEnabled, userId);
		if(resultCnt!=0)
			return new ResponseEntity<String>("{\"response\":\"SUCCESS\"}",HttpStatus.OK);
		else
			return new ResponseEntity<String>("{\"response\":\"FAIL\"}",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/list/dataJson", method=RequestMethod.POST,produces="application/json")
	public @ResponseBody String  getConfigurationData(HttpServletRequest  request,
			@RequestParam(value = "searchJsonData", required = false) String searchJsonData,
			@RequestParam(value = "page", required = false, defaultValue = "1") String pageStr){
		
		
		ConfigSearch configSearch = null;
		
		try {
			if(searchJsonData != null && searchJsonData.length() != 0)
				configSearch = (ConfigSearch) Api.fromJson(searchJsonData, ConfigSearch.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int totalRecords = configManager.findCountPushConfiguration(configSearch);
		
		int pageNumber = Integer.parseInt(pageStr);
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
		if (null != request.getParameter("iDisplayStart"))
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength) + 1;
		
		int offset = (pageNumber - 1)*pageDisplayLength;
		
		
		List<ConfigurationInfo> configList = configManager.getAllConfiguration(offset,pageDisplayLength,configSearch);
		
		ConfigurationListFilterJson configJsonObject = new ConfigurationListFilterJson();
		configJsonObject.setiTotalDisplayRecords(totalRecords);

		
		if(totalRecords%pageDisplayLength==0)
			configJsonObject.setiTotalRecords((int)(totalRecords/pageDisplayLength));
		else
			configJsonObject.setiTotalRecords((int)(totalRecords/pageDisplayLength)+1);
		
		configJsonObject.setAaData(configList);

		String configJson = "false";
		try {
			configJson = Api.toJson(configJsonObject);
			if (!Api.isEmptyString(configJson)) {
				configJson = configJson.replace("<", "&lt;").replace(">", "&gt;");
			}
		} catch (JsonGenerationException e) {
			log.error(e.getLocalizedMessage());
		} catch (JsonMappingException e) {
			log.error(e.getLocalizedMessage());
		} catch (IOException e) {
			log.error(e.getLocalizedMessage());
		}
		return configJson;
	}

	
	@RequestMapping(value="/list/{isEnabled}", method=RequestMethod.POST)
	public String  getEnableOrDisableConfigurationData(HttpServletRequest  request,
			Model model,
			@PathVariable("isEnabled") String isEnabled,
			@RequestParam(value = "ids", required = false) String pushIds){
																								
		
		Integer userId = 1;
		int resultCount = configManager.enableDisableConfigurationByIds(pushIds,isEnabled, userId); 
		if(resultCount > 0) {
			model.addAttribute("message", "Updated Successfully");
		}else {
			model.addAttribute("message", "Some Went Wrong");
		}
		
		return "redirect:/configure/list";
	}	
	
	
	@RequestMapping(value="/navAdd", method=RequestMethod.GET)
	public ModelAndView  getAddConfiguration(@RequestParam(value = "pushId", required = false) String pushId,
			@RequestParam(value = "disableAllInputs", required = false,defaultValue = "false") boolean disableAllInputs){
		ModelAndView modelAndView = new ModelAndView("addConfiguration");
		Map<String, String> triggerSourceMap = null;
		Map<Integer, String> companyMap = null;
		if(!StringUtils.isEmpty(pushId)){
			ConfigurationInfo configInfo = configManager.getConfigurationById(pushId);
			triggerSourceMap = configManager.findSourceByTriggerType(configInfo.getCompanyId(), configInfo.getTriggerType());
			modelAndView.addObject("triggerSourceMap", triggerSourceMap);
			modelAndView.addObject("configInfo", configInfo);
		}
		companyMap = configManager.findAllCompany();
		modelAndView.addObject("companyMap", companyMap);
		modelAndView.addObject("disableAllInputs", disableAllInputs);
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE )
	public String addConfiguration(@RequestParam(value = "isSaveThenNewAgain", required = false) Boolean isSaveThenNewAgain, 
			ConfigurationInfo configInfo){
		try {
			configManager.addConfiguration(configInfo);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		if(isSaveThenNewAgain)
			return "redirect:navAdd";
		else
			return "redirect:list";
	}

	@RequestMapping(value="/update", method=RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE )
	public String updateConfiguration(@RequestParam(value = "isSaveThenNewAgain", required = false) Boolean isSaveThenNewAgain,
			ConfigurationInfo configInfo){
		try {
			configManager.updateConfiguration(configInfo);
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}
		if(isSaveThenNewAgain)
			return "redirect:navAdd";
		else
			return "redirect:list";
	}

	@RequestMapping(value="/delete/{configId}", method=RequestMethod.POST )
	public ResponseEntity<String> deleteConfiguration(@PathVariable("configId") String configId){
		int resultCnt = configManager.deleteConfiguration(configId);
		if(resultCnt!=0)
			return new ResponseEntity<String>("{\"response\":\"SUCCESS\"}",HttpStatus.OK);
		else
			return new ResponseEntity<String>("{\"response\":\"FAIL\"}",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@RequestMapping(value="/triggerSources/{companyId}/{type}", method=RequestMethod.POST)
	public @ResponseBody Map<String, String> findSourceByTriggerType(@PathVariable("companyId") Integer companyId,
			@PathVariable("type") Integer type){
		return configManager.findSourceByTriggerType(companyId, type);
	}
	
	
	@RequestMapping(value = "/field/mappings/{configId}", method = RequestMethod.GET)
	public String configFieldMappings(Model model, HttpSession httpSession,
			@PathVariable Integer configId,
			@RequestParam(value = "success", required = false) String success,
			@RequestParam(value = "triggerType", required = false) Integer triggerType) throws JsonGenerationException, JsonMappingException, IOException {

		success = SecurityUtils.stripInvalidCharacters(success, SecurityUtils.INPUT_TYPE_TEXT);
		//triggerType = SecurityUtils.stripInvalidCharacters(triggerType, SecurityUtils.INPUT_TYPE_NUMBER);
		ConfigurationInfo configInfo = configManager.getConfigurationById(configId.toString());
		long formSpecId = 0;
		String formUniqueIdOrWorkSpecId = configInfo.getTriggerId();
		if(ConfigurationConstants.TRIGGER_TYPE_WORKS.equals(triggerType)) {
			WorkSpec workSpec = configManager.findWorkSpecById(Long.valueOf(formUniqueIdOrWorkSpecId));
			FormSpec formSpec = configManager.getFormSpecByUniqueIdAndCompanyId(workSpec.getFormSpecUniqueId(),workSpec.getCompanyId());
			formSpecId = formSpec.getFormSpecId();
		}else{
			FormSpec formSpec = configManager.getFormSpecByUniqueIdAndCompanyId(formUniqueIdOrWorkSpecId,configInfo.getCompanyId());
			formSpecId = formSpec.getFormSpecId();
		}
		
		List<FormFieldSpec> formFieldSpecs = configManager.getFormFieldSpecs(formSpecId);
		List<EmployeeField> employeeFields = configManager.getEmployeeFieldsByCompanyId(configInfo.getCompanyId());
		List<CustomerField> customerFields=configManager.getCustomerFieldsForCompany(configInfo.getCompanyId());
		
		List<FormFieldSpec> resolvedFormFieldSpecs = configManager.removeUnSupportedFieldTypes(formFieldSpecs);
		
		List<FormAndWorkFieldMappingBean> formAndWorkFieldMappingBeans = configCallLogManager.getConfigFieldsByConfigId(configId);
		
		model.addAttribute("formFieldSpecs", resolvedFormFieldSpecs);
		model.addAttribute("employeeFields", employeeFields);
		model.addAttribute("customerFields", customerFields);
		model.addAttribute("formAndWorkFieldMappingListBean", new FormAndWorkFieldMappingListBean());
		model.addAttribute("formAndWorkFieldMappingBeansJson", Api.toJson(formAndWorkFieldMappingBeans));
		model.addAttribute("configId", configId);
		
		
		return "addFieldMapping";
	}
	
	@RequestMapping(value = "/save/field/mapping", method = RequestMethod.POST)
	public String saveConfigureFieldMapping(Model model,
			HttpSession httpSession,
			@ModelAttribute("formAndWorkFieldMappingListBean") FormAndWorkFieldMappingListBean formAndWorkFieldMappingListBean,
			@RequestParam(value = "success", required = false) String success,
			@RequestParam(value = "configId", required = false) int configId) throws SQLException {
		
		success = SecurityUtils.stripInvalidCharacters(success, SecurityUtils.INPUT_TYPE_TEXT);
		
		configManager.saveConfigureFieldMapping(formAndWorkFieldMappingListBean,configId);
		
		return "redirect:/configure/list";
	}
}
