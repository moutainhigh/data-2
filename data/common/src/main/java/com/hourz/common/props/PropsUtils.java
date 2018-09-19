/**
 * 
 */
package com.hourz.common.props;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>读取application.yml配置文件</p>
 * @author hourz
 * @since 2018-09-19
 */
@Component
@ConfigurationProperties(prefix="spring")
public class PropsUtils {

	// 双重锁模式：是饱汉模式的优化，进行双重判断，当已经创建过实例对象后就无需加锁，提高效率。也是一种推荐使用的方式
	private static PropsUtils singleton;
	
	/**
	 * <p>无参构造器</p>
	 */
	private PropsUtils(){
	}
	
	/**
	 * <p>双重锁模式实现</p>
	 * @return
	 */
	public static PropsUtils getInstance(){
		if(singleton == null){
			synchronized(PropsUtils.class){
				if(singleton == null){
					singleton = new PropsUtils();
				}
			}
		}
		return singleton;
	}
	
	// 读取数据源
	private List<Map<String, String>> datasource;

	public List<Map<String, String>> getDatasource() {
		return datasource;
	}
	public void setDatasource(List<Map<String, String>> datasource) {
		this.datasource = datasource;
	}
}
