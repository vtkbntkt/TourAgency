package ua.gudkov.fp.db.api;


/**
 * Transaction interface.
 * 
 * @author A.Gudkov
 *
 * @param <T>
 *            returned object
 */
public interface Operation<T> {
	/**
	 * Execution method.
	 * 
	 * @return result of execution
	 */
	T execute();
}