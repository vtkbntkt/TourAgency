package ua.gudkov.fp.servlet.users;

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
import ua.gudkov.fp.service.api.UserService;

/**
 * Servlet implementation class UserStatusServlet
 */
@WebServlet("/user_status")
public class UserStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(UserStatusServlet.class);

	private ServletContext servletContext;
	private UserService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		servletContext = getServletContext();
		userService = (UserService) servletContext.getAttribute(Const.ContextAttr.USER_SERVICE);
		LOG.debug("User status servlet initialization finished");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter(Const.RequestParam.USER_ID);
		if (StringUtils.isNumeric(userId)) {
			userService.changeStatus((Long.parseLong(userId)));
		}

		String referer = request.getHeader(Const.HttpHeaders.REFERER);
		String path = referer != null ? referer : Const.Url.Servlet.USERS;
		response.sendRedirect(path);
	}

}
