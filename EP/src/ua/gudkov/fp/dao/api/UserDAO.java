package ua.gudkov.fp.dao.api;

import java.util.List;

import ua.gudkov.fp.entity.FullUser;
import ua.gudkov.fp.entity.User;

/**
 * User DAO interface.
 * 
 * @author A.Gudkov
 *
 */
public interface UserDAO {

	/**
	 * Find user by its email.
	 * 
	 * @param email
	 *            the email
	 * @return user entity
	 */
	User findByEmail(String email);

	/**
	 * Inserts user into the data base
	 * 
	 * @param user
	 *            the user
	 * @return true if user is added into the data base, false otherwise
	 */
	boolean insertUser(User user);

	/**
	 * Returns list of detailed users (FullUser) found with given limit
	 * 
	 * @param from
	 *            start row number in a table
	 * @param range
	 *            list size
	 * @return
	 */
	List<FullUser> findAllUsers(long from, int range);

	/**
	 * Returns number of all users without user with administrator role
	 * 
	 * @return number of users
	 */
	long countAllUsers();

	/**
	 * Updates user status found by user id
	 * 
	 * @param userId
	 *            user id
	 * @param statusId
	 *            user status
	 * @return true if the user status is updated, false otherwise
	 */
	boolean updateStatusById(long userId, int statusId);

	/**
	 * User found by its id
	 * 
	 * @param userId
	 *            user id
	 * @return the user
	 */
	User findById(long userId);

}
