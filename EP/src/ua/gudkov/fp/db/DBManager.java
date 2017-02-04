package ua.gudkov.fp.db;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.db.api.Operation;
import ua.gudkov.fp.db.exception.DBException;

/**
 * Data base manager implementation.
 * 
 * @author A.Gudkov
 *
 */
public class DBManager {
	private static final Logger LOG = Logger.getLogger(DBManager.class);
	private DataSource dataSource;

	public DBManager(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public <T> T execute(Operation<T> operation) {
		Connection connection = null;
		T value = null;
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			ConnectionHolder.setConnection(connection);
			value = operation.execute();
			connection.commit();
		} catch (SQLException ex) {
			LOG.error(ex);
			rollback(connection);
			throw new DBException(Const.ErrMsg.CONNECTION, ex);
		} finally {
			close(connection);
			ConnectionHolder.removeConnection();
		}
		return value;
	}

	private void rollback(Connection connection) {
		if (connection != null) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				LOG.error(ex);
			}
		}
	}

	private void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException ex) {
				LOG.error(ex);
			}
		}
	}

}
