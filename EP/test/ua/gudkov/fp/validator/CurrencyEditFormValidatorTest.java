package ua.gudkov.fp.validator;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ua.gudkov.fp.bean.CurrencyEditFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.entity.ErrorHolder;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class CurrencyEditFormValidatorTest {

	private CurrencyEditFormBean currencyForm;
	private ErrorHolder expectedResult;

	public CurrencyEditFormValidatorTest(CurrencyEditFormBean currencyForm, ErrorHolder expectedResult) {
		super();
		this.expectedResult = expectedResult;
		this.currencyForm = currencyForm;
	}

	@Parameters
	public static Collection<Object[]> setParameters() {
		/*
		 * All errors
		 */
		ErrorHolder allErrors = new ErrorHolder();
		allErrors.add(Const.CurrencyEditForm.BANK_CODE, Const.ErrorConstants.EDITCURR_CODE_KEY);
		allErrors.add(Const.CurrencyEditForm.RATE, Const.ErrorConstants.EDITCURR_RATE_KEY);

		/*
		 * Without errors
		 */
		ErrorHolder emptyErrors = new ErrorHolder();

		/*
		 * Rate errors, include negative or zero value and non double value
		 */
		ErrorHolder rateErrors = new ErrorHolder();
		rateErrors.add(Const.CurrencyEditForm.RATE, Const.ErrorConstants.EDITCURR_RATE_KEY);

		/*
		 * Currency bank code errors
		 */
		ErrorHolder bcErrors = new ErrorHolder();
		bcErrors.add(Const.CurrencyEditForm.BANK_CODE, Const.ErrorConstants.EDITCURR_CODE_KEY);

		return Arrays.asList(new Object[][] { { new CurrencyEditFormBean("", ""), allErrors },
				{ new CurrencyEditFormBean("usd", "2.55"), emptyErrors },
				{ new CurrencyEditFormBean("", "2.55"), bcErrors },
				{ new CurrencyEditFormBean("uah", "r"), rateErrors },
				{ new CurrencyEditFormBean("eur", "-1"), rateErrors },
				
		
		});
	}

	@Test
	public void testValidate() {
		CurrencyEditFormValidator validator = new CurrencyEditFormValidator();
		ErrorHolder errors = validator.validate(currencyForm);
		assertEquals(expectedResult.getErrors(), errors.getErrors());
	}

}
