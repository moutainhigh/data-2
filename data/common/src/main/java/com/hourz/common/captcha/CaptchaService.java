package com.hourz.common.captcha;

import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

/**
 * <p>图片服务提供类</p>
 * @author hourz
 * @since 2016-06-20
 */
public class CaptchaService {
	// 图片验证码服务
	private static ImageCaptchaService instance = new DefaultManageableImageCaptchaService(
			new FastHashMapCaptchaStore(), 
			new ImageEngine(), 
			180,        // 验证码至少能保存秒数
			100000,   // 最多能存储的验证码个数
			75000);
	
	/**
	 * 获取验证码服务的实例
	 * @return
	 */
	public static ImageCaptchaService instance() {
		return instance;
	}
}
