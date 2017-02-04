package ua.gudkov.fp.dao.api;

import java.util.List;

import ua.gudkov.fp.entity.FullOrder;
import ua.gudkov.fp.entity.Order;

/**
 * Order DAO interface.
 * 
 * @author A.Gudkov
 *
 */
public interface OrderDAO {

	/**
	 * Adds order in the base
	 * 
	 * @param order
	 *            the order
	 * @return true if the order is added, false otherwise
	 */
	boolean insertOrder(Order order);

	/**
	 * Returns number of orders found by user id and order status
	 * 
	 * @param userId
	 *            user id
	 * @param statusId
	 *            order status
	 * @return number of orders
	 */
	long countByUserStatusId(long userId, int statusId);

	/**
	 * Return list of detailed orders (FullOrder) found by user id with given
	 * limit.
	 * 
	 * @param id
	 *            user id
	 * @param from
	 *            start column in a table
	 * @param range
	 *            size list
	 * @return list of fullorders
	 */
	List<FullOrder> findByUserId(long id, long from, int range);

	/**
	 * Return list of detailed orders (FullOrder) found by order status with
	 * given limit.
	 * 
	 * @param id
	 *            order status
	 * @param from
	 *            start column in a table
	 * @param range
	 *            size list
	 * @return list of fullorders
	 */
	List<FullOrder> findByStatusId(int id, long from, int range);

	/**
	 * Returns order found by its id
	 * 
	 * @param orderId
	 *            order id
	 * @return order
	 */
	Order findById(long orderId);

	/**
	 * Returns number of orders found by user id
	 * 
	 * @param userId
	 *            user id
	 * @return number of orders
	 */
	long countByUserId(long userId);

	/**
	 * Returns number of orders found by order status
	 * 
	 * @param statusId
	 *            order status
	 * @return number of orders
	 */
	long countByStatusId(int statusId);

	/**
	 * Updates order status found by order id
	 * 
	 * @param orderId
	 *            order id
	 * @param orderStatus
	 *            order status
	 * @return true if the order is updated, false otherwise
	 */
	boolean updateStatusById(long orderId, int orderStatus);

}
