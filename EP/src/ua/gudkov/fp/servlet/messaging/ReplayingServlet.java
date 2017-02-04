package ua.gudkov.fp.servlet.messaging;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.gudkov.fp.bean.ReFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.entity.ErrorHolder;
import ua.gudkov.fp.extractor.ReFormExtractor;
import ua.gudkov.fp.extractor.api.Extractor;
import ua.gudkov.fp.util.Mail;
import ua.gudkov.fp.validator.ReFormValidator;
import ua.gudkov.fp.validator.api.Validator;

/**
 * Servlet implementation class ReplayingServlet
 */
@WebServlet("/msgreplaying")
public class ReplayingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ReplayingServlet.class);
	private Extractor<ReFormBean> extractor = new ReFormExtractor();
	private Validator<ReFormBean> validator = new ReFormValidator();
	
	@Override
	public void init() throws ServletException {
		super.init();
		LOG.debug("Message replay servlet initialization finished");


	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object errorsSession = session.getAttribute(Const.ErrorConstants.REPLAYING_ERRORS);
		if (errorsSession != null) {
			session.removeAttribute(Const.ErrorConstants.REPLAYING_ERRORS);
			request.setAttribute(Const.ErrorConstants.REPLAYING_ERRORS, errorsSession);
		}

		Object formBeanSession = session.getAttribute(Const.SessionAttr.RE_MSG_FORM);
		if (formBeanSession != null) {
			session.removeAttribute(Const.SessionAttr.RE_MSG_FORM);
			request.setAttribute(Const.SessionAttr.RE_MSG_FORM, formBeanSession);
		}

		Object noticeSession = session.getAttribute(Const.SuccessMsg.REPLAYING_MSG);
		if (noticeSession != null) {
			session.removeAttribute(Const.SuccessMsg.REPLAYING_MSG);
			request.setAttribute(Const.SuccessMsg.REPLAYING_MSG, noticeSession);
		}
		request.getRequestDispatcher(Const.Url.Jsp.RE_MSG).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReFormBean reformBean = extractor.extract(request);
		ErrorHolder errors = validator.validate(reformBean);
		HttpSession session = request.getSession();
		if (errors.isEmpty()) {
			try {
				Mail.sendMail(reformBean.getEmail(), reformBean.getSubject(), reformBean.getMessage());
				session.setAttribute(Const.SuccessMsg.REPLAYING_MSG, Const.SuccessMsg.RE_SEND_KEY);		
				response.sendRedirect(Const.Url.Servlet.RE_MSG);

				return;
			} catch (Exception ex) {
				LOG.warn("Can't send email", ex);
				errors.add(Const.ReplayingForm.NOTICE, Const.ErrorConstants.RE_SEND_KEY);
			}
		}
		session.setAttribute(Const.ErrorConstants.REPLAYING_ERRORS, errors.getErrors());
		session.setAttribute(Const.SessionAttr.RE_MSG_FORM, reformBean);
		response.sendRedirect(Const.Url.Servlet.RE_MSG);

	}

}
