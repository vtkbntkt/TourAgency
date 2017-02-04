package ua.gudkov.fp.validator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;

import javax.servlet.http.Part;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ua.gudkov.fp.bean.TourFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.entity.ErrorHolder;
import ua.gudkov.fp.entity.Type;

@RunWith(Parameterized.class)
public class TourFormValidatorTest {
	private TourFormBean formBean;
	private ErrorHolder expectedResult;

	public TourFormValidatorTest(TourFormBean formBean, ErrorHolder expectedResult) {
		super();
		this.formBean = formBean;
		this.expectedResult = expectedResult;
	}

	@Parameters
	public static Collection<Object[]> setParameters() {
		ErrorHolder allErrors = new ErrorHolder();
		allErrors.add(Const.TourForm.FILE, Const.ErrorConstants.ADDTOUR_FILE_KEY);
		allErrors.add(Const.TourForm.DATE, Const.ErrorConstants.ADDTOUR_DATE_KEY);
		allErrors.add(Const.TourForm.NIGHTS, Const.ErrorConstants.ADDTOUR_NIGHTS_KEY);
		allErrors.add(Const.TourForm.PERSONS, Const.ErrorConstants.ADDTOUR_PERSONS_KEY);
		allErrors.add(Const.TourForm.HOTEL, Const.ErrorConstants.ADDTOUR_HOTEL_KEY);
		allErrors.add(Const.TourForm.COST, Const.ErrorConstants.ADDTOUR_COST_KEY);
		allErrors.add(Const.TourForm.TYPE, Const.ErrorConstants.ADDTOUR_TYPE_KEY);

		ErrorHolder emptyErrors = new ErrorHolder();

		ErrorHolder fileErrors = new ErrorHolder();
		fileErrors.add(Const.TourForm.FILE, Const.ErrorConstants.ADDTOUR_FILE_KEY);

		ErrorHolder dateErrors = new ErrorHolder();
		dateErrors.add(Const.TourForm.DATE, Const.ErrorConstants.ADDTOUR_DATE_KEY);

		ErrorHolder nightsErrors = new ErrorHolder();
		nightsErrors.add(Const.TourForm.NIGHTS, Const.ErrorConstants.ADDTOUR_NIGHTS_KEY);

		ErrorHolder personsErrors = new ErrorHolder();
		personsErrors.add(Const.TourForm.PERSONS, Const.ErrorConstants.ADDTOUR_PERSONS_KEY);

		ErrorHolder hotelErrors = new ErrorHolder();
		hotelErrors.add(Const.TourForm.HOTEL, Const.ErrorConstants.ADDTOUR_HOTEL_KEY);

		ErrorHolder costErrors = new ErrorHolder();
		costErrors.add(Const.TourForm.COST, Const.ErrorConstants.ADDTOUR_COST_KEY);

		ErrorHolder typeErrors = new ErrorHolder();
		typeErrors.add(Const.TourForm.TYPE, Const.ErrorConstants.ADDTOUR_TYPE_KEY);

		Part validPart = mock(Part.class);
		when(validPart.getSubmittedFileName()).thenReturn("file.png");
		when(validPart.getContentType()).thenReturn("image/png");
		when(validPart.getSize()).thenReturn(1024L * 1024L * 5);

		Part invalidPartByContentType = mock(Part.class);
		when(invalidPartByContentType.getSubmittedFileName()).thenReturn("file.png");
		when(invalidPartByContentType.getContentType()).thenReturn("image/xls");
		when(invalidPartByContentType.getSize()).thenReturn(1024L * 1024L * 5);

		Part invalidPartBySize = mock(Part.class);
		when(invalidPartBySize.getSubmittedFileName()).thenReturn("file.png");
		when(invalidPartBySize.getContentType()).thenReturn("image/png");
		when(invalidPartBySize.getSize()).thenReturn(1024L * 1024L * 6);

		Part emptyPart = mock(Part.class);
		when(emptyPart.getSubmittedFileName()).thenReturn("");

		Date currentDate = new Date(new java.util.Date().getTime());

		Calendar today = Calendar.getInstance();
		today.add(Calendar.DATE, +1);
		Date tomorrow = new Date(today.getTimeInMillis());

		return Arrays.asList(new Object[][] {
				{ new TourFormBean("type", "date", "nights", "cost", "persons", "hotel", invalidPartByContentType),
						allErrors },
				{ new TourFormBean("0", tomorrow.toString(), "1", "350", "1", "1", validPart), emptyErrors },
				{ new TourFormBean("0", tomorrow.toString(), "1", "350", "1", "1", emptyPart), emptyErrors },
				{ new TourFormBean("0", tomorrow.toString(), "1", "350", "1", "1", invalidPartBySize), fileErrors },
				{ new TourFormBean("0", tomorrow.toString(), "1", "350", "1", "1", invalidPartByContentType),
						fileErrors },
				{ new TourFormBean("0", currentDate.toString(), "1", "350", "1", "1", validPart), dateErrors },
				{ new TourFormBean("0", tomorrow.toString(), "0", "350", "1", "1", validPart), nightsErrors },
				{ new TourFormBean("0", tomorrow.toString(), "15", "350", "1", "1", validPart), nightsErrors },
				{ new TourFormBean("0", tomorrow.toString(), "1", "350", "0", "1", validPart), personsErrors },
				{ new TourFormBean("0", tomorrow.toString(), "1", "350", "6", "1", validPart), personsErrors },
				{ new TourFormBean("0", tomorrow.toString(), "1", "350", "1", "0", validPart), hotelErrors },
				{ new TourFormBean(String.valueOf(Type.values().length + 1), tomorrow.toString(), "1", "350", "1", "1",
						validPart), typeErrors }

		});
	}

	@Test
	public void testValidate() {
		TourFormValidator validator = new TourFormValidator();
		ErrorHolder errors = validator.validate(formBean);
		assertEquals(expectedResult.getErrors(), errors.getErrors());
	}

}
