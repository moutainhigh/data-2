package com.hourz.cas.server.common.http;

public class HttpUtils {

	// 双重锁模式:是饱汉模式的优化,进行双重判断,当已经创建过实例对象后就无需加锁,提高效率.
	private static HttpUtils singleton;
	/**
	 * <p>无参构造器</p>
	 */
	private HttpUtils(){
	}
	/**
	 * <p>双重锁模式实现</p>
	 * @return
	 */
	public static HttpUtils getInstance(){
		if(singleton == null){
			synchronized(HttpUtils.class){
				if(singleton == null){
					singleton = new HttpUtils();
				}
			}
		}
		return singleton;
	}
	
	
}
