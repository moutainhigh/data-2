package com.hourz.cas.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@MapperScan("com.hourz.dao")
@EnableEurekaClient
@ImportResource(locations = {"classpath:kaptcha.xml"})
public class Application {
	private static final Logger logger = LogManager.getLogger(Application.class);
	
	/**
	 * 主函数main
	 * @param args 未知变量
	 */
	public static void main(String[] args) {
		logger.info("启动中央认证服务！");
		SpringApplication.run(Application.class, args);
	}
}
