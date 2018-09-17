package com.hourz.cas.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hourz.cas.server.service.AuthService;
import com.hourz.common.pattern.PatternUtils;
import com.hourz.dao.AuthDao;
import com.hourz.pojo.Auth;
import com.hourz.pojo.User;

/**
 * <p>登录实现对象</p>
 * @author hourz
 * @since 2018-09-09
 */
@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	AuthDao authDao;
	
	@Override
	public User login(Auth auth) {
		User user = new User();
		// 验证邮箱
		if(PatternUtils.isEmail(auth.getLoginName())) {
			user = authDao.loginMail(auth);
		// 验证手机号
		} else if(PatternUtils.isMobile(auth.getLoginName())) {
			user = authDao.loginMobile(auth);
		// 验证登录名
		} else {
			user = authDao.loginName(auth);
		}
		return user;
	}

}
