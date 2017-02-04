package ua.gudkov.fp.bean;

import java.io.Serializable;

/**
 * Currency edit form bean implementation.
 * 
 * @author A.Gudkov
 *
 */
public class CurrencyEditFormBean implements Serializable {
	private static final long serialVersionUID = 2757842445654699827L;
	private String bc;
	private String rate;

	public CurrencyEditFormBean(String bc, String rate) {
		super();
		this.bc = bc;
		this.rate = rate;
	}

	public CurrencyEditFormBean() {
		super();
	}

	public String getBc() {
		return bc;
	}

	public void setBc(String bc) {
		this.bc = bc;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "CurrencyBean [bc=" + bc + ", rate=" + rate + "]";
	}

}
