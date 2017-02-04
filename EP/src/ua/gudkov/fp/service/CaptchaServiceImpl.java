package ua.gudkov.fp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import ua.gudkov.fp.bean.CaptchaBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.entity.ErrorHolder;
import ua.gudkov.fp.service.api.CaptchaService;

/**
 * Captcha service implementation.
 * 
 * @author A.Gudkov
 *
 */
public class CaptchaServiceImpl implements CaptchaService {

	private long timeoutValue;
	private static final Logger LOG = Logger.getLogger(CaptchaServiceImpl.class);

	public CaptchaServiceImpl(long timeoutValue) {
		this.timeoutValue = timeoutValue;
		LOG.debug("Service created");
	}

	@Override
	public CaptchaBean extractCaptcha(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (CaptchaBean) session.getAttribute(Const.SessionAttr.CAPTCHA);
	}

	@Override
	public CaptchaBean putCaptcha(HttpServletRequest request) {
		CaptchaBean captchaBean = createCaptchaBean();
		request.getSession().setAttribute(Const.SessionAttr.CAPTCHA, captchaBean);
		return captchaBean;
	}

	@Override
	public boolean removeCaptcha(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute(Const.SessionAttr.CAPTCHA) != null) {
			session.removeAttribute(Const.SessionAttr.CAPTCHA);
			return true;
		}
		return false;

	}

	@Override
	public ErrorHolder validateCaptcha(String captchaValue, CaptchaBean captchaBean) {
		ErrorHolder errorHolder = new ErrorHolder();
		if (!isAlive(captchaBean, timeoutValue)) {
			errorHolder.add(Const.ErrorConstants.CAPTCHA_TIME_KEY);
		}
		if (!isEqual(captchaBean.getValue(), captchaValue)) {
			errorHolder.add(Const.ErrorConstants.CAPTCHA_VALUE_KEY);
		}
		return errorHolder;
	}

	/**
	 * Checks captcha is alive or not.
	 *
	 * @param captchaBean
	 *            the captcha bean
	 * @param timeoutValue
	 *            the interval of invalidation
	 * @return true if is alive, false - otherwise
	 */
	private boolean isAlive(CaptchaBean captchaBean, long timeoutValue) {
		long creationTime = captchaBean.getStartTime();
		long currentTime = System.currentTimeMillis();
		long spendTime = currentTime - creationTime;
		return spendTime <= timeoutValue;
	}

	/**
	 * Checks captcha is equal or not.
	 *
	 * @param captchaValue
	 *            the captcha value
	 * @param userValue
	 *            the user captcha value
	 * @return true if is equal, false - otherwise
	 */
	private boolean isEqual(String captchaValue, String userValue) {
		return !(StringUtils.isEmpty(captchaValue) || StringUtils.isEmpty(userValue)) && captchaValue.equals(userValue);
	}

	/**
	 * Creates the captcha bean.
	 *
	 * @return the captcha bean
	 */
	private CaptchaBean createCaptchaBean() {
		String captchaValue = RandomStringUtils.randomAlphanumeric(6);
		return new CaptchaBean(captchaValue, System.currentTimeMillis());
	}

}
