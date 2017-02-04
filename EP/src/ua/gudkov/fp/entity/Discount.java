package ua.gudkov.fp.entity;

import java.io.Serializable;

/**
 * Discount entity.
 * 
 * @author A.Gudkov
 *
 */
public class Discount implements Serializable {
	private static final long serialVersionUID = -4542976954222897060L;
	private long id;
	private double percentPerTour;
	private double maxPercent;

	public Discount(long id, double percentPerTour, double maxPercent) {
		super();
		this.id = id;
		this.percentPerTour = percentPerTour;
		this.maxPercent = maxPercent;
	}

	public Discount() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getPercentPerTour() {
		return percentPerTour;
	}

	public void setPercentPerTour(double percentPerTour) {
		this.percentPerTour = percentPerTour;
	}

	public double getMaxPercent() {
		return maxPercent;
	}

	public void setMaxPercent(double maxPercent) {
		this.maxPercent = maxPercent;
	}

	@Override
	public String toString() {
		return "Discount [id=" + id + ", percentPerTour=" + percentPerTour + ", maxPercent=" + maxPercent + "]";
	}

}
