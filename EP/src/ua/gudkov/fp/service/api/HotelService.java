package ua.gudkov.fp.service.api;

import java.util.List;

import ua.gudkov.fp.entity.Hotel;

/**
 * Hotel service interface.
 * 
 * @author A.Gudkov
 *
 */
public interface HotelService {

	/**
	 * Adds a hotel into the data base.
	 * 
	 * @param hotel
	 *            the hotel
	 * @return true if hotel is added, false otherwise
	 */
	boolean addHotel(Hotel hotel);

	/**
	 * Returns list of all hotels contained on the base.
	 * 
	 * @return list of hotels
	 */
	List<Hotel> findAll();

	/**
	 * Returns list of countries where available hotels stands. The list
	 * contains unique countries.
	 * 
	 * @return country list
	 */
	List<String> countryValues();

	/**
	 * Returns list of unique hotel rating values. The list contains ratings
	 * only existing hotels in the base.
	 * 
	 * @return hotel rating list
	 */
	List<String> ratingValues();
}
