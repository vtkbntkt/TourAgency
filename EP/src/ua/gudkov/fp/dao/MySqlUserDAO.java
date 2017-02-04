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
import ua.gudkov.fp.dao.api.UserDAO;
import ua.gudkov.fp.dao.exception.DAOException;
import ua.gudkov.fp.db.ConnectionHolder;
import ua.gudkov.fp.entity.FullUser;
import ua.gudkov.fp.entity.User;

/**
 * MySQL user DAO implementation.
 * 
 * @author A.Gudkov
 *
 */
public class MySqlUserDAO implements UserDAO {
	private static final Logger LOG = Logger.getLogger(MySqlUserDAO.class);

	@Override
	public User findByEmail(String email) {
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.FIND_USER_BY_EMAIL);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.FIND_USER_BY_EMAIL, ex);
		} finally {
			close(pstmt);
			close(rs);
		}
		return user;
	}

	@Override
	public boolean insertUser(User user) {
		boolean res = false;
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.ADD_USER, Statement.RETURN_GENERATED_KEYS);
			fillUserStatement(pstmt, user);
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					user.setId(rs.getLong(1));
					res = true;
				}
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.INSERT_USER, ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return res;
	}

	/**
	 * Puts user entity into the prepared statement.
	 * 
	 * @param preparedStatement
	 *            PreparedStatement
	 * @param user
	 *            user entity
	 */
	private void fillUserStatement(PreparedStatement preparedStatement, User user) throws SQLException {
		int index = 1;
		preparedStatement.setString(index++, user.getEmail());
		preparedStatement.setString(index++, user.getPassword());
		preparedStatement.setString(index++, user.getFirstName());
		preparedStatement.setString(index++, user.getLastName());
		preparedStatement.setInt(index++, user.getUserStatusId());
		preparedStatement.setInt(index++, user.getRoleId());
	}

	/**
	 * Extracts user entity from resultset.
	 * 
	 * @param resultSet
	 * @return user
	 */
	private User extractUser(ResultSet resultSet) throws SQLException {
		int index = 1;
		User user = new User();
		user.setId(resultSet.getLong(index++));
		user.setEmail(resultSet.getString(index++));
		user.setPassword(resultSet.getString(index++));
		user.setFirstName(resultSet.getString(index++));
		user.setLastName(resultSet.getString(index++));
		user.setUserStatusId(resultSet.getInt(index++));
		user.setRoleId(resultSet.getInt(index++));
		return user;
	}

	/**
	 * Extracts full user entity from resultset.
	 * 
	 * @param resultSet
	 * @return full user
	 */
	private FullUser extractFullUser(ResultSet resultSet) throws SQLException {
		int index = 1;
		FullUser user = new FullUser();
		user.setId(resultSet.getLong(index++));
		user.setEmail(resultSet.getString(index++));
		user.setFirstName(resultSet.getString(index++));
		user.setLastName(resultSet.getString(index++));
		user.setStatus(resultSet.getString(index++));
		user.setRole(resultSet.getString(index++));
		return user;
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
	public List<FullUser> findAllUsers(long from, int range) {
		List<FullUser> users = new ArrayList<FullUser>();
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.FIND_ALL_USERS);
			pstmt.setLong(1, from);
			pstmt.setInt(2, range);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				users.add(extractFullUser(rs));
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.FIND_ALL_USERS, ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return users;
	}

	@Override
	public long countAllUsers() {
		long userNum = 0;
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.COUNT_ALL_USERS);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				userNum = rs.getLong(1);
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.COUNT_ALL_USERS, ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return userNum;
	}

	@Override
	public boolean updateStatusById(long userId, int statusId) {
		boolean res = false;
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.UPDATE_USER_STATUS);
			pstmt.setInt(1, statusId);
			pstmt.setLong(2, userId);
			if (pstmt.executeUpdate() > 0) {
				res = true;
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.UPDATE_USER_STATUS, ex);
		} finally {
			close(pstmt);
		}
		return res;
	}

	@Override
	public User findById(long userId) {
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.FIND_USER_BY_ID);
			pstmt.setLong(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.FIND_USER_BY_ID, ex);
		} finally {
			close(pstmt);
			close(rs);
		}
		return user;
	}

}
