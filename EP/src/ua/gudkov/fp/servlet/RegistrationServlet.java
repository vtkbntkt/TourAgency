package ua.gudkov.fp.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import ua.gudkov.fp.bean.RegisterFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.converter.RegisterFormBeanToUser;
import ua.gudkov.fp.converter.api.Converter;
import ua.gudkov.fp.entity.ErrorHolder;
import ua.gudkov.fp.entity.User;
import ua.gudkov.fp.extractor.RegistrationFormExtractor;
import ua.gudkov.fp.extractor.api.Extractor;
import ua.gudkov.fp.service.api.UserService;
import ua.gudkov.fp.util.Mail;
import ua.gudkov.fp.validator.RegistrationFormValidator;
import ua.gudkov.fp.validator.api.Validator;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(RegistrationServlet.class);
	private Extractor<RegisterFormBean> extractor = new RegistrationFormExtractor();
	private Validator<RegisterFormBean> validator = new RegistrationFormValidator();
	private Converter<RegisterFormBean, User> converter = new RegisterFormBeanToUser();

	private ServletContext servletContext;
	private UserService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		servletContext = getServletContext();
		userService = (UserService) servletContext.getAttribute(Const.ContextAttr.USER_SERVICE);
		LOG.debug("Registration servlet initialization finished");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object errorsSession = session.getAttribute(Const.ErrorConstants.REGISTER_ERRORS);
		if (errorsSession != null) {
			session.removeAttribute(Const.ErrorConstants.REGISTER_ERRORS);
			request.setAttribute(Const.ErrorConstants.REGISTER_ERRORS, errorsSession);
		}

		Object formBeanSession = session.getAttribute(Const.SessionAttr.REGISTER_FORM);
		if (formBeanSession != null) {
			session.removeAttribute(Const.SessionAttr.REGISTER_FORM);
			request.setAttribute(Const.SessionAttr.REGISTER_FORM, formBeanSession);
		}
		request.getRequestDispatcher(Const.Url.Jsp.SIGN_UP).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RegisterFormBean registerForm = extractor.extract(request);
		ErrorHolder errors = validator.validate(registerForm);
		User user;
		if (errors.isEmpty()) {
			user = converter.convert(registerForm);
			if (userService.registerUser(user)) {
				sendRegistrationMsg();
				response.sendRedirect(Const.Url.Servlet.SIGN_IN);

				return;
			}
			errors.add(Const.ErrorConstants.REGISTER_MSG_KEY);
		}
		registerForm.setPassword(StringUtils.EMPTY);
		registerForm.setRepeatPassword(StringUtils.EMPTY);
		session.setAttribute(Const.ErrorConstants.REGISTER_ERRORS, errors.getErrors());
		session.setAttribute(Const.SessionAttr.REGISTER_FORM, registerForm);
		response.sendRedirect(Const.Url.Servlet.SIGN_UP);

	}

	/**
	 * Sends welcome message to recently registered user
	 */
	private void sendRegistrationMsg() {
		try {
			Mail.sendMail("aleksej.gudkov@gmail.com", "Registration", "Hi, You have been successfully registered.");
		} catch (Exception ex) {
			LOG.warn("Can't send email", ex);
		}
	}

}
