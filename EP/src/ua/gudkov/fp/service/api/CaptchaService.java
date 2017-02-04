package ua.gudkov.fp.service.api;

import javax.servlet.http.HttpServletRequest;

import ua.gudkov.fp.bean.CaptchaBean;
import ua.gudkov.fp.entity.ErrorHolder;

/**
 * Captcha service interface.
 *
 * @author A.Gudkov
 */
public interface CaptchaService {
	
	/**
	 * Removes captcha bean from session
	 * 
	 * @param request the request
	 * @return true if captcha bean is deleted, false otherwise
	 */
	boolean removeCaptcha(HttpServletRequest request);

	/**
	 * Extracts captcha bean from request
	 * 
	 * @param request
	 *            the request
	 * @return the captcha bean
	 */
	CaptchaBean extractCaptcha(HttpServletRequest request);

	/**
	 * Creates captcha bean and puts it into session
	 * 
	 * @param request
	 *            the request
	 * @return the captcha bean
	 */
	CaptchaBean putCaptcha(HttpServletRequest request);

	/**
	 * Validates given captcha value according to given captcha bean.
	 * 
	 * @param captchaValue
	 *            value received from user
	 * @param captchaBean
	 *            captcha bean extracted from session
	 * @return error holder contained errors if they exists or empty error
	 *         holder if the value is valid
	 */
	ErrorHolder validateCaptcha(String captchaValue, CaptchaBean captchaBean);

}
