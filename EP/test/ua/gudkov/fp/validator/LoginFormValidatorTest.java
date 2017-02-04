package ua.gudkov.fp.validator;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ua.gudkov.fp.bean.LoginFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.entity.ErrorHolder;

@RunWith(Parameterized.class)
public class LoginFormValidatorTest {
	private LoginFormBean formBean;
	private ErrorHolder expectedResult;

	public LoginFormValidatorTest(LoginFormBean formBean, ErrorHolder expectedResult) {
		super();
		this.formBean = formBean;
		this.expectedResult = expectedResult;
	}

	@Parameters
	public static Collection<Object[]> setParameters() {
		ErrorHolder allErrors = new ErrorHolder();
		allErrors.add(Const.LoginForm.USERNAME, Const.ErrorConstants.LOGIN_NAME_KEY);
		allErrors.add(Const.LoginForm.PASSWORD, Const.ErrorConstants.LOGIN_PWD_KEY);

		ErrorHolder emptyErrors = new ErrorHolder();

		ErrorHolder nameErrors = new ErrorHolder();
		nameErrors.add(Const.LoginForm.USERNAME, Const.ErrorConstants.LOGIN_NAME_KEY);
		ErrorHolder pwdErrors = new ErrorHolder();
		pwdErrors.add(Const.LoginForm.PASSWORD, Const.ErrorConstants.LOGIN_PWD_KEY);

		return Arrays.asList(new Object[][] { { new LoginFormBean("", "", null), allErrors },
				{ new LoginFormBean("name", "pwd", null), emptyErrors },
				{ new LoginFormBean("", "pwd", null), nameErrors },
				{ new LoginFormBean("name", "", null), pwdErrors } });
	}

	@Test
	public void testValidate() {
		LoginFormValidator validator = new LoginFormValidator();
		ErrorHolder errors = validator.validate(formBean);
		assertEquals(expectedResult.getErrors(), errors.getErrors());
	}

}
