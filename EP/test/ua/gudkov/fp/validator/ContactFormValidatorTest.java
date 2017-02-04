package ua.gudkov.fp.validator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import javax.servlet.http.Part;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ua.gudkov.fp.bean.ContactFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.entity.ErrorHolder;

@RunWith(Parameterized.class)
public class ContactFormValidatorTest {

	private ErrorHolder expectedResult;
	private ContactFormBean contactForm;

	public ContactFormValidatorTest(ContactFormBean contactForm, ErrorHolder expectedResult) {
		this.expectedResult = expectedResult;
		this.contactForm = contactForm;
	}

	@Parameters
	public static Collection<Object[]> setParameters() {
		/*
		 * All errors
		 */
		ErrorHolder allErrors = new ErrorHolder();
		allErrors.add(Const.ContactForm.NAME, Const.ErrorConstants.CONTACTUS_NAME_KEY);
		allErrors.add(Const.ContactForm.EMAIL, Const.ErrorConstants.CONTACTUS_EMAIL_KEY);
		allErrors.add(Const.ContactForm.MESSAGE, Const.ErrorConstants.CONTACTUS_MSG_KEY);
		allErrors.add(Const.ContactForm.FILE, Const.ErrorConstants.CONTACTUS_FILE_KEY);

		/*
		 * Without errors
		 */
		ErrorHolder emptyErrors = new ErrorHolder();

		/*
		 * Name errors
		 */
		ErrorHolder nameErrors = new ErrorHolder();
		nameErrors.add(Const.ContactForm.NAME, Const.ErrorConstants.CONTACTUS_NAME_KEY);

		/*
		 * File errors, includes size and type file errors
		 */
		ErrorHolder fileErrors = new ErrorHolder();
		fileErrors.add(Const.ContactForm.FILE, Const.ErrorConstants.CONTACTUS_FILE_KEY);

		/*
		 * Email errors
		 */
		ErrorHolder emailErrors = new ErrorHolder();
		emailErrors.add(Const.ContactForm.EMAIL, Const.ErrorConstants.CONTACTUS_EMAIL_KEY);

		/*
		 * Message errors
		 */
		ErrorHolder msgErrors = new ErrorHolder();
		msgErrors.add(Const.ContactForm.MESSAGE, Const.ErrorConstants.CONTACTUS_MSG_KEY);

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

		return Arrays
				.asList(new Object[][] {
						{ new ContactFormBean("", "", "", invalidPartByContentType, null), allErrors }, {
								new ContactFormBean("Aleksej", "gudkov@mail.com", "Something", validPart, null),
								emptyErrors },
						{ new ContactFormBean("Aleksej", "gudkov@mail.com", "Something", invalidPartByContentType,
								null), fileErrors },
						{ new ContactFormBean("Aleksej", "gudkov@mail.com", "Something", invalidPartBySize, null),
								fileErrors },
						{ new ContactFormBean("Aleksej", "gudkov@mail.com", "Something", emptyPart, null),
								emptyErrors },
						{ new ContactFormBean("aleksej", "gudkov@mail.com", "Something", validPart, null), nameErrors },
						{ new ContactFormBean("Aleksej", "gudkov_mail.com", "Something", validPart, null),
								emailErrors },
						{ new ContactFormBean("Aleksej", "gudkov@mail.com", "", validPart, null), msgErrors } });
	}

	@Test
	public void testValidate() {
		ContactFormValidator validator = new ContactFormValidator();
		ErrorHolder errors = validator.validate(contactForm);
		assertEquals(expectedResult.getErrors(), errors.getErrors());
	}

}
