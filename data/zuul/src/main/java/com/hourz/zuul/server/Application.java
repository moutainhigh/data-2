package com.hourz.zuul.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * <p>网关主服务</p>
 * @author hourz
 * @since 2018-09-18
 */
@EnableZuulProxy
@SpringBootApplication
@EnableEurekaClient
public class Application {
	
	private static final Logger logger = LogManager.getLogger(Application.class); 
	
	public static void main(String[] args) {
		logger.info("启动网关主服务!");
		SpringApplication.run(Application.class, args);
	}
}
