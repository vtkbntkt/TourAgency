package ua.gudkov.fp.extractor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import ua.gudkov.fp.bean.SimpleFilterFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.extractor.api.Extractor;

/**
 * Simple filter form bean extractor.
 * 
 * @author A.Gudkov
 *
 */
public class SimpleFilterFormExtractor implements Extractor<SimpleFilterFormBean> {

	@Override
	public SimpleFilterFormBean extract(HttpServletRequest request) throws IOException, ServletException {
		SimpleFilterFormBean sfb = new SimpleFilterFormBean();
		sfb.setCurrentPage(request.getParameter(Const.SimpleFilterForm.CURRENT_PAGE));
		sfb.setItemsPerPage(request.getParameter(Const.SimpleFilterForm.ITEMS_PER_PAGE));
		return sfb;
	}

}
