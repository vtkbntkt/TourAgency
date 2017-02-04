package ua.gudkov.fp.servlet.order;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.gudkov.fp.bean.SimpleFilterFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.converter.SimpleFilterBeanToSimpleFilter;
import ua.gudkov.fp.converter.api.Converter;
import ua.gudkov.fp.entity.OrderStatus;
import ua.gudkov.fp.entity.SimpleFilter;
import ua.gudkov.fp.entity.User;
import ua.gudkov.fp.extractor.SimpleFilterFormExtractor;
import ua.gudkov.fp.extractor.api.Extractor;
import ua.gudkov.fp.service.api.OrderService;

/**
 * Servlet implementation class OrdersServlet
 */
@WebServlet("/orders")
public class OrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(OrdersServlet.class);
	private ServletContext servletContext;
	private OrderService orderService;
	private Extractor<SimpleFilterFormBean> extractor = new SimpleFilterFormExtractor();
	private Converter<SimpleFilterFormBean, SimpleFilter> converter = new SimpleFilterBeanToSimpleFilter();

	@Override
	public void init() throws ServletException {
		super.init();
		servletContext = getServletContext();
		orderService = (OrderService) servletContext.getAttribute(Const.ContextAttr.ORDER_SERVICE);
		LOG.debug("Orders servlet initialization finished");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Const.SessionAttr.USER);
		SimpleFilterFormBean filterForm = extractor.extract(request);
		SimpleFilter filter = converter.convert(filterForm);

		request.setAttribute(Const.RequestAttr.ORDER_STATUS_LIST, OrderStatus.values());
		request.setAttribute(Const.RequestAttr.ORDER_LIST, orderService.getOrders(user, filter));
		request.setAttribute(Const.RequestAttr.ITEMS_TOTAL, orderService.orderNumber(user));
		request.getRequestDispatcher(Const.Url.Jsp.ORDERS).forward(request, response);

	}

	

}
