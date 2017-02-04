package ua.gudkov.fp.service;

import java.util.List;

import org.apache.log4j.Logger;

import ua.gudkov.fp.dao.api.UserDAO;
import ua.gudkov.fp.db.DBManager;
import ua.gudkov.fp.db.api.Operation;
import ua.gudkov.fp.entity.FullUser;
import ua.gudkov.fp.entity.SimpleFilter;
import ua.gudkov.fp.entity.User;
import ua.gudkov.fp.entity.UserStatus;
import ua.gudkov.fp.service.api.UserService;

/**
 * User service implementation.
 * 
 * @author A.Gudkov
 *
 */
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;
	private DBManager dbManager;
	private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

	public UserServiceImpl(UserDAO userDAO, DBManager dbManager) {
		this.userDAO = userDAO;
		this.dbManager = dbManager;
		LOG.debug("Service created");
	}

	@Override
	public User login(String email, String password) {
		return dbManager.execute(new Operation<User>() {
			@Override
			public User execute() {
				User user = userDAO.findByEmail(email);
				if (user != null && user.getPassword().equals(password)
						&& UserStatus.BLOCKED.ordinal() != user.getUserStatusId()) {
					return user;
				}
				return null;
			}

		});
	}

	@Override
	public boolean registerUser(User user) {
		return dbManager.execute(new Operation<Boolean>() {
			@Override
			public Boolean execute() {
				if (userDAO.findByEmail(user.getEmail()) != null) {
					return false;
				}
				return userDAO.insertUser(user);
			}
		});
	}

	@Override
	public long userNumber() {
		return dbManager.execute(new Operation<Long>() {
			@Override
			public Long execute() {
				return userDAO.countAllUsers();
			}
		});
	}

	@Override
	public List<FullUser> getAllUsers(SimpleFilter filter) {
		return dbManager.execute(new Operation<List<FullUser>>() {
			@Override
			public List<FullUser> execute() {
				return userDAO.findAllUsers(filter.getStartRow(), filter.getRowNum());
			}
		});
	}

	@Override
	public boolean changeStatus(long userId) {
		User user = getUser(userId);
		if (user != null) {
			int statusId = (user.getUserStatusId() == 0 ? 1 : 0);
			return dbManager.execute(new Operation<Boolean>() {
				@Override
				public Boolean execute() {
					return userDAO.updateStatusById(userId, statusId);
				}

			});
		}
		return false;
	}

	@Override
	public User getUser(Long userId) {
		return dbManager.execute(new Operation<User>() {
			@Override
			public User execute() {
				return userDAO.findById(userId);
			}
		});
	}

}
