package ua.gudkov.fp.db.exception;

/**
 * An exception that provides information on a database access error occurred in
 * the DBManager.
 * 
 * @author A.Gudkov
 *
 */
public class DBException extends RuntimeException {

	private static final long serialVersionUID = 209873525791393878L;

	public DBException(String message, Exception exception) {
		super(message, exception);
	}

}
