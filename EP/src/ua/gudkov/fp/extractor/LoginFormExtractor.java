package ua.gudkov.fp.extractor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import ua.gudkov.fp.bean.LoginFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.extractor.api.Extractor;

/**
 * Login form bean extractor.
 * 
 * @author A.Gudkov
 *
 */
public class LoginFormExtractor implements Extractor<LoginFormBean> {

	@Override
	public LoginFormBean extract(HttpServletRequest request) throws IOException, ServletException {
		LoginFormBean lfb = new LoginFormBean();
		lfb.setPassword(request.getParameter(Const.LoginForm.PASSWORD));
		lfb.setUsername(request.getParameter(Const.LoginForm.USERNAME));
		lfb.setCaptchaValue(request.getParameter(Const.LoginForm.CAPTCHA_VALUE));
		return lfb;
	}

}
