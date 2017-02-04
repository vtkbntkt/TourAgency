package ua.gudkov.fp.validator;


import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;

import ua.gudkov.fp.bean.ContactFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.entity.ErrorHolder;
import ua.gudkov.fp.validator.api.Validator;

/**
 * Contact form validator.
 * 
 * @author A.Gudkov
 *
 */
public class ContactFormValidator implements Validator<ContactFormBean> {
	public static final long MAX_FILE_SIZE = 1024 * 1024 * 5;

	@Override
	public ErrorHolder validate(ContactFormBean e) {
		ErrorHolder errors = new ErrorHolder();
		String name = e.getName();
		String email = e.getEmail();
		String msg = e.getMessage();
		Part filePart = e.getFilePart();

		if (!isValidByPattern(name, Const.RegexConstants.NAME_REG_EXP)) {
			errors.add(Const.ContactForm.NAME, Const.ErrorConstants.CONTACTUS_NAME_KEY);
		}
		if (!(isValidByPattern(email, Const.RegexConstants.EMAIL_REG_EXP))) {
			errors.add(Const.ContactForm.EMAIL, Const.ErrorConstants.CONTACTUS_EMAIL_KEY);
		}

		if (StringUtils.isEmpty(msg)) {
			errors.add(Const.ContactForm.MESSAGE, Const.ErrorConstants.CONTACTUS_MSG_KEY);
		}

		String fileName = filePart.getSubmittedFileName();
		if (!StringUtils.isEmpty(fileName)) {
			if (!isValidByPattern(filePart.getContentType(), Const.RegexConstants.CONTENT_TYPE_REG_EXP)) {
				errors.add(Const.ContactForm.FILE, Const.ErrorConstants.CONTACTUS_FILE_KEY);
			}
			if (MAX_FILE_SIZE < filePart.getSize()) {
				errors.add(Const.ContactForm.FILE, Const.ErrorConstants.CONTACTUS_FILE_KEY);
			}
		}

		return errors;
	}


}
