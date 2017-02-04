package ua.gudkov.fp.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Message entity.
 * 
 * @author A.Gudkov
 *
 */
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	private long idMsg;
	private Timestamp creationDate;
	private String name;
	private String email;
	private String msg;

	public Message(long idMsg, Timestamp creationDate, String name, String email, String msg) {
		super();
		this.idMsg = idMsg;
		this.creationDate = creationDate;
		this.name = name;
		this.email = email;
		this.msg = msg;
	}

	public Message() {
		super();
	}

	public long getIdMsg() {
		return idMsg;
	}

	public void setIdMsg(long idMsg) {
		this.idMsg = idMsg;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Message [idMsg=" + idMsg + ", creationDate=" + creationDate + ", name=" + name + ", email=" + email
				+ ", msg=" + msg + "]";
	}

}
