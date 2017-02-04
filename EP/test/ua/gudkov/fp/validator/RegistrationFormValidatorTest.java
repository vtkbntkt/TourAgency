package ua.gudkov.fp.validator;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ua.gudkov.fp.bean.RegisterFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.entity.ErrorHolder;

@RunWith(Parameterized.class)
public class RegistrationFormValidatorTest {
	private RegisterFormBean formBean;
	private ErrorHolder expectedResult;

	public RegistrationFormValidatorTest(RegisterFormBean formBean, ErrorHolder expectedResult) {
		super();
		this.formBean = formBean;
		this.expectedResult = expectedResult;
	}

	@Parameters
	public static Collection<Object[]> setParameters() {
		ErrorHolder allErrors = new ErrorHolder();
		allErrors.add(Const.SignUpForm.FIRST_NAME, Const.ErrorConstants.REGISTER_FIRST_NAME_KEY);
		allErrors.add(Const.SignUpForm.LAST_NAME, Const.ErrorConstants.REGISTER_LAST_NAME_KEY);
		allErrors.add(Const.SignUpForm.EMAIL, Const.ErrorConstants.REGISTER_EMAIL_KEY);
		allErrors.add(Const.SignUpForm.PWD, Const.ErrorConstants.REGISTER_PWD_KEY);
		allErrors.add(Const.SignUpForm.REPEATED_PWD, Const.ErrorConstants.REGISTER_RE_PWD_KEY);

		ErrorHolder emptyErrors = new ErrorHolder();

		ErrorHolder firstNameErrors = new ErrorHolder();
		firstNameErrors.add(Const.SignUpForm.FIRST_NAME, Const.ErrorConstants.REGISTER_FIRST_NAME_KEY);

		ErrorHolder lastNameErrors = new ErrorHolder();
		lastNameErrors.add(Const.SignUpForm.LAST_NAME, Const.ErrorConstants.REGISTER_LAST_NAME_KEY);

		ErrorHolder emailErrors = new ErrorHolder();
		emailErrors.add(Const.SignUpForm.EMAIL, Const.ErrorConstants.REGISTER_EMAIL_KEY);

		ErrorHolder pwdErrors = new ErrorHolder();
		pwdErrors.add(Const.SignUpForm.PWD, Const.ErrorConstants.REGISTER_PWD_KEY);

		ErrorHolder rePwdErrors = new ErrorHolder();
		rePwdErrors.add(Const.SignUpForm.REPEATED_PWD, Const.ErrorConstants.REGISTER_RE_PWD_KEY);

		return Arrays.asList(new Object[][] { { new RegisterFormBean("name", "name1", "email", "123", "1"), allErrors },
				{ new RegisterFormBean("Alex", "Kog", "email@mail.com", "123456Kb", "123456Kb"), emptyErrors },
				{ new RegisterFormBean("alex", "Kog", "email@mail.com", "123456Kb", "123456Kb"), firstNameErrors },
				{ new RegisterFormBean("Alex", "kog", "email@mail.com", "123456Kb", "123456Kb"), lastNameErrors },
				{ new RegisterFormBean("Alex", "Kog", "email_mail.com", "123456Kb", "123456Kb"), emailErrors },
				{ new RegisterFormBean("Alex", "Kog", "email@mail.com", "123456kb", "123456kb"), pwdErrors },
				{ new RegisterFormBean("Alex", "Kog", "email@mail.com", "123456Kb", "143456Kb"), rePwdErrors }

		});
	}

	@Test
	public void testValidate() {
		RegistrationFormValidator validator = new RegistrationFormValidator();
		ErrorHolder errors = validator.validate(formBean);
		assertEquals(expectedResult.getErrors(), errors.getErrors());
	}

}
