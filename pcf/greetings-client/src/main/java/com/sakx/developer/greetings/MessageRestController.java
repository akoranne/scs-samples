package com.sakx.developer.greetings;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * Created by ajaykoranne on 4/26/17.
 */
@RefreshScope
@RestController
public class MessageRestController {

	private static final Logger log = LoggerFactory.getLogger(MessageRestController.class);

	@Value("${message:Hello default}")
	private String message;

	@Value("${user:unknown}")
	private String user;

	@Value("${password:unknown}")
	private String password;

	@Value("${endpoint:host:port}")
	private String endpoint;

	@Value("${sid:sid}")
	private String sid;

	@Value("${config.name:unknown}")
	private String name;

    @Autowired
    private DiscoveryClient discoveryClient;

	@RequestMapping("/")
	public String whoAmI() {
		return (" Hello " + name);
	}

	@RequestMapping("/message")
	public String getMessage() {
		log.info(" in getMessage() " + this.user + ", " + this.password);
		StringBuilder buf = new StringBuilder();
		buf.append("jdbc:oracle:thin:")
				.append(user)
				.append("/")
				.append(password)
				.append("@")
				.append(endpoint)
				.append(":")
				.append(sid);

		StringBuilder msg = new StringBuilder();
		msg.append(" Message: ").append(this.message);
		msg.append(" [dburl: ").append(buf).append("]");
		return msg.toString();
	}

	@RequestMapping("/hi/{name}")
	public Map<String, String> hi(
			@PathVariable String name,
			@RequestHeader(value = "X-CNJ-Name", required = false) Optional<String> cn) {
		String resolvedName =  cn.orElse(name);
		return Collections.singletonMap("greeting", "Hello, " + resolvedName + "!");
	}


    @RequestMapping("/discover")
    public Map<String, String> discover() {
	    StringBuilder buf = new StringBuilder();
        RestTemplate restTemplate = new RestTemplate();
        discoveryClient.getInstances("DISCOVERY-CLIENT").forEach((ServiceInstance s) -> {
            log.info(ToStringBuilder.reflectionToString(s));
            log.info("-----> {}, {}, {}, {}", s.getUri(), s.getHost(), s.getPort(), s.getServiceId());
            // buf.append(restTemplate.getForObject(s.getUri()+"/message", String.class));
        });

        buf.append(restTemplate.getForObject("http://discovery-client/message", String.class));
        return Collections.singletonMap("greeting", buf.toString());
    }


}
