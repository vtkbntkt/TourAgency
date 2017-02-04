package ua.gudkov.fp.entity;

/**
 * User status entity.
 * 
 * @author A.Gudkov
 *
 */
public enum UserStatus {
	ACTIVE, BLOCKED;

	public static Role getRole(User user) {
		int roleId = user.getRoleId();
		return Role.values()[roleId];
	}

	public String getName() {
		return name().toLowerCase();
	}

}
