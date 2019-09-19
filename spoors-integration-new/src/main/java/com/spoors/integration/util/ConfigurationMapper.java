package com.spoors.integration.util;

import java.io.StringWriter;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigurationMapper {
	
	public static String createXML(Map<String,String> fieldMap) throws JAXBException{
		String configFieldXML = null;
		XMLMap fieldXMLMap = new XMLMap();
		fieldMap.forEach((k,v) -> {fieldXMLMap.addEntry(k,v);});
		
		StringWriter sb = new StringWriter();
		JAXBContext.newInstance(XMLMap.class).createMarshaller().marshal(fieldXMLMap, sb);
		
		configFieldXML = sb.toString();
		return configFieldXML;
	}

	public static String createJSON(Map<String,String> fieldMap) throws JsonProcessingException{
		String configFieldJson = null;
		ObjectMapper mapper = new ObjectMapper();
		configFieldJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(fieldMap);
		return configFieldJson;
	}
}
