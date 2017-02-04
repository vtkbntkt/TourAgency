package ua.gudkov.fp.validator;

import ua.gudkov.fp.bean.CurrencyEditFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.entity.BankCode;
import ua.gudkov.fp.entity.ErrorHolder;
import ua.gudkov.fp.validator.api.Validator;

/**
 * Currency edit form validator.
 * 
 * @author A.Gudkov
 *
 */
public class CurrencyEditFormValidator implements Validator<CurrencyEditFormBean> {

	@Override
	public ErrorHolder validate(CurrencyEditFormBean e) {
		ErrorHolder errorHolder = new ErrorHolder();
		if (BankCode.getValue(e.getBc()) == null) {
			errorHolder.add(Const.CurrencyEditForm.BANK_CODE, Const.ErrorConstants.EDITCURR_CODE_KEY);
		}

		try {
			double rate = Double.parseDouble(e.getRate());
			if (rate <= 0) {
				errorHolder.add(Const.CurrencyEditForm.RATE, Const.ErrorConstants.EDITCURR_RATE_KEY);
			}
		} catch (Exception ex) {
			errorHolder.add(Const.CurrencyEditForm.RATE, Const.ErrorConstants.EDITCURR_RATE_KEY);
		}
		return errorHolder;
	}

}
