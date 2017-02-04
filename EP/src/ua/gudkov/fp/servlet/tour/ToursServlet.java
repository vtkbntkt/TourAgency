package ua.gudkov.fp.servlet.tour;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.gudkov.fp.bean.TourFilterBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.converter.TourFilterFormBeanToTourFilter;
import ua.gudkov.fp.converter.api.Converter;
import ua.gudkov.fp.entity.TourFilter;
import ua.gudkov.fp.extractor.TourFilterFormExtractor;
import ua.gudkov.fp.extractor.api.Extractor;
import ua.gudkov.fp.service.api.CurrencyService;
import ua.gudkov.fp.service.api.HotelService;
import ua.gudkov.fp.service.api.TourService;

/**
 * Servlet implementation class ToursServlet
 */
@WebServlet("/tours")
public class ToursServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ToursServlet.class);

	private TourService tourService;
	private HotelService hotelService;
	private ServletContext servletContext;
	private CurrencyService currencyService;

	private Extractor<TourFilterBean> extractor = new TourFilterFormExtractor();
	private Converter<TourFilterBean, TourFilter> converter = new TourFilterFormBeanToTourFilter();

	@Override
	public void init() throws ServletException {
		super.init();
		servletContext = getServletContext();
		tourService = (TourService) servletContext.getAttribute(Const.ContextAttr.TOUR_SERVICE);
		hotelService = (HotelService) servletContext.getAttribute(Const.ContextAttr.HOTEL_SERVICE);
		currencyService = (CurrencyService) servletContext.getAttribute(Const.ContextAttr.CURRENCY_SERVICE);
		LOG.debug("Tours servlet initialization finished");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TourFilterBean filterForm = extractor.extract(request);
		HttpSession session = request.getSession();
		Object currency = session.getAttribute(Const.SessionAttr.CURR_ATTR);
		if (currency != null) {
			String bankCode = String.valueOf(currency);
			filterForm.setRate(String.valueOf(currencyService.findByName(bankCode).getRate()));
		}

		TourFilter tourFilter = converter.convert(filterForm);
		request.setAttribute(Const.RequestAttr.TOUR_LIST, tourService.getFullTours(tourFilter));
		request.setAttribute(Const.RequestAttr.ITEMS_TOTAL, tourService.getFullTourNum(tourFilter));
		request.setAttribute(Const.RequestAttr.DATE_VALUES, tourService.deptDateValues());
		request.setAttribute(Const.RequestAttr.RATING_VALUES, hotelService.ratingValues());
		request.setAttribute(Const.RequestAttr.CONTRY_VALUES, hotelService.countryValues());
		request.setAttribute(Const.RequestAttr.NIGHT_VALUES, tourService.nightCountValues());
		request.setAttribute(Const.RequestAttr.PERSON_VALUES, tourService.personCountValues());
		request.setAttribute(Const.RequestAttr.TYPE_VALUES, tourService.tourTypeValues());

		request.getRequestDispatcher(Const.Url.Jsp.FIND_TOUR).forward(request, response);
	}

}
