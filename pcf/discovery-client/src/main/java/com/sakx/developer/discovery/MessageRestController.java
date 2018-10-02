package com.sakx.developer.discovery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

