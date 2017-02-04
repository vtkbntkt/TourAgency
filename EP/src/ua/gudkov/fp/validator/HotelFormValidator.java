package ua.gudkov.fp.validator;

import java.util.List;

import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;

import ua.gudkov.fp.bean.HotelFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.entity.ErrorHolder;
import ua.gudkov.fp.validator.api.Validator;

/**
 * Hotel adding form validator.
 * 
 * @author A.Gudkov
 *
 */
public class HotelFormValidator implements Validator<HotelFormBean> {
	public static final long MAX_MSG_LENGTH = 500;
	public static final long MAX_FILE_SIZE = 1024 * 1024 * 5;

	@Override
	public ErrorHolder validate(HotelFormBean e) {
		ErrorHolder errors = new ErrorHolder();
		String name = e.getName();
		String city = e.getCity();
		String country = e.getCountry();
		String description = e.getDescription();
		String raiting = e.getRating();
		List<Part> parts = e.getParts();

		if (StringUtils.isEmpty(name)) {
			errors.add(Const.HotelForm.NAME, Const.ErrorConstants.ADDHOTEL_NAME_KEY);
		}
		if (StringUtils.isEmpty(city)) {
			errors.add(Const.HotelForm.SITY, Const.ErrorConstants.ADDHOTEL_CITY_KEY);
		}
		if (StringUtils.isEmpty(country)) {
			errors.add(Const.HotelForm.COUNTRY, Const.ErrorConstants.ADDHOTEL_COUNTRY_KEY);
		}
		if (StringUtils.isEmpty(description) || description.length() > MAX_MSG_LENGTH) {
			errors.add(Const.HotelForm.DESCRIPTION, Const.ErrorConstants.ADDHOTEL_DESC_KEY);
		}

		if (!StringUtils.isNumeric(raiting) || Integer.valueOf(raiting) < 2 || Integer.valueOf(raiting) > 5) {
			errors.add(Const.HotelForm.RAITING, Const.ErrorConstants.ADDHOTEL_RAITING_KEY);
		}
		for (Part part : parts) {
			String fileName = part.getSubmittedFileName();
			if (!StringUtils.isEmpty(fileName)) {
				if (!isValidByPattern(part.getContentType(), Const.RegexConstants.CONTENT_TYPE_REG_EXP)) {
					errors.add(Const.HotelForm.FILE, Const.ErrorConstants.ADDHOTEL_FILE_KEY);
				}
				if (MAX_FILE_SIZE < part.getSize()) {
					errors.add(Const.HotelForm.FILE, Const.ErrorConstants.ADDHOTEL_FILE_KEY);
				}
			}
		}

		return errors;
	}

}
