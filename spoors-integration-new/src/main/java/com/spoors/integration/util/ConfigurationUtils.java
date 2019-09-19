package com.spoors.integration.util;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.ayansys.effort.beans.entity.Mail;
import com.spoors.integration.beans.ConfigEscalationBean;
import com.spoors.integration.beans.IntegrationCallLogsBean;

public class ConfigurationUtils {
	
	public static void setMailParams(Mail mailParams, ConfigEscalationBean configEscalation, 
			IntegrationCallLogsBean integrationLogBean) {

		mailParams.setMailTo(configEscalation.getMailIds());
		mailParams.setMailFrom("NoReply@SpoorsIntegration.com");
		mailParams.setMailSubject(configEscalation.getSubjectTemplate());
		String bodyTemplate = configEscalation.getBodyTemplate();
		if(!StringUtils.isEmpty(bodyTemplate)){
			if(configEscalation.getAddRequestInMail()){
				bodyTemplate.replace("$${{requestBody}}", integrationLogBean.getRequestData());
			}
			if(configEscalation.getAddResponseInMail()){
				bodyTemplate.replace("$${{responseBody}}", integrationLogBean.getResponseData());
			}
			mailParams.setMailBody(bodyTemplate);
			mailParams.setMailBodyType(configEscalation.getBodyType());
		}
	}
	
	
	public static boolean isTagExists(String xml, String key) throws XPathExpressionException
    {
        Document doc = getDoc(xml);
        XPath xPath = XPathFactory.newInstance().newXPath();
        XPathExpression expr = xPath.compile("*/"+key);
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        return nodes.getLength() > 0 ? true: false;

    }

    private static Document getDoc(String xml) 
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document dom = db.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
            return dom;
        }catch(Exception pce) {
            pce.printStackTrace();
        }
        return null;
    }
}
