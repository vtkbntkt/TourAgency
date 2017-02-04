package ua.gudkov.fp.bean;

import java.io.Serializable;

/**
 * Discount edit form bean implementation.
 * 
 * @author A.Gudkov
 *
 */
public class DiscountEditFormBean implements Serializable {
	private static final long serialVersionUID = -3879506887284842601L;
	private String percentPerTour;
	private String maxPercenr;

	public DiscountEditFormBean(String percentPerTour, String maxPercenr) {
		super();
		this.percentPerTour = percentPerTour;
		this.maxPercenr = maxPercenr;
	}

	public DiscountEditFormBean() {
		super();
	}

	public String getPercentPerTour() {
		return percentPerTour;
	}

	public void setPercentPerTour(String percentPerTour) {
		this.percentPerTour = percentPerTour;
	}

	public String getMaxPercenr() {
		return maxPercenr;
	}

	public void setMaxPercenr(String maxPercenr) {
		this.maxPercenr = maxPercenr;
	}

	@Override
	public String toString() {
		return "DiscountEditFormBean [percentPerTour=" + percentPerTour + ", maxPercenr=" + maxPercenr + "]";
	}

}
