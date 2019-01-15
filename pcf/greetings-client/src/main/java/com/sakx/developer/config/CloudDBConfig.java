package com.sakx.developer.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
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

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(
			ServerHttpSecurity http) {
		return http.authorizeExchange()
				       .pathMatchers("/actuator/**").permitAll()
				       .anyExchange().authenticated()
				       .and().build();
	}

}
