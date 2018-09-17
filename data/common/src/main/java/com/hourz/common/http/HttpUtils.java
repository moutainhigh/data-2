package com.hourz.common.http;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hourz.common.captcha.CaptchaService;
import com.hourz.common.config.Config;
import com.hourz.common.constant.Constant;
import com.hourz.common.encrypt.EncryptUtils;

/**
 * <p>HTTP工具类</p>
 * @author hourz
 * @since 2016-05-24
 */
public class HttpUtils {
	
	/**
	 * <p>验证验证码</p>
	 * @param session_id
	 * @param captcha
	 * @return
	 */
	public static boolean validateCaptcha(String session_id, String captcha) {
		try {
			return CaptchaService.instance().validateResponseForID(session_id, captcha);
		} catch(Exception ex) {
			return false;
		}
	}	
	
	/**
	 * <p>当前的用户是否开启了RememberMe功能</p>
	 * @return
	 */
	@SuppressWarnings("unlikely-arg-type")
	public static boolean isRememberMeEnabled(HttpServletRequest request) {
		// 获取请求中所有的cookies
		String enable_key = Config.getInstance().getProperty(Constant.Config_Remember_Me_Cookie_Enable_Key);
		Map<String, String> cookieMap = analyseCookie(request.getCookies());
		// 判断用户是否选中了RememberMe功能
		String isEnable = cookieMap.get(enable_key);
		if (isEnable == null || isEnable.trim().equals(0)) return false;
		return true;
	}
	
	/**
	 * <p>打开RememberMe</p>
	 * @param loginName 登录名
	 * @param password 密码
	 * @param validDays 有效天数
	 * @throws Exception 
	 */
	public static void openRememberMe(HttpServletResponse response,String loginName, String password, int validDays) throws Exception {
		// 对用户名和密码加密
		String encrypt_key = Config.getInstance().getProperty(Constant.Config_Remember_Me_Encryption_Key);
		String encrypt_content = EncryptUtils.threeDesEncoder(loginName+"&"+password, encrypt_key);
		// 设置RememberMe为可用
		String enable_key = Config.getInstance().getProperty(Constant.Config_Remember_Me_Cookie_Enable_Key);
		Cookie cookie_enable = new Cookie(enable_key, "1");
		cookie_enable.setPath("/");
		cookie_enable.setMaxAge(60*60*24*validDays);
		response.addCookie(cookie_enable);	
		// 保存加密的“登录信息字符串”
		String secret_key = Config.getInstance().getProperty(Constant.Config_Remember_Me_Cookie_Secret_Key);
		Cookie cookie_secret = new Cookie(secret_key, encrypt_content);
		cookie_secret.setPath("/");
		cookie_secret.setMaxAge(60*60*24*validDays);
		response.addCookie(cookie_secret);			
	}
	
	/**
	 * <p>关闭RememberMe</p>
	 */
	public static void closeRememberMe(HttpServletResponse response) {
		// 设置RememberMe为不可用
		String enable_key = Config.getInstance().getProperty(Constant.Config_Remember_Me_Cookie_Enable_Key);
		Cookie cookie_enable = new Cookie(enable_key, "");
		cookie_enable.setPath("/");
		cookie_enable.setMaxAge(0); // 立即失效
		response.addCookie(cookie_enable);
		// 清除加密的“登录信息字符串”
		String secret_key = Config.getInstance().getProperty(Constant.Config_Remember_Me_Cookie_Secret_Key);
		Cookie cookie_secret = new Cookie(secret_key, "");
		cookie_secret.setPath("/");
		cookie_secret.setMaxAge(0); // 立即失效
		response.addCookie(cookie_secret);			
	}		
	
	/**
	 * <p>将Cookie数组解析成Map对象</p>
	 * @param cookies Cookie数组
	 * @return Map对象
	 */
	public static Map<String, String> analyseCookie(Cookie[] cookies){
		Map<String, String> cookieMap = new HashMap<String, String>();
		if (cookies == null) return cookieMap;
		for (Cookie cookie : cookies) cookieMap.put(cookie.getName(), cookie.getValue());
		return cookieMap;
	}
	
	/**  
	 * <p>根据名字获取cookie</p>  
	 * @param request  
	 * @param name cookie名字  
	 * @return  
	 */ 
	public static Cookie getCookieByName(HttpServletRequest request, String name){  
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		
		if(cookies != null && cookies.length > 0) {
			for (Cookie co : cookies) {
				if(co.getName() != null && co.getName().equalsIgnoreCase(name)) {
					cookie = co;
				}
			}
		}
		return cookie;
	}	
	
	/**
	 * <p>获取应用程序上下文</p>
	 * @return
	 */
	/* public static ApplicationContext getApplicationContext() {
		return WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
	}*/
	
	/**
	 * <p>获取请求中的IP地址</p>
	 * @param request Http请求
	 * @return IP地址
	 */
	public static String getIPOfRequest(HttpServletRequest request) {
		String address = request.getHeader("X-Forwarded-For");
        if (address != null) {
            return address;
        }
        return request.getRemoteAddr();
	}		
	
	/**
	 * <p>获取请求中的Sessionid</p>
	 * @param request http请求
	 * @return
	 */
	public static String getSessionidFromRequest(HttpServletRequest request){
		HttpSession session = request.getSession();
		return session.getId();
	}
	
	/**
	 * <p>获得请求路径</p>
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestPath(HttpServletRequest request) {
		String requestPath = request.getRequestURI();
		requestPath = requestPath.substring(request.getContextPath().length());// 去掉项目路径
		return requestPath;
	}	
	
	/**
	 * <p>从请求中过滤post过来的字节数组</p>
	 * @param request 请求
	 * @return
	 */
	public static byte[] filterBytesFromRequest(HttpServletRequest request){
		try {
            BufferedInputStream inputStream = new BufferedInputStream(request.getInputStream());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            
            byte [] bytes = new byte[1024];
            int v;
            while((v=inputStream.read(bytes))>0){
            	baos.write(bytes,0,v);
            }
            byte[] retBytes = baos.toByteArray();
            inputStream.close();
            baos.close();
            return retBytes;
		} catch(IOException ioex) {
			ioex.getStackTrace();
			return null;
		}
	}    
    
    /**
     * <p>获取指定服务的数据</p>
     * @param serv_location 服务地址
     * @return
     */
    public static byte[] getHttpServiceData(String serv_location){
    	HttpURLConnection urlConnection = null;
        try {
        	URL url = new URL(serv_location);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);        	 
        	BufferedInputStream bis = new BufferedInputStream(urlConnection.getInputStream());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte [] bytes = new byte[1024];
            int v;
            while((v=bis.read(bytes))>0) baos.write(bytes,0,v);
            return baos.toByteArray();
        } catch (IOException e) {
        	e.getStackTrace();
        } finally {
            if (urlConnection != null)urlConnection.disconnect();
        }
    	return null;
    }
}
