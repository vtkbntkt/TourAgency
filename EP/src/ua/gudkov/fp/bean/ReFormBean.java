package ua.gudkov.fp.bean;

import java.io.Serializable;

/**
 * Message reply form bean implementation.
 * 
 * @author A.Gudkov
 *
 */
public class ReFormBean implements Serializable {
	private static final long serialVersionUID = 2871666696400957536L;
	private String message;
	private String email;
	private String subject;

	public ReFormBean(String message, String email, String subject) {
		super();
		this.message = message;
		this.email = email;
		this.subject = subject;
	}

	public ReFormBean() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "ReFormBean [message=" + message + ", email=" + email + ", subject=" + subject + "]";
	}

}
