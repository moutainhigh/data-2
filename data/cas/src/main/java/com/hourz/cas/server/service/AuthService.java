package com.hourz.cas.server.service;

import com.hourz.pojo.Auth;
import com.hourz.pojo.User;
/**
 * <p>登录验证接口</P>
 * @author hourz
 * @since 2018-09-02
 */
public interface AuthService {
	
	/**
	 * 登录验证
	 * @param auth 登录信息
	 * @return 
	 */
	User login(Auth auth);
}
