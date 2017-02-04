package ua.gudkov.fp.converter;

import ua.gudkov.fp.bean.TourEditFormBean;
import ua.gudkov.fp.converter.api.Converter;
import ua.gudkov.fp.entity.Tour;

/**
 * Tour entity to tour edit form bean
 * 
 * @author A.Gudkov
 *
 */
public class TourToTourEditFormBean implements Converter<Tour, TourEditFormBean> {

	@Override
	public TourEditFormBean convert(Tour tour) {
		TourEditFormBean tefb = new TourEditFormBean();
		tefb.setCost(String.valueOf((int) tour.getPrice()));
		tefb.setDate(String.valueOf(tour.getDepartureDate()));
		tefb.setHotel(String.valueOf(tour.getHotelId()));
		tefb.setNightNum(String.valueOf(tour.getNights()));
		tefb.setPersonNum(String.valueOf(tour.getPersonCount()));
		tefb.setType(String.valueOf(tour.getTypeId()));
		tefb.setId(String.valueOf(tour.getId()));
		return tefb;
	}

}
