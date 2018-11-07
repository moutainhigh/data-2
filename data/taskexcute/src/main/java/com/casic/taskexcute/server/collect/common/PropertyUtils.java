package com.casic.taskexcute.server.collect.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件读取
 * @author:zgp
 * @time:2018-08-03
 */
public class PropertyUtils {
	private static volatile Properties properties;
	
	public static Properties loadPro() throws IOException {
		if(properties==null) {
			createPro();
		}
		return properties;
	}
	public static synchronized Properties createPro() throws IOException {
		if(properties==null) {
			Properties pro  = new Properties();
			InputStream inStream = PropertyUtils.class.getClassLoader().getResourceAsStream("config.properties");
			pro.load(inStream);
			properties = pro;
		}
		return properties;
	}
	public static String getPro(String key) {
		String value = null;
		try {
			loadPro();
			value = properties.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
	public static void main(String[] args) {
		String url = PropertyUtils.getPro("DATABASE.EXCHANGE.URL");
		System.out.println(url);
	}
}
