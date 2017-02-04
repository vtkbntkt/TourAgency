package ua.gudkov.fp.service.api;

import java.util.List;

import ua.gudkov.fp.entity.FullOrder;
import ua.gudkov.fp.entity.Order;
import ua.gudkov.fp.entity.SimpleFilter;
import ua.gudkov.fp.entity.User;

/**
 * Order service interface.
 * 
 * @author A.Gudkov
 *
 */
public interface OrderService {

	/**
	 * Adds order into the base
	 * 
	 * @param order
	 *            Order
	 * @return true if an order is added, false otherwise
	 */
	boolean addOrder(Order order);

	/**
	 * Returns number of paid orders found by its user id
	 * 
	 * @param userId
	 * @return number of paid orders
	 */
	long paidOrderNum(long userId);

	/**
	 * Returns number of all registered orders
	 * 
	 * @return number of orders
	 */
	long registeredOrderNum();

	/**
	 * Returns order number found by user id.
	 * 
	 * @param userId
	 * @return number of orders
	 */
	long orderNumByUserId(long userId);

	/**
	 * Returns number of orders, found according to user role
	 * 
	 * @param user
	 *            the user
	 * @return number of orders
	 */
	long orderNumber(User user);

	/**
	 * Returns list of full orders found by user role and given filter
	 * 
	 * @param user
	 *            the user
	 * @param filter
	 *            the filter
	 * @return list of full orders
	 */
	List<FullOrder> getOrders(User user, SimpleFilter filter);

	/**
	 * Returns order list found by user id according to given list limit
	 * 
	 * @param userId
	 *            user id
	 * @param from
	 *            start position in base table
	 * @param range
	 *            size of list
	 * @return list of full orders
	 */
	List<FullOrder> getOrdersByUserId(long userId, long from, int range);

	/**
	 * Returns all orders with status registered with given list limit
	 * 
	 * @param from
	 *            start position in base table
	 * @param range
	 *            size of list
	 * @return list of full orders
	 */
	List<FullOrder> getRegisteredOrders(long from, int range);

	/**
	 * Return order found by its id
	 * 
	 * @param orderId
	 *            order id
	 * @return order
	 */
	Order getOrder(long orderId);

	/**
	 * Updates order status found by order id. The status is changed according
	 * to current status.
	 * 
	 * @param orderId
	 *            order id
	 * @param statusId
	 *            status id
	 * @return true if order status is changed, false otherwise
	 */
	boolean changeStatus(long orderId, int statusId);

}
