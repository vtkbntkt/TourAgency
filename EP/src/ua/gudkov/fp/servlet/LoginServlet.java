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

import ua.gudkov.fp.bean.LoginFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.entity.ErrorHolder;
import ua.gudkov.fp.entity.User;
import ua.gudkov.fp.extractor.LoginFormExtractor;
import ua.gudkov.fp.extractor.api.Extractor;
import ua.gudkov.fp.service.api.CaptchaService;
import ua.gudkov.fp.service.api.UserService;
import ua.gudkov.fp.validator.LoginFormValidator;
import ua.gudkov.fp.validator.api.Validator;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(LoginServlet.class);
	private Extractor<LoginFormBean> extractor = new LoginFormExtractor();
	private Validator<LoginFormBean> validator = new LoginFormValidator();

	private ServletContext servletContext;
	private UserService userService;
	private CaptchaService captchaService;

	@Override
	public void init() throws ServletException {
		super.init();
		servletContext = getServletContext();
		userService = (UserService) servletContext.getAttribute(Const.ContextAttr.USER_SERVICE);
		captchaService = (CaptchaService) getServletContext().getAttribute(Const.ContextAttr.CAPTCHA_SERVICE);
		LOG.debug("Login servlet initialization finished");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object errorsSession = session.getAttribute(Const.ErrorConstants.LOGIN_ERRORS);
		if (errorsSession != null) {
			session.removeAttribute(Const.ErrorConstants.LOGIN_ERRORS);
			request.setAttribute(Const.ErrorConstants.LOGIN_ERRORS, errorsSession);
		}

		Object formBeanSession = session.getAttribute(Const.SessionAttr.LOGIN_FORM);
		if (formBeanSession != null) {
			session.removeAttribute(Const.SessionAttr.LOGIN_FORM);
			request.setAttribute(Const.SessionAttr.LOGIN_FORM, formBeanSession);
		}
		request.getRequestDispatcher(Const.Url.Jsp.SIGN_IN).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		LoginFormBean loginForm = extractor.extract(request);
		ErrorHolder errors = captchaService.validateCaptcha(loginForm.getCaptchaValue(),
				captchaService.extractCaptcha(request));
		captchaService.removeCaptcha(request);

		if (errors.isEmpty()) {
			errors = validator.validate(loginForm);
			if (errors.isEmpty()) {
				User user = userService.login(loginForm.getUsername(), loginForm.getPassword());
				if (user != null) {
					session.setAttribute(Const.SessionAttr.USER, user);
					response.sendRedirect(Const.Url.Servlet.ORDERS);
					return;
				}
				errors.add(Const.ErrorConstants.LOGIN_MSG_KEY);
			}
		}

		loginForm.setPassword(StringUtils.EMPTY);
		session.setAttribute(Const.ErrorConstants.LOGIN_ERRORS, errors.getErrors());
		session.setAttribute(Const.SessionAttr.LOGIN_FORM, loginForm);
		response.sendRedirect(Const.Url.Servlet.SIGN_IN);

	}

}
