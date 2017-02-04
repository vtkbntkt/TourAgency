package ua.gudkov.fp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.dao.api.OrderDAO;
import ua.gudkov.fp.dao.exception.DAOException;
import ua.gudkov.fp.db.ConnectionHolder;
import ua.gudkov.fp.entity.FullOrder;
import ua.gudkov.fp.entity.Order;
import ua.gudkov.fp.entity.OrderStatus;

/**
 * MySQL order DAO implementation.
 * 
 * @author A.Gudkov
 *
 */
public class MySqlOrderDAO implements OrderDAO {
	private static final Logger LOG = Logger.getLogger(MySqlOrderDAO.class);

	@Override
	public boolean insertOrder(Order order) {
		boolean res = false;
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.ADD_ORDER, Statement.RETURN_GENERATED_KEYS);
			fillOrderStatement(pstmt, order);
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					order.setId((rs.getLong(1)));
					res = true;
				}
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.INSERT_TOUR, ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return res;
	}

	@Override
	public long countByUserStatusId(long userId, int statusId) {
		long orderNum = 0;
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.COUNT_ORDER_BY_USERID_STATUSID);
			pstmt.setLong(1, userId);
			pstmt.setInt(2, statusId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orderNum = rs.getLong(1);
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.COUNT_ORDER_BY_USERID_STATUSID, ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return orderNum;
	}

	/**
	 * Extracts full order from resultset.
	 * 
	 * @param resultSet
	 *            ResultSet
	 * @return full order
	 */
	private FullOrder extractFullOrder(ResultSet resultSet) throws SQLException {
		int index = 1;
		FullOrder order = new FullOrder();
		order.setId(resultSet.getLong(index++));
		order.setOrderDate(resultSet.getDate(index++));
		order.setTourId(resultSet.getLong(index++));
		order.setUserId(resultSet.getLong(index++));
		order.setTotalCost(resultSet.getDouble(index++));
		order.setOrderStatus(OrderStatus.values()[resultSet.getInt(index++)]);
		order.setFirstName(resultSet.getString(index++));
		order.setLastName(resultSet.getString(index++));
		return order;
	}

	/**
	 * Extracts order from resultset.
	 * 
	 * @param resultSet
	 *            ResultSet
	 * @return order
	 */
	private Order extractOrder(ResultSet resultSet) throws SQLException {
		int index = 1;
		Order order = new Order();
		order.setId(resultSet.getLong(index++));
		order.setOrderDate(resultSet.getDate(index++));
		order.setTourId(resultSet.getLong(index++));
		order.setUserId(resultSet.getLong(index++));
		order.setTotalCost(resultSet.getDouble(index++));
		order.setOrderStatus(OrderStatus.values()[resultSet.getInt(index++)]);
		return order;
	}

	/**
	 * Puts order entity into the prepared statement.
	 * 
	 * @param preparedStatement
	 *            PreparedStatement
	 * @param order
	 *            order
	 */
	private void fillOrderStatement(PreparedStatement preparedStatement, Order order) throws SQLException {
		int index = 1;
		preparedStatement.setLong(index++, order.getTourId());
		preparedStatement.setLong(index++, order.getUserId());
		preparedStatement.setDouble(index++, order.getTotalCost());

	}

	/**
	 * Close resultset.
	 * 
	 * @param rs
	 *            ResultSet
	 */
	private void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			LOG.error(Const.ErrMsg.CLOSE_RESULTSET, e);
		}

	}

	/**
	 * Close statement.
	 * 
	 * @param stmt
	 *            Statement
	 */
	private void close(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			LOG.error(Const.ErrMsg.CLOSE_STATEMENT, e);
		}
	}

	@Override
	public List<FullOrder> findByUserId(long id, long from, int range) {
		List<FullOrder> orders = new ArrayList<FullOrder>();
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.FIND_ORDERS_BY_USERID);
			pstmt.setLong(1, id);
			pstmt.setLong(2, from);
			pstmt.setInt(3, range);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				orders.add(extractFullOrder(rs));
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.FIND_ORDER_BY_USERID, ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return orders;
	}

	@Override
	public List<FullOrder> findByStatusId(int id, long from, int range) {
		List<FullOrder> orders = new ArrayList<FullOrder>();
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.FIND_ORDERS_BY_STATUSID);
			pstmt.setInt(1, id);
			pstmt.setLong(2, from);
			pstmt.setInt(3, range);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				orders.add(extractFullOrder(rs));
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.FIND_ORDER_BY_STATUSID, ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return orders;
	}

	@Override
	public long countByUserId(long userId) {
		long orderNum = 0;
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.COUNT_ORDER_BY_USERID);
			pstmt.setLong(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orderNum = rs.getLong(1);
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.COUNT_ORDER_BY_USERID, ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return orderNum;
	}

	@Override
	public long countByStatusId(int statusId) {
		long orderNum = 0;
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.COUNT_ORDER_BY_STATUSID);
			pstmt.setInt(1, statusId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orderNum = rs.getLong(1);
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.COUNT_ORDER_BY_STATUSID, ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return orderNum;
	}

	@Override
	public Order findById(long orderId) {
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Order order = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.FIND_ORDER_BY_ID);
			pstmt.setLong(1, orderId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				order = extractOrder(rs);
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.FIND_ORDER_BY_ID, ex);
		} finally {
			close(pstmt);
			close(rs);
		}
		return order;
	}

	@Override
	public boolean updateStatusById(long orderId, int orderStatus) {
		boolean res = false;
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.UPDATE_ORDER_STATUS);
			pstmt.setInt(1, orderStatus);
			pstmt.setLong(2, orderId);
			if (pstmt.executeUpdate() > 0) {
				res = true;
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.UPDATE_ORDER_STATUS, ex);
		} finally {
			close(pstmt);
		}
		return res;
	}

}
