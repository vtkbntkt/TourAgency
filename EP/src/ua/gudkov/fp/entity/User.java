package ua.gudkov.fp.entity;

import java.io.Serializable;

/**
 * User entity.
 * 
 * @author A.Gudkov
 * 
 */
public class User implements Serializable {
	private static final long serialVersionUID = -8051614758709785999L;
	private long id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private int roleId;
	private int userStatusId;

	public User() {
		super();
	}

	public User(long id, String email, String password, String firstName, String lastName, int roleId,
			int userStatusId) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roleId = roleId;
		this.userStatusId = userStatusId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getUserStatusId() {
		return userStatusId;
	}

	public void setUserStatusId(int userStatusId) {
		this.userStatusId = userStatusId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", roleId=" + roleId + ", userStatusId=" + userStatusId + "]";
	}

}
