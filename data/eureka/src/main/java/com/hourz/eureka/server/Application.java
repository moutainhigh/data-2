package com.hourz.eureka.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <p>Description</p>
 * @author hourz
 * @since 2018-10-15
 */
@EnableEurekaServer
@SpringBootApplication
public class Application {
	
	private static final Logger logger = LogManager.getLogger(Application.class); 
	
	public static void main(String[] args) {
		logger.info("启动注册与发现服务端！");
		SpringApplication.run(Application.class, args);
	}
}
