package ua.gudkov.fp.entity;

/**
 * Role entity.
 * 
 * @author A.Gudkov
 * 
 */
public enum Role {

	ADMIN, CLIENT, MANAGER;

	public static Role getRole(User user) {
		int roleId = user.getRoleId();
		return Role.values()[roleId];
	}

	public String getName() {
		return name().toLowerCase();
	}
}
