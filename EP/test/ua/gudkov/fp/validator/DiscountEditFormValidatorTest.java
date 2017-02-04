package ua.gudkov.fp.validator;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ua.gudkov.fp.bean.DiscountEditFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.entity.ErrorHolder;

@RunWith(Parameterized.class)
public class DiscountEditFormValidatorTest {
	private DiscountEditFormBean discountForm;
	private ErrorHolder expectedResult;

	public DiscountEditFormValidatorTest(DiscountEditFormBean discountForm, ErrorHolder expectedResult) {
		super();
		this.discountForm = discountForm;
		this.expectedResult = expectedResult;
	}

	@Parameters
	public static Collection<Object[]> setParameters() {
		/*
		 * Without errors
		 */
		ErrorHolder emptyErrors = new ErrorHolder();

		/*
		 * Discount format errors, should be double
		 */
		ErrorHolder formatErrors = new ErrorHolder();
		formatErrors.add(Const.DiscountEditForm.PER_TOUR_DISCOUNT, Const.ErrorConstants.EDITDISC_FORMAT_KEY);

		/*
		 * Discount value errors (value should be at least 0, max discount
		 * should be greater than discount per tour)
		 */
		ErrorHolder valueErrors = new ErrorHolder();
		valueErrors.add(Const.DiscountEditForm.MAX_DISCOUNT, Const.ErrorConstants.EDITDISC_VALUE_KEY);

		return Arrays.asList(new Object[][] { { new DiscountEditFormBean("1.22", "2.55"), emptyErrors },
				{ new DiscountEditFormBean("w", "w"), formatErrors },
				{ new DiscountEditFormBean("-1", "2.55"), valueErrors },
				{ new DiscountEditFormBean("2.55", "-1"), valueErrors },
				{ new DiscountEditFormBean("18", "1"), valueErrors },

		});
	}

	@Test
	public void testValidate() {
		DiscountEditFormValidator validator = new DiscountEditFormValidator();
		ErrorHolder errors = validator.validate(discountForm);
		assertEquals(expectedResult.getErrors(), errors.getErrors());
	}

}
