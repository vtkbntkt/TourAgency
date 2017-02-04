package ua.gudkov.fp.servlet.tour;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.entity.FullTour;
import ua.gudkov.fp.service.api.TourService;
import ua.gudkov.fp.util.FileManager;

/**
 * Servlet implementation class TourServlet
 */
@WebServlet("/tour")
public class TourServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(TourServlet.class);
	private TourService tourServise;
	private ServletContext servletContext;

	@Override
	public void init() throws ServletException {
		super.init();
		servletContext = getServletContext();
		tourServise = (TourService) servletContext.getAttribute(Const.ContextAttr.TOUR_SERVICE);
		LOG.debug("Tour servlet initialization finished");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tourId = request.getParameter(Const.RequestParam.TOUR_ID);
		if (StringUtils.isNumeric(tourId)) {
			FullTour tour = tourServise.getFullTour(Long.parseLong(tourId));
			if (Objects.nonNull(tour)) {
				List<String> images = FileManager.getFileNames(Const.File.HOTEL_PHOTOS_DIR,
						String.valueOf(tour.getHotelId()));
				
				request.setAttribute(Const.RequestAttr.IMAGE_NAME_LIST, images);
				request.setAttribute(Const.RequestAttr.SINGLE_TOUR, tour);
				request.getRequestDispatcher(Const.Url.Jsp.VIEW_TOUR).forward(request, response);

				return;
			}
		}
		request.getRequestDispatcher(Const.Url.Servlet.TOURS).forward(request, response);
	}

}
