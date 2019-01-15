package com.sakx.developer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@EnableAutoConfiguration
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DiscoveryClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryClientApplication.class, args);
    }
}

