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

/**
 * Servlet implementation class LanguageServlet
 */
@WebServlet("/language")
public class LanguageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(LanguageServlet.class);
       
	@Override
	public void init() throws ServletException {
		super.init();
		LOG.debug("Language servlet initialization finished");

	}
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String lang = (String) request.getParameter(Const.LangForm.LANG);
		if (lang == null) {
			lang = Const.LangForm.DEF_LANG;
		} else if (!lang.equals(Const.LangForm.EN_LANG) && !lang.equals(Const.LangForm.RU_LANG)) {
			lang = Const.LangForm.DEF_LANG;

		}
		session.setAttribute(Const.SessionAttr.LANG_ATTR, lang);
		
		String referer = request.getHeader(Const.HttpHeaders.REFERER);
		String path = referer != null ? referer : Const.Url.Servlet.TOURS;
		response.sendRedirect(path);


	}

	

}
