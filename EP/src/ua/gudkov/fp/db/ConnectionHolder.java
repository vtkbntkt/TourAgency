package ua.gudkov.fp.db;

import java.sql.Connection;

/**
 * Thread local connection holder implementation.
 * 
 * @author A.Gudkov
 *
 */
public class ConnectionHolder {
	private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

	public static void setConnection(Connection connection) {
		threadLocal.set(connection);
	}

	public static Connection getConnection() {
		return threadLocal.get();
	}

	public static void removeConnection() {
		threadLocal.remove();
	}

}
