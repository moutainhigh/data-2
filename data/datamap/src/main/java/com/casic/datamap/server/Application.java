package com.casic.datamap.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * <p>Description</p>
 * @author hourz
 * @since 2018-10-19
 */
@SpringBootApplication
@MapperScan("om.casic.datamap.server.dao")
@EnableEurekaClient
public class Application {
	
	private static final Logger logger = LogManager.getLogger(Application.class);
	
	/**
	 * 主函数main
	 * @param args 未知变量
	 */
	public static void main(String[] args) {
		logger.info("启动数据资源融合应用服务！");
		SpringApplication.run(Application.class, args);
	}
}
