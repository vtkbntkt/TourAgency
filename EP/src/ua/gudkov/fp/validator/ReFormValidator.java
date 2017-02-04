package ua.gudkov.fp.validator;

import org.apache.commons.lang3.StringUtils;

import ua.gudkov.fp.bean.ReFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.entity.ErrorHolder;
import ua.gudkov.fp.validator.api.Validator;

/**
 * Message reply form validator.
 * 
 * @author A.Gudkov
 *
 */
public class ReFormValidator implements Validator<ReFormBean> {
	public static final long MAX_MSG_LENGTH = 500;

	@Override
	public ErrorHolder validate(ReFormBean e) {
		ErrorHolder errors = new ErrorHolder();
		String email = e.getEmail();
		String msg = e.getMessage();
		String subject = e.getSubject();

		if (!(isValidByPattern(email, Const.RegexConstants.EMAIL_REG_EXP))) {
			errors.add(Const.ReplayingForm.EMAIL, Const.ErrorConstants.RE_EMAIL_KEY);
		}

		if (StringUtils.isEmpty(msg) || msg.length() > MAX_MSG_LENGTH) {
			errors.add(Const.ReplayingForm.MESSAGE, Const.ErrorConstants.RE_MSG_KEY);
		}

		if (StringUtils.isEmpty(subject)) {
			errors.add(Const.ReplayingForm.SUBJECT, Const.ErrorConstants.RE_SUBJECT_KEY);
		}

		return errors;
	}

}
