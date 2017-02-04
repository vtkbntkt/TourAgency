package ua.gudkov.fp.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * Tour entity.
 * 
 * @author A.Gudkov
 *
 */
public class Tour implements Serializable {
	private static final long serialVersionUID = -6257424057069125060L;
	private long id;
	private int typeId;
	private double price;
	private int personCount;
	private long hotelId;
	private boolean isHot;
	private Date departureDate;
	private int nights;

	public Tour(long id, int typeId, double price, int personCount, long hotelId, boolean isHot, Date departureDate,
			int nights) {
		super();
		this.id = id;
		this.typeId = typeId;
		this.price = price;
		this.personCount = personCount;
		this.hotelId = hotelId;
		this.isHot = isHot;
		this.departureDate = departureDate;
		this.nights = nights;
	}

	@Override
	public String toString() {
		return "Tour [id=" + id + ", typeId=" + typeId + ", price=" + price + ", personCount=" + personCount
				+ ", hotelId=" + hotelId + ", isHot=" + isHot + ", departureDate=" + departureDate + ", nights="
				+ nights + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getPersonCount() {
		return personCount;
	}

	public void setPersonCount(int personCount) {
		this.personCount = personCount;
	}

	public long getHotelId() {
		return hotelId;
	}

	public void setHotelId(long hotelId) {
		this.hotelId = hotelId;
	}

	public boolean isHot() {
		return isHot;
	}

	public void setHot(int isHot) {
		if (isHot == 1) {
			this.isHot = true;
		} else {
			this.isHot = false;
		}
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public int getNights() {
		return nights;
	}

	public void setNights(int nights) {
		this.nights = nights;
	}

	public Tour() {
		super();
	}

}
