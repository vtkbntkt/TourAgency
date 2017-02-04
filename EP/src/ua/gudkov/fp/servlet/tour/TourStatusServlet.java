package ua.gudkov.fp.servlet.tour;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.service.api.TourService;

/**
 * Servlet implementation class TourStatusServlet
 */
@WebServlet("/tour_status")
public class TourStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(TourStatusServlet.class);

	private TourService tourServise;
	private ServletContext servletContext;

	@Override
	public void init() throws ServletException {
		super.init();
		servletContext = getServletContext();
		tourServise = (TourService) servletContext.getAttribute(Const.ContextAttr.TOUR_SERVICE);
		LOG.debug("Tour status servlet initialization finished");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tourId = request.getParameter(Const.RequestParam.TOUR_ID);
		if (StringUtils.isNumeric(tourId)) {
			tourServise.changeStatus((Long.parseLong(tourId)));
		}

		String referer = request.getHeader(Const.HttpHeaders.REFERER);
		String path = referer != null ? referer : Const.Url.Servlet.TOURS;
		response.sendRedirect(path);
	}

}
