package ua.gudkov.fp.bean;

import java.io.Serializable;

/**
 * Captcha bean implementation.
 * 
 * @author A.Gudkov
 *
 */
public class CaptchaBean implements Serializable {
	private static final long serialVersionUID = 165755482596750030L;
	private String value;
	private long startTime;

	public CaptchaBean(String value, long startTime) {
		super();
		this.value = value;
		this.startTime = startTime;
	}

	public CaptchaBean() {
		super();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	@Override
	public String toString() {
		return "CaptchaBean [value=" + value + ", startTime=" + startTime + "]";
	}

}
