package ua.gudkov.fp.entity;

import java.io.Serializable;

/**
 * Currency entity.
 * 
 * @author A.Gudkov
 *
 */
public class Currency implements Serializable {
	private static final long serialVersionUID = -4261511316073154299L;
	private int id;
	private BankCode bc;
	private double rate;

	public Currency(int id, BankCode bc, double rate) {
		super();
		this.id = id;
		this.bc = bc;
		this.rate = rate;
	}

	public Currency() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BankCode getBc() {
		return bc;
	}

	public void setBc(BankCode bc) {
		this.bc = bc;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "Currency [id=" + id + ", bc=" + bc + ", rate=" + rate + "]";
	}

}
