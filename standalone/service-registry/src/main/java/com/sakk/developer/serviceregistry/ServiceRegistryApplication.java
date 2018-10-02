package com.sakk.developer.serviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaServer
@RestController
@SpringBootApplication
public class ServiceRegistryApplication {

	@RequestMapping("/")
	public String home() {
		return "service registry";
	}

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryApplication.class, args);
	}
}
