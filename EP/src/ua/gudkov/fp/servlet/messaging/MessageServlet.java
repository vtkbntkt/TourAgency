package ua.gudkov.fp.servlet.messaging;

import java.io.IOException;
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
import ua.gudkov.fp.entity.Message;
import ua.gudkov.fp.service.api.MsgService;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet("/message")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(MessageServlet.class);
	private ServletContext servletContext;
	private MsgService msgService;

	@Override
	public void init() throws ServletException {
		super.init();
		servletContext = getServletContext();
		msgService = (MsgService) servletContext.getAttribute(Const.ContextAttr.MSG_SERVICE);
		LOG.debug("Message servlet initialization finished");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idMsg = request.getParameter(Const.RequestParam.MSG_ID);
		Message msg = null;
		if (StringUtils.isNumeric(idMsg)) {
			msg = msgService.findByIdMessage(Long.parseLong(idMsg));
			if (Objects.nonNull(msg)) {
				request.setAttribute(Const.RequestAttr.SINGLE_MSG, msg);
				request.getRequestDispatcher(Const.Url.Jsp.VIEW_MSG).forward(request, response);

				return;
			}
		}
		request.getRequestDispatcher(Const.Url.Servlet.MESSAGES).forward(request, response);

	}

}
