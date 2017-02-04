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
import ua.gudkov.fp.dao.api.CurrencyDAO;
import ua.gudkov.fp.dao.exception.DAOException;
import ua.gudkov.fp.db.ConnectionHolder;
import ua.gudkov.fp.entity.BankCode;
import ua.gudkov.fp.entity.Currency;

/**
 * MySQL currency DAO implementation.
 * 
 * @author A.Gudkov
 *
 */
public class MySqlCurrencyDAO implements CurrencyDAO {
	private static final Logger LOG = Logger.getLogger(MySqlCurrencyDAO.class);

	@Override
	public List<Currency> findAll() {
		List<Currency> currencies = new ArrayList<Currency>();
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.FIND_ALL_CURRENCIES);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				currencies.add(extractCurrency(rs));
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.FIND_ALL_HOTELS, ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return currencies;
	}

	@Override
	public Currency findByName(String name) {
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Currency currency = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.FIND_CURRENCY_BY_NAME);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				currency = extractCurrency(rs);
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.FIND_CURRENCY_BY_NAME, ex);
		} finally {
			close(pstmt);
			close(rs);
		}
		return currency;
	}

	@Override
	public boolean updateRate(int bc, double rate) {
		boolean res = false;
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.UPDATE_RATE);
			pstmt.setDouble(1, rate);
			pstmt.setInt(2, bc);
			if (pstmt.executeUpdate() > 0) {
				res = true;
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.UPDATE_TOUR_STATUS, ex);
		} finally {
			close(pstmt);
		}
		return res;
	}

	/**
	 * Extracts currency entity from resultset.
	 * 
	 * @param resultSet
	 * @return currency
	 */
	private Currency extractCurrency(ResultSet resultSet) throws SQLException {
		int index = 1;
		Currency currency = new Currency();
		currency.setId(resultSet.getInt(index++));
		currency.setBc(BankCode.valueOf(resultSet.getString(index++).toUpperCase()));
		currency.setRate(resultSet.getDouble(index++));
		return currency;
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
