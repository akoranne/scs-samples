package com.sakx.developer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ajaykoranne on 4/26/17.
 */
@RefreshScope
@RestController
public class MessageRestController {

	private static final Logger log = LoggerFactory.getLogger(MessageRestController.class);

	@Value("${version:unknown}")
	private String propsVersion;

	@Value("${message:Hello default}")
	private String message;

	@Value("${spring.cloud.config.uri:unknown}")
	private String configUri;

	@Value("${user:scott}")
	private String user;

	@Value("${password:tiger}")
	private String password;

	@Value("${endpoint:host:port}")
	private String endpoint;

	@Value("${sid:sid}")
	private String sid;

	@Value("${config.name}")
	String name = "World";

	@Value("${config.info}")
	String details = "unknown";

	@RequestMapping("/")
	public String home() {
		StringBuilder buf = new StringBuilder();
		buf.append("Hello " + name);
		buf.append("... info: " + details);
		return buf.toString();
	}

//	@RequestMapping(value = "/",
//			method = RequestMethod.GET,
//			produces = {"application/json; charset=UTF-8"}
//	)
//	public ResponseEntity<?> hello() {
//		Map<String, String> amap = new HashMap<String, String>();
//		amap.put("message", "Config client ");
//		return new ResponseEntity<>(amap, HttpStatus.OK);
//	}

	@RequestMapping(value = "/message",
			method = RequestMethod.GET,
			produces = {"application/json; charset=UTF-8"}
	)
	public ResponseEntity<?> getMessage() {
		log.info(" in getMessage() " + this.message);

		Map<String, String> amap = new HashMap<String, String>();
		amap.put("version", propsVersion);
		amap.put("configUri", configUri);
		amap.put("timestamp", LocalDateTime.now().toString());
		amap.put("message", this.message);

		amap.put("dburl", dataSource());
		return new ResponseEntity<>(amap, HttpStatus.OK);
	}

	private String dataSource() {
		log.debug(" .......... profile ('default'). Creating datasource");

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

}

