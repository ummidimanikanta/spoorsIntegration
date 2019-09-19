/**
 * 
 */
package com.spoors.integration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.client.RestTemplate;

import com.spoors.integration.util.MailTask;

/**
 * @author Anand
 *
 */
@Configuration
@ImportResource({"classpath*:spoors-integration-servlet.xml"})
public class IntegrationConfiguration {

	@Bean
	@Qualifier("spoorsJdbcTemplate")
    public JdbcTemplate spoorsJdbcTemplate(DataSource localDataSource) {
        return new JdbcTemplate(localDataSource);
    }
	
	@Bean
	@Qualifier("effortJdbcTemplate")
    public JdbcTemplate effortJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
	
	@Bean
	@Qualifier("spoorsNamedJdbcTemplate")
    public NamedParameterJdbcTemplate spoorsNamedParameterJdbcTemplate(DataSource localDataSource) {
        return new NamedParameterJdbcTemplate(localDataSource);
    }
	
	@Bean
	@Qualifier("effortNamedJdbcTemplate")
    public NamedParameterJdbcTemplate effortNamedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
	
	@Bean
	public MailTask getMailTask(){
		return new MailTask();
	}
	
}
