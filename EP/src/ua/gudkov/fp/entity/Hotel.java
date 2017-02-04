package ua.gudkov.fp.entity;

import java.io.Serializable;

/**
 * Hotel entity.
 * 
 * @author A.Gudkov
 *
 */
public class Hotel implements Serializable {
	private static final long serialVersionUID = 6431404541554523821L;
	private long id;
	private String name;
	private int raiting;
	private String country;
	private String city;
	private String description;

	public Hotel(long id, String name, int raiting, String country, String city, String description) {
		super();
		this.id = id;
		this.name = name;
		this.raiting = raiting;
		this.country = country;
		this.city = city;
		this.description = description;
	}

	public Hotel() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRaiting() {
		return raiting;
	}

	public void setRaiting(int raiting) {
		this.raiting = raiting;
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
		return "Hotel [id=" + id + ", name=" + name + ", raiting=" + raiting + ", country=" + country + ", city=" + city
				+ ", description=" + description + "]";
	}

}
