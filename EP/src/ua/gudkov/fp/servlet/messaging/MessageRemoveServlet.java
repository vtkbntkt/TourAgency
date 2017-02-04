package ua.gudkov.fp.servlet.messaging;

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
import ua.gudkov.fp.service.api.MsgService;
import ua.gudkov.fp.util.FileManager;

/**
 * Servlet implementation class MessageRemoveServlet
 */
@WebServlet("/message_remove")
public class MessageRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(MessageRemoveServlet.class);

	private ServletContext servletContext;
	private MsgService msgService;

	@Override
	public void init() throws ServletException {
		super.init();
		servletContext = getServletContext();
		msgService = (MsgService) servletContext.getAttribute(Const.ContextAttr.MSG_SERVICE);
		LOG.debug("Message remove servlet initialization finished");


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String delMsg = request.getParameter(Const.RequestParam.DEL_MSG_ID);
		if (!StringUtils.isEmpty(delMsg)) {
			delMessages(delMsg);
		}

		String referer = request.getHeader(Const.HttpHeaders.REFERER);
		String path = referer != null ? referer : Const.Url.Servlet.MESSAGES;
		response.sendRedirect(path);
	}

	/**
	 * Deletes messages found by given ids
	 * 
	 * @param idMsg id of messages
	 */
	private void delMessages(String idMsg) {
		String[] ids = idMsg.split(",");
		for (String str : ids) {
			if (msgService.delMsg(Long.valueOf(str))) {
				FileManager.delImage(Const.File.ATTACHMENTS_DIR, str);
			}
		}

	}

}
