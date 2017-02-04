package ua.gudkov.fp.service.api;

import java.util.List;

import ua.gudkov.fp.entity.FullUser;
import ua.gudkov.fp.entity.SimpleFilter;
import ua.gudkov.fp.entity.User;

/**
 * User service interface.
 * 
 * @author A.Gudkov
 *
 */
public interface UserService {

	/**
	 * Logs user into account.
	 *
	 * @param email
	 *            the email
	 * @param password
	 *            the password
	 * @return recently logged user
	 */
	User login(String email, String password);

	/**
	 * Checks whether the user exists in the data base. If the user is new, puts
	 * in the data base user registration information.
	 * 
	 * @param user
	 *            user
	 * @return true if the user does not exists in the data base, false if is.
	 */
	boolean registerUser(User user);

	/**
	 * Returns list of users (excluding user with administrator role) according
	 * to given filter.
	 * 
	 * @param filter
	 *            filter
	 * @return full user list
	 */
	List<FullUser> getAllUsers(SimpleFilter filter);

	/**
	 * Returns number of users
	 * 
	 * @return number of users
	 */
	long userNumber();

	/**
	 * Updates user status (blocked/active);
	 * 
	 * @param userId
	 *            user id
	 * @return true if status is changed, false otherwise
	 */
	boolean changeStatus(long userId);

	/**
	 * Returns user found by its id
	 * 
	 * @param userId
	 *            user id
	 * @return user
	 */
	User getUser(Long userId);

}
