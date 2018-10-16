package com.hourz.cas.server.common.captcha;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.hourz.common.constant.Constant;

/**
 * <p> 验证码生成 </p>
 * @author hourz
 * @since 2018-10-15
 */
public class CaptchaUtils {
	
	// 双重锁模式:是饱汉模式的优化,进行双重判断,当已经创建过实例对象后就无需加锁,提高效率.
	private static CaptchaUtils singleton;
	/**
	 * <p>无参构造器</p>
	 */
	private CaptchaUtils(){
	}
	/**
	 * <p>双重锁模式实现</p>
	 * @return
	 */
	public static CaptchaUtils getInstance(){
		if(singleton == null){
			synchronized(CaptchaUtils.class){
				if(singleton == null){
					singleton = new CaptchaUtils();
				}
			}
		}
		return singleton;
	}

	@Autowired
	Producer captchaProducer;
	
	/**
	 * 验证验证码
	 * @param request
	 * @param captcha
	 * @return
	 */
	public boolean checkVerifyCaptcha(HttpServletRequest request, String captcha) {
        //获取生成的验证码
        String verifyCaptcha = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(verifyCaptcha == null || !verifyCaptcha.equals(captcha)) {
            return false;
        }
        return true;
    }
	
	/**
	 * 生成验证码
	 * @param request 
	 * @param response 
	 * @throws IOException
	 */
	public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
	    response.setDateHeader("Expires", 0);
	    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
	    response.addHeader("Cache-Control", "post-check=0, pre-check=0");
	    response.setHeader("Pragma", "no-cache");
	    response.setContentType("image/jpeg");
	    // 生成验证码
	    String captcha = captchaProducer.createText();
		session.setAttribute(Constant.Session_LoginInfo_Key, captcha);
	    // 向客户端写出
	    BufferedImage bi = captchaProducer.createImage(captcha);
	    ServletOutputStream out = response.getOutputStream();
	    ImageIO.write(bi, "jpg", out);
	    out.flush();
	    out.close();
	}

}
