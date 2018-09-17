package com.hourz.common.config;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>配置信息</p>
 * <p>这个类会读取类路径下得config.properties文件，
 * <p>并为其他程序提供key/value方式读取
 * @author hourz 
 * @since 2016-05-24
 */
public class Config {
	protected final static Log logger = LogFactory.getLog(Config.class);

	// 同步对象
	private static Object synObj = new Object();
	// 单例对象
	private static Config config;
	// 配置对象
	private Properties properties;

	private Config() {
		// 载入外部配置文件
		load();
	}

	private void load() {
		try {
			properties = new Properties();
			properties.load(Config.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (Exception ex) {
			logger.error("载入配置文件 'config.properties' 失败!", ex);
		}
	}

	/**
	 * 获取Config对象(获取此对象的唯一入口)
	 * @return Config对象
	 */
	public static Config getInstance() {
		if (config == null) {
			synchronized (synObj) {
				if (config == null)
					config = new Config();
			}
		}
		return config;
	}

	/**
	 * 获取配置指定属性值
	 * @param key 键
	 * @return 值
	 */
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
}
