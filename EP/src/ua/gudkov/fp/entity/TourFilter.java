package ua.gudkov.fp.entity;

import java.io.Serializable;

/**
 * Tour filter entity.
 * 
 * @author A.Gudkov
 *
 */
public class TourFilter implements Serializable {
	private static final long serialVersionUID = -6447869870340253592L;
	private String country;
	private int type;
	private int costFrom;
	private int costTo;
	private int personNum;
	private int rating;
	private int nightNum;
	private String deptDate;
	private String sortBy;
	private int itemsPerPage = 10;
	private int currentPage = 1;
	private long tourNum;

	public TourFilter(String country, int type, int costFrom, int costTo, int personNum, int rating, int nightNum,
			String deptDate, String sortBy, int itemsPerPage, int currentPage, long tourNum) {
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
		this.tourNum = tourNum;
	}

	public TourFilter() {
		super();
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCostFrom() {
		return costFrom;
	}

	public void setCostFrom(int costFrom) {
		this.costFrom = costFrom;
	}

	public int getCostTo() {
		return costTo;
	}

	public void setCostTo(int costTo) {
		this.costTo = costTo;
	}

	public int getPersonNum() {
		return personNum;
	}

	public void setPersonNum(int personNum) {
		this.personNum = personNum;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getNightNum() {
		return nightNum;
	}

	public void setNightNum(int nightNum) {
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

	public int getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public long getTourNum() {
		return tourNum;
	}

	public void setTourNum(long tourNum) {
		this.tourNum = tourNum;
	}

	@Override
	public String toString() {
		return "TourFilter [country=" + country + ", type=" + type + ", costFrom=" + costFrom + ", costTo=" + costTo
				+ ", personNum=" + personNum + ", rating=" + rating + ", nightNum=" + nightNum + ", deptDate="
				+ deptDate + ", sortBy=" + sortBy + ", itemsPerPage=" + itemsPerPage + ", currentPage=" + currentPage
				+ ", tourNum=" + tourNum + "]";
	}

}
