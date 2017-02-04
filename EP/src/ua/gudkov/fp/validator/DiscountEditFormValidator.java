package ua.gudkov.fp.validator;

import ua.gudkov.fp.bean.DiscountEditFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.entity.ErrorHolder;
import ua.gudkov.fp.validator.api.Validator;

/**
 * Discount edit form validator.
 * 
 * @author A.Gudkov
 *
 */
public class DiscountEditFormValidator implements Validator<DiscountEditFormBean> {

	@Override
	public ErrorHolder validate(DiscountEditFormBean e) {
		ErrorHolder errorHolder = new ErrorHolder();

		try {
			double perTourDisc = Double.parseDouble(e.getPercentPerTour());
			double maxDisc = Double.parseDouble(e.getMaxPercenr());
			if (perTourDisc < 0 || maxDisc < 0 || perTourDisc > maxDisc) {
				errorHolder.add(Const.DiscountEditForm.MAX_DISCOUNT, Const.ErrorConstants.EDITDISC_VALUE_KEY);
			}
		} catch (Exception ex) {
			errorHolder.add(Const.DiscountEditForm.PER_TOUR_DISCOUNT, Const.ErrorConstants.EDITDISC_FORMAT_KEY);
		}
		return errorHolder;
	}

}
