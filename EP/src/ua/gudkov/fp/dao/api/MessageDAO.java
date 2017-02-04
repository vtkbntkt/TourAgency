package ua.gudkov.fp.dao.api;

import java.util.List;

import ua.gudkov.fp.entity.Message;

/**
 * Message DAO interface.
 * 
 * @author A.Gudkov
 *
 */
public interface MessageDAO {

	/**
	 * Adds message into data base.
	 * 
	 * @param msg
	 *            the message
	 * @return true if the message is added into database, false otherwise.
	 */
	boolean insertMsg(Message msg);

	/**
	 * Returns list of all messages contained on the base with given limit.
	 * 
	 * @param from
	 *            start row in table
	 * @param range
	 *            size of list
	 * @return message list
	 */
	List<Message> findAll(long from, int range);

	/**
	 * Return message found by its id.
	 * 
	 * @param idMessage
	 *            the message id
	 * @return message
	 */
	Message findByIdMessage(long idMessage);

	/**
	 * Deletes message found by its id.
	 * 
	 * @param idMessage
	 *            the message id
	 * @return true if the message is deleted, false otherwise.
	 */
	boolean delMessage(long idMessage);

	/**
	 * Returns number of all messages in the base
	 * 
	 * @return number of messages
	 */
	long countAllMessages();

}
