package ua.gudkov.fp.validator;

import ua.gudkov.fp.bean.RegisterFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.entity.ErrorHolder;
import ua.gudkov.fp.validator.api.Validator;

/**
 * Registration form validator.
 * 
 * @author A.Gudkov
 *
 */
public class RegistrationFormValidator implements Validator<RegisterFormBean> {

	@Override
	public ErrorHolder validate(RegisterFormBean e) {
		ErrorHolder errorHolder = new ErrorHolder();
		String firstName = e.getFirstName();
		String lastName = e.getLastName();
		String email = e.getEmail();
		String pwd = e.getPassword();
		String repwd = e.getRepeatPassword();

		if (!isValidByPattern(firstName, Const.RegexConstants.NAME_REG_EXP)) {
			errorHolder.add(Const.SignUpForm.FIRST_NAME, Const.ErrorConstants.REGISTER_FIRST_NAME_KEY);
		}
		if (!isValidByPattern(lastName, Const.RegexConstants.NAME_REG_EXP)) {
			errorHolder.add(Const.SignUpForm.LAST_NAME, Const.ErrorConstants.REGISTER_LAST_NAME_KEY);
		}
		if (!(isValidByPattern(email, Const.RegexConstants.EMAIL_REG_EXP))) {
			errorHolder.add(Const.SignUpForm.EMAIL, Const.ErrorConstants.REGISTER_EMAIL_KEY);
		}

		if (!isValidByPattern(pwd, Const.RegexConstants.PASSWORD_REG_EXP)) {
			errorHolder.add(Const.SignUpForm.PWD, Const.ErrorConstants.REGISTER_PWD_KEY);
		}
		if (!isEqual(pwd, repwd)) {
			errorHolder.add(Const.SignUpForm.REPEATED_PWD, Const.ErrorConstants.REGISTER_RE_PWD_KEY);
		}

		return errorHolder;
	}

}
