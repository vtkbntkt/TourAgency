package ua.gudkov.fp.service.api;

import java.util.List;


import ua.gudkov.fp.entity.Message;
import ua.gudkov.fp.entity.SimpleFilter;

/**
 * Message service interface.
 * 
 * @author A.Gudkov
 *
 */
public interface MsgService {

	/**
	 * Adds an message into the data base.
	 * 
	 * @param msg
	 *            the message
	 * @return true if message is added into the data base, false otherwise
	 */
	boolean addMsg(Message msg);

	/**
	 * Deletes message found by its id.
	 * 
	 * @param idMessage
	 *            the message id
	 * @return true if message is deleted, false otherwise.
	 */
	boolean delMsg(long idMessage);

	/**
	 * Returns list of all messages contained on the base according to give
	 * filter.
	 * 
	 * @param filter
	 *            SimpleFilter
	 * @return list of messages
	 */
	List<Message> findAll(SimpleFilter filter);

	/**
	 * Return message found by its id.
	 * 
	 * @param idMessage
	 *            the message id
	 * @return message found by its id or null if a message with given id does
	 *         not exist
	 */
	Message findByIdMessage(long idMessage);

	/**
	 * Returns number of messages in the base
	 * 
	 * @return number of messages
	 */
	long msgNumber();

}
