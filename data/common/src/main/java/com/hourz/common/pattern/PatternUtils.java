package com.hourz.common.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则判断
 */
public class PatternUtils {

	// 判断局域网地址的正则表达式
	private static final Pattern P_LAN_IP = Pattern.compile("(127[.]0[.]0[.]1)|(localhost)|(10[.]\\d{1,3}[.]\\d{1,3}[.]\\d{1,3})|(172[.]((1[6-9])|(2\\d)|(3[01]))[.]\\d{1,3}[.]\\d{1,3})|(192[.]168[.]\\d{1,3}[.]\\d{1,3})");
	// 获取domain的表达式
	private static final Pattern P_DOMAIN = Pattern.compile("http://([\\w|\\d|\\.]+)[:|/]");
	// 获取port的表达式
	private static final Pattern P_PORT = Pattern.compile("http://[\\w|\\d|\\.]+:(\\d+)"); 
	// 获取dmoain和port的表达式
	private static final Pattern P_DOMAIN_PORT = Pattern.compile("http://([\\w|\\d|\\.]+[:|\\d]*)/"); 
	// 获取服务名称的表达式
	private static final Pattern P_SERV_NAME = Pattern.compile("http://[\\w|\\d|\\.]+[:|\\d]*/(\\w+)/"); 
	// 判断手机号码的表达式
	private static final Pattern P_MOBILE = Pattern.compile("^\\d+{11}$");
	// 判断数字的表达式
	private static final Pattern P_NUMERIC = Pattern.compile("^\\d+(\\.\\d+)*$");
	// 判断IP地址的表达式
	private static final Pattern P_IP = Pattern.compile("(((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))");
	// 判断邮箱的表达式
	private static final Pattern P_EMAIL = Pattern.compile("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
	
	/**
	 * <p>判断字符串是否为数字</p>
	 * @param str 字符串
	 * @return
	 */
	public static boolean isNumeric(String str){ 
	    return P_NUMERIC.matcher(str).matches();    
	} 	
	
	/**
	 * <p>判断字符串是否为手机号码</p>
	 * @param str 字符串
	 * @return
	 */
	public static boolean isMobile(String str) {
		return P_MOBILE.matcher(str).matches();
	}

	/**
     * <p>ip校验</ip>
     * @param str
     * @return
     */
    public static boolean isIpAddress(String str){
        return P_IP.matcher(str).matches();
    }	
    
    /**
     * <p>是否为Email</p>
     * @param str
     * @return
     */
    public static boolean isEmail(String str) {
    	return P_EMAIL.matcher(str).matches();
    }
	
	
	/**
	 * <p>获取URL的Domain部分(可能是域名或IP地址)</p>
	 * @param url Url
	 * @return
	 */
	public static String getDomainPartOfURL(String url){
		Matcher m = P_DOMAIN.matcher(url);
		m.find();
		return m.group(1);
	}
	
	/**
	 * <p>获取URL的Port部分</p>
	 * @param url Url
	 * @return
	 */
	public static String getPortPartOfURL(String url){
		Matcher m = P_PORT.matcher(url);
		m.find();
		return m.group(1);		
	}
	
	/**
	 * <p>获取URL的Domain和Port部分</p>
	 * @param url Url
	 * @return
	 */
	public static String getDomainPortOfURL(String url){
		Matcher m = P_DOMAIN_PORT.matcher(url);
		m.find();
		return m.group(1);			
	}

	/**
	 * 获取URL的服务名称部分
	 * @param url
	 * @return
	 */
	public static String getServNameOfURL(String url) {
		Matcher m = P_SERV_NAME.matcher(url);
		m.find();
		return m.group(1);	
	}	
	
	/**
	 * 判断地址是否为局域网地址(包括localhost)
	 * @param address 有效的网络地址(包括localhost)
	 * @return
	 */
	public static boolean isLanAddress(String address) {
		return P_LAN_IP.matcher(address).matches();
	}

}
