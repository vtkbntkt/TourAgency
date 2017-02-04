package ua.gudkov.fp.bean;

import java.io.Serializable;

import javax.servlet.http.Part;

/**
 * Contact form bean implementation.
 * 
 * @author A.Gudkov
 *
 */
public class ContactFormBean implements Serializable {

	private static final long serialVersionUID = -7950992600607271002L;
	private String name;
	private String email;
	private String message;
	private Part filePart;
	private String filePath;

	public ContactFormBean() {
		super();
	}

	public ContactFormBean(String name, String email, String message, Part filePart, String filePath) {
		super();
		this.name = name;
		this.email = email;
		this.message = message;
		this.filePart = filePart;
		this.filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Part getFilePart() {
		return filePart;
	}

	public void setFilePart(Part filePart) {
		this.filePart = filePart;
	}

	@Override
	public String toString() {
		return "ContactFormBean [name=" + name + ", email=" + email + ", message=" + message + ", filePart=" + filePart
				+ "]";
	}

}
