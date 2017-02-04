package ua.gudkov.fp.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * Represents tour entity combined with tour type entity, and hotel entity.
 * 
 * @author A.Gudkov
 *
 */
public class FullTour implements Serializable {
	private static final long serialVersionUID = 3281740474974397501L;
	private long tourId;
	private Type type;
	private double price;
	private int personCount;
	private boolean isHot;
	private Date departureDate;
	private int nights;
	private long hotelId;
	private String hotelName;
	private int hotelRating;
	private String country;
	private String city;
	private String description;

	public FullTour(long tourId, Type type, double price, int personCount, boolean isHot, Date departureDate,
			int nights, long hotelId, String hotelName, int hotelRating, String country, String city,
			String description) {
		super();
		this.tourId = tourId;
		this.type = type;
		this.price = price;
		this.personCount = personCount;
		this.isHot = isHot;
		this.departureDate = departureDate;
		this.nights = nights;
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.hotelRating = hotelRating;
		this.country = country;
		this.city = city;
		this.description = description;
	}

	public FullTour() {
		super();
	}

	public void setHot(boolean isHot) {
		this.isHot = isHot;
	}

	public long getHotelId() {
		return hotelId;
	}

	public void setHotelId(long hotelId) {
		this.hotelId = hotelId;
	}

	public long getTourId() {
		return tourId;
	}

	public void setTourId(long tourId) {
		this.tourId = tourId;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
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

	public boolean isHot() {
		return isHot;
	}

	public boolean getIsHot() {
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

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public int getHotelRating() {
		return hotelRating;
	}

	public void setHotelRating(int hotelRating) {
		this.hotelRating = hotelRating;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "FullTour [tourId=" + tourId + ", type=" + type + ", price=" + price + ", personCount=" + personCount
				+ ", isHot=" + isHot + ", departureDate=" + departureDate + ", nights=" + nights + ", hotelId="
				+ hotelId + ", hotelName=" + hotelName + ", hotelRating=" + hotelRating + ", country=" + country
				+ ", city=" + city + ", description=" + description + "]";
	}

}
