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

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import ua.gudkov.fp.bean.TourEditFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.converter.TourEditFormBeanToTour;
import ua.gudkov.fp.converter.TourToTourEditFormBean;
import ua.gudkov.fp.converter.api.Converter;
import ua.gudkov.fp.entity.ErrorHolder;
import ua.gudkov.fp.entity.Hotel;
import ua.gudkov.fp.entity.Tour;
import ua.gudkov.fp.entity.Type;
import ua.gudkov.fp.extractor.TourEditFormExtractor;
import ua.gudkov.fp.extractor.api.Extractor;
import ua.gudkov.fp.service.api.HotelService;
import ua.gudkov.fp.service.api.TourService;
import ua.gudkov.fp.util.FileManager;
import ua.gudkov.fp.validator.TourEditFormValidator;
import ua.gudkov.fp.validator.api.Validator;

/**
 * Servlet implementation class TourEditing
 */
@WebServlet("/edit_tour")
@MultipartConfig
public class TourEditingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(TourEditingServlet.class);
	private Extractor<TourEditFormBean> extractor = new TourEditFormExtractor();
	private Validator<TourEditFormBean> validator = new TourEditFormValidator();
	private Converter<TourEditFormBean, Tour> formConverter = new TourEditFormBeanToTour();
	private Converter<Tour, TourEditFormBean> tourConverter = new TourToTourEditFormBean();

	private HotelService hotelService;
	private TourService tourService;
	private ServletContext servletContext;

	@Override
	public void init() throws ServletException {
		super.init();
		servletContext = getServletContext();
		hotelService = (HotelService) servletContext.getAttribute(Const.ContextAttr.HOTEL_SERVICE);
		tourService = (TourService) servletContext.getAttribute(Const.ContextAttr.TOUR_SERVICE);
		LOG.debug("Tour editing servlet initialization finished");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object errorsSession = session.getAttribute(Const.ErrorConstants.EDITTOUR_ERRORS);
		if (errorsSession != null) {
			session.removeAttribute(Const.ErrorConstants.EDITTOUR_ERRORS);
			request.setAttribute(Const.ErrorConstants.EDITTOUR_ERRORS, errorsSession);
		}

		Object formBeanSession = session.getAttribute(Const.SessionAttr.TOUR_FORM);
		if (formBeanSession != null) {
			session.removeAttribute(Const.SessionAttr.TOUR_EDIT_FORM);
			request.setAttribute(Const.SessionAttr.TOUR_EDIT_FORM, formBeanSession);
		} else {
			setExistingForm(request);
		}

		List<Hotel> hotels = hotelService.findAll();
		request.setAttribute(Const.RequestAttr.HOTEL_LIST, hotels);
		request.setAttribute(Const.RequestAttr.TYPE_LIST, Type.values());
		request.getRequestDispatcher(Const.Url.Jsp.EDIT_TOUR).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TourEditFormBean tourForm = extractor.extract(request);
		Tour tour = null;
		HttpSession session = request.getSession();
		ErrorHolder errors = validator.validate(tourForm);
		if (errors.isEmpty()) {
			tour = formConverter.convert(tourForm);
			if (tourService.updateTour(tour)) {
				FileManager.updateImage(tourForm.getPart(), Const.File.TOUR_PHOTOS_DIR, String.valueOf(tour.getId()));
				response.sendRedirect(
						Const.Url.Servlet.VIEW_TOUR + "?" + Const.RequestParam.TOUR_ID + "=" + tour.getId());
				return;
			}
			errors.add(Const.ErrorConstants.EDITTOUR_MSG_KEY);
		}

		session.setAttribute(Const.ErrorConstants.EDITTOUR_ERRORS, errors.getErrors());
		session.setAttribute(Const.SessionAttr.TOUR_EDIT_FORM, tourForm);
		response.sendRedirect(Const.Url.Servlet.EDIT_TOUR);

	}

	/**
	 * Puts in the request existing tour 
	 * 
	 * @param request
	 *            HttpServletRequest
	 */
	private void setExistingForm(HttpServletRequest request) {
		String tourId = request.getParameter(Const.RequestParam.TOUR_ID);
		if (StringUtils.isNumeric(tourId)) {
			Tour tour = tourService.getTour(Long.parseLong(tourId));
			if (tour != null) {
				TourEditFormBean tourForm = tourConverter.convert(tour);
				request.setAttribute(Const.SessionAttr.TOUR_EDIT_FORM, tourForm);
			}

		}
	}

}
