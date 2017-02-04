package ua.gudkov.fp.extractor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import ua.gudkov.fp.bean.DiscountEditFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.extractor.api.Extractor;

/**
 * Discount edit form bean extractor.
 * 
 * @author A.Gudkov
 *
 */
public class DiscountEditFormExtractor implements Extractor<DiscountEditFormBean> {

	@Override
	public DiscountEditFormBean extract(HttpServletRequest request) throws IOException, ServletException {
		DiscountEditFormBean defb = new DiscountEditFormBean();
		defb.setPercentPerTour(request.getParameter(Const.DiscountEditForm.PER_TOUR_DISCOUNT));
		defb.setMaxPercenr(request.getParameter(Const.DiscountEditForm.MAX_DISCOUNT));

		return defb;
	}

}
