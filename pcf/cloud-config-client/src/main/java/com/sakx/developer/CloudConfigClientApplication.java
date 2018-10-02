package com.sakx.developer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CloudConfigClientApplication {
	private static final Logger log = LoggerFactory.getLogger(CloudConfigClientApplication.class);

	@Autowired
	private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(CloudConfigClientApplication.class, args);
	}
}
