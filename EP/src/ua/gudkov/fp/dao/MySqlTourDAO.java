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
import ua.gudkov.fp.dao.api.TourDAO;
import ua.gudkov.fp.dao.exception.DAOException;
import ua.gudkov.fp.db.ConnectionHolder;
import ua.gudkov.fp.entity.FullTour;
import ua.gudkov.fp.entity.Tour;
import ua.gudkov.fp.entity.TourFilter;
import ua.gudkov.fp.entity.Type;
import ua.gudkov.fp.util.SqlBuilder;

/**
 * MySQL tour DAO implementation.
 * 
 * @author A.Gudkov
 *
 */
public class MySqlTourDAO implements TourDAO {
	private static final Logger LOG = Logger.getLogger(MySqlTourDAO.class);

	@Override
	public boolean insertTour(Tour tour) {
		boolean res = false;
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.ADD_TOUR, Statement.RETURN_GENERATED_KEYS);
			fillTourStatement(pstmt, tour);
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					tour.setId((rs.getLong(1)));
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
	public FullTour findFullTourById(long tourId) {
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FullTour tour = null;
		try {
			pstmt = connection.prepareStatement(SqlBuilder.selectQuery(tourId));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				tour = extractFullTour(rs);
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.FIND_FULL_TOUR, ex);
		} finally {
			close(pstmt);
			close(rs);
		}
		return tour;
	}

	@Override
	public Tour findTourById(long tourId) {
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Tour tour = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.FIND_TOUR_BY_ID);
			pstmt.setLong(1, tourId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				tour = extractTour(rs);
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.FIND_TOUR, ex);
		} finally {
			close(pstmt);
			close(rs);
		}
		return tour;
	}

	@Override
	public List<FullTour> findToursByFilter(TourFilter tourFilter) {
		List<FullTour> tours = new ArrayList<FullTour>();
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(SqlBuilder.selectQuery(tourFilter));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tours.add(extractFullTour(rs));
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.FIND_TOURS_BY_FILTER, ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return tours;
	}

	@Override
	public long findTourNumberByFilter(TourFilter tourFilter) {
		long tourNumber = 0;
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(SqlBuilder.countQuery(tourFilter));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				tourNumber = rs.getLong(1);
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.FIND_TOUR_NUMBER_BY_FILTER, ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return tourNumber;

	}

	@Override
	public boolean updateTourStatus(long tourId, int status) {
		boolean res = false;
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.UPDATE_TOUR_STATUS);
			pstmt.setInt(1, status);
			pstmt.setLong(2, tourId);
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

	@Override
	public boolean updateTour(Tour tour) {
		boolean res = false;
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.UPDATE_TOUR);
			fillTourStatement(pstmt, tour);
			pstmt.setLong(7, tour.getId());
			if (pstmt.executeUpdate() > 0) {
				res = true;
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.UPDATE_TOUR, ex);
		} finally {
			close(pstmt);
		}
		return res;
	}

	@Override
	public boolean deleteTourById(long tourId) {
		boolean res = false;
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.DELETE_TOUR);
			pstmt.setLong(1, tourId);
			if (pstmt.executeUpdate() > 0) {
				res = true;
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.DELETE_TOUR, ex);
		} finally {
			close(pstmt);
		}
		return res;
	}

	/**
	 * Puts tour entity into the prepared statement.
	 * 
	 * @param preparedStatement
	 *            PreparedStatement
	 * @param tour
	 *            tour
	 */
	private void fillTourStatement(PreparedStatement preparedStatement, Tour tour) throws SQLException {
		int index = 1;
		preparedStatement.setInt(index++, tour.getTypeId());
		preparedStatement.setDouble(index++, tour.getPrice());
		preparedStatement.setInt(index++, tour.getPersonCount());
		preparedStatement.setLong(index++, tour.getHotelId());
		preparedStatement.setDate(index++, tour.getDepartureDate());
		preparedStatement.setInt(index++, tour.getNights());

	}

	/**
	 * Extracts full tour from resultset.
	 * 
	 * @param resultSet
	 *            ResultSet
	 * @return full tour
	 */
	private FullTour extractFullTour(ResultSet resultSet) throws SQLException {
		int index = 1;
		FullTour tour = new FullTour();
		tour.setTourId(resultSet.getLong(index++));
		tour.setPrice(resultSet.getDouble(index++));
		tour.setPersonCount(resultSet.getInt(index++));
		tour.setHot(resultSet.getInt(index++));
		tour.setDepartureDate(resultSet.getDate(index++));
		tour.setNights(resultSet.getInt(index++));
		tour.setType(Type.values()[resultSet.getInt(index++)]);
		tour.setHotelId(resultSet.getLong(index++));
		tour.setHotelName(resultSet.getString(index++));
		tour.setHotelRating(resultSet.getInt(index++));
		tour.setCountry(resultSet.getString(index++));
		tour.setCity(resultSet.getString(index++));
		tour.setDescription(resultSet.getString(index++));
		return tour;
	}

	/**
	 * Extracts tour from resultset.
	 * 
	 * @param resultSet
	 *            ResultSet
	 * @return tour
	 */
	private Tour extractTour(ResultSet resultSet) throws SQLException {
		int index = 1;
		Tour tour = new Tour();
		tour.setId(resultSet.getLong(index++));
		tour.setTypeId(resultSet.getInt(index++));
		tour.setPrice(resultSet.getDouble(index++));
		tour.setPersonCount(resultSet.getInt(index++));
		tour.setHotelId(resultSet.getLong(index++));
		tour.setHot(resultSet.getInt(index++));
		tour.setDepartureDate(resultSet.getDate(index++));
		tour.setNights(resultSet.getInt(index++));
		return tour;
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
	public List<String> nightCountValues() {
		return findValuesByColumn(Const.Query.VAL_ALL_NIGHTS);
	}

	@Override
	public List<String> deptDateValues() {
		return findValuesByColumn(Const.Query.VAL_ALL_DATES);
	}

	@Override
	public List<String> tourTypeValues() {
		return findValuesByColumn(Const.Query.VAL_ALL_TYPES);
	}

	@Override
	public List<String> personCountValues() {
		return findValuesByColumn(Const.Query.VAL_ALL_PERSONS);
	}

}
