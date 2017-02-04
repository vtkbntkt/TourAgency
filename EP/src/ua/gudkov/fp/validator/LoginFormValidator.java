package ua.gudkov.fp.validator;

import org.apache.commons.lang3.StringUtils;

import ua.gudkov.fp.bean.LoginFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.entity.ErrorHolder;
import ua.gudkov.fp.validator.api.Validator;

/**
 * Login form validator.
 * 
 * @author A.Gudkov
 *
 */
public class LoginFormValidator implements Validator<LoginFormBean> {

	@Override
	public ErrorHolder validate(LoginFormBean e) {
		ErrorHolder errorHolder = new ErrorHolder();
		if (StringUtils.isEmpty(e.getUsername())) {
			errorHolder.add(Const.LoginForm.USERNAME, Const.ErrorConstants.LOGIN_NAME_KEY);
		}

		if (StringUtils.isEmpty(e.getPassword())) {
			errorHolder.add(Const.LoginForm.PASSWORD, Const.ErrorConstants.LOGIN_PWD_KEY);
		}

		return errorHolder;
	}

}
