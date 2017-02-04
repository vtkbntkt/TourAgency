package ua.gudkov.fp.servlet.currency;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.entity.BankCode;

/**
 * Servlet implementation class CurrencyServlet
 */
@WebServlet("/currency")
public class CurrencyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(CurrencyServlet.class);
	
	@Override
	public void init() throws ServletException {
		super.init();
		LOG.debug("Currency servlet initialization finished");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String bc = (String) request.getParameter(Const.CurrencyForm.CURRENCY_OPTION);
		if (BankCode.getValue(bc) != null) {
			session.setAttribute(Const.SessionAttr.CURR_ATTR, bc);
		}

		String referer = request.getHeader(Const.HttpHeaders.REFERER);
		String path = referer != null ? referer : Const.Url.Servlet.TOURS;
		response.sendRedirect(path);
	}

}
