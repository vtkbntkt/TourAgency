package ua.gudkov.fp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.dao.api.DiscountDAO;
import ua.gudkov.fp.dao.exception.DAOException;
import ua.gudkov.fp.db.ConnectionHolder;
import ua.gudkov.fp.entity.Discount;

/**
 * MySQL discount DAO implementation.
 * 
 * @author A.Gudkov
 *
 */
public class MySqlDiscountDAO implements DiscountDAO {
	private static final Logger LOG = Logger.getLogger(MySqlDiscountDAO.class);

	@Override
	public Discount findById(long id) {
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Discount discount = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.FIND_DISCOUNT_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				discount = extractDiscount(rs);
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.FIND_DISCOUNT_BY_ID, ex);
		} finally {
			close(pstmt);
			close(rs);
		}
		return discount;
	}

	@Override
	public boolean updateDiscount(long id, double perTourDiscount, double maxDiscount) {
		boolean res = false;
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.UPDATE_DISCOUNT);
			pstmt.setDouble(1, perTourDiscount);
			pstmt.setDouble(2, maxDiscount);
			pstmt.setLong(3, id);
			if (pstmt.executeUpdate() > 0) {
				res = true;
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.UPDATE_DISCOUNT, ex);
		} finally {
			close(pstmt);
		}
		return res;
	}

	/**
	 * Extracts discount entity from resultset.
	 * 
	 * @param resultSet
	 * @return discount
	 */
	private Discount extractDiscount(ResultSet resultSet) throws SQLException {
		int index = 1;
		Discount discount = new Discount();
		discount.setId(resultSet.getLong(index++));
		discount.setPercentPerTour(resultSet.getDouble(index++));
		discount.setMaxPercent(resultSet.getDouble(index++));
		return discount;
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

}
