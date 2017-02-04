package ua.gudkov.fp.bean;

import java.io.Serializable;

/**
 * Tour filter form bean implementation.
 * 
 * @author A.Gudkov
 *
 */
public class TourFilterBean implements Serializable {
	private static final long serialVersionUID = 3490947785986235902L;
	private String country;
	private String type;
	private String costFrom;
	private String costTo;
	private String personNum;
	private String rating;
	private String nightNum;
	private String deptDate;
	private String sortBy;
	private String itemsPerPage;
	private String currentPage;
	private String currency;
	private String rate;

	public TourFilterBean(String country, String type, String costFrom, String costTo, String personNum, String rating,
			String nightNum, String deptDate, String sortBy, String itemsPerPage, String currentPage, String currency,
			String rate) {
		super();
		this.country = country;
		this.type = type;
		this.costFrom = costFrom;
		this.costTo = costTo;
		this.personNum = personNum;
		this.rating = rating;
		this.nightNum = nightNum;
		this.deptDate = deptDate;
		this.sortBy = sortBy;
		this.itemsPerPage = itemsPerPage;
		this.currentPage = currentPage;
		this.currency = currency;
		this.rate = rate;
	}

	public TourFilterBean() {
		super();
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCostFrom() {
		return costFrom;
	}

	public void setCostFrom(String costFrom) {
		this.costFrom = costFrom;
	}

	public String getCostTo() {
		return costTo;
	}

	public void setCostTo(String costTo) {
		this.costTo = costTo;
	}

	public String getPersonNum() {
		return personNum;
	}

	public void setPersonNum(String personNum) {
		this.personNum = personNum;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getNightNum() {
		return nightNum;
	}

	public void setNightNum(String nightNum) {
		this.nightNum = nightNum;
	}

	public String getDeptDate() {
		return deptDate;
	}

	public void setDeptDate(String deptDate) {
		this.deptDate = deptDate;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(String itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "TourFilterBean [country=" + country + ", type=" + type + ", costFrom=" + costFrom + ", costTo=" + costTo
				+ ", personNum=" + personNum + ", rating=" + rating + ", nightNum=" + nightNum + ", deptDate="
				+ deptDate + ", sortBy=" + sortBy + ", itemsPerPage=" + itemsPerPage + ", currentPage=" + currentPage
				+ ", currency=" + currency + ", rate=" + rate + "]";
	}

}
