package ua.gudkov.fp.bean;

import java.io.Serializable;

import javax.servlet.http.Part;

/**
 * Tour edit form bean implementation.
 * 
 * @author A.Gudkov
 *
 */
public class TourEditFormBean implements Serializable {
	private static final long serialVersionUID = 3694893270269003035L;
	private String id;
	private String type;
	private String date;
	private String nightNum;
	private String cost;
	private String personNum;
	private String hotel;
	private Part part;

	public TourEditFormBean(String id, String type, String date, String nightNum, String cost, String personNum,
			String hotel, Part part) {
		super();
		this.id = id;
		this.type = type;
		this.date = date;
		this.nightNum = nightNum;
		this.cost = cost;
		this.personNum = personNum;
		this.hotel = hotel;
		this.part = part;
	}

	public TourEditFormBean() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNightNum() {
		return nightNum;
	}

	public void setNightNum(String nightNum) {
		this.nightNum = nightNum;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getPersonNum() {
		return personNum;
	}

	public void setPersonNum(String personNum) {
		this.personNum = personNum;
	}

	public String getHotel() {
		return hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	@Override
	public String toString() {
		return "TourEditFormBean [id=" + id + ", type=" + type + ", date=" + date + ", nightNum=" + nightNum + ", cost="
				+ cost + ", personNum=" + personNum + ", hotel=" + hotel + ", part=" + part + "]";
	}

}
