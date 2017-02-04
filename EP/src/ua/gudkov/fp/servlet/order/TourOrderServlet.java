package ua.gudkov.fp.servlet.order;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.entity.Discount;
import ua.gudkov.fp.entity.Order;
import ua.gudkov.fp.entity.Tour;
import ua.gudkov.fp.entity.User;
import ua.gudkov.fp.service.api.DiscountService;
import ua.gudkov.fp.service.api.OrderService;
import ua.gudkov.fp.service.api.TourService;

/**
 * Servlet implementation class OrderAddingServlet
 */
@WebServlet("/order_tour")
public class TourOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(TourOrderServlet.class);

	private TourService tourServise;
	private OrderService orderService;
	private DiscountService discountService;

	private ServletContext servletContext;

	@Override
	public void init() throws ServletException {
		super.init();
		servletContext = getServletContext();
		tourServise = (TourService) servletContext.getAttribute(Const.ContextAttr.TOUR_SERVICE);
		orderService = (OrderService) servletContext.getAttribute(Const.ContextAttr.ORDER_SERVICE);
		discountService = (DiscountService) servletContext.getAttribute(Const.ContextAttr.DISCOUNT_SERVICE);
		LOG.debug("Tour order servlet initialization finished");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String tourId = request.getParameter(Const.RequestParam.TOUR_ID);
		if (StringUtils.isNumeric(tourId)) {
			Tour tour = tourServise.getTour(Long.parseLong(tourId));
			if (Objects.nonNull(tour)) {
				User user = (User) session.getAttribute(Const.SessionAttr.USER);
				double totalCost = calcTotalCost(discountService.findDiscount(),
						orderService.paidOrderNum(user.getId()), tour.getPrice());
				orderService.addOrder(fillOrder(user, totalCost, tourId));
				response.sendRedirect(Const.Url.Servlet.ORDERS);

				return;
			}
		}

		response.sendRedirect(Const.Url.Servlet.TOURS);
	}

	/**
	 * Creates order object with given parameters.
	 * 
	 * @param user user
	 * @param totalCost total cost
	 * @param tourId tour id
	 * @return order
	 */
	private Order fillOrder(User user, double totalCost, String tourId) {
		Order order = new Order();
		order.setTotalCost(totalCost);
		order.setTourId(Long.parseLong(tourId));
		order.setUserId(user.getId());
		return order;

	}

	/**
	 * Calculates tour total cost according to current discount, number of paid
	 * tours and current tour cost.
	 * 
	 * @param discount
	 *            current discount
	 * @param paidOrders
	 *            number of paid orders
	 * @param cost
	 *            cost per tour
	 * @return
	 */
	private double calcTotalCost(Discount discount, long paidOrders, double cost) {
		double totalCost = cost;
		double perTour = discount.getPercentPerTour();
		double max = discount.getMaxPercent();
		if (paidOrders != 0 && perTour != 0) {
			double percent = (paidOrders * perTour);
			percent = percent <= max ? percent : max;
			totalCost = cost * (1 - percent / 100);
		}
		return totalCost;
	}

}
