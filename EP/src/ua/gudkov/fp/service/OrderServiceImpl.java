package ua.gudkov.fp.service;

import java.util.List;

import org.apache.log4j.Logger;

import ua.gudkov.fp.dao.api.OrderDAO;
import ua.gudkov.fp.db.DBManager;
import ua.gudkov.fp.db.api.Operation;
import ua.gudkov.fp.entity.FullOrder;
import ua.gudkov.fp.entity.Order;
import ua.gudkov.fp.entity.OrderStatus;
import ua.gudkov.fp.entity.Role;
import ua.gudkov.fp.entity.SimpleFilter;
import ua.gudkov.fp.entity.User;
import ua.gudkov.fp.service.api.OrderService;

/**
 * Order service implementation.
 * 
 * @author A.Gudkov
 *
 */
public class OrderServiceImpl implements OrderService {

	private OrderDAO orderDAO;
	private DBManager dbManager;
	private static final Logger LOG = Logger.getLogger(OrderServiceImpl.class);

	public OrderServiceImpl(OrderDAO orderDAO, DBManager dbManager) {
		this.orderDAO = orderDAO;
		this.dbManager = dbManager;
		LOG.debug("Service created");
	}

	@Override
	public boolean addOrder(Order order) {
		return dbManager.execute(new Operation<Boolean>() {
			@Override
			public Boolean execute() {
				return orderDAO.insertOrder(order);
			}

		});
	}

	@Override
	public long paidOrderNum(long userId) {
		return dbManager.execute(new Operation<Long>() {
			@Override
			public Long execute() {
				return orderDAO.countByUserStatusId(userId, OrderStatus.PAID.ordinal());
			}

		});
	}

	@Override
	public List<FullOrder> getOrders(User user, SimpleFilter filter) {
		if (Role.getRole(user).equals(Role.CLIENT)) {
			return getOrdersByUserId(user.getId(), filter.getStartRow(), filter.getRowNum());
		}
		return getRegisteredOrders(filter.getStartRow(), filter.getRowNum());
	}

	@Override
	public List<FullOrder> getOrdersByUserId(long userId, long from, int range) {
		return dbManager.execute(new Operation<List<FullOrder>>() {
			@Override
			public List<FullOrder> execute() {
				return orderDAO.findByUserId(userId, from, range);
			}

		});

	}

	@Override
	public List<FullOrder> getRegisteredOrders(long from, int range) {
		return dbManager.execute(new Operation<List<FullOrder>>() {
			@Override
			public List<FullOrder> execute() {
				return orderDAO.findByStatusId(OrderStatus.REGISTERED.ordinal(), from, range);
			}

		});
	}

	@Override
	public long registeredOrderNum() {
		return dbManager.execute(new Operation<Long>() {
			@Override
			public Long execute() {
				return orderDAO.countByStatusId(OrderStatus.REGISTERED.ordinal());
			}

		});
	}

	@Override
	public long orderNumByUserId(long userId) {
		return dbManager.execute(new Operation<Long>() {
			@Override
			public Long execute() {
				return orderDAO.countByUserId(userId);
			}
		});
	}

	@Override
	public long orderNumber(User user) {
		if (Role.getRole(user).equals(Role.CLIENT)) {
			return orderNumByUserId(user.getId());
		}
		return registeredOrderNum();
	}

	@Override
	public Order getOrder(long orderId) {
		return dbManager.execute(new Operation<Order>() {
			@Override
			public Order execute() {
				return orderDAO.findById(orderId);
			}
		});
	}

	@Override
	public boolean changeStatus(long orderId, int statusId) {
		return dbManager.execute(new Operation<Boolean>() {
			@Override
			public Boolean execute() {
				return orderDAO.updateStatusById(orderId, statusId);
			}
		});
	}
}
