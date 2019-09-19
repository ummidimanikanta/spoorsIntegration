package com.spoors.integration.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spoors.integration.beans.ConfigurationInfo;
import com.spoors.integration.beans.IntegrationCallLogsBean;
import com.spoors.integration.manager.ConfigurationCallLogManager;
import com.spoors.integration.manager.ConfigurationManager;

@Controller
@RequestMapping("/integration")
public class ConfigCallLogController {

	public static Logger log = LoggerFactory.getLogger(ConfigCallLogController.class); 
	
	@Autowired
	ConfigurationCallLogManager callLogService;

	@Autowired
	ConfigurationManager configManager;
	
	@RequestMapping(value = "callLogs/list",method = RequestMethod.GET)
	public String listIntegrationCallLogsList(Model model, HttpSession httpSession,
			@RequestParam(value = "orderBy", required = false, defaultValue = "configurationName") String orderBy,
			@RequestParam(value = "order", required = false, defaultValue = "ASC") String order,
			@RequestParam(value = "page", required = false, defaultValue = "1") String pageStr
			) {
		//orderBy = SecurityUtils.stripInvalidCharacters(orderBy, SecurityUtils.INPUT_TYPE_TEXT);
		//order = SecurityUtils.stripInvalidCharacters(order, SecurityUtils.INPUT_TYPE_SQL_ORDER);
		//pageStr = SecurityUtils.stripInvalidCharacters(pageStr, SecurityUtils.INPUT_TYPE_NUMBER);
		//EffortSessionData sessionData = getEffortSessionData(httpSession);
		//WebUser webUser = sessionData.getWebUser();
	   
		//count is here total records in table
		//have to query for all records in table 
		long count = callLogService.getIntegrationCallLogsCount();
		int pageSize = 10;
		long totalPage = Math.round(Math.floor(count / pageSize));
		
		if (count % pageSize > 0 || count == 0) {
			totalPage++;
		}
		
		int page = Integer.parseInt(pageStr);
		int offset = (page - 1) * pageSize;
		
		 
		//orderBy,offset,pageSize
		 List<IntegrationCallLogsBean> integrationCallLogsBeanList = callLogService.getIntegrationCallLogsList(offset,pageSize);
		model.addAttribute("size", pageSize);
		model.addAttribute("page", page);
	    model.addAttribute("orderBy", orderBy);
		model.addAttribute("total", totalPage);
		model.addAttribute("integrationCallLogsBeanList", integrationCallLogsBeanList);

		return "integrationCallLogsList";
	}
	
	@RequestMapping(value = "callLogs/audit/list",method = RequestMethod.GET)
	public String listIntegrationCallAuditList(Model model, HttpSession httpSession,
			@RequestParam(value = "orderBy", required = false, defaultValue = "configurationName") String orderBy,
			@RequestParam(value = "order", required = false, defaultValue = "ASC") String order,
			@RequestParam(value = "page", required = false, defaultValue = "1") String pageStr
			) throws ParseException {
		/*orderBy = SecurityUtils.stripInvalidCharacters(orderBy, SecurityUtils.INPUT_TYPE_TEXT);
		order = SecurityUtils.stripInvalidCharacters(order, SecurityUtils.INPUT_TYPE_SQL_ORDER);
		pageStr = SecurityUtils.stripInvalidCharacters(pageStr, SecurityUtils.INPUT_TYPE_NUMBER);
		EffortSessionData sessionData = getEffortSessionData(httpSession);
		WebUser webUser = sessionData.getWebUser();*/
	   
		//count is here total records in table
		//have to query for all records in table 
		long count = callLogService.getIntegrationCallAuditBeanCount(); 
		int pageSize = 10;
		long totalPage = Math.round(Math.floor(count / pageSize));
		
		if (count % pageSize > 0 || count == 0) {
			totalPage++;
		}
		
		int page = Integer.parseInt(pageStr);
		int offset = (page - 1) * pageSize;
		
		 
		//orderBy,offset,pageSize
		List<IntegrationCallLogsBean> integrationCallAuditBeanList = callLogService.getIntegrationCallAuditList(offset,pageSize);
		model.addAttribute("size", pageSize);
		model.addAttribute("page", page);
	    model.addAttribute("orderBy", orderBy);
		model.addAttribute("total", totalPage);
		model.addAttribute("integrationCallAuditBeanList", integrationCallAuditBeanList);

		return "integrationCallAuditList";
	}
	
	@RequestMapping(value="/txnHit/{companyId}", method=RequestMethod.GET)
	public String hitConfigurationIntegrationCall(@PathVariable String companyId, @RequestParam(value="formId", required=false) String formId,
			@RequestParam(value="workId", required=false) String workId,@RequestParam(value="isUpdate", required=false) boolean isUpdate){
		Map<String,String> configMap = new HashMap<>();
		if(!StringUtils.isEmpty(companyId)){
			configMap.put("companyId",companyId);
		}
		
		if(!StringUtils.isEmpty(workId)){
			configMap.put("workId",workId);
		}

		if(!StringUtils.isEmpty(formId)){
			configMap.put("formId",formId);
		}
		if(!StringUtils.isEmpty(isUpdate)){
			configMap.put("isUpdate",String.valueOf(isUpdate));
		}
		try {
			callLogService.hitConfigurationIntegrationCall(configMap);
		} catch (JAXBException | IOException e) {
			log.error(e.getLocalizedMessage());
		}
		return "redirect:/configure/list";
	}
	
	@RequestMapping(value="/txnRetry/{configCallId}", method=RequestMethod.GET)
	public String retryConfigurationIntegrationCall(@PathVariable String configCallId){
		Map<String,String> configMap = new HashMap<>();
		if(!StringUtils.isEmpty(configCallId)){
			configMap.put("configCallId",configCallId);
		}
		configMap.put("isRetry","true");
		try {
			callLogService.hitConfigurationIntegrationCall(configMap);
		} catch (JAXBException | IOException e) {
			log.error(e.getLocalizedMessage());
		}
		return "redirect:/integration/callLogs/list";
	}
	
}
