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
import ua.gudkov.fp.dao.api.MessageDAO;
import ua.gudkov.fp.dao.exception.DAOException;
import ua.gudkov.fp.db.ConnectionHolder;
import ua.gudkov.fp.entity.Message;

/**
 * MySQL message DAO implementation.
 * 
 * @author A.Gudkov
 *
 */
public class MySqlMessageDAO implements MessageDAO {

	private static final Logger LOG = Logger.getLogger(MySqlMessageDAO.class);

	@Override
	public boolean insertMsg(Message msg) {
		boolean res = false;
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.ADD_MSG, Statement.RETURN_GENERATED_KEYS);
			fillMessageStatement(pstmt, msg);
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					msg.setIdMsg((rs.getLong(1)));
					res = true;
				}
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.INSERT_MSG, ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return res;
	}

	@Override
	public List<Message> findAll(long from, int range) {
		List<Message> messages = new ArrayList<Message>();
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.FIND_ALL_MSG);
			pstmt.setLong(1, from);
			pstmt.setInt(2, range);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				messages.add(extractMessage(rs));
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.FIND_ALL_MSG, ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return messages;
	}

	@Override
	public Message findByIdMessage(long idMessage) {
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Message msg = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.FIND_MSG_BY_ID);
			pstmt.setLong(1, idMessage);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				msg = extractMessage(rs);
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.FIND_MSG, ex);
		} finally {
			close(pstmt);
			close(rs);
		}
		return msg;
	}

	@Override
	public boolean delMessage(long idMessage) {
		boolean res = false;
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.DEL_MSG_BY_ID);
			pstmt.setLong(1, idMessage);
			if (pstmt.executeUpdate() > 0) {
				res = true;
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.DEL_MSG, ex);
		} finally {
			close(pstmt);
		}
		return res;
	}

	/**
	 * Puts message entity into the prepared statement.
	 * 
	 * @param preparedStatement
	 *            PreparedStatement
	 * @param msg
	 *            message
	 */
	private void fillMessageStatement(PreparedStatement preparedStatement, Message msg) throws SQLException {
		int index = 1;
		preparedStatement.setString(index++, msg.getName());
		preparedStatement.setString(index++, msg.getEmail());
		preparedStatement.setString(index++, msg.getMsg());
	}

	/**
	 * Extracts message from resultset.
	 * 
	 * @param resultSet
	 *            ResultSet
	 * @return message
	 */
	private Message extractMessage(ResultSet resultSet) throws SQLException {
		int index = 1;
		Message msg = new Message();
		msg.setIdMsg(resultSet.getLong(index++));
		msg.setCreationDate(resultSet.getTimestamp(index++));
		msg.setName(resultSet.getString(index++));
		msg.setEmail(resultSet.getString(index++));
		msg.setMsg(resultSet.getString(index++));
		return msg;
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
	public long countAllMessages() {
		long msgNum = 0;
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(Const.Query.COUNT_ALL_MESSAGES);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				msgNum = rs.getLong(1);
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Const.ErrMsg.COUNT_ALL_MESSAGES, ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return msgNum;
	}

}
