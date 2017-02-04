package ua.gudkov.fp.converter;

import ua.gudkov.fp.bean.HotelFormBean;
import ua.gudkov.fp.converter.api.Converter;
import ua.gudkov.fp.entity.Hotel;

/**
 * Hotel adding form bean to hotel entity converter.
 * 
 * @author A.Gudkov
 *
 */
public class HotelFormBeanToHotel implements Converter<HotelFormBean, Hotel> {

	@Override
	public Hotel convert(HotelFormBean hfb) {
		Hotel hotel = new Hotel();
		hotel.setCity(hfb.getCity());
		hotel.setCountry(hfb.getCountry());
		hotel.setDescription(hfb.getDescription());
		hotel.setName(hfb.getName());
		hotel.setRaiting(Integer.valueOf(hfb.getRating()));
		return hotel;
	}

}
