package ua.gudkov.fp.converter;

import java.sql.Date;

import ua.gudkov.fp.bean.TourEditFormBean;
import ua.gudkov.fp.converter.api.Converter;
import ua.gudkov.fp.entity.Tour;

/**
 * Tour edit form bean to tour entity converter.
 * 
 * @author A.Gudkov
 *
 */
public class TourEditFormBeanToTour implements Converter<TourEditFormBean, Tour> {

	@Override
	public Tour convert(TourEditFormBean tourForm) {
		Tour tour = new Tour();
		tour.setDepartureDate(Date.valueOf(tourForm.getDate()));
		tour.setHotelId(Long.parseLong(tourForm.getHotel()));
		tour.setNights(Integer.parseInt(tourForm.getNightNum()));
		tour.setPersonCount(Integer.parseInt(tourForm.getPersonNum()));
		tour.setPrice(Double.parseDouble(tourForm.getCost()));
		tour.setTypeId(Integer.parseInt(tourForm.getType()));
		tour.setId(Long.parseLong(tourForm.getId()));
		return tour;
	}

}
