package ua.gudkov.fp.entity;

import java.io.Serializable;

/**
 * Represents user entity combined with user role entity, and user status
 * entity.
 * 
 * @author A.Gudkov
 *
 */
public class FullUser implements Serializable {
	private static final long serialVersionUID = -7223480169150026460L;
	private long id;
	private String email;
	private String firstName;
	private String lastName;
	private String role;
	private String status;

	public FullUser(long id, String email, String firstName, String lastName, String role, String status) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.status = status;
	}

	public FullUser() {
		super();
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "FullUser [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", role=" + role + ", status=" + status + "]";
	}

}
