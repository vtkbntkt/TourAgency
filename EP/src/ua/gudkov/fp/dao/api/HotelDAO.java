package ua.gudkov.fp.dao.api;

import java.util.List;

import ua.gudkov.fp.entity.Hotel;

/**
 * Hotel DAO interface.
 * 
 * @author A.Gudkov
 *
 */
public interface HotelDAO {

	/**
	 * Adds hotel into data base.
	 * 
	 * @param hotel
	 *            the hotel
	 * @return true if the hotel is added into database, false otherwise.
	 */
	boolean insertHotel(Hotel hotel);

	/**
	 * Returns list of all hotels contained on the base.
	 * 
	 * @return list of hotels
	 */
	List<Hotel> findAll();

	/**
	 * Returns list of unique ratings of all available hotels
	 * 
	 * @return hotel rating value list
	 */
	List<String> ratingValues();

	/**
	 * Returns list of unique countries of all available hotels
	 * 
	 * @return country value list
	 */
	List<String> countryValues();

}
