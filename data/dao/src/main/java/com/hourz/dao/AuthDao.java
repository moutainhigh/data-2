package com.hourz.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import com.hourz.dao.provider.AuthProvider;
import com.hourz.pojo.Auth;
import com.hourz.pojo.User;

/**
 * <p>登录验证</p>
 * @author hourz
 * @since 2018-09-10
 */
@Mapper
public interface AuthDao {

	/**
	 * 根据邮箱获取用户信息
	 * @param auth 权限
	 * @return 用户信息
	 */
	@SelectProvider(method = "email", type = AuthProvider.class)
	User loginMail(Auth auth);
	
	/**
	 * 根据手机号获取用户信息
	 * @param auth 权限
	 * @return 用户信息
	 */
	@SelectProvider(method = "mobile", type = AuthProvider.class)
	User loginMobile(Auth auth);
	
	/**
	 * 根据登录名获取用户信息
	 * @param auth 权限
	 * @return 用户信息
	 */
	@SelectProvider(method = "name", type = AuthProvider.class)
	User loginName(Auth auth);
	
}
