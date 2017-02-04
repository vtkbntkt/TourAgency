package ua.gudkov.fp.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.gudkov.fp.bean.ContactFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.converter.ContactFormBeanToMessage;
import ua.gudkov.fp.converter.api.Converter;
import ua.gudkov.fp.entity.ErrorHolder;
import ua.gudkov.fp.entity.Message;
import ua.gudkov.fp.extractor.ContactFormExtractor;
import ua.gudkov.fp.extractor.api.Extractor;
import ua.gudkov.fp.service.api.MsgService;
import ua.gudkov.fp.util.FileManager;
import ua.gudkov.fp.validator.ContactFormValidator;
import ua.gudkov.fp.validator.api.Validator;

/**
 * Servlet implementation class ContactUsServlet
 */
@WebServlet("/contactus")
@MultipartConfig
public class ContactUsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ContactUsServlet.class);
	private Extractor<ContactFormBean> extractor = new ContactFormExtractor();
	private Validator<ContactFormBean> validator = new ContactFormValidator();
	private Converter<ContactFormBean, Message> converter = new ContactFormBeanToMessage();

	private MsgService msgService;
	private ServletContext servletContext;

	@Override
	public void init() throws ServletException {
		super.init();
		servletContext = getServletContext();
		msgService = (MsgService) servletContext.getAttribute(Const.ContextAttr.MSG_SERVICE);
		LOG.debug("Contact us Servlet initialization finished");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object errorsSession = session.getAttribute(Const.ErrorConstants.CONTACTUS_ERRORS);
		if (errorsSession != null) {
			session.removeAttribute(Const.ErrorConstants.CONTACTUS_ERRORS);
			request.setAttribute(Const.ErrorConstants.CONTACTUS_ERRORS, errorsSession);
		}

		Object formBeanSession = session.getAttribute(Const.SessionAttr.CONTACTUS_FORM);
		if (formBeanSession != null) {
			session.removeAttribute(Const.SessionAttr.CONTACTUS_FORM);
			request.setAttribute(Const.SessionAttr.CONTACTUS_FORM, formBeanSession);
		}

		Object noticeSession = session.getAttribute(Const.SuccessMsg.CONTACTUS_MSG);
		if (noticeSession != null) {
			session.removeAttribute(Const.SuccessMsg.CONTACTUS_MSG);
			request.setAttribute(Const.SuccessMsg.CONTACTUS_MSG, noticeSession);
		}
		request.getRequestDispatcher(Const.Url.Jsp.CONTACT_US).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ContactFormBean contactformBean = extractor.extract(request);
		ErrorHolder errors = validator.validate(contactformBean);
		Message msg = null;
		HttpSession session = request.getSession();
		if (errors.isEmpty()) {
			msg = converter.convert(contactformBean);
			if (msgService.addMsg(msg)) {
				FileManager.saveImage(contactformBean.getFilePart(), Const.File.ATTACHMENTS_DIR,
						String.valueOf(msg.getIdMsg()));
				session.setAttribute(Const.SuccessMsg.CONTACTUS_MSG, Const.SuccessMsg.CONTACTUS_SEND_KEY);
				response.sendRedirect(Const.Url.Servlet.CONTACT_US);
				return;
			}
			errors.add(Const.ContactForm.NOTICE, Const.ErrorConstants.CONTACTUS_SEND_KEY);
		}
		session.setAttribute(Const.ErrorConstants.CONTACTUS_ERRORS, errors.getErrors());
		session.setAttribute(Const.SessionAttr.CONTACTUS_FORM, contactformBean);
		response.sendRedirect(Const.Url.Servlet.CONTACT_US);

	}

	

}
