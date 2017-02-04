package ua.gudkov.fp.validator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ua.gudkov.fp.bean.HotelFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.entity.ErrorHolder;

@RunWith(Parameterized.class)
public class HotelFormValidatorTest {
	private ErrorHolder expectedResult;
	private HotelFormBean hotelForm;

	public HotelFormValidatorTest(HotelFormBean hotelForm, ErrorHolder expectedResult) {
		super();
		this.expectedResult = expectedResult;
		this.hotelForm = hotelForm;
	}

	@Parameters
	public static Collection<Object[]> setParameters() {
		ErrorHolder allErrors = new ErrorHolder();
		allErrors.add(Const.HotelForm.NAME, Const.ErrorConstants.ADDHOTEL_NAME_KEY);
		allErrors.add(Const.HotelForm.SITY, Const.ErrorConstants.ADDHOTEL_CITY_KEY);
		allErrors.add(Const.HotelForm.COUNTRY, Const.ErrorConstants.ADDHOTEL_COUNTRY_KEY);
		allErrors.add(Const.HotelForm.DESCRIPTION, Const.ErrorConstants.ADDHOTEL_DESC_KEY);
		allErrors.add(Const.HotelForm.RAITING, Const.ErrorConstants.ADDHOTEL_RAITING_KEY);
		allErrors.add(Const.HotelForm.FILE, Const.ErrorConstants.ADDHOTEL_FILE_KEY);

		ErrorHolder emptyErrors = new ErrorHolder();

		ErrorHolder nameErrors = new ErrorHolder();
		nameErrors.add(Const.HotelForm.NAME, Const.ErrorConstants.ADDHOTEL_NAME_KEY);
		ErrorHolder cityErrors = new ErrorHolder();
		cityErrors.add(Const.HotelForm.SITY, Const.ErrorConstants.ADDHOTEL_CITY_KEY);
		ErrorHolder countryErrors = new ErrorHolder();
		countryErrors.add(Const.HotelForm.COUNTRY, Const.ErrorConstants.ADDHOTEL_COUNTRY_KEY);
		ErrorHolder descErrors = new ErrorHolder();
		descErrors.add(Const.HotelForm.DESCRIPTION, Const.ErrorConstants.ADDHOTEL_DESC_KEY);
		ErrorHolder ratingErrors = new ErrorHolder();
		ratingErrors.add(Const.HotelForm.RAITING, Const.ErrorConstants.ADDHOTEL_RAITING_KEY);
		ErrorHolder fileErrors = new ErrorHolder();
		fileErrors.add(Const.HotelForm.FILE, Const.ErrorConstants.ADDHOTEL_FILE_KEY);

		Part validPart = mock(Part.class);
		when(validPart.getSubmittedFileName()).thenReturn("file.png");
		when(validPart.getContentType()).thenReturn("image/png");
		when(validPart.getSize()).thenReturn(1024L * 1024L * 5);
		List<Part> validParts = new ArrayList<Part>();
		validParts.add(validPart);

		Part invalidPartByContentType = mock(Part.class);
		when(invalidPartByContentType.getSubmittedFileName()).thenReturn("file.png");
		when(invalidPartByContentType.getContentType()).thenReturn("image/xls");
		when(invalidPartByContentType.getSize()).thenReturn(1024L * 1024L * 5);
		List<Part> invalidPartsByContentType = new ArrayList<Part>();
		invalidPartsByContentType.add(invalidPartByContentType);

		Part invalidPartBySize = mock(Part.class);
		when(invalidPartBySize.getSubmittedFileName()).thenReturn("file.png");
		when(invalidPartBySize.getContentType()).thenReturn("image/png");
		when(invalidPartBySize.getSize()).thenReturn(1024L * 1024L * 6);
		List<Part> invalidPartsBySize = new ArrayList<Part>();
		invalidPartsBySize.add(invalidPartBySize);

		Part emptyPart = mock(Part.class);
		when(emptyPart.getSubmittedFileName()).thenReturn("");
		List<Part> emptyParts = new ArrayList<Part>();
		emptyParts.add(emptyPart);

		return Arrays.asList(new Object[][] {
				{ new HotelFormBean("", "", "", "", "", invalidPartsByContentType), allErrors },
				{ new HotelFormBean("Test", "5", "Ukr", "Kh", "Desc", validParts), emptyErrors },
				{ new HotelFormBean("Test", "5", "Ukr", "Kh", StringUtils.leftPad("*", 501, "*"), validParts),
						descErrors },
				{ new HotelFormBean("", "5", "Ukr", "Kh", "Desc", validParts), nameErrors },
				{ new HotelFormBean("Test", "5", "Ukr", "", "Desc", validParts), cityErrors },
				{ new HotelFormBean("Test", "5", "", "Kh", "Desc", validParts), countryErrors },
				{ new HotelFormBean("Test", "1", "Ukr", "Kh", "Desc", validParts), ratingErrors },
				{ new HotelFormBean("Test", "6", "Ukr", "Kh", "Desc", validParts), ratingErrors },
				{ new HotelFormBean("Test", "5", "Ukr", "Kh", "Desc", emptyParts), emptyErrors },
				{ new HotelFormBean("Test", "5", "Ukr", "Kh", "Desc", invalidPartsByContentType), fileErrors },
				{ new HotelFormBean("Test", "5", "Ukr", "Kh", "Desc", invalidPartsBySize), fileErrors }, });
	}

	@Test
	public void testValidate() {
		HotelFormValidator validator = new HotelFormValidator();
		ErrorHolder errors = validator.validate(hotelForm);
		assertEquals(expectedResult.getErrors(), errors.getErrors());
	}

}
