package ua.gudkov.fp.bean;

import java.io.Serializable;

/**
 * Registration form bean implementation.
 * 
 * @author A.Gudkov
 *
 */
public class RegisterFormBean implements Serializable {
	private static final long serialVersionUID = 7961199886581879889L;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String repeatPassword;

	public RegisterFormBean() {
		super();
	}

	public RegisterFormBean(String firstName, String lastName, String email, String password, String repeatPassword) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.repeatPassword = repeatPassword;
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

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	@Override
	public String toString() {
		return "RegisterFormBean [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", repeatPassword=" + repeatPassword + "]";
	}

}
