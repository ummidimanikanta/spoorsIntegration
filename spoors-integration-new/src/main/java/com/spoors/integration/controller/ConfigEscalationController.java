package com.spoors.integration.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spoors.integration.beans.ConfigEscalationBean;
import com.spoors.integration.beans.ConfigurationInfo;
import com.spoors.integration.manager.ConfigEscalationManager;

@RequestMapping("/escalations")
@Controller
public class ConfigEscalationController {

	public static Logger log = LoggerFactory.getLogger(ConfigEscalationController.class); 

	@Autowired
	ConfigEscalationManager escalationManager;
	
	@RequestMapping(value="/addPage/{configId}", method=RequestMethod.GET)
	public ModelAndView  addEscalationToConfig(@PathVariable Integer configId){
		ModelAndView modelAndView = new ModelAndView("AddConfigEscalation");
		List<ConfigEscalationBean> configEscalationList = escalationManager.getEscalationsByConfigId(configId);
		ConfigurationInfo configInfo = new ConfigurationInfo();
		configInfo.setConfigEscalationList(configEscalationList);
		configInfo.setId(configId);
		modelAndView.addObject("configId",configId);
		modelAndView.addObject("configurationInfo",configInfo);
		return modelAndView;
	}
	
	@RequestMapping(value="/add/{configId}", method=RequestMethod.POST)
	public String addConfiguration(@PathVariable Integer configId, @ModelAttribute("configurationInfo") ConfigurationInfo configurationInfo) {
		try {
			System.out.println(configurationInfo);
			escalationManager.addConfigEscalation(configId, configurationInfo.getConfigEscalationList());
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return "redirect:/configure/list";
	}
}
