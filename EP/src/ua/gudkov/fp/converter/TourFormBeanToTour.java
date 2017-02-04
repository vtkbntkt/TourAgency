package ua.gudkov.fp.converter;

import java.sql.Date;

import ua.gudkov.fp.bean.TourFormBean;
import ua.gudkov.fp.converter.api.Converter;
import ua.gudkov.fp.entity.Tour;

/**
 * Tour adding form bean to tour entity converter.
 * 
 * @author A.Gudkov
 *
 */
public class TourFormBeanToTour implements Converter<TourFormBean,Tour> {

	@Override
	public Tour convert(TourFormBean tourForm) {
		Tour tour = new Tour();
		tour.setDepartureDate(Date.valueOf(tourForm.getDate()));
		tour.setHotelId(Long.parseLong(tourForm.getHotel()));
		tour.setNights(Integer.parseInt(tourForm.getNightNum()));
		tour.setPersonCount(Integer.parseInt(tourForm.getPersonNum()));
		tour.setPrice(Double.parseDouble(tourForm.getCost()));
		tour.setTypeId(Integer.parseInt(tourForm.getType()));
		return tour;
	}

}
