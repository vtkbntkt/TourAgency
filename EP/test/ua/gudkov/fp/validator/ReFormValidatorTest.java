package ua.gudkov.fp.validator;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ua.gudkov.fp.bean.ReFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.entity.ErrorHolder;

@RunWith(Parameterized.class)
public class ReFormValidatorTest {
	private ReFormBean formBean;
	private ErrorHolder expectedResult;

	public ReFormValidatorTest(ReFormBean formBean, ErrorHolder expectedResult) {
		super();
		this.formBean = formBean;
		this.expectedResult = expectedResult;
	}

	@Parameters
	public static Collection<Object[]> setParameters() {
		ErrorHolder allErrors = new ErrorHolder();
		allErrors.add(Const.ReplayingForm.EMAIL, Const.ErrorConstants.RE_EMAIL_KEY);
		allErrors.add(Const.ReplayingForm.MESSAGE, Const.ErrorConstants.RE_MSG_KEY);
		allErrors.add(Const.ReplayingForm.SUBJECT, Const.ErrorConstants.RE_SUBJECT_KEY);
		ErrorHolder emptyErrors = new ErrorHolder();

		ErrorHolder emailErrors = new ErrorHolder();
		emailErrors.add(Const.ReplayingForm.EMAIL, Const.ErrorConstants.RE_EMAIL_KEY);

		ErrorHolder msgErrors = new ErrorHolder();
		msgErrors.add(Const.ReplayingForm.MESSAGE, Const.ErrorConstants.RE_MSG_KEY);

		ErrorHolder subjectErrors = new ErrorHolder();
		subjectErrors.add(Const.ReplayingForm.SUBJECT, Const.ErrorConstants.RE_SUBJECT_KEY);

		return Arrays.asList(new Object[][] { { new ReFormBean("", "", ""), allErrors },
				{ new ReFormBean("msg", "email@mail.com", "subject"), emptyErrors },
				{ new ReFormBean("msg", "email_mail.com", "subject"), emailErrors },
				{ new ReFormBean("", "email@mail.com", "subject"), msgErrors },
				{ new ReFormBean(StringUtils.leftPad("*", 501, "*"), "email@mail.com", "subject"), msgErrors },
				{ new ReFormBean("msg", "email@mail.com", ""), subjectErrors },

		});
	}

	@Test
	public void testValidate() {
		ReFormValidator validator = new ReFormValidator();
		ErrorHolder errors = validator.validate(formBean);
		assertEquals(expectedResult.getErrors(), errors.getErrors());
	}

}
