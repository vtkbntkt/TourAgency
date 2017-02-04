package ua.gudkov.fp.servlet.tour;

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

import ua.gudkov.fp.bean.HotelFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.converter.HotelFormBeanToHotel;
import ua.gudkov.fp.converter.api.Converter;
import ua.gudkov.fp.entity.ErrorHolder;
import ua.gudkov.fp.entity.Hotel;
import ua.gudkov.fp.extractor.HotelFormExtractor;
import ua.gudkov.fp.extractor.api.Extractor;
import ua.gudkov.fp.service.api.HotelService;
import ua.gudkov.fp.util.FileManager;
import ua.gudkov.fp.validator.HotelFormValidator;
import ua.gudkov.fp.validator.api.Validator;

/**
 * Servlet implementation class HotelServlet
 */
@WebServlet("/hotel")
@MultipartConfig
public class HotelAddingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(HotelAddingServlet.class);
	private Extractor<HotelFormBean> extractor = new HotelFormExtractor();
	private Validator<HotelFormBean> validator = new HotelFormValidator();
	private Converter<HotelFormBean, Hotel> converter = new HotelFormBeanToHotel();

	private HotelService hotelService;
	private ServletContext servletContext;

	@Override
	public void init() throws ServletException {
		super.init();
		servletContext = getServletContext();
		hotelService = (HotelService) servletContext.getAttribute(Const.ContextAttr.HOTEL_SERVICE);
		LOG.debug("Hotel adding servlet initialization finished");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object errorsSession = session.getAttribute(Const.ErrorConstants.ADDHOTEL_ERRORS);
		if (errorsSession != null) {
			session.removeAttribute(Const.ErrorConstants.ADDHOTEL_ERRORS);
			request.setAttribute(Const.ErrorConstants.ADDHOTEL_ERRORS, errorsSession);
		}

		Object formBeanSession = session.getAttribute(Const.SessionAttr.HOTEL_FORM);
		if (formBeanSession != null) {
			session.removeAttribute(Const.SessionAttr.HOTEL_FORM);
			request.setAttribute(Const.SessionAttr.HOTEL_FORM, formBeanSession);
		}

		Object noticeSession = session.getAttribute(Const.SuccessMsg.HOTEL_MSG);
		if (noticeSession != null) {
			session.removeAttribute(Const.SuccessMsg.HOTEL_MSG);
			request.setAttribute(Const.SuccessMsg.HOTEL_MSG, noticeSession);
		}

		request.getRequestDispatcher(Const.Url.Jsp.ADD_HOTEL).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HotelFormBean hotelForm = extractor.extract(request);
		Hotel hotel = null;
		HttpSession session = request.getSession();
		ErrorHolder errors = validator.validate(hotelForm);
		if (errors.isEmpty()) {
			hotel = converter.convert(hotelForm);
			if (hotelService.addHotel(hotel)) {
				FileManager.saveImages(hotelForm.getParts(), Const.File.HOTEL_PHOTOS_DIR,
						String.valueOf(hotel.getId()));
				session.setAttribute(Const.SuccessMsg.HOTEL_MSG, Const.SuccessMsg.HOTEL_ADD_KEY);
				response.sendRedirect(Const.Url.Servlet.HOTEL);

				return;
			}
			errors.add(Const.ErrorConstants.ADDHOTEL_MSG_KEY);

		}

		session.setAttribute(Const.ErrorConstants.ADDHOTEL_ERRORS, errors.getErrors());
		session.setAttribute(Const.SessionAttr.HOTEL_FORM, hotelForm);
		response.sendRedirect(Const.Url.Servlet.HOTEL);

	}

}
