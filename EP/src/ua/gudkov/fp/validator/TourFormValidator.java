package ua.gudkov.fp.validator;

import java.sql.Date;

import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;

import ua.gudkov.fp.bean.TourFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.entity.ErrorHolder;
import ua.gudkov.fp.entity.Type;
import ua.gudkov.fp.validator.api.Validator;

/**
 * Tour adding form validator.
 * 
 * @author A.Gudkov
 *
 */
public class TourFormValidator implements Validator<TourFormBean> {
	public static final long MAX_FILE_SIZE = 1024 * 1024 * 5;
	public static final int MIN_PERSON_NUMBER = 1;
	public static final int MAX_PERSON_NUMBER = 5;
	public static final int MIN_NIGHT_NUMBER = 1;
	public static final int MAX_NIGHT_NUMBER = 14;

	@Override
	public ErrorHolder validate(TourFormBean e) {
		ErrorHolder errors = new ErrorHolder();
		String cost = e.getCost();
		String date = e.getDate();
		String hotel = e.getHotel();
		String nights = e.getNightNum();
		String persons = e.getPersonNum();
		String type = e.getType();
		Part part = e.getPart();
		Date currentDate = new Date(new java.util.Date().getTime());

		String fileName = part.getSubmittedFileName();
		if (!StringUtils.isEmpty(fileName)) {
			if (!isValidByPattern(part.getContentType(), Const.RegexConstants.CONTENT_TYPE_REG_EXP)) {
				errors.add(Const.TourForm.FILE, Const.ErrorConstants.ADDTOUR_FILE_KEY);
			}
			if (MAX_FILE_SIZE < part.getSize()) {
				errors.add(Const.TourForm.FILE, Const.ErrorConstants.ADDTOUR_FILE_KEY);
			}
		}

		if (!isValidByPattern(date, Const.RegexConstants.DATE_REG_EXP)) {
			errors.add(Const.TourForm.DATE, Const.ErrorConstants.ADDTOUR_DATE_KEY);
		} else {
			if (Date.valueOf(date).before(currentDate)) {
				errors.add(Const.TourForm.DATE, Const.ErrorConstants.ADDTOUR_DATE_KEY);
			}
		}

		if (!StringUtils.isNumeric(nights) || Integer.parseInt(nights) < MIN_NIGHT_NUMBER
				|| Integer.parseInt(nights) > MAX_NIGHT_NUMBER) {
			errors.add(Const.TourForm.NIGHTS, Const.ErrorConstants.ADDTOUR_NIGHTS_KEY);

		}

		if (!StringUtils.isNumeric(persons) || Integer.parseInt(persons) < MIN_PERSON_NUMBER
				|| Integer.parseInt(persons) > MAX_PERSON_NUMBER) {
			errors.add(Const.TourForm.PERSONS, Const.ErrorConstants.ADDTOUR_PERSONS_KEY);

		}
		if (!StringUtils.isNumeric(hotel) || Integer.parseInt(hotel) < 1) {
			errors.add(Const.TourForm.HOTEL, Const.ErrorConstants.ADDTOUR_HOTEL_KEY);

		}

		if (!isValidByPattern(cost, Const.RegexConstants.COST_REG_EXP)) {
			errors.add(Const.TourForm.COST, Const.ErrorConstants.ADDTOUR_COST_KEY);
		}

		int typeLength = Type.values().length;
		if (!StringUtils.isNumeric(type) || Integer.parseInt(type) > typeLength) {
			errors.add(Const.TourForm.TYPE, Const.ErrorConstants.ADDTOUR_TYPE_KEY);
		}

		return errors;
	}

}
