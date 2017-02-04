package ua.gudkov.fp.servlet.users;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.gudkov.fp.bean.SimpleFilterFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.converter.SimpleFilterBeanToSimpleFilter;
import ua.gudkov.fp.converter.api.Converter;
import ua.gudkov.fp.entity.SimpleFilter;
import ua.gudkov.fp.extractor.SimpleFilterFormExtractor;
import ua.gudkov.fp.extractor.api.Extractor;
import ua.gudkov.fp.service.api.UserService;

/**
 * Servlet implementation class UsersServlet
 */
@WebServlet("/users")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(UsersServlet.class);

	private ServletContext servletContext;
	private UserService userService;
	private Extractor<SimpleFilterFormBean> extractor = new SimpleFilterFormExtractor();
	private Converter<SimpleFilterFormBean, SimpleFilter> converter = new SimpleFilterBeanToSimpleFilter();

	@Override
	public void init() throws ServletException {
		super.init();
		servletContext = getServletContext();
		userService = (UserService) servletContext.getAttribute(Const.ContextAttr.USER_SERVICE);
		LOG.debug("Users servlet initialization finished");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SimpleFilterFormBean filterForm = extractor.extract(request);
		SimpleFilter filter = converter.convert(filterForm);
		request.setAttribute(Const.RequestAttr.USER_LIST, userService.getAllUsers(filter));
		request.setAttribute(Const.RequestAttr.ITEMS_TOTAL, userService.userNumber());
		request.getRequestDispatcher(Const.Url.Jsp.USERS).forward(request, response);

	}

}
