package ua.gudkov.fp.servlet.messaging;

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
import ua.gudkov.fp.service.api.MsgService;

/**
 * Servlet implementation class MessagesServlet
 */
@WebServlet("/messages")
public class MessagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(MessagesServlet.class);

	private ServletContext servletContext;
	private MsgService msgService;
	private Extractor<SimpleFilterFormBean> extractor = new SimpleFilterFormExtractor();
	private Converter<SimpleFilterFormBean, SimpleFilter> converter = new SimpleFilterBeanToSimpleFilter();

	@Override
	public void init() throws ServletException {
		super.init();
		servletContext = getServletContext();
		msgService = (MsgService) servletContext.getAttribute(Const.ContextAttr.MSG_SERVICE);
		LOG.debug("Messages servlet initialization finished");


	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SimpleFilterFormBean filterForm = extractor.extract(request);
		SimpleFilter filter = converter.convert(filterForm);
		 
		request.setAttribute(Const.RequestAttr.ITEMS_TOTAL, msgService.msgNumber());
		request.setAttribute(Const.RequestAttr.MSG_LIST, msgService.findAll(filter));
		request.getRequestDispatcher(Const.Url.Jsp.MESSAGES).forward(request, response);

	}

	

}
