package ua.gudkov.fp.validator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ContactFormValidatorTest.class, CurrencyEditFormValidatorTest.class,
		DiscountEditFormValidatorTest.class, HotelFormValidatorTest.class, LoginFormValidatorTest.class,
		ReFormValidatorTest.class, RegistrationFormValidatorTest.class, TourEditFormValidatorTest.class,
		TourFormValidatorTest.class })
public class AllTests {

}
