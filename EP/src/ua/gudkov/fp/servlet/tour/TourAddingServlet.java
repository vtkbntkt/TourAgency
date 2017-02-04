package ua.gudkov.fp.servlet.tour;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.gudkov.fp.bean.TourFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.converter.TourFormBeanToTour;
import ua.gudkov.fp.converter.api.Converter;
import ua.gudkov.fp.entity.ErrorHolder;
import ua.gudkov.fp.entity.Hotel;
import ua.gudkov.fp.entity.Tour;
import ua.gudkov.fp.entity.Type;
import ua.gudkov.fp.extractor.TourFormExtractor;
import ua.gudkov.fp.extractor.api.Extractor;
import ua.gudkov.fp.service.api.HotelService;
import ua.gudkov.fp.service.api.TourService;
import ua.gudkov.fp.util.FileManager;
import ua.gudkov.fp.validator.TourFormValidator;
import ua.gudkov.fp.validator.api.Validator;

/**
 * Servlet implementation class TourAddingServlet
 */
@WebServlet("/touradding")
@MultipartConfig
public class TourAddingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(TourAddingServlet.class);
	private Extractor<TourFormBean> extractor = new TourFormExtractor();
	private Validator<TourFormBean> validator= new TourFormValidator();
	private Converter<TourFormBean, Tour> converter = new TourFormBeanToTour();

	private HotelService hotelService;
	private TourService tourService;
	private ServletContext servletContext;

	@Override
	public void init() throws ServletException {
		super.init();
		servletContext = getServletContext();
		hotelService = (HotelService) servletContext.getAttribute(Const.ContextAttr.HOTEL_SERVICE);
		tourService = (TourService) servletContext.getAttribute(Const.ContextAttr.TOUR_SERVICE);
		LOG.debug("Tour adding servlet initialization finished");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object errorsSession = session.getAttribute(Const.ErrorConstants.ADDTOUR_ERRORS);
		if (errorsSession != null) {
			session.removeAttribute(Const.ErrorConstants.ADDTOUR_ERRORS);
			request.setAttribute(Const.ErrorConstants.ADDTOUR_ERRORS, errorsSession);
		}

		Object formBeanSession = session.getAttribute(Const.SessionAttr.TOUR_FORM);
		if (formBeanSession != null) {
			session.removeAttribute(Const.SessionAttr.TOUR_FORM);
			request.setAttribute(Const.SessionAttr.TOUR_FORM, formBeanSession);
		}

		Object noticeSession = session.getAttribute(Const.SuccessMsg.TOUR_MSG);
		if (noticeSession != null) {
			session.removeAttribute(Const.SuccessMsg.TOUR_MSG);
			request.setAttribute(Const.SuccessMsg.TOUR_MSG, noticeSession);
		}

		List<Hotel> hotels = hotelService.findAll();
		request.setAttribute(Const.RequestAttr.HOTEL_LIST, hotels);
		request.setAttribute(Const.RequestAttr.TYPE_LIST, Type.values());
		request.getRequestDispatcher(Const.Url.Jsp.ADD_TOUR).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TourFormBean tourForm = extractor.extract(request);
		Tour tour = null;
		HttpSession session = request.getSession();
		ErrorHolder errors = validator.validate(tourForm);
		if (errors.isEmpty()) {
			tour = converter.convert(tourForm);
			if (tourService.addTour(tour)) {
				FileManager.saveImage(tourForm.getPart(), Const.File.TOUR_PHOTOS_DIR,
						String.valueOf(tour.getId()));
				session.setAttribute(Const.SuccessMsg.TOUR_MSG, Const.SuccessMsg.TOUR_ADD_KEY);
				response.sendRedirect(Const.Url.Servlet.ADD_TOUR);

				return;
			}
			errors.add(Const.ErrorConstants.ADDTOUR_MSG_KEY);

		}

		session.setAttribute(Const.ErrorConstants.ADDTOUR_ERRORS, errors.getErrors());
		session.setAttribute(Const.SessionAttr.TOUR_FORM, tourForm);
		response.sendRedirect(Const.Url.Servlet.ADD_TOUR);

	}

}
