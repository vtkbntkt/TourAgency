package ua.gudkov.fp.bean;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.Part;

/**
 * Hotel adding form bean implementation.
 * 
 * @author A.Gudkov
 *
 */
public class HotelFormBean implements Serializable {
	private static final long serialVersionUID = -5411019175665289996L;
	private String name;
	private String rating;
	private String country;
	private String city;
	private String description;
	private List<Part> parts;

	public HotelFormBean(String name, String rating, String country, String city, String description,
			List<Part> parts) {
		super();
		this.name = name;
		this.rating = rating;
		this.country = country;
		this.city = city;
		this.description = description;
		this.parts = parts;
	}

	public HotelFormBean() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
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

	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
	}

	@Override
	public String toString() {
		return "HotelFormBean [name=" + name + ", rating=" + rating + ", country=" + country + ", city=" + city
				+ ", description=" + description + ", parts=" + parts + "]";
	}

}
