package ua.gudkov.fp.servlet.order;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.gudkov.fp.bean.DiscountEditFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.entity.Discount;
import ua.gudkov.fp.entity.ErrorHolder;
import ua.gudkov.fp.extractor.DiscountEditFormExtractor;
import ua.gudkov.fp.extractor.api.Extractor;
import ua.gudkov.fp.service.api.DiscountService;
import ua.gudkov.fp.validator.DiscountEditFormValidator;
import ua.gudkov.fp.validator.api.Validator;

/**
 * Servlet implementation class DiscountEditingServlet
 */
@WebServlet("/edit_discount")
public class DiscountEditingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(DiscountEditingServlet.class);
	private Extractor<DiscountEditFormBean> extractor = new DiscountEditFormExtractor();
	private Validator<DiscountEditFormBean> validator = new DiscountEditFormValidator();

	private DiscountService discountService;
	private ServletContext servletContext;

	@Override
	public void init() throws ServletException {
		super.init();
		servletContext = getServletContext();
		discountService = (DiscountService) servletContext.getAttribute(Const.ContextAttr.DISCOUNT_SERVICE);
		LOG.debug("Discount editing Servlet initialization finished");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		Object errorsSession = session.getAttribute(Const.ErrorConstants.EDITDISC_ERRORS);
		if (errorsSession != null) {
			session.removeAttribute(Const.ErrorConstants.EDITDISC_ERRORS);
			request.setAttribute(Const.ErrorConstants.EDITDISC_ERRORS, errorsSession);
		}

		Object noticeSession = session.getAttribute(Const.SuccessMsg.DISCOUNT_MSG);
		if (noticeSession != null) {
			session.removeAttribute(Const.SuccessMsg.DISCOUNT_MSG);
			request.setAttribute(Const.SuccessMsg.DISCOUNT_MSG, noticeSession);
		}

		Discount currentDiscount = discountService.findDiscount();
		request.setAttribute(Const.RequestAttr.CURRENT_DISCOUNT, currentDiscount);
		request.getRequestDispatcher(Const.Url.Jsp.EDIT_DISCOUNT).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DiscountEditFormBean form = extractor.extract(request);
		HttpSession session = request.getSession();
		ErrorHolder errors = validator.validate(form);
		if (errors.isEmpty()) {
			if (discountService.updateDiscount(0, Double.valueOf(form.getPercentPerTour()),
					Double.valueOf(form.getMaxPercenr()))) {
				session.setAttribute(Const.SuccessMsg.DISCOUNT_MSG, Const.SuccessMsg.DISC_UPDATE_KEY);
				response.sendRedirect(Const.Url.Servlet.EDIT_DISCOUNT);
				return;
			}
			errors.add(Const.ErrorConstants.EDITDISC_MSG_KEY);
		}

		session.setAttribute(Const.ErrorConstants.EDITDISC_ERRORS, errors.getErrors());
		response.sendRedirect(Const.Url.Servlet.EDIT_DISCOUNT);
	}

}
