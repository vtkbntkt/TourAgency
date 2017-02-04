package ua.gudkov.fp.extractor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import ua.gudkov.fp.bean.RegisterFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.extractor.api.Extractor;

/**
 * Registration form bean extractor.
 * 
 * @author A.Gudkov
 *
 */
public class RegistrationFormExtractor implements Extractor<RegisterFormBean> {

	@Override
	public RegisterFormBean extract(HttpServletRequest request) throws IOException, ServletException {
		RegisterFormBean rfb = new RegisterFormBean();
		rfb.setEmail(request.getParameter(Const.SignUpForm.EMAIL));
		rfb.setFirstName(request.getParameter(Const.SignUpForm.FIRST_NAME));
		rfb.setLastName(request.getParameter(Const.SignUpForm.LAST_NAME));
		rfb.setPassword(request.getParameter(Const.SignUpForm.PWD));
		rfb.setRepeatPassword(request.getParameter(Const.SignUpForm.REPEATED_PWD));
		return rfb;
	}

}
