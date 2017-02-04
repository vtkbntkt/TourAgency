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
import ua.gudkov.fp.dao.api.HotelDAO;
import ua.gudkov.fp.dao.exception.DAOException;
import ua.gudkov.fp.db.ConnectionHolder;
import ua.gudkov.fp.entity.Hotel;

/**
 * MySQL hotel DAO implementation.
 * 
 * @author A.Gudkov
 *
 */
public class MySqlHotelDAO implements HotelDAO {
	private static final Logger LOG = Logger.getLogger(MySqlHotelDAO.class);

	@Override
	public List<Hotel> findAll() {
		List<Hotel> hotels = new ArrayList<Hotel>();
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.FIND_ALL_HOTELS);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				hotels.add(extractHotel(rs));
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.FIND_ALL_HOTELS, ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return hotels;
	}

	@Override
	public boolean insertHotel(Hotel hotel) {
		boolean res = false;
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.ADD_HOTEL, Statement.RETURN_GENERATED_KEYS);
			fillHotelStatement(pstmt, hotel);
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					hotel.setId((rs.getLong(1)));
					res = true;
				}
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.INSERT_HOTEL, ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return res;
	}

	/**
	 * Puts hotel entity into the prepared statement.
	 * 
	 * @param preparedStatement
	 *            PreparedStatement
	 * @param hotel
	 *            hotel
	 */
	private void fillHotelStatement(PreparedStatement preparedStatement, Hotel hotel) throws SQLException {
		int index = 1;
		preparedStatement.setString(index++, hotel.getName());
		preparedStatement.setInt(index++, hotel.getRaiting());
		preparedStatement.setString(index++, hotel.getCountry());
		preparedStatement.setString(index++, hotel.getCity());
		preparedStatement.setString(index++, hotel.getDescription());
	}

	/**
	 * Extracts hotel from resultset.
	 * 
	 * @param resultSet
	 *            ResultSet
	 * @return hotel
	 */
	private Hotel extractHotel(ResultSet resultSet) throws SQLException {
		int index = 1;
		Hotel hotel = new Hotel();
		hotel.setId(resultSet.getLong(index++));
		hotel.setName(resultSet.getString(index++));
		hotel.setRaiting(resultSet.getInt(index++));
		hotel.setCountry(resultSet.getString(index++));
		hotel.setCity(resultSet.getString(index++));
		hotel.setDescription(resultSet.getString(index++));
		return hotel;
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

	/**
	 * Returns list of unique values found by column, defined in the query
	 * 
	 * @param query
	 *            query
	 * @return list of unique value
	 */
	private List<String> findValuesByColumn(String query) {
		List<String> values = new ArrayList<String>();
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				values.add(rs.getString(1));
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.FIND_VALUES_BY_COLUMN, ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return values;
	}

	@Override
	public List<String> ratingValues() {
		return findValuesByColumn(Const.Query.VAL_ALL_STARS);
	}

	@Override
	public List<String> countryValues() {
		return findValuesByColumn(Const.Query.VAL_ALL_COUNTRIES);

	}

}
