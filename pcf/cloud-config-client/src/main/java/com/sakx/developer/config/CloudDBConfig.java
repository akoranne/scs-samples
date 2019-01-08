package com.sakx.developer.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Created by ajaykoranne on 4/29/17.
 */

// TODO: cleanup
//@Configuration
//@RefreshScope
@Profile("cloud")
public class CloudDBConfig {
	private static final Logger log = LoggerFactory.getLogger(CloudDBConfig.class);

	@Value("${user:unknown}")
	private String user;

	@Value("${password:unknown}")
	private String password;

	@Value("${endpoint:host:port}")
	private String endpoint;

	@Value("${sid:sid}")
	private String sid;

	@Bean
	@Primary
	public String dataSource() {
		log.debug(" .......... profile ('cloud'). Creating datasource");

		StringBuilder buf = new StringBuilder();
		buf.append("jdbc:oracle:thin:")
				.append(user)
				.append("/")
				.append(password)
				.append("@")
				.append(endpoint)
				.append(":")
				.append(sid);
		return buf.toString();
	}


	@Bean
	public SecurityWebFilterChain securityWebFilterChain(
			ServerHttpSecurity http) {
		return http.authorizeExchange()
				       .pathMatchers("/actuator/**").permitAll()
				       .anyExchange().authenticated()
				       .and().build();
	}

}
