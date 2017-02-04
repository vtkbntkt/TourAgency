package ua.gudkov.fp.servlet.order;

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
import ua.gudkov.fp.service.api.OrderService;

/**
 * Servlet implementation class OrderStatusServlet
 */
@WebServlet("/order_status")
public class OrderStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(OrderStatusServlet.class);
	private ServletContext servletContext;
	private OrderService orderService;

	@Override
	public void init() throws ServletException {
		super.init();
		servletContext = getServletContext();
		orderService = (OrderService) servletContext.getAttribute(Const.ContextAttr.ORDER_SERVICE);
		LOG.debug("Order status servlet initialization finished");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderId = request.getParameter(Const.RequestParam.ORDER_ID);
		String orderStatus = request.getParameter(Const.RequestParam.ORDER_STATUS);
		if (StringUtils.isNumeric(orderId) && StringUtils.isNumeric(orderStatus)) {
			orderService.changeStatus((Long.parseLong(orderId)), Integer.parseInt(orderStatus));
		}
		String referer = request.getHeader(Const.HttpHeaders.REFERER);
		String path = referer != null ? referer : Const.Url.Servlet.ORDERS;
		response.sendRedirect(path);
	}

}
