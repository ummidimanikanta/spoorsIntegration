/**
 * 
 */
package com.spoors.integration.manager;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spoors.integration.beans.ConfigEscalationBean;
import com.spoors.integration.dao.ConfigEscalationDAO;

/**
 * @author Anand
 *
 */
@Service
public class ConfigEscalationManager {

	@Autowired
	ConfigEscalationDAO escalationDAO;
	
	public List<ConfigEscalationBean> getEscalationsByConfigId(Integer configId) {
		return escalationDAO.getEscalationsByConfigId(configId);
	}

	public boolean addConfigEscalation(Integer configId, List<ConfigEscalationBean> escalationInfoList) throws SQLException {
			if(escalationInfoList!=null && escalationInfoList.size()>0){
				escalationInfoList = escalationInfoList.stream().filter(escalationConfig -> (StringUtils.isNotBlank(escalationConfig.getBodyTemplate())
				&& StringUtils.isNotBlank(escalationConfig.getSubjectTemplate())&& StringUtils.isNotBlank(escalationConfig.getMailIds()))).collect(Collectors.toList());
				return escalationDAO.addConfigEscalation(configId, escalationInfoList);
			}
			return false;
	}

}
