/**
 * 
 */
package com.spoors.integration.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spoors.integration.beans.ConfigEscalationBean;

/**
 * @author Anand
 *
 */
@Repository
public class ConfigEscalationDAO {

	@Autowired
	@Qualifier("spoorsJdbcTemplate")
	JdbcTemplate spoorsJdbcTemplate;
	
	@Autowired
	@Qualifier("spoorsNamedJdbcTemplate")
	NamedParameterJdbcTemplate spoorsNamedJdbcTemplate;
	
	
	public List<ConfigEscalationBean> getEscalationsByConfigId(Integer configId) {
		
		String sql = "SELECT id, pushConfigId,STATUS, addRequestInMail, addResponseInMail, mailIds, mailSubject, mailBody, bodyType, mailType"
				+ " FROM escalationsConfiguration WHERE isDeleted=0 AND pushConfigId="+configId;
		
        List<ConfigEscalationBean> listConfigEscalations = spoorsJdbcTemplate.query(sql, new RowMapper<ConfigEscalationBean>() {
 
            public ConfigEscalationBean mapRow(ResultSet rs, int rowNumber) throws SQLException {
            	ConfigEscalationBean escalationInfo = new ConfigEscalationBean();
            	escalationInfo.setId(rs.getInt("id"));
            	escalationInfo.setConfigId(rs.getInt("pushConfigId"));
            	escalationInfo.setStatus(rs.getInt("status"));
            	escalationInfo.setMailIds(rs.getString("mailIds"));
            	escalationInfo.setSubjectTemplate(rs.getString("mailSubject"));
            	escalationInfo.setBodyTemplate(rs.getString("mailBody"));
            	escalationInfo.setBodyType(rs.getInt("bodyType"));
            	escalationInfo.setMailType(rs.getInt("mailType"));
            	escalationInfo.setAddRequestInMail(rs.getInt("addRequestInMail")!=0?true:false);
            	escalationInfo.setAddResponseInMail(rs.getInt("addResponseInMail")!=0?true:false);
                return escalationInfo;
            }
        });
        return listConfigEscalations;
	}


	@SuppressWarnings("unchecked")
	public boolean addConfigEscalation(Integer configId, List<ConfigEscalationBean> escalationInfoList) {
		
		String deleteConfigEscalationSql = "UPDATE escalationsConfiguration SET isDeleted=1,"
				+ " modifiedBy=:modifiedBy, modifiedTime=UTC_TIMESTAMP() WHERE PUSHCONFIGID=:configId";
		MapSqlParameterSource deleteConfigEscalationParameters = new MapSqlParameterSource();
		deleteConfigEscalationParameters.addValue("modifiedBy", 1234);
		deleteConfigEscalationParameters.addValue("configId", configId);
		
		spoorsNamedJdbcTemplate.update(deleteConfigEscalationSql, deleteConfigEscalationParameters);
		
		String insertEscalationsSql = "INSERT INTO escalationsConfiguration " +
				"(pushConfigId, status, addRequestInMail, addResponseInMail, mailIds, mailSubject, mailBody, bodyType, mailType, CREATEDBY, CREATEDTIME) "
				+ "VALUES (:configId, :status, :addRequestInMail, :addResponseInMail, :mailIds, :mailSubject,:mailBody,:bodyType, :mailType, :CREATEDBY, UTC_TIMESTAMP())";

		
		List<Map<String, Object>> batchValues = new ArrayList<>(escalationInfoList.size());
		for (ConfigEscalationBean configEscalationInfo : escalationInfoList) {
		    batchValues.add(
		            new MapSqlParameterSource("configId", configId)
		                    .addValue("status", configEscalationInfo.getStatus())
		                    .addValue("addRequestInMail", (configEscalationInfo.getAddRequestInMail()!= null && configEscalationInfo.getAddRequestInMail())? 1:0)
		                    .addValue("addResponseInMail", (configEscalationInfo.getAddResponseInMail()!=null && configEscalationInfo.getAddResponseInMail())?1:0)
		                    .addValue("mailIds", configEscalationInfo.getMailIds())
		                    .addValue("mailSubject", configEscalationInfo.getSubjectTemplate())
		                    .addValue("mailBody", configEscalationInfo.getBodyTemplate())
		                    .addValue("bodyType", configEscalationInfo.getBodyType())
		                    .addValue("mailType", configEscalationInfo.getMailType())
		                    .addValue("CREATEDBY", configEscalationInfo.getCreatedBy())
		                    .getValues());
		}
		
		spoorsNamedJdbcTemplate.batchUpdate(insertEscalationsSql, batchValues.toArray(new Map[escalationInfoList.size()]));
			return true;
	}

}
