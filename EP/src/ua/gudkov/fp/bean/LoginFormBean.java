package ua.gudkov.fp.bean;

import java.io.Serializable;

/**
 * Log in form bean implementation.
 * 
 * @author A.Gudkov
 *
 */
public class LoginFormBean implements Serializable {
	private static final long serialVersionUID = -8966111817006582821L;
	String username;
	String password;
	String captchaValue;

	public LoginFormBean() {
		super();
	}

	public LoginFormBean(String username, String password, String captchaValue) {
		super();
		this.username = username;
		this.password = password;
		this.captchaValue = captchaValue;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCaptchaValue() {
		return captchaValue;
	}

	public void setCaptchaValue(String captchaValue) {
		this.captchaValue = captchaValue;
	}

	@Override
	public String toString() {
		return "LoginFormBean [username=" + username + ", password=" + password + ", captchaValue=" + captchaValue
				+ "]";
	}

}
