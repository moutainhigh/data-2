package com.hourz.pmodel;

public class P_Auth {
	
	// 登录名(可以是用户名、手机、邮箱)
	private String loginName;
	// 密码
	private String password;
	// 是否记住登录
	private boolean rememberMe;
	// 验证码
	private String captcha;
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
}
