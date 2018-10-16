package com.hourz.common.constant;

/**
 * <p>常量</p>
 * @author hourz
 * @since 2018-09-02
 */
public class Constant {

	// Session中保存LoginInfo对象的键
	public static final String Session_LoginInfo_Key = "Session_LoginInfo_Key";
	// 没有权限
	public static final String Res_Code_No_Auth = "No_Auth";
	// 没有登陆
	public static final String Res_Code_No_Login = "No_Login";
	// Cookie中，“是否记住登录状态” 的Key
	public static final String Config_Remember_Me_Cookie_Enable_Key = "remember_me_cookie_enable_key";
	// Cookie中，“加密的登录信息” 的Key
	public static final String Config_Remember_Me_Cookie_Secret_Key = "remember_me_cookie_secret_key";
	// Cookie中，“用于加密登录信息的秘钥” 的Key
	public static final String Config_Remember_Me_Encryption_Key = "remember_me_encryption_key";
	// 保存登录信息的天数
	public static final String Config_Remember_Me_Valid_Days =  "remember_me_valid_days";
	// 验证码错误
	public static final String Res_Code_Captcha_Error = "captcha_error ";
	// 密码错误
	public static final String Res_Code_Password_Error = "password_error";
	// 登录成功
	public static final String Res_Code_Login_Success = "login_success";
	// 登录失败
	public static final String Res_Code_Login_Fail = "login_fail";
	// 验证码SESSIONKEY
	public static final String KAPTCHA_SESSION_KEY = "";
	
}
