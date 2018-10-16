package com.hourz.cas.server.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.Producer;
import com.hourz.cas.server.common.captcha.CaptchaUtils;
import com.hourz.cas.server.service.AuthService;
import com.hourz.common.config.Config;
import com.hourz.common.constant.Constant;
import com.hourz.common.json.CResult;
import com.hourz.pojo.Auth;
import com.hourz.pojo.LoginInfo;
import com.hourz.pojo.User;

/**
 * <p>登录接口</p>
 * @author hourz
 * @since 2018-09-01
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

	protected final static Log logger = LogFactory.getLog(AuthController.class);
	
	@Autowired
	AuthService authService;
	
	@Autowired
	Producer captchaProducer;
	
	
	@RequestMapping(value = "captcha", method = RequestMethod.GET)
	@ExceptionHandler(value = Exception.class)
	public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    CaptchaUtils.getInstance().getCaptcha(request, response);
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ExceptionHandler(value = Exception.class)
	public CResult<User> login(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Auth auth) {
		// 判断登录结果
		boolean success = false;
		// 判断登录信息
		String message = null;
		// 判断验证码是否正确
		if (!CaptchaUtils.getInstance().checkVerifyCaptcha(request, auth.getCaptcha())) {
			logger.debug("验证码错误，用户验证码为："+auth.getCaptcha());
			message=Config.getInstance().getProperty(Constant.Res_Code_Captcha_Error);
			return new CResult<User>(success, null, null, null, null, message);
		}
		// 进行登录
		User user = authService.login(auth);
		if (user != null) {
			// 记录登录状态
			if (auth.isRememberMe()) {
				success = true;
				int validDays = Integer.parseInt(Config.getInstance().getProperty(Constant.Config_Remember_Me_Valid_Days));
				//HttpUtils.openRememberMe(response, auth.getLoginName(), auth.getPassword(), validDays);
				message=Config.getInstance().getProperty(Constant.Res_Code_Login_Success);
				logger.debug("用户 '" + auth.getLoginName() + "' 登录成功！");
			} else {
				//HttpUtils.closeRememberMe(response);
				message=Config.getInstance().getProperty(Constant.Res_Code_Password_Error);
				logger.debug("密码错误，错误登录密码为：" + user.getPassword());
			}
		}
		return new CResult<User>(success, null, user, null, null, message);
	}
	
	/**
	 * 是否已经处于登录状态
	 */
	@RequestMapping(value = "/islogin", method = RequestMethod.GET)
	@ExceptionHandler(value = Exception.class)
	public CResult<Boolean> isLogin(HttpServletRequest request, HttpServletResponse response) {
		LoginInfo info = getLoginInfo(request, response);
		return new CResult<Boolean>(true, null, info!=null, null, null, null);
	}
	
	/**
	 * 退出
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public CResult<Object> logout(HttpServletRequest request, HttpServletResponse response) {
		// 将登录信息对象保存到Session 
		request.getSession().removeAttribute(Constant.Session_LoginInfo_Key);
		// 关闭RememberMe
		//HttpUtils.closeRememberMe(response);
		// 返回结果
		return new CResult<Object>(true);
	}
	
	/**
	 * 当前是否为RememberMe状态
	 */
	public CResult<Boolean> isRememberMe(HttpServletRequest request, HttpServletResponse response) {
		return new CResult<Boolean>(true, null, 
				//HttpUtils.isRememberMeEnabled(request)
				null
				, null, null, null);
	}
	
	/**
	 * 获取登录信息对象
	 * @return
	 */
	protected LoginInfo getLoginInfo(HttpServletRequest request, HttpServletResponse response){
		LoginInfo loginInfo = (LoginInfo) request.getSession().getAttribute(Constant.Session_LoginInfo_Key);
		return loginInfo;
	}
}
