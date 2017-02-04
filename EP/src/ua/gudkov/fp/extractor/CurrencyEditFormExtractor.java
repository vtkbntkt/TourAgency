package ua.gudkov.fp.extractor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import ua.gudkov.fp.bean.CurrencyEditFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.extractor.api.Extractor;

/**
 * Currency edit form bean extractor.
 * 
 * @author A.Gudkov
 *
 */
public class CurrencyEditFormExtractor implements Extractor<CurrencyEditFormBean> {

	@Override
	public CurrencyEditFormBean extract(HttpServletRequest request) throws IOException, ServletException {
		CurrencyEditFormBean cefe = new CurrencyEditFormBean();
		cefe.setBc(request.getParameter(Const.CurrencyEditForm.BANK_CODE));
		cefe.setRate(request.getParameter(Const.CurrencyEditForm.RATE));
		return cefe;
	}

}
