package com.spoors.integration.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.spoors.integration.beans.IntegrationCallLogsBean;

@Repository
public class ConfigurationCallLogDAO {

	@Autowired
	@Qualifier("spoorsJdbcTemplate")
	JdbcTemplate spoorsJdbcTemplate;
	
	@Autowired
	@Qualifier("spoorsNamedJdbcTemplate")
	NamedParameterJdbcTemplate spoorsNamedJdbcTemplate;
	
	
	public List<IntegrationCallLogsBean> getIntegrationCallLogsList(int offset,int pageSize) {
		
		String sql = "SELECT pc.id pcId,pc.name,cl.id clId, cl.requestData,cl.responseData,cl.status,cl.retryCount,cl.externalSystemId "
				+ " FROM configurationcalllogs cl JOIN pushconfiguration pc ON cl.pushConfigId=pc.id"
				+ " WHERE pc.isDeleted=0 "
				+ " ORDER BY cl.id desc "
				+ " LIMIT ?,?";
	
		List<IntegrationCallLogsBean> integrationCallLongBeanList = spoorsJdbcTemplate.query(sql,new Object[]{offset,pageSize}, new RowMapper<IntegrationCallLogsBean>() {
 
            public IntegrationCallLogsBean mapRow(ResultSet rs, int rowNumber) throws SQLException {
            	IntegrationCallLogsBean callLogInfo = new IntegrationCallLogsBean();
            	callLogInfo.setId(rs.getInt("clId"));
            	callLogInfo.setConfigurationName(rs.getString("name"));
            	callLogInfo.setPushId(rs.getInt("pcId"));
            	callLogInfo.setRequestData(rs.getString("requestData"));
            	callLogInfo.setResponseData(rs.getString("responseData"));
            	callLogInfo.setStatus(rs.getInt("status"));
            	callLogInfo.setRetryCount(rs.getInt("retryCount"));
            	callLogInfo.setExternalSystemId(rs.getString("externalSystemId"));
                return callLogInfo;
            }
             
        });
		return integrationCallLongBeanList;
	}
	
	public int getIntegrationCallLogsCount() {
		String sql = "SELECT count(*)"
				+ " FROM configurationcalllogs cl JOIN pushconfiguration pc ON cl.pushConfigId=pc.id";
		
		return spoorsJdbcTemplate.queryForObject(sql,new Object[] {}, Integer.class);
             
	}
	
	

	
	public IntegrationCallLogsBean getIntegrationCallLogByConfigId(Integer configId) {
		
		String sql = "SELECT pc.id pcId,pc.name, cl.id clId, cl.requestData, cl.responseData,"
				+ " cl.isUpdate, cl.status, cl.retryCount, cl.externalSystemId, cl.createdBy, cl.createdTime,"
				+ " cl.modifiedBy, cl.modifiedTime "
				+ " FROM configurationcalllogs cl JOIN pushconfiguration pc ON cl.pushConfigId=pc.id"
				+ " JOIN configurationcallstatistics cs ON cs.ConfigCallLogId=cl.id "
				+ " WHERE cl.pushConfigId=:configId and pc.isDeleted=0 ";
	

		List<IntegrationCallLogsBean> IntegrationCallLogsList = spoorsNamedJdbcTemplate.query(sql,
				new MapSqlParameterSource("configId", configId), new RowMapper<IntegrationCallLogsBean>() {
 
            public IntegrationCallLogsBean mapRow(ResultSet rs, int rowNumber) throws SQLException {
            	IntegrationCallLogsBean callLogInfo = new IntegrationCallLogsBean();
            	callLogInfo.setId(rs.getInt("clId"));
            	callLogInfo.setConfigurationName(rs.getString("name"));
            	callLogInfo.setPushId(rs.getInt("pcId"));
            	callLogInfo.setRequestData(rs.getString("requestData"));
            	callLogInfo.setResponseData(rs.getString("responseData"));
            	callLogInfo.setUpdate(rs.getBoolean("isUpdate"));
            	callLogInfo.setStatus(rs.getInt("status"));
            	callLogInfo.setRetryCount(rs.getInt("retryCount"));
            	callLogInfo.setExternalSystemId(rs.getString("externalSystemId"));
            	callLogInfo.setCreatedBy(rs.getInt("createdBy"));
            	callLogInfo.setCreatedDate(rs.getDate("createdTime"));
            	callLogInfo.setModifiedBy(rs.getInt("modifiedBy"));
            	callLogInfo.setModifiedDate(rs.getDate("modifiedTime"));
                return callLogInfo;
            }
        });
		if ( IntegrationCallLogsList.isEmpty() ){
			  return null;
			}else { // list contains exactly 1 element
			  return IntegrationCallLogsList.get(0);
			}
	}
	
	public IntegrationCallLogsBean getIntegrationCallLogById(Integer configCallLogId) {
		
		String sql = "SELECT pc.id pcId,pc.name, cl.id clId, cl.requestData, cl.responseData,"
				+ " cl.isUpdate, cl.status, cl.retryCount, cl.externalSystemId, cl.createdBy, cl.createdTime,"
				+ " cl.modifiedBy, cl.modifiedTime "
				+ " FROM configurationcalllogs cl JOIN pushconfiguration pc ON cl.pushConfigId=pc.id"
				+ " JOIN configurationcallstatistics cs ON cs.ConfigCallLogId=cl.id "
				+ " WHERE cl.id=:configCallLogId and pc.isDeleted=0 ";
	

		List<IntegrationCallLogsBean> IntegrationCallLogsList = spoorsNamedJdbcTemplate.query(sql,
				new MapSqlParameterSource("configCallLogId", configCallLogId), new RowMapper<IntegrationCallLogsBean>() {
 
            public IntegrationCallLogsBean mapRow(ResultSet rs, int rowNumber) throws SQLException {
            	IntegrationCallLogsBean callLogInfo = new IntegrationCallLogsBean();
            	callLogInfo.setId(rs.getInt("clId"));
            	callLogInfo.setConfigurationName(rs.getString("name"));
            	callLogInfo.setPushId(rs.getInt("pcId"));
            	callLogInfo.setRequestData(rs.getString("requestData"));
            	callLogInfo.setResponseData(rs.getString("responseData"));
            	callLogInfo.setUpdate(rs.getBoolean("isUpdate"));
            	callLogInfo.setStatus(rs.getInt("status"));
            	callLogInfo.setRetryCount(rs.getInt("retryCount"));
            	callLogInfo.setExternalSystemId(rs.getString("externalSystemId"));
            	callLogInfo.setCreatedBy(rs.getInt("createdBy"));
            	callLogInfo.setCreatedDate(rs.getDate("createdTime"));
            	callLogInfo.setModifiedBy(rs.getInt("modifiedBy"));
            	callLogInfo.setModifiedDate(rs.getDate("modifiedTime"));
                return callLogInfo;
            }
        });
		if ( IntegrationCallLogsList.isEmpty() ){
			  return null;
			}else { // list contains exactly 1 element
			  return IntegrationCallLogsList.get(0);
			}
	}
	
	
	public List<IntegrationCallLogsBean> getIntegrationCallAuditList(int offset,int pageSize) {
		
		String sql = "SELECT pc.id pcId, pc.name, cl.id clId,cs.id csId, cs.startTime, cs.endTime"
				+ " FROM configurationcalllogs cl JOIN pushconfiguration pc ON cl.pushConfigId=pc.id"
				+ " JOIN configurationcallstatistics cs ON cs.ConfigCallLogId=cl.id"
				+ " WHERE pc.isDeleted=0"
				+ " ORDER BY cs.id DESC "
				+ " LIMIT ?,?";

		List<IntegrationCallLogsBean> integrationCallsAuditBeanList = spoorsJdbcTemplate.query(sql,new Object[]{offset,pageSize}, new RowMapper<IntegrationCallLogsBean>() {
 
		    public IntegrationCallLogsBean mapRow(ResultSet rs, int rowNumber) throws SQLException {
		    	IntegrationCallLogsBean callLogInfo = new IntegrationCallLogsBean();
		    	callLogInfo.setId(rs.getInt("clId"));
		    	callLogInfo.setConfigurationName(rs.getString("name"));
		    	callLogInfo.setPushId(rs.getInt("pcId"));
		    	callLogInfo.setCallStatiscticsId(rs.getInt("csId"));
		    	callLogInfo.setStartTime(rs.getString("startTime"));
		    	callLogInfo.setEndTime(rs.getString("endTime"));
		        return callLogInfo;
		    }
		     
		});
		return integrationCallsAuditBeanList;
	}
	
	public int getIntegrationCallAuditBeanCount() {
		
		String sql = "SELECT count(*)"
				+ " FROM configurationcalllogs cl JOIN pushconfiguration pc ON cl.pushConfigId=pc.id"
				+ " JOIN configurationcallstatistics cs ON cs.ConfigCallLogId=cl.id";

		return  spoorsJdbcTemplate.queryForObject(sql,new Object[] {}, Integer.class);
	}

	public Integer insertIntegrationCallLog(IntegrationCallLogsBean integrationLogBean){
		
		String enableSql = "INSERT INTO configurationcalllogs (pushConfigId, requestData, isUpdate, createdBy, createdTime) "
				+ "VALUES (:configId, :requestData, :isUpdate, :createdBy, UTC_TIMESTAMP())";
		KeyHolder holder = new GeneratedKeyHolder();	
		MapSqlParameterSource insertEnableParameters = new MapSqlParameterSource();
		insertEnableParameters.addValue("configId", integrationLogBean.getPushId());
		insertEnableParameters.addValue("requestData", integrationLogBean.getRequestData());
		insertEnableParameters.addValue("isUpdate", integrationLogBean.isUpdate());
		insertEnableParameters.addValue("createdBy", integrationLogBean.getCreatedBy());
		
		int recordCnt = spoorsNamedJdbcTemplate.update(enableSql, insertEnableParameters,holder);
		int callLogGenId = holder.getKey().intValue();
		return recordCnt>0? callLogGenId:null;
	}

	public Integer insertIntegrationCallStatistics(IntegrationCallLogsBean integrationLogBean){
		
		String sql = "INSERT INTO configurationcallstatistics "
				+ " (ConfigCallLogId, startTime) VALUES (:callLogId, UTC_TIMESTAMP())";

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("callLogId", integrationLogBean.getId());
		int recordCnt = spoorsNamedJdbcTemplate.update(sql, parameters);
		return recordCnt>0? recordCnt:null;
	}

	
	public Integer updateIntegrationCallLog(IntegrationCallLogsBean integrationLogBean) {
		String sql = "UPDATE configurationcalllogs SET responseData=:responseData, STATUS=:status, retryCount=IFNULL(retryCount, 0)+1, "
				+ " externalSystemId=:externalSystemId, MODIFIEDBY=:modifiedBy, MODIFIEDTIME=UTC_TIMESTAMP() "
				+ " WHERE ID= :callLogId";
				
		MapSqlParameterSource updateParameters = new MapSqlParameterSource();
		updateParameters.addValue("responseData", integrationLogBean.getResponseData());
		updateParameters.addValue("status", integrationLogBean.getStatus());
		updateParameters.addValue("externalSystemId", integrationLogBean.getExternalSystemId());
		updateParameters.addValue("modifiedBy", integrationLogBean.getModifiedBy());
		updateParameters.addValue("callLogId", integrationLogBean.getId());
		int recordCnt = spoorsNamedJdbcTemplate.update(sql, updateParameters);
		return recordCnt>0?recordCnt:null;
	}

	public Integer updateIntegrationCallStatistics(IntegrationCallLogsBean integrationLogBean) {
		String callStatisticsSql = "UPDATE configurationcallstatistics SET endTime= UTC_TIMESTAMP() WHERE ConfigCallLogId=:callLogId";
		int callStatisticsCnt = spoorsNamedJdbcTemplate.update(callStatisticsSql, new MapSqlParameterSource(
                "callLogId", integrationLogBean.getId()));
		return callStatisticsCnt>0?callStatisticsCnt:null;
	}


	public int getCallLogsStatusCountByConfigId(Integer configId, Integer calllogStatusFailed,
			Integer... escalationFailedDurationMins) {
		return 1;
	}
	
}
