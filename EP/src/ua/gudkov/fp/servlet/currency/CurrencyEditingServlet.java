package ua.gudkov.fp.servlet.currency;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import ua.gudkov.fp.bean.CurrencyEditFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.entity.BankCode;
import ua.gudkov.fp.entity.Currency;
import ua.gudkov.fp.entity.ErrorHolder;
import ua.gudkov.fp.extractor.CurrencyEditFormExtractor;
import ua.gudkov.fp.extractor.api.Extractor;
import ua.gudkov.fp.service.api.CurrencyService;
import ua.gudkov.fp.validator.CurrencyEditFormValidator;
import ua.gudkov.fp.validator.api.Validator;

/**
 * Servlet implementation class CurrencyEditing
 */
@WebServlet("/edit_currency")
public class CurrencyEditingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(CurrencyEditingServlet.class);
	private Extractor<CurrencyEditFormBean> extractor = new CurrencyEditFormExtractor();
	private Validator<CurrencyEditFormBean> validator = new CurrencyEditFormValidator();

	private CurrencyService currencyService;
	private ServletContext servletContext;

	@Override
	public void init() throws ServletException {
		super.init();
		servletContext = getServletContext();
		currencyService = (CurrencyService) servletContext.getAttribute(Const.ContextAttr.CURRENCY_SERVICE);
		LOG.debug("Currency editing servlet initialization finished");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object errorsSession = session.getAttribute(Const.ErrorConstants.EDITCURR_ERRORS);
		if (errorsSession != null) {
			session.removeAttribute(Const.ErrorConstants.EDITCURR_ERRORS);
			request.setAttribute(Const.ErrorConstants.EDITCURR_ERRORS, errorsSession);
		}
		Object formBeanSession = session.getAttribute(Const.SessionAttr.CURR_EDIT_FORM);
		if (formBeanSession != null) {
			session.removeAttribute(Const.SessionAttr.CURR_EDIT_FORM);
			request.setAttribute(Const.SessionAttr.CURR_EDIT_FORM, formBeanSession);
		}
		Object noticeSession = session.getAttribute(Const.SuccessMsg.RATE_MSG);
		if (noticeSession != null) {
			session.removeAttribute(Const.SuccessMsg.RATE_MSG);
			request.setAttribute(Const.SuccessMsg.RATE_MSG, noticeSession);
		}
		List<Currency> currencies = currencyService.findAll();
		request.setAttribute(Const.SessionAttr.CURR_LIST, currencies);
		request.getRequestDispatcher(Const.Url.Jsp.EDIT_CURRENCY).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CurrencyEditFormBean currForm = extractor.extract(request);
		HttpSession session = request.getSession();
		ErrorHolder errors = validator.validate(currForm);
		if (errors.isEmpty()) {
			if (currencyService.updateRate(BankCode.valueOf(currForm.getBc().toUpperCase()),
					Double.valueOf(currForm.getRate()))) {
				session.setAttribute(Const.SuccessMsg.RATE_MSG, Const.SuccessMsg.RATE_UPDATE_KEY);
				session.setAttribute(Const.SessionAttr.CURR_EDIT_FORM, currForm);
				response.sendRedirect(Const.Url.Servlet.EDIT_CURRENCY);

				return;
			}
			errors.add(Const.ErrorConstants.EDITCURR_MSG_KEY);
		}

		session.setAttribute(Const.ErrorConstants.EDITCURR_ERRORS, errors.getErrors());
		session.setAttribute(Const.SessionAttr.CURR_EDIT_FORM, currForm);
		response.sendRedirect(Const.Url.Servlet.EDIT_CURRENCY);

	}

}
