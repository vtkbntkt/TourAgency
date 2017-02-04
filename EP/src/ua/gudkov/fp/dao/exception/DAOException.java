package ua.gudkov.fp.dao.exception;

/**
 * An exception that provides information on a database access error occurred in
 * DAO layer.
 * 
 * @author A.Gudkov
 *
 */
public class DAOException extends RuntimeException {

	private static final long serialVersionUID = -6649199493628510367L;

	public DAOException(String message, Exception exception) {
		super(message, exception);
	}

}
