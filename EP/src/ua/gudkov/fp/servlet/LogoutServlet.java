package ua.gudkov.fp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.gudkov.fp.constant.Const;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(LogoutServlet.class);
	
	@Override
	public void init() throws ServletException {
		super.init();
		LOG.debug("Logout servlet initialization finished");

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute(Const.SessionAttr.USER);
		}

		request.getRequestDispatcher(Const.Url.Servlet.TOURS).forward(request, response);


	}

}
